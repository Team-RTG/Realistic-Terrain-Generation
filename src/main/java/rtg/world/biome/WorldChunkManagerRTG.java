package rtg.world.biome;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.annotation.Nullable;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeCache;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.WorldTypeEvent;

import gnu.trove.map.hash.TLongObjectHashMap;

import rtg.config.rtg.ConfigRTG;
import rtg.util.*;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.biome.realistic.RealisticBiomePatcher;

public class WorldChunkManagerRTG extends BiomeProvider implements RTGBiomeProvider
{
    /** A GenLayer containing the indices into Biome.biomeList[] */
    private GenLayer genBiomes;
    private GenLayer biomeIndexLayer;
    private List biomesToSpawnIn;
    private OpenSimplexNoise simplex;
    private CellNoise cell;
    //private SimplexCellularNoise simplexCell;
    private VoronoiCellNoise river;
    private float[] borderNoise;
    private TLongObjectHashMap<RealisticBiomeBase> biomeDataMap = new TLongObjectHashMap<RealisticBiomeBase>();
    private BiomeCache biomeCache;
    private RealisticBiomePatcher biomePatcher;

    protected WorldChunkManagerRTG()
    {

        this.biomeCache = new BiomeCache(this);
        this.biomesToSpawnIn = new ArrayList();
        this.borderNoise = new float[256];
        this.biomePatcher = new RealisticBiomePatcher();
        this.riverSeparation /= ConfigRTG.riverFrequencyMultiplier;
        this.riverValleyLevel *= ConfigRTG.riverSizeMultiplier();
        this.largeBendSize *= ConfigRTG.riverBendinessMultiplier;
        this.smallBendSize *= ConfigRTG.riverBendinessMultiplier;
    }

    public WorldChunkManagerRTG(World par1World,WorldType worldType)
    {

        this();
        long seed = par1World.getSeed();
        if (par1World.provider.getDimension() != 0) throw new RuntimeException();

        simplex = new OpenSimplexNoise(seed);
        cell = new SimplexCellularNoise(seed);
        //simplexCell = new SimplexCellularNoise(seed);
        river = new VoronoiCellNoise(seed);
        GenLayer[] agenlayer = GenLayer.initializeAllBiomeGenerators(seed, worldType, "");
        agenlayer = getModdedBiomeGenerators(worldType, seed, agenlayer);
        this.genBiomes = agenlayer[0]; //maybe this will be needed
        this.biomeIndexLayer = agenlayer[1];
        testCellBorder();
    }

    /**
     * @see RTGBiomeProvider
     */
    @Override
    public int[] getBiomesGens(int par1, int par2, int par3, int par4)
    {

        int[] d = new int[par3 * par4];

        for (int i = 0; i < par3; i++)
        {
            for (int j = 0; j < par4; j++)
            {
                d[j * par3  + i] = BiomeUtils.getId(getBiomeGenAt(par1 + i, par2 + j));
            }
        }
        return d;
    }

    public boolean diff(float sample1, float sample2, float base)
    {

        if ((sample1 < base && sample2 > base) || (sample1 > base && sample2 < base))
        {
            return true;
        }
        return false;
    }

    public float[] getRainfall(float[] par1ArrayOfFloat, int par2, int par3, int par4, int par5)
    {
        IntCache.resetIntCache();

        if (par1ArrayOfFloat == null || par1ArrayOfFloat.length < par4 * par5)
        {
            par1ArrayOfFloat = new float[par4 * par5];
        }

        int[] aint = this.biomeIndexLayer.getInts(par2, par3, par4, par5);

        for (int i1 = 0; i1 < par4 * par5; ++i1)
        {
            float f = 0;
            int biome = aint[i1];

            try {
                if (biome>255) throw new RuntimeException(biomeIndexLayer.toString());
                f = (float) RealisticBiomeBase.getBiome(biome).baseBiome.getRainfall() / 65536.0F;
            } catch (Exception e) {
                if (biome>255) throw new RuntimeException(biomeIndexLayer.toString());
                if (RealisticBiomeBase.getBiome(biome)== null) {
                    f = (float) biomePatcher.getPatchedRealisticBiome("Problem with biome "+biome+" from "+e.getMessage()).baseBiome.getRainfall() / 65536.0F;
                }
            }

            if (f > 1.0F) { f = 1.0F;}

            par1ArrayOfFloat[i1] = f;
        }

        return par1ArrayOfFloat;

    }

    /**
     * @see RTGBiomeProvider
     */
    @Override
    public Biome getBiomeGenAt(int par1, int par2) {
        Biome result;

        result = this.biomeCache.getBiomeCacheBlock(par1, par2).getBiome(par1, par2);

        if (result == null) {
            result = biomePatcher.getPatchedBaseBiome("Biome cache contains NULL biome at " + par1 + "," + par2);
        }

        return result;
    }

