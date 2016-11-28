package rtg.world.gen.surface;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;

import rtg.config.BiomeConfig;
import rtg.util.CellNoise;
import rtg.api.util.noise.OpenSimplexNoise;

public class SurfaceMountainPolar extends SurfaceBase {

    private float min;

    public SurfaceMountainPolar(BiomeConfig config, IBlockState top, IBlockState fill, float minCliff) {

        super(config, top, fill);
        min = minCliff;
    }

    @Override
    public void paintTerrain(ChunkPrimer primer, int i, int j, int x, int y, int depth, World world, Random rand, OpenSimplexNoise simplex, CellNoise cell, float[] noise, float river, Biome[] base) {

    }
}