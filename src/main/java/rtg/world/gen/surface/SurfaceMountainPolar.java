package rtg.world.gen.surface;

import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.ChunkPrimer;
import rtg.util.noise.CellNoise;
import rtg.util.noise.OpenSimplexNoise;
import rtg.world.biome.realistic.RealisticBiomeBase;

import java.util.Random;

public class SurfaceMountainPolar extends SurfaceBase {
    private boolean beach;
    private float min;

    public SurfaceMountainPolar(RealisticBiomeBase biome, boolean genBeach, float minCliff) {
        super(biome);
        beach = genBeach;

        min = minCliff;
    }

    @Override
    public void paintTerrain(ChunkPrimer primer, int i, int j, int x, int y, int depth, World world, Random rand, OpenSimplexNoise simplex, CellNoise cell, float[] noise, float river, BiomeGenBase[] base) {

    }
}