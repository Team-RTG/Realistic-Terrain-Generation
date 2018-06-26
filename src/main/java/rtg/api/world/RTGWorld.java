package rtg.api.world;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;

import rtg.api.util.PlateauUtil;
import rtg.api.util.TimedHashSet;
import rtg.api.util.noise.CellularNoise;
import rtg.api.util.noise.SimplexNoise;
import rtg.api.util.noise.OpenSimplexNoise;
import rtg.api.util.noise.SpacedCellularNoise;
import rtg.api.world.gen.RTGChunkGenSettings;

/**
 *  A wrapper class for a {@link World} that contains collections of noise generator instances used in terrain gen.
 *
 * @author topisani (original)
 * @author srs_bsns
 *
 * @since 1.0.0
 */
public final class RTGWorld
{
    private static final Map<World, RTGWorld> INSTANCE_CACHE = new HashMap<>();

    private static final int SIMPLEX_INSTANCE_COUNT = 10;
    private static final int CELLULAR_INSTANCE_COUNT = 5;

    private final World                  world;
    private final RTGChunkGenSettings    generatorSettings;
    private final SimplexNoise[]         simplexNoiseInstances  = new SimplexNoise[SIMPLEX_INSTANCE_COUNT];
    private final CellularNoise[]        cellularNoiseInstances = new CellularNoise[CELLULAR_INSTANCE_COUNT];
    private final TimedHashSet<ChunkPos> decoratedChunks        = new TimedHashSet<>(5000);

    private RTGWorld(World world) {

        this.world = world;
        this.generatorSettings = RTGChunkGenSettings.Factory.jsonToFactory(world.getWorldInfo().getGeneratorOptions()).build();

//TODO: [1.12] KdotJPG's implemetation uses D. Knuth's LCG internally in a loop to generate the permutation tables, perhaps we should use a different LCG
// to create new instances of OpenSimplex and Cell noise, for better randomisation, instead of just adding 1 to the seed value value.
// Ultimately, setting up proper octaves of SimplexNoise would be most beneficial, but the amount of time and effort that it would take to update and tweak
// all of the terrain processes to use those octaves, instead of the currently employed inline division, would be most impratical at this point.
        for (int i = 0; i < SIMPLEX_INSTANCE_COUNT; i++) {
            this.simplexNoiseInstances[i] = new OpenSimplexNoise(this.seed() + i);
        }
        for (int i = 0; i < CELLULAR_INSTANCE_COUNT; i++) {
            this.cellularNoiseInstances[i] = new SpacedCellularNoise(this.seed() + i);
        }

// TODO: [1.12] To be moved to the common proxy initialiser. See PlateauUtil
        PlateauUtil.init(this.seed());
    }

    /**
     *  Gets an instance of RTGWorld of a {@link World} from the cache. If one does not exist, create it and store it first.
     *
     * @param world the world from which to get an instance of RTGWorld for
     *
     * @return a cached or new instance of RTGWorld
     *
     * @since 1.0.0
     */
    public static RTGWorld getInstance(World world) {
        if (!INSTANCE_CACHE.containsKey(world)) { INSTANCE_CACHE.put(world, new RTGWorld(world)); }
        return INSTANCE_CACHE.get(world);
    }

    /**
     *  Removes an instance of RTGWorld from the cache. This should be done when worlds are unloaded
     *
     * @param world the world to remove an instance of RTGWorld for
     *
     * @return true if an instance was removed, false otherwise
     *
     * @since 1.0.0
     */
    public static boolean removeInstance(World world) {
        return (INSTANCE_CACHE.remove(world) != null);
    }

    /**
     *  Gets the {@link World} object for this wrapper.
     *
     * @return The world object for this wrapper
     *
     * @since 1.0.0
     */
    public World world() {
        return this.world;
    }

    /**
     *  Gets the stored {@link RTGChunkGenSettings} instance for the world of this wrapper.
     *
     * @return The stored RTGChunkGenSettings object
     *
     * @since 1.0.0
     */
    public RTGChunkGenSettings getGeneratorSettings() {
        return this.generatorSettings;
    }

    /**
     *  Alias to get the seed value of the {@link World} for this wrapper.
     *
     * @return The seed value
     *
     * @since 1.0.0
     */
    public long seed() {
        return this.world().getSeed();
    }

    /**
     *  Alias to get the instance of Random of the {@link World} object for this wrapper.
     *
     * @return The world's Random instance
     *
     * @since 1.0.0
     */
    public Random rand() {
        return this.world().rand;
    }

    /**
     *  Calculates the seed for a Chunk based on the coord parameters.
     *
     * @param chunkX The chunk X coord
     * @param chunkZ The chunk Z coord
     *
     * @return the chunk seed value
     *
     * @since 1.0.0
     */
    public long getChunkSeed(final int chunkX, final int chunkZ) {
        this.rand().setSeed(this.seed());
        return (chunkX * (this.rand().nextLong() / 2L * 2L + 1L)) + (chunkZ * (this.rand().nextLong() / 2L * 2L + 1L)) ^ this.seed();
    }

    /**
     *  Gets a cached instance of {@link SimplexNoise} for use in world gen. If the passed index is out of range, then the instance at index 0 is returned.
     *
     * @param index The index of the cached noise instance to get
     *
     * @return An instance of OpenSimplex
     *
     * @since 1.0.0
     */
    public SimplexNoise simplexInstance(int index) {
        if (index >= this.simplexNoiseInstances.length) { index = 0; }
        return this.simplexNoiseInstances[index];
    }

    /**
     *  Gets a cached instance of {@link CellularNoise} (Voronoi) for use in world gen. If the passed index is out of range, then the instance at index 0 is returned.
     *
     * @param index The index of the cached noise instance to get
     *
     * @return An instance of CellularNoise
     *
     * @since 1.0.0
     */
    public CellularNoise cellularInstance(int index) {
        if (index >= this.cellularNoiseInstances.length) { index = 0; }
        return this.cellularNoiseInstances[index];
    }

    @Deprecated // To be removed when the decoration system is rewritten.
    public TimedHashSet<ChunkPos> decoratedChunks() {
        return this.decoratedChunks;
    }
}
