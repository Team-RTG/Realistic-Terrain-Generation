package rtg.world.biome;

import gnu.trove.map.hash.TLongObjectHashMap;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeCache;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;
import rtg.util.genlayers.GenLayerUtils;
import rtg.util.noise.CellNoise;
import rtg.util.noise.OpenSimplexNoise;
import rtg.util.noise.SimplexCellularNoise;
import rtg.util.noise.SimplexOctave;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.biome.realistic.RealisticBiomePatcher;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class BiomeProviderRTG extends BiomeProvider {
    private static int[] incidences = new int[100];
    private static int references = 0;
    /**
     * A GenLayer containing the indices into Biomes.biomeList[]
     */
    private GenLayer genBiomes;
    private GenLayer biomeIndexLayer;
    private List biomesToSpawnIn;
    private OpenSimplexNoise simplex;
    private CellNoise cell;
    private SimplexCellularNoise simplexCell;
    private float[] borderNoise;
    private TLongObjectHashMap<RealisticBiomeBase> biomeDataMap = new TLongObjectHashMap<RealisticBiomeBase>();
    private BiomeCache biomeCache;
    private RealisticBiomePatcher biomePatcher;

    public BiomeProviderRTG(World par1World, WorldType worldType) {

        this();
        long seed = par1World.getSeed();
        if (par1World.provider.getDimension() != 0) throw new RuntimeException();

        simplex = new OpenSimplexNoise(seed);
        cell = new SimplexCellularNoise(seed);
        simplexCell = new SimplexCellularNoise(seed);
        GenLayer[] agenlayer = GenLayerUtils.initializeAllBiomeGenerators(seed, worldType, par1World.getWorldInfo().getGeneratorOptions());
        agenlayer = getModdedBiomeGenerators(worldType, seed, agenlayer);
        this.genBiomes = agenlayer[0]; //maybe this will be needed
        this.biomeIndexLayer = agenlayer[1];
    }

    protected BiomeProviderRTG() {

        this.biomeCache = new BiomeCache(this);
        this.biomesToSpawnIn = new ArrayList();
        borderNoise = new float[256];
        biomePatcher = new RealisticBiomePatcher();
    }

    public int[] getBiomesGens(int par1, int par2, int par3, int par4) {

        int[] d = new int[par3 * par4];

        for (int i = 0; i < par3; i++) {
            for (int j = 0; j < par4; j++) {
                d[j * par3 + i] = RealisticBiomeBase.getIdForBiome(getBiomeGenAt(par1 + i, par2 + j));
            }
        }
        return d;
    }

    public BiomeGenBase getBiomeGenAt(int par1, int par2) {
        BiomeGenBase result;
        result = this.biomeCache.getBiomeCacheBlock(par1, par2).getBiomeGenAt(par1, par2);

        if (result == null) {
            result = biomePatcher.getPatchedBaseBiome("Biome cache contains NULL biome at " + par1 + "," + par2);
        }
        return result;
    }

    public boolean diff(float sample1, float sample2, float base) {

        return (sample1 < base && sample2 > base) || (sample1 > base && sample2 < base);
    }

    public float[] getRainfall(float[] par1ArrayOfFloat, int x, int z, int width, int length) {
        IntCache.resetIntCache();

        if (par1ArrayOfFloat == null || par1ArrayOfFloat.length < width * length) {
            par1ArrayOfFloat = new float[width * length];
        }

        int[] aint = this.biomeIndexLayer.getInts(x, z, width, length);

        for (int i1 = 0; i1 < width * length; ++i1) {
            float f = 0;
            try {
                f = RealisticBiomeBase.getBiome(aint[i1]).getRainfall() / 65536.0F;
            } catch (Exception e) {
                if (RealisticBiomeBase.getBiome(aint[i1]) == null) {
                    f = biomePatcher.getPatchedRealisticBiome("Problem with biome " + aint[i1] + " from " + e.getMessage()).getRainfall() / 65536.0F;
                }
            }

            if (f > 1.0F) {
                f = 1.0F;
            }
            if (f > 1.0F) {
                f = 1.0F;
            }

            par1ArrayOfFloat[i1] = f;
        }

        return par1ArrayOfFloat;

    }

    public RealisticBiomeBase[] getRealisticBiomesForGeneration(RealisticBiomeBase[] biomes, int x, int z, int width, int height) {
        IntCache.resetIntCache();

        if (biomes == null || biomes.length < width * height) {
            biomes = new RealisticBiomeBase[width * height];
        }

        int[] aint = this.genBiomes.getInts(x, z, width, height);

        for (int i1 = 0; i1 < width * height; ++i1) {
            biomes[i1] = RealisticBiomeBase.getBiome(aint[i1]);
        }

        return biomes;
    }

    public boolean isBorderlessAt(int x, int y) {

        int bx, by;

        for (bx = -2; bx <= 2; bx++) {
            for (by = -2; by <= 2; by++) {
                borderNoise[RealisticBiomeBase.getIdForBiome(getBiomeDataAt(x + bx * 16, y + by * 16))] += 0.04f;
            }
        }

        by = 0;
        for (bx = 0; bx < 256; bx++) {
            if (borderNoise[bx] > 0.98f) {
                by = 1;
            }
            borderNoise[bx] = 0;
        }

        return by == 1 ? true : false;
    }

    public RealisticBiomeBase getBiomeDataAt(int par1, int par2) {
        RealisticBiomeBase output;
        output = (RealisticBiomeBase) (this.getBiomeGenAt(par1, par2));
        if (output == null) output = biomePatcher.getPatchedRealisticBiome("No biome " + par1 + " " + par2);
        return output;
    }

    public List getBiomesToSpawnIn() {

        return this.biomesToSpawnIn;
    }

    public float getTemperatureAtHeight(float par1, int par2) {

        return par1;
    }

    public BiomeGenBase[] getBiomesForGeneration(BiomeGenBase[] biomes, int x, int z, int width, int height) {
        IntCache.resetIntCache();

        if (biomes == null || biomes.length < width * height) {
            biomes = new BiomeGenBase[width * height];
        }

        int[] aint = this.genBiomes.getInts(x, z, width, height);

        for (int i1 = 0; i1 < width * height; ++i1) {
            biomes[i1] = RealisticBiomeBase.getBiome(aint[i1]);
        }

        return biomes;
    }

    @Override
    public BiomeGenBase[] loadBlockGeneratorData(BiomeGenBase[] par1ArrayOfBiomeGenBase, int par2, int par3, int par4, int par5) {

        return this.getBiomeGenAt(par1ArrayOfBiomeGenBase, par2, par3, par4, par5, true);
    }

    public BiomeGenBase[] getBiomeGenAt(BiomeGenBase[] listToReuse, int x, int z, int width, int length, boolean cacheFlag) {
        IntCache.resetIntCache();

        if (listToReuse == null || listToReuse.length < width * length) {
            listToReuse = new BiomeGenBase[width * length];
        }

        if (cacheFlag && width == 16 && length == 16 && (x & 15) == 0 && (z & 15) == 0) {
            BiomeGenBase[] abiomegenbase1 = this.biomeCache.getCachedBiomes(x, z);
            System.arraycopy(abiomegenbase1, 0, listToReuse, 0, width * length);
            return listToReuse;
        } else {
            int[] aint = this.biomeIndexLayer.getInts(x, z, width, length);

            for (int i1 = 0; i1 < width * length; ++i1) {
                try {
                    listToReuse[i1] = RealisticBiomeBase.getBiome(aint[i1]);
                } catch (Exception e) {
                    listToReuse[i1] = biomePatcher.getPatchedRealisticBiome(genBiomes.toString() + " " + this.biomeIndexLayer.toString());
                }
                if (listToReuse[i1] == null) {
                    listToReuse[i1] = biomePatcher.getPatchedRealisticBiome("Missing biome " + aint[i1]);
                }
            }

            return listToReuse;
        }
    }

    public boolean areBiomesViable(int x, int y, int par3, List par4List) {

        float centerNoise = getNoiseAt(x, y);
        if (centerNoise < 62) {
            return false;
        }

        float lowestNoise = centerNoise;
        float highestNoise = centerNoise;
        for (int i = -2; i <= 2; i++) {
            for (int j = -2; j <= 2; j++) {
                if (i != 0 && j != 0) {
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

        return highestNoise - lowestNoise < 22;

    }

    public float getNoiseAt(int x, int y) {

        float river = getRiverStrength(x, y) + 1f;
        if (river < 0.5f) {
            return 59f;
        }

        return getBiomeDataAt(x, y).rNoise(simplex, cell, x, y, 1f, river);
    }

    public float getRiverStrength(int x, int y) {
        //New river curve function. No longer creates worldwide curve correlations along cardinal axes.
        SimplexOctave.Disk jitter = new SimplexOctave.Disk();
        simplex.riverJitter().evaluateNoise(x / 240.0, y / 240.0, jitter);
        double pX = x + jitter.deltax() * 220f;
        double pY = y + jitter.deltay() * 220f;
            /*double[] simplexResults = new double[2];
            OpenSimplexNoise.noise(x / 240.0, y / 240.0, riverOpenSimplexNoiseInstances, simplexResults);
            double pX = x + simplexResults[0] * 220f;
            double pY = y + simplexResults[1] * 220f;*/

        //New cellular noise.
        //TODO move the initialization of the results in a way that's more efficient but still thread safe.
        double[] results = simplexCell.river().eval(pX / 1875.0, pY / 1875.0);
        if (x == -200 && y == -750) {
            //throw new RuntimeException(""+ results[1]+ " " +results[0]);
        }
        return (float) cellBorder(results, 30.0 / 600.0, 1.0);
    }

    private static double cellBorder(double[] results, double width, double depth) {
        double c = (results[1] - results[0]);
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

    @Override
    public BlockPos findBiomePosition(int p_150795_1_, int p_150795_2_, int p_150795_3_, List p_150795_4_, Random p_150795_5_) {
        IntCache.resetIntCache();
        int l = p_150795_1_ - p_150795_3_ >> 2;
        int i1 = p_150795_2_ - p_150795_3_ >> 2;
        int j1 = p_150795_1_ + p_150795_3_ >> 2;
        int k1 = p_150795_2_ + p_150795_3_ >> 2;
        int l1 = j1 - l + 1;
        int i2 = k1 - i1 + 1;
        int[] aint = this.genBiomes.getInts(l, i1, l1, i2);
        BlockPos blockPos = null;
        int j2 = 0;

        for (int k2 = 0; k2 < l1 * i2; ++k2) {
            int l2 = l + k2 % l1 << 2;
            int i3 = i1 + k2 / l1 << 2;
            BiomeGenBase biomegenbase = BiomeGenBase.getBiome(aint[k2]);

            if (p_150795_4_.contains(biomegenbase) && (blockPos == null || p_150795_5_.nextInt(j2 + 1) == 0)) {
                blockPos = new BlockPos(l2, 0, i3);
                ++j2;
            }
        }

        return blockPos;
    }

    @Override
    public void cleanupCache() {
        this.biomeCache.cleanupCache();
    }
}
