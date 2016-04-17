package rtg.world.biome;

import gnu.trove.map.hash.TLongObjectHashMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.util.SimplexCellularNoise;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.biome.realistic.RealisticBiomePatcher;

import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeCache;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;
import rtg.config.rtg.ConfigRTG;
import rtg.util.SimplexCellularOctave;
import rtg.util.SimplexOctave;
import rtg.util.VoronoiCellNoise;

public class WorldChunkManagerRTG extends WorldChunkManager implements RTGBiomeProvider
{
    /** A GenLayer containing the indices into BiomeGenBase.biomeList[] */
    private GenLayer genBiomes;
    private GenLayer biomeIndexLayer;
    private List biomesToSpawnIn;
    private OpenSimplexNoise simplex;
    private CellNoise cell;
    //private SimplexCellularNoise simplexCell;
    private float[] borderNoise;
    private TLongObjectHashMap<RealisticBiomeBase> biomeDataMap = new TLongObjectHashMap<RealisticBiomeBase>();
    private BiomeCache biomeCache;
    private RealisticBiomePatcher biomePatcher;
    
    protected WorldChunkManagerRTG()
    {
        
        this.biomeCache = new BiomeCache(this);
        this.biomesToSpawnIn = new ArrayList();
        borderNoise = new float[256];
        biomePatcher = new RealisticBiomePatcher();
        this.riverSeparation /= ConfigRTG.riverFrequencyMultiplier;
        this.riverValleyLevel *= ConfigRTG.riverSizeMultiplier();
        this.largeBendSize *= ConfigRTG.riverBendinessMultiplier;
        this.smallBendSize *= ConfigRTG.riverBendinessMultiplier;
    }
    
    public WorldChunkManagerRTG(World par1World,WorldType worldType)
    {

        this();
        long seed = par1World.getSeed();
        if (par1World.provider.dimensionId !=0) throw new RuntimeException();

        simplex = new OpenSimplexNoise(seed);
        cell = new SimplexCellularNoise(seed);
        //simplexCell = new SimplexCellularNoise(seed);
        GenLayer[] agenlayer = GenLayer.initializeAllBiomeGenerators(seed, worldType);
        agenlayer = getModdedBiomeGenerators(worldType, seed, agenlayer);
        this.genBiomes = agenlayer[0]; //maybe this will be needed
        this.biomeIndexLayer = agenlayer[1];
        testCellBorder();
    }
    
    public int[] getBiomesGens(int par1, int par2, int par3, int par4)
    {
        
        int[] d = new int[par3 * par4];
        
        for (int i = 0; i < par3; i++)
        {
            for (int j = 0; j < par4; j++)
            {
                d[j * par3  + i] = getBiomeGenAt(par1 + i, par2 + j).biomeID;
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
            // Is this a single biome world?
            if (biomePatcher.isSingleBiomeWorld())
            {
                f = (float) biomePatcher.getSingleRealisticBiome().getIntRainfall() / 65536.0F;
            } else {
                int biome = aint[i1];
                try {
                    if (biome>255) throw new RuntimeException(biomeIndexLayer.toString());
                    f = (float) RealisticBiomeBase.getBiome(biome).getIntRainfall() / 65536.0F;
                } catch (Exception e) {
                    if (biome>255) throw new RuntimeException(biomeIndexLayer.toString());
                    if (RealisticBiomeBase.getBiome(biome)== null) {
                        f = (float) biomePatcher.getPatchedRealisticBiome("Problem with biome "+biome+" from "+e.getMessage()).getIntRainfall() / 65536.0F;
                    }
                }
            }
            if (f > 1.0F) { f = 1.0F;}

            par1ArrayOfFloat[i1] = f;
        }

        return par1ArrayOfFloat;

    }

    @Override
    public BiomeGenBase[] loadBlockGeneratorData(BiomeGenBase[] par1ArrayOfBiomeGenBase, int par2, int par3, int par4, int par5)
    {

        return this.getBiomeGenAt(par1ArrayOfBiomeGenBase, par2, par3, par4, par5, true);
    }

    @Override
    public BiomeGenBase getBiomeGenAt(int par1, int par2) {
        BiomeGenBase result;
        // Is this a single biome world?
        if (biomePatcher.isSingleBiomeWorld()){
            result = biomePatcher.getSingleBaseBiome();
        } else {
            result = this.biomeCache.getBiomeGenAt(par1, par2);

            if (result == null) {
                result = biomePatcher.getPatchedBaseBiome("Biome cache contains NULL biome at " + par1 + "," + par2);
            }
        }
        return result;
    }

    public BiomeGenBase[] getBiomesForGeneration(BiomeGenBase[] par1ArrayOfBiomeGenBase, int par2, int par3, int par4, int par5)
    {
        IntCache.resetIntCache();

        if (par1ArrayOfBiomeGenBase == null || par1ArrayOfBiomeGenBase.length < par4 * par5)
        {
            par1ArrayOfBiomeGenBase = new BiomeGenBase[par4 * par5];
        }

        int[] aint = this.genBiomes.getInts(par2, par3, par4, par5);

        for (int i1 = 0; i1 < par4 * par5; ++i1)
        {
            par1ArrayOfBiomeGenBase[i1] = RealisticBiomeBase.getBiome(aint[i1]);
        }

        return par1ArrayOfBiomeGenBase;
    }

    public RealisticBiomeBase getBiomeDataAt(int par1, int par2)
    {
        /*long coords = ChunkCoordIntPair.chunkXZ2Int(par1, par2);
        if (biomeDataMap.containsKey(coords)) {
            return biomeDataMap.get(coords);
        }*/
        RealisticBiomeBase output;

        // Is this a single biome world?
        if (biomePatcher.isSingleBiomeWorld())
        {
            output = biomePatcher.getSingleRealisticBiome();
        }
        else
        {
            output = (RealisticBiomeBase)(this.getBiomeGenAt(par1, par2));
            if (output == null) output = biomePatcher.getPatchedRealisticBiome("No biome " + par1 + " " + par2);
        }
        /*if (biomeDataMap.size() > 4096) {
            biomeDataMap.clear();
        }

        biomeDataMap.put(coords, output);*/

        return output;
    }

    @Override
    public void cleanupCache()
    {
        this.biomeCache.cleanupCache();
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

    private double riverValleyLevel = 60.0 / 450.0;
    private float riverSeparation = 1875;
    private float largeBendSize = 140;
    private float smallBendSize = 35;

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
        double[] results = cell.river().eval(xRiver,yRiver );
        return (float) cellBorder(results, riverValleyLevel, 1.0);
        //return cell.octave(1).border2(xRiver, yRiver, riverValleyLevel, 1f);
    }
    	
    public boolean isBorderlessAt(int x, int y)
    {
        
        int bx, by;
        
        for (bx = -2; bx <= 2; bx++)
        {
            for (by = -2; by <= 2; by++)
            {
                borderNoise[getBiomeDataAt(x + bx * 16, y + by * 16).biomeID] += 0.04f;
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
    
    public List getBiomesToSpawnIn()
    {
        
        return this.biomesToSpawnIn;
    }
    
    public float getTemperatureAtHeight(float par1, int par2)
    {
        
        return par1;
    }
    
    public BiomeGenBase[] getBiomeGenAt(BiomeGenBase[] par1ArrayOfBiomeGenBase, int par2, int par3, int par4, int par5, boolean par6)
    {
        IntCache.resetIntCache();

        if (par1ArrayOfBiomeGenBase == null || par1ArrayOfBiomeGenBase.length < par4 * par5)
        {
            par1ArrayOfBiomeGenBase = new BiomeGenBase[par4 * par5];
        }

        if (par6 && par4 == 16 && par5 == 16 && (par2 & 15) == 0 && (par3 & 15) == 0)
        {
            BiomeGenBase[] abiomegenbase1 = this.biomeCache.getCachedBiomes(par2, par3);
            System.arraycopy(abiomegenbase1, 0, par1ArrayOfBiomeGenBase, 0, par4 * par5);
            return par1ArrayOfBiomeGenBase;
        }
        else
        {
            int[] aint = this.biomeIndexLayer.getInts(par2, par3, par4, par5);

            for (int i1 = 0; i1 < par4 * par5; ++i1)
            {
                // Is this a single biome world?
                if (biomePatcher.isSingleBiomeWorld())
                {
                    par1ArrayOfBiomeGenBase[i1] = biomePatcher.getSingleRealisticBiome();
                } else {
                    try {
                        par1ArrayOfBiomeGenBase[i1] = RealisticBiomeBase.getBiome(aint[i1]);
                    } catch (Exception e) {
                        par1ArrayOfBiomeGenBase[i1] = biomePatcher.getPatchedRealisticBiome(genBiomes.toString()+ " " + this.biomeIndexLayer.toString());
                    }
                    if (par1ArrayOfBiomeGenBase[i1] == null) {
                        par1ArrayOfBiomeGenBase[i1] = biomePatcher.getPatchedRealisticBiome("Missing biome "+aint[i1]);
                    }
                }
            }

            return par1ArrayOfBiomeGenBase;
        }
    }
    
    public boolean areBiomesViable(int x, int y, int par3, List par4List)
    {
        
        float centerNoise = getNoiseAt(x, y);
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
                    float n = getNoiseAt(x + i * 16, y + j * 16);
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
    
    @Override
    public ChunkPosition findBiomePosition(int p_150795_1_, int p_150795_2_, int p_150795_3_, List p_150795_4_, Random p_150795_5_)
    {
        IntCache.resetIntCache();
        int l = p_150795_1_ - p_150795_3_ >> 2;
        int i1 = p_150795_2_ - p_150795_3_ >> 2;
        int j1 = p_150795_1_ + p_150795_3_ >> 2;
        int k1 = p_150795_2_ + p_150795_3_ >> 2;
        int l1 = j1 - l + 1;
        int i2 = k1 - i1 + 1;
        int[] aint = this.genBiomes.getInts(l, i1, l1, i2);
        ChunkPosition chunkposition = null;
        int j2 = 0;

        for (int k2 = 0; k2 < l1 * i2; ++k2)
        {
            int l2 = l + k2 % l1 << 2;
            int i3 = i1 + k2 / l1 << 2;
            BiomeGenBase biomegenbase = BiomeGenBase.getBiome(aint[k2]);

            if (p_150795_4_.contains(biomegenbase) && (chunkposition == null || p_150795_5_.nextInt(j2 + 1) == 0))
            {
                chunkposition = new ChunkPosition(l2, 0, i3);
                ++j2;
            }
        }

        return chunkposition;
    }
}
