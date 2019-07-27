package rtg.api.world;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.gen.ChunkProviderServer;

import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import rtg.api.RTGAPI;
import rtg.api.util.Logger;
import rtg.api.util.noise.CellularNoise;
import rtg.api.util.noise.OpenSimplexNoise;
import rtg.api.util.noise.SimplexNoise;
import rtg.api.util.noise.SpacedCellularNoise;
import rtg.api.world.gen.RTGChunkGenSettings;


/**
 * A wrapper class for a {@link World} that contains collections of noise generator instances used in terrain gen.
 *
 * @author topisani (original)
 * @author srs_bsns
 * @since 1.0.0
 */
@Mod.EventBusSubscriber(modid = RTGAPI.RTG_MOD_ID)
public final class RTGWorld {

    public static final float ACTUAL_RIVER_PROPORTION = 150f / 1600f;//This value is also used in BiomeAnalyser#riverAdjusted
    public static final float RIVER_FLATTENING_ADDEND = ACTUAL_RIVER_PROPORTION / (1f - ACTUAL_RIVER_PROPORTION);
    private static final double RIVER_LARGE_BEND_SIZE_BASE = 140D;
    private static final double RIVER_SMALL_BEND_SIZE_BASE = 30D;
    private static final double RIVER_SEPARATION_BASE = 975D;
    private static final double RIVER_VALLEY_LEVEL_BASE = 140D / 450D;
    private static final float LAKE_FREQUENCY_BASE = 649.0f;
    private static final float LAKE_SHORE_LEVEL_BASE = 0.035f;
    private static final float LAKE_DEPRESSION_LEVEL = 0.15f; // the lakeStrength below which land should start to be lowered
    private static final float LAKE_BEND_SIZE_LARGE = 80;
    private static final float LAKE_BEND_SIZE_MEDIUM = 30;
    private static final float LAKE_BEND_SIZE_SMALL = 12;
    private static final int SIMPLEX_INSTANCE_COUNT = 10;
    private static final int CELLULAR_INSTANCE_COUNT = 5;

    private static final Map<World, RTGWorld> INSTANCE_CACHE = new HashMap<>();

    private final World world;
    private final RTGChunkGenSettings generatorSettings;
    private final SimplexNoise[] simplexNoiseInstances = new SimplexNoise[SIMPLEX_INSTANCE_COUNT];
    private final CellularNoise[] cellularNoiseInstances = new CellularNoise[CELLULAR_INSTANCE_COUNT];
    // this field is mutable, but it can only be set once from #setRandom when the ChunkGeneratorRTG is initialised
    private Random generatorRandom = null;

    private RTGWorld(World world) {

        this.world = world;
        this.generatorSettings = RTGChunkGenSettings.Factory.jsonToFactory(world.getWorldInfo().getGeneratorOptions()).build();

        for (int i = 0; i < SIMPLEX_INSTANCE_COUNT; i++) {
            this.simplexNoiseInstances[i] = new OpenSimplexNoise(this.seed() + i);
        }
        for (int i = 0; i < CELLULAR_INSTANCE_COUNT; i++) {
            this.cellularNoiseInstances[i] = new SpacedCellularNoise(this.seed() + i);
        }
    }

    /**
     * Gets an instance of RTGWorld of a {@link World} from the cache. If one does not exist, create it and store it first.
     *
     * @param world the world from which to get an instance of RTGWorld for
     * @return a cached or new instance of RTGWorld
     * @since 1.0.0
     */
    public static RTGWorld getInstance(World world) {
        if (!INSTANCE_CACHE.containsKey(world)) {
            INSTANCE_CACHE.put(world, new RTGWorld(world));
        }
        return INSTANCE_CACHE.get(world);
    }

    /**
     * Syncs this objects Random to the chunk generator
     *
     * @param random the chunk generator Random
     */
    public void setRandom(Random random) {
        if (this.generatorRandom == null) {
            this.generatorRandom = random;
        }
    }

    /**
     * Gets the {@link World} object for this wrapper.
     *
     * @return The world object for this wrapper
     * @since 1.0.0
     */
    public World world() {
        return this.world;
    }

    /**
     * Gets the stored {@link RTGChunkGenSettings} instance for the world of this wrapper.
     *
     * @return The stored RTGChunkGenSettings object
     * @since 1.0.0
     */
    public RTGChunkGenSettings getGeneratorSettings() {
        return this.generatorSettings;
    }

    /**
     * Alias to get the seed value of the {@link World} for this wrapper.
     *
     * @return The seed value
     * @since 1.0.0
     */
    public long seed() {
        return this.world().getSeed();
    }

    /**
     * Alias to get the instance of Random of the {@link rtg.world.gen.ChunkGeneratorRTG} for this wrapper.
     *
     * @return The chunk generator's Random instance
     * @since 1.0.0
     */
    public Random rand() {
        return this.generatorRandom;
    }

    /**
     * Calculates the seed for a Chunk based on the coord parameters.
     *
     * @param chunkX The chunk X coord
     * @param chunkZ The chunk Z coord
     * @return the chunk seed value
     * @since 1.0.0
     */
    public long getChunkSeed(final int chunkX, final int chunkZ) {
        final long seed = world().getSeed();
        final Random rand = new Random(seed);
        return (chunkX * (rand.nextLong() / 2L * 2L + 1L)) + (chunkZ * (rand.nextLong() / 2L * 2L + 1L)) ^ seed;
    }

    /**
     * Gets a cached instance of {@link SimplexNoise} for use in world gen. If the passed index is out of range, then the instance at index 0 is returned.
     *
     * @param index The index of the cached noise instance to get
     * @return An instance of OpenSimplex
     * @since 1.0.0
     */
    public SimplexNoise simplexInstance(int index) {
        if (index >= this.simplexNoiseInstances.length) {
            index = 0;
        }
        return this.simplexNoiseInstances[index];
    }

    /**
     * Gets a cached instance of {@link CellularNoise} (Voronoi) for use in world gen. If the passed index is out of range, then the instance at index 0 is returned.
     *
     * @param index The index of the cached noise instance to get
     * @return An instance of CellularNoise
     * @since 1.0.0
     */
    public CellularNoise cellularInstance(int index) {
        if (index >= this.cellularNoiseInstances.length) {
            index = 0;
        }
        return this.cellularNoiseInstances[index];
    }

    /**
     * Gets the large bend size value of rivers for this world.
     *
     * @return the river large bend size
     * @since 1.0.0
     */
    public double getRiverLargeBendSize() {
        return RIVER_LARGE_BEND_SIZE_BASE * generatorSettings.riverBendMult;
    }

    /**
     * Gets the small bend size value of rivers for this world.
     *
     * @return the river small bend size
     * @since 1.0.0
     */
    public double getRiverSmallBendSize() {
        return RIVER_SMALL_BEND_SIZE_BASE * generatorSettings.riverBendMult;
    }

    /**
     * Gets the river separation for this world.
     *
     * @return the river separation
     * @since 1.0.0
     */
    public double getRiverSeparation() {
        return RIVER_SEPARATION_BASE / generatorSettings.riverFrequency;
    }

    /**
     * Gets the river valley level for this world.
     *
     * @return the river valley level
     * @since 1.0.0
     */
    public double getRiverValleyLevel() {
        return RIVER_VALLEY_LEVEL_BASE * generatorSettings.riverSizeMult * generatorSettings.riverFrequency;
    }

    public float getLakeFrequency() {
        return LAKE_FREQUENCY_BASE * generatorSettings.RTGlakeFreqMult;
    }

    public float getLakeShoreLevel() {
        return LAKE_SHORE_LEVEL_BASE * generatorSettings.RTGlakeFreqMult * generatorSettings.RTGlakeSizeMult;
    }

    public float getLakeDepressionLevel() {
        return LAKE_DEPRESSION_LEVEL * generatorSettings.RTGlakeFreqMult * generatorSettings.RTGlakeSizeMult;
    }

    public float getLakeBendSizeLarge() {
        return LAKE_BEND_SIZE_LARGE * generatorSettings.RTGlakeShoreBend;
    }

    public float getLakeBendSizeMedium() {
        return LAKE_BEND_SIZE_MEDIUM * generatorSettings.RTGlakeShoreBend;
    }

    public float getLakeBendSizeSmall() {
        return LAKE_BEND_SIZE_SMALL * generatorSettings.RTGlakeShoreBend;
    }

    @SubscribeEvent
    public static void onWorldLoad(WorldEvent.Load event) {

        World world = event.getWorld();
        if (!world.isRemote) {
            Logger.debug("WorldEvent.Load: WorldType: {}, DimID: {}, DimType: {}, BiomeProvider: {}, IChunkGenerator: {}",
                world.getWorldType().getClass().getSimpleName(),
                world.provider.getDimension(),
                world.provider.getDimensionType(),
                world.provider.getBiomeProvider().getClass().getName(),
                ((ChunkProviderServer)world.getChunkProvider()).chunkGenerator.getClass().getName()
            );
            if (world.provider.getDimension() == 0) {
                Logger.info("World Seed: " + world.getSeed());
            }
        }
    }

    @SubscribeEvent
    public static void onWorldUnload(WorldEvent.Unload event) {

        final World world = event.getWorld();
        if (!world.isRemote) {
            Logger.debug("WorldEvent.Unload: WorldType: {}, DimID: {}, DimType: {}, BiomeProvider: {}, IChunkGenerator: {}",
                world.getWorldType().getClass().getSimpleName(),
                world.provider.getDimension(),
                world.provider.getDimensionType(),
                world.provider.getBiomeProvider().getClass().getName(),
                ((ChunkProviderServer)world.getChunkProvider()).chunkGenerator.getClass().getName()
            );
        }

        // Cached instances of RTGWorld need to be removed because they contain a strong reference to the World object, which should be GC'd.
        INSTANCE_CACHE.remove(world);
    }
}