    /**
     * @see RTGBiomeProvider
     */
    @Override
    public RealisticBiomeBase getBiomeDataAt(int par1, int par2)
    {
        /*long coords = ChunkCoordIntPair.chunkXZ2Int(par1, par2);
        if (biomeDataMap.containsKey(coords)) {
            return biomeDataMap.get(coords);
        }*/
        RealisticBiomeBase output;

        output = RealisticBiomeBase.getBiome(BiomeUtils.getId(this.getBiomeGenAt(par1, par2)));
        if (output == null) output = biomePatcher.getPatchedRealisticBiome("No biome " + par1 + " " + par2);

        /*if (biomeDataMap.size() > 4096) {
            biomeDataMap.clear();
        }

        biomeDataMap.put(coords, output);*/

        return output;
    }

    public float getNoiseAt(int x, int y)
    {

        float river = getRiverStrength(x, y) + 1f;
        if (river < 0.5f)
        {
            return 59f;
        }

        return getBiomeDataAt(x, y).rNoise(simplex, cell, x, y, 1f, river);
    }

    private static int [] incidences = new int[100];
    private static int references = 0;
    private static double cellBorder(double[] results, double width, double depth) {
        double c = (results[1] - results[0])/results[1];
        if (c<0) throw new RuntimeException();
        /*int slot = (int)Math.floor(c*100.0);
        incidences[slot] += 1;
        references ++;
        if (references>40000) {
            String result = "";
            for (int i = 0; i< 100; i ++) {
                result += " " + incidences[i];
            }
            throw new RuntimeException(result);
        }*/
        if (c < width) {
            return ((c / width) - 1f) * depth;
        } else {

            return 0;
        }
    }

    private static void testCellBorder() {
        double [] result = new double[2];
        result[0] = 0.5;
        result [1] = 1;
        if (cellBorder(result,0.5,1)<0) throw new RuntimeException();
    }

    private double riverValleyLevel = 60.0 / 450.0;//60.0/450.0;
    private float riverSeparation = 1875;
    private float largeBendSize = 140;
    private float smallBendSize = 30;

    /**
     * @see RTGBiomeProvider
     */
    @Override
    public float getRiverStrength(int x, int y)
    {
        //New river curve function. No longer creates worldwide curve correlations along cardinal axes.
        SimplexOctave.Disk jitter = new SimplexOctave.Disk();
        simplex.riverJitter().evaluateNoise((float)x / 240.0, (float)y / 240.0, jitter);
        double pX = x + jitter.deltax() * largeBendSize;
        double pY = y + jitter.deltay() * largeBendSize;



        simplex.octave(2).evaluateNoise((float)x / 80.0, (float)y / 80.0, jitter);
        pX += jitter.deltax() * smallBendSize;
        pY += jitter.deltay() * smallBendSize;

        double xRiver = pX / riverSeparation;
        double yRiver = pY / riverSeparation;

        //New cellular noise.
        //TODO move the initialization of the results in a way that's more efficient but still thread safe.
        //double[] results = cell.river().eval(xRiver,yRiver );
        //return (float) cellBorder(results, riverValleyLevel, 1.0);
        return river.octave(0).border2(xRiver, yRiver, riverValleyLevel, 1f);
    }

    /**
     * @see RTGBiomeProvider
     */
    @Override
    public boolean isBorderlessAt(int x, int y)
    {

        int bx, by;

        for (bx = -2; bx <= 2; bx++)
        {
            for (by = -2; by <= 2; by++)
            {
                borderNoise[BiomeUtils.getId(getBiomeDataAt(x + bx * 16, y + by * 16).baseBiome)] += 0.04f;
            }
        }

        by = 0;
        for (bx = 0; bx < 256; bx++)
        {
            if (borderNoise[bx] > 0.98f)
            {
                by = 1;
            }
            borderNoise[bx] = 0;
        }

        return by == 1 ? true : false;
    }

    /**
     * @see BiomeProvider
     */
    @Override
    public List<Biome> getBiomesToSpawnIn()
    {
        return this.biomesToSpawnIn;
    }

    /**
     * @see BiomeProvider
     */
    @Override
    public Biome getBiome(BlockPos pos)
    {
        return this.getBiome(pos, (Biome)null);
    }

    /**
     * @see BiomeProvider
     */
    @Override
    public Biome getBiome(BlockPos pos, Biome defaultBiome)
    {
        return this.biomeCache.getBiome(pos.getX(), pos.getZ(), defaultBiome);
    }

    /**
     * @see BiomeProvider
     */
    @Override
    public float getTemperatureAtHeight(float p_76939_1_, int p_76939_2_)
    {
        return p_76939_1_;
    }

    /**
     * @see BiomeProvider
     */
    @Override
    public Biome[] getBiomesForGeneration(Biome[] biomes, int x, int z, int width, int height)
    {
        IntCache.resetIntCache();

        if (biomes == null || biomes.length < width * height) {
            biomes = new Biome[width * height];
        }

        int[] aint = this.genBiomes.getInts(x, z, width, height);

        for (int i1 = 0; i1 < width * height; ++i1) {
            biomes[i1] = Biome.getBiomeForId(aint[i1]);

            if (biomes[i1] == null) {
                biomes[i1] = biomePatcher.getPatchedBaseBiome("WCMRTG.getBiomesForGeneration() could not find biome " + aint[i1]);
            }
        }

        return biomes;
    }

    /**
     * @see BiomeProvider
     */
    @Override
    public Biome[] getBiomes(@Nullable Biome[] oldBiomeList, int x, int z, int width, int depth)
    {
        return this.getBiomes(oldBiomeList, x, z, width, depth, true);
    }

    /**
     * @see BiomeProvider
     */
    @Override
    public Biome[] getBiomes(@Nullable Biome[] listToReuse, int x, int z, int width, int length, boolean cacheFlag)
    {
        IntCache.resetIntCache();

        if (listToReuse == null || listToReuse.length < width * length) {
            listToReuse = new Biome[width * length];
        }

        if (cacheFlag && width == 16 && length == 16 && (x & 15) == 0 && (z & 15) == 0) {
            Biome[] abiomegenbase1 = this.biomeCache.getCachedBiomes(x, z);
            System.arraycopy(abiomegenbase1, 0, listToReuse, 0, width * length);
            return listToReuse;
        }
        else {
            int[] aint = this.biomeIndexLayer.getInts(x, z, width, length);

            for (int i1 = 0; i1 < width * length; ++i1) {
                listToReuse[i1] = RealisticBiomeBase.getBiome(aint[i1]).baseBiome;

                if (listToReuse[i1] == null) {
                    listToReuse[i1] = biomePatcher.getPatchedBaseBiome("WCMRTG.getBiomes() could not find biome " + aint[i1]);
                }
            }

            return listToReuse;
        }
    }

    /**
     * @see BiomeProvider
     */
    @Override
    public boolean areBiomesViable(int x, int z, int radius, List<Biome> allowed)
    {
        float centerNoise = getNoiseAt(x, z);
        if (centerNoise < 62)
        {
            return false;
        }

        float lowestNoise = centerNoise;
        float highestNoise = centerNoise;
        for (int i = -2; i <= 2; i++)
        {
            for (int j = -2; j <= 2; j++)
            {
                if (i != 0 && j != 0)
                {
                    float n = getNoiseAt(x + i * 16, z + j * 16);
                    if (n < lowestNoise) {
                        lowestNoise = n;
                    }
                    if (n > highestNoise) {
                        highestNoise = n;
                    }
                }
            }
        }

        if (highestNoise - lowestNoise < 22)
        {
            return true;
        }

        return false;
    }

    /**
     * @see BiomeProvider
     */
    @Nullable
    @Override
    public BlockPos findBiomePosition(int x, int z, int range, List<Biome> biomes, Random random)
    {
        IntCache.resetIntCache();
        int i = x - range >> 2;
        int j = z - range >> 2;
        int k = x + range >> 2;
        int l = z + range >> 2;
        int i1 = k - i + 1;
        int j1 = l - j + 1;
        int[] aint = this.genBiomes.getInts(i, j, i1, j1);
        BlockPos blockpos = null;
        int k1 = 0;

        for (int l1 = 0; l1 < i1 * j1; ++l1)
        {
            int i2 = i + l1 % i1 << 2;
            int j2 = j + l1 / i1 << 2;
            Biome biome = Biome.getBiome(aint[l1]);

            if (biomes.contains(biome) && (blockpos == null || random.nextInt(k1 + 1) == 0))
            {
                blockpos = new BlockPos(i2, 0, j2);
                ++k1;
            }
        }

        return blockpos;
    }

    /**
     * @see BiomeProvider
     */
    @Override
    public void cleanupCache()
    {
        this.biomeCache.cleanupCache();
    }

    /**
     * @see BiomeProvider
     */
    @Override
    public GenLayer[] getModdedBiomeGenerators(WorldType worldType, long seed, GenLayer[] original)
    {
        WorldTypeEvent.InitBiomeGens event = new WorldTypeEvent.InitBiomeGens(worldType, seed, original);
        MinecraftForge.TERRAIN_GEN_BUS.post(event);
        return event.getNewBiomeGens();
    }
}
