package rtg.world.gen.surface;

import net.minecraft.block.state.IBlockState;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.ChunkPrimer;
import rtg.api.config.BiomeConfig;
import rtg.util.noise.CellNoise;
import rtg.util.noise.OpenSimplexNoise;

import java.util.Random;

public class SurfaceMountainPolar extends SurfaceBase {
    private boolean beach;
    private IBlockState beachBlock;
    private float min;

    public SurfaceMountainPolar(RealisticBiomeBase biome, boolean genBeach, IBlockState genBeachBlock, float minCliff) {
        super(biome);
        beach = genBeach;
        beachBlock = genBeachBlock;
        min = minCliff;
    }

    @Override
    public void paintTerrain(ChunkPrimer primer, int i, int j, int x, int y, int depth, World world, Random rand, OpenSimplexNoise simplex, CellNoise cell, float[] noise, float river, BiomeGenBase[] base) {

    }
}