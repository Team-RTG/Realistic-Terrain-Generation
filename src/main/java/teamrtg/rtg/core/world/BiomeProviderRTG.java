package teamrtg.rtg.core.world;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.util.ReportedException;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeCache;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;
import teamrtg.rtg.api.module.Mods;
import teamrtg.rtg.api.util.BiomeUtils;
import teamrtg.rtg.api.util.LimitedMap;
import teamrtg.rtg.api.util.PlaneLocation;
import teamrtg.rtg.api.util.debug.Logger;
import teamrtg.rtg.api.util.genlayers.GenLayerUtils;
import teamrtg.rtg.api.util.noise.CellNoise;
import teamrtg.rtg.api.util.noise.OpenSimplexNoise;
import teamrtg.rtg.api.util.noise.SimplexCellularNoise;
import teamrtg.rtg.api.util.noise.SimplexOctave;
import teamrtg.rtg.api.world.biome.RTGBiome;


public class BiomeProviderRTG extends BiomeProvider {
    private static int[] incidences = new int[100];
    private static int references = 0;
    public final LimitedMap<PlaneLocation, int[]> biomes = new LimitedMap<>(64);
    public final LimitedMap<PlaneLocation, float[]> heights = new LimitedMap<>(64);
    public ChunkProviderRTG chunkProvider;
    /**
     * A GenLayer containing the indices into Biomes.biomeList[]
     */
    private GenLayer genBiomes;
    private GenLayer biomeIndexLayer;
    private List<Biome> biomesToSpawnIn;
    private OpenSimplexNoise simplex;
    private CellNoise cell;
    private BiomeCache biomeCache;
    private double riverValleyLevel = 60.0 / 450.0;
    private float riverSeparation = 1875;
    private float largeBendSize = 140;
    private float smallBendSize = 35;

    public BiomeProviderRTG(World par1World, WorldType worldType) {

        this();
        long seed = par1World.getSeed();

        if (par1World.provider.getDimension() != 0) {
            throw new RuntimeException();
        }

        simplex = new OpenSimplexNoise(seed);
        cell = new SimplexCellularNoise(seed);
        Logger.info(par1World.getWorldInfo().getGeneratorOptions());
        GenLayer[] agenlayer = GenLayerUtils.initializeAllBiomeGenerators(seed, worldType, par1World.getWorldInfo().getGeneratorOptions());
        agenlayer = getModdedBiomeGenerators(worldType, seed, agenlayer);
        this.genBiomes = agenlayer[0]; //maybe this will be needed
        this.biomeIndexLayer = agenlayer[1];

        this.riverSeparation /= Mods.RTG.config.RIVER_FREQUENCY_MULTIPLIER.get();
        this.riverValleyLevel *= Mods.RTG.config.RIVER_SIZE_MULTIPLIER.get();
        this.largeBendSize *= Mods.RTG.config.RIVER_BENDINESS_MULTIPLIER.get();
        this.smallBendSize *= Mods.RTG.config.RIVER_BENDINESS_MULTIPLIER.get();
    }

    protected BiomeProviderRTG() {

        this.biomeCache = new BiomeCache(this);
        this.biomesToSpawnIn = new ArrayList();
    }

    /**
     * Get the biome ids in a rectangle pre repair.
     * @param bx starting point x coord
     * @param bz starting point z coord
     * @param xSize size along x axis
     * @param zSize size along z axis
     * @return an array of IDs for that square, indexed by x * xSize + z
     */
    public int[] getBiomesGens(int bx, int bz, int xSize, int zSize) {

        int[] d = new int[xSize * zSize];

        for (int i = 0; i < xSize; i++) {
            for (int j = 0; j < zSize; j++) {
                d[i * xSize + j] = BiomeUtils.getId(getPreRepair(bx + i, bz + j));
            }
        }
        return d;
    }

    /**
     * Get biome at coordinate before biome repair
     * @param x x coordinate
     * @param z z coordinate
     * @return biome at coordinate
     */
    public Biome getPreRepair(int x, int z) {
        Biome result;
        result = this.biomeCache.getBiomeCacheBlock(x, z).getBiome(x, z);
        return result;
    }

    public RTGBiome getRTGBiomeAt(int bx, int bz) {
        return RTGBiome.forBiome(getBiomeGenAt(bx, bz));
    }

    public Biome getBiomeGenAt(int x, int z) {
        return Biome.getBiomeForId(chunkProvider.landscapeGenerator.getBiomeDataAt(this, x, z));
    }

    @Override
    public List<Biome> getBiomesToSpawnIn() {

        return this.biomesToSpawnIn;
    }

    public float getTemperatureAtHeight(float par1, int par2) {

        return par1;
    }

    @Override
    public Biome[] getBiomesForGeneration(Biome[] biomes, int x, int z, int width, int height) {
        IntCache.resetIntCache();

        if (biomes == null || biomes.length < width * height) {
            biomes = new Biome[width * height];
        }

        int[] aint = this.genBiomes.getInts(x, z, width, height);

        for (int i1 = 0; i1 < width * height; ++i1) {
            biomes[i1] = Biome.getBiomeForId(aint[i1]);
        }

        return biomes;
    }

    @Override
    @MethodsReturnNonnullByDefault
    public Biome[] getBiomes(Biome[] listToReuse, int x, int z, int width, int length, boolean cacheFlag) {
        IntCache.resetIntCache();

        if (listToReuse == null || listToReuse.length < width * length) {
            listToReuse = new Biome[width * length];
        }

        if (cacheFlag && width == 16 && length == 16 && (x & 15) == 0 && (z & 15) == 0) {
            Biome[] abiomegenbase1 = this.biomeCache.getCachedBiomes(x, z);
            System.arraycopy(abiomegenbase1, 0, listToReuse, 0, width * length);
            return listToReuse;
        } else {
            int[] aint = this.biomeIndexLayer.getInts(x, z, width, length);

            for (int i1 = 0; i1 < width * length; ++i1) {
                listToReuse[i1] = Biome.getBiomeForId(aint[i1]);
            }

            return listToReuse;
        }
    }

    /**
     * checks given Chunk's Biomes against List of allowed ones
     */
    @Override
    public boolean areBiomesViable(int x, int z, int radius, List<Biome> allowed) {
        IntCache.resetIntCache();
        int i = x - radius >> 2;
        int j = z - radius >> 2;
        int k = x + radius >> 2;
        int l = z + radius >> 2;
        int i1 = k - i + 1;
        int j1 = l - j + 1;
        int[] aint = this.genBiomes.getInts(i, j, i1, j1);

        try {
            for (int k1 = 0; k1 < i1 * j1; ++k1) {
                Biome biomegenbase = Biome.getBiome(aint[k1]);

                if (!allowed.contains(biomegenbase)) {
                    return false;
                }
            }

            return true;
        } catch (Throwable throwable) {
            CrashReport crashreport = CrashReport.makeCrashReport(throwable, "Invalid Biome id");
            CrashReportCategory crashreportcategory = crashreport.makeCategory("Layer");
            crashreportcategory.addCrashSection("Layer", this.genBiomes.toString());
            crashreportcategory.addCrashSection("x", Integer.valueOf(x));
            crashreportcategory.addCrashSection("z", Integer.valueOf(z));
            crashreportcategory.addCrashSection("radius", Integer.valueOf(radius));
            crashreportcategory.addCrashSection("allowed", allowed);
            throw new ReportedException(crashreport);
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
            Biome biomegenbase = Biome.getBiome(aint[k2]);

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

    public float getRiverStrength(int x, int y) {

        //New river curve function. No longer creates worldwide curve correlations along cardinal axes.
        SimplexOctave.Disk jitter = new SimplexOctave.Disk();
        simplex.riverJitter().evaluateNoise((float) x / 240.0, (float) y / 240.0, jitter);
        double pX = x + jitter.deltax() * largeBendSize;
        double pY = y + jitter.deltay() * largeBendSize;


        simplex.octave(2).evaluateNoise((float) x / 80.0, (float) y / 80.0, jitter);
        pX += jitter.deltax() * smallBendSize;
        pY += jitter.deltay() * smallBendSize;

        double xRiver = pX / riverSeparation;
        double yRiver = pY / riverSeparation;

        //New cellular noise.
        //TODO move the initialization of the results in a way that's more efficient but still thread safe.
        double[] results = cell.river().eval(xRiver, yRiver);
        return (float) cellBorder(results, riverValleyLevel, 1.0);
        //return cell.octave(1).border2(xRiver, yRiver, riverValleyLevel, 1f);
    }

    private static double cellBorder(double[] results, double width, double depth) {
        double c = (results[1] - results[0]) / results[1];
        if (c < 0) throw new RuntimeException();
        if (c < width) {
            return ((c / width) - 1f) * depth;
        } else {

            return 0;
        }
    }
}
