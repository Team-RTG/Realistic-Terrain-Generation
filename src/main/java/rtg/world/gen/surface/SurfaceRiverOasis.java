package rtg.world.gen.surface;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;

import rtg.config.BiomeConfig;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;

public class SurfaceRiverOasis extends SurfaceBase {

    public SurfaceRiverOasis(BiomeConfig config) {

        super(config, Blocks.GRASS, (byte) 0, Blocks.DIRT, (byte) 0);
    }

    @Override
    public void paintTerrain(ChunkPrimer primer, int i, int j, int x, int y, int depth, World world, Random rand, OpenSimplexNoise simplex, CellNoise cell, float[] noise, float river, Biome[] base) {

        if (river > 0.05f && river + (simplex.noise2(i / 10f, j / 10f) * 0.15f) > 0.8f) {
            Block b;
            for (int k = 255; k > -1; k--) {
                b = primer.getBlockState(x, k, y).getBlock();
                if (b == Blocks.AIR) {
                    depth = -1;
                }
                else if (b != Blocks.WATER) {
                    depth++;

                    if (depth == 0 && k > 61) {
                        primer.setBlockState(x, k, y, Blocks.GRASS.getDefaultState());
                    }
                    else if (depth < 4) {
                        primer.setBlockState(x, k, y, Blocks.DIRT.getDefaultState());
                    }
                    else if (depth > 4) {
                        return;
                    }
                }
            }
        }
    }
}
