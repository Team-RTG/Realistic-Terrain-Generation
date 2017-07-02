package rtg.api.world;

import java.util.Random;

import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeMesa;

import rtg.api.util.TimedHashSet;
import rtg.api.util.noise.CellNoise;
import rtg.api.util.noise.OpenSimplexNoise;
import rtg.api.util.noise.SimplexOctave;
import rtg.api.world.biome.IBiomeProviderRTG;
import rtg.api.world.biome.OrganicBiomeGenerator;

/**
 * Created by WhichOnesPink on 26/06/2017.
 */
public interface IRTGWorld {
    World world();
    OpenSimplexNoise simplex();
    CellNoise cell();
    CellNoise voronoi();
    Random rand();
    SimplexOctave.Disk surfaceJitter();
    TimedHashSet<ChunkPos> decoratedChunks();
    BiomeMesa mesaBiome();
    OrganicBiomeGenerator organicBiomeGenerator();
    int getRepairedBiomeAt(IBiomeProviderRTG cmr, int cx, int cz);
}
