package rtg.api.world;

import java.util.Random;

import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;

import rtg.api.util.TimedHashSet;
import rtg.api.util.noise.SimplexNoise;
import rtg.api.util.noise.CellularNoise;
import rtg.api.world.biome.OrganicBiomeGenerator;

/**
 * Created by WhichOnesPink on 26/06/2017.
 */
@Deprecated // To be removed.
public interface IRTGWorld {
    World world();
    long seed();
    Random rand();
    Random getChunkRand(int x, int z);
    SimplexNoise simplexInstance(int index);
    CellularNoise cellularInstance(int index);
    TimedHashSet<ChunkPos> decoratedChunks();
    OrganicBiomeGenerator organicBiomeGenerator();
}
