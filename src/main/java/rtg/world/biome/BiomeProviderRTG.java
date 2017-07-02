package rtg.world.biome;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.annotation.Nonnull;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.WorldTypeEvent;

import rtg.api.RTGAPI;
import rtg.api.dimension.DimensionManagerRTG;
import rtg.api.util.Bayesian;
import rtg.api.util.noise.OpenSimplexNoise;
import rtg.api.util.noise.SimplexOctave;
import rtg.api.util.noise.SpacedCellNoise;
import rtg.api.world.biome.IBiomeProviderRTG;
import rtg.world.RTGWorld;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.biome.realistic.RealisticBiomePatcher;

@SuppressWarnings({"WeakerAccess", "unused"})
public class BiomeProviderRTG extends BiomeProvider implements IBiomeProviderRTG
{
    private static int[] incidences = new int[100];
    private static int references = 0;
    /** A GenLayer containing the indices into Biome.biomeList[] */
    private GenLayer genBiomes;
    private GenLayer biomeIndexLayer;
    private List<Biome> biomesToSpawnIn;
    private RTGWorld rtgWorld;
    private OpenSimplexNoise simplex;
    //private SimplexCellularNoise simplexCell;
    private SpacedCellNoise river;
    private float[] borderNoise;
    private RealisticBiomePatcher biomePatcher;
    private double riverValleyLevel = 140.0 / 450.0;//60.0/450.0;
    private float riverSeparation = 975;
    private float largeBendSize = 140;
    private float smallBendSize = 30;

    public BiomeProviderRTG(World world, WorldType worldType) {

        super(world.getWorldInfo());

        this.rtgWorld = new RTGWorld(world);
        this.biomesToSpawnIn = new ArrayList<>();
        this.borderNoise = new float[256];
        this.biomePatcher = new RealisticBiomePatcher();
        this.riverSeparation /= RTGAPI.config().RIVER_FREQUENCY_MULTIPLIER.get();
        this.riverValleyLevel *= RTGAPI.config().riverSizeMultiplier();
        this.largeBendSize *= RTGAPI.config().RIVER_BENDINESS_MULTIPLIER.get();
        this.smallBendSize *= RTGAPI.config().RIVER_BENDINESS_MULTIPLIER.get();

        long seed = world.getSeed();

        if (!DimensionManagerRTG.isValidDimension(world.provider.getDimension())) throw new RuntimeException();

        simplex = new OpenSimplexNoise(seed);
        //simplexCell = new SimplexCellularNoise(seed);
        river = new SpacedCellNoise(seed);
        GenLayer[] agenlayer = GenLayer.initializeAllBiomeGenerators(seed, worldType, "");
        agenlayer = getModdedBiomeGenerators(worldType, seed, agenlayer);
        this.genBiomes = agenlayer[0]; //maybe this will be needed
        this.biomeIndexLayer = agenlayer[1];
    }

    @Override
    public int[] getBiomesGens(int x, int z, int par3, int par4) {

        int[] d = new int[par3 * par4];

        for (int i = 0; i < par3; i++) {
            for (int j = 0; j < par4; j++) {
                d[i * par3 + j] = Biome.getIdForBiome(getBiomeGenAt(x + i, z + j));
            }
        }
        return d;
    }

    @Override
    public float getRiverStrength(int x, int z) {
        //New river curve function. No longer creates worldwide curve correlations along cardinal axes.
        SimplexOctave.Disk jitter = new SimplexOctave.Disk();
        simplex.riverJitter().evaluateNoise((float) x / 240.0, (float) z / 240.0, jitter);
        double pX = x + jitter.deltax() * largeBendSize;
        double pZ = z + jitter.deltay() * largeBendSize;

        simplex.octave(2).evaluateNoise((float) x / 80.0, (float) z / 80.0, jitter);
        pX += jitter.deltax() * smallBendSize;
        pZ += jitter.deltay() * smallBendSize;

        double xRiver = pX / riverSeparation;
        double zRiver = pZ / riverSeparation;

        //New cellular noise.
        //TODO move the initialization of the results in a way that's more efficient but still thread safe.
        //double[] results = cell.river().eval(xRiver,zRiver );
        //return (float) cellBorder(results, riverValleyLevel, 1.0);
        float riverFactor = (float)river.octave(0).eval(xRiver, zRiver).interiorValue();
        // the output is a curved function of relative distance from the center, so adjust to make it flatter
        riverFactor = Bayesian.adjustment(riverFactor, .5f);
        if (riverFactor>riverValleyLevel) return 0; // no river effect
        return (float)(riverFactor/riverValleyLevel) -1f;
    }

    /**
     * @see IBiomeProviderRTG
     */
    @Override
    public Biome getBiomeGenAt(int x, int z) {
        return this.getBiome(new BlockPos(x, 0, z));
    }

    /**
     * @see IBiomeProviderRTG
     */
    @Override
    public RealisticBiomeBase getBiomeDataAt(int par1, int par2) {
        /*long coords = ChunkCoordIntPair.chunkXZ2Int(par1, par2);
        if (biomeDataMap.containsKey(coords)) {
            return biomeDataMap.get(coords);
        }*/
        RealisticBiomeBase output;

        output = RealisticBiomeBase.getBiome(Biome.getIdForBiome(this.getBiomeGenAt(par1, par2)));
        if (output == null) output = biomePatcher.getPatchedRealisticBiome("No biome " + par1 + " " + par2);

        /*if (biomeDataMap.size() > 4096) {
            biomeDataMap.clear();
        }

        biomeDataMap.put(coords, output);*/

        return output;
    }

    /**
     * @see IBiomeProviderRTG
     */
    @Override
    public boolean isBorderlessAt(int x, int z) {

        int bx, bz;
        for (bx = -2; bx <= 2; bx++) {
            for (bz = -2; bz <= 2; bz++) {
                borderNoise[Biome.getIdForBiome(getBiomeDataAt(x + bx * 16, z + bz * 16).baseBiome)] += 0.04f;
            }
        }

        bz = 0;
        for (bx = 0; bx < 256; bx++) {
            if (borderNoise[bx] > 0.98f) bz = 1;
            borderNoise[bx] = 0;
        }
        return bz == 1;
    }

    public boolean diff(float sample1, float sample2, float base) {
        return (sample1 < base && sample2 > base) || (sample1 > base && sample2 < base);
    }

    public float[] getRainfall(float[] par1ArrayOfFloat, int par2, int par3, int par4, int par5) {
        IntCache.resetIntCache();

        if (par1ArrayOfFloat == null || par1ArrayOfFloat.length < par4 * par5) {
            par1ArrayOfFloat = new float[par4 * par5];
        }

        int[] aint = this.biomeIndexLayer.getInts(par2, par3, par4, par5);

        for (int i1 = 0; i1 < par4 * par5; ++i1) {
            float f = 0;
            int biome = aint[i1];

            try {
                if (biome > 255) throw new RuntimeException(biomeIndexLayer.toString());
                f = RealisticBiomeBase.getBiome(biome).baseBiome.getRainfall() / 65536.0F;
            }
            catch (Exception e) {
                if (biome > 255) throw new RuntimeException(biomeIndexLayer.toString());
                if (RealisticBiomeBase.getBiome(biome) == null) {
                    f = biomePatcher.getPatchedRealisticBiome("Problem with biome " + biome + " from " +
                        e.getMessage()).baseBiome.getRainfall() / 65536.0F;
                }
            }
            if (f > 1.0F) f = 1.0F;
            par1ArrayOfFloat[i1] = f;
        }
        return par1ArrayOfFloat;
    }

    @Override
    @Nonnull
    public List<Biome> getBiomesToSpawnIn() {
        return this.biomesToSpawnIn;
    }

    @Override
    public float getTemperatureAtHeight(float p_76939_1_, int p_76939_2_) {
        return p_76939_1_;
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

            if (biomes[i1] == null) {
                biomes[i1] = biomePatcher.getPatchedBaseBiome(
                    "BPRTG.getBiomesForGeneration() could not find biome " + aint[i1]);
            }
        }
        return biomes;
    }

    @Override
    public boolean areBiomesViable(int x, int z, int radius, @Nonnull List<Biome> allowed) {

        float centerNoise = getNoiseAt(x, z);
        if (centerNoise < 62) return false;

        float lowestNoise = centerNoise;
        float highestNoise = centerNoise;
        for (int i = -2; i <= 2; i++) {
            for (int j = -2; j <= 2; j++) {
                if (i != 0 && j != 0) {
                    float n = getNoiseAt(x + i * 16, z + j * 16);
                    if (n < lowestNoise)  lowestNoise = n;
                    if (n > highestNoise) highestNoise = n;
                }
            }
        }
        return highestNoise - lowestNoise < 22;
    }

    public float getNoiseAt(int x, int y) {

        float river = getRiverStrength(x, y) + 1f;
        if (river < 0.5f) return 59f;
        return getBiomeDataAt(x, y).rNoise(this.rtgWorld, x, y, 1f, river);
    }

    @Override
    public BlockPos findBiomePosition(int x, int z, int range, @Nonnull List<Biome> biomes, @Nonnull Random random) {
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

        for (int l1 = 0; l1 < i1 * j1; ++l1) {
            int i2 = i + l1 % i1 << 2;
            int j2 = j + l1 / i1 << 2;
            Biome biome = Biome.getBiome(aint[l1]);

            if (biomes.contains(biome) && (blockpos == null || random.nextInt(k1 + 1) == 0)) {
                blockpos = new BlockPos(i2, 0, j2);
                ++k1;
            }
        }
        return blockpos;
    }

    @Override
    @Nonnull
    public GenLayer[] getModdedBiomeGenerators(@Nonnull WorldType worldType, long seed, @Nonnull GenLayer[] original) {
        WorldTypeEvent.InitBiomeGens event = new WorldTypeEvent.InitBiomeGens(worldType, seed, original);
        MinecraftForge.TERRAIN_GEN_BUS.post(event);
        return event.getNewBiomeGens();
    }
}
