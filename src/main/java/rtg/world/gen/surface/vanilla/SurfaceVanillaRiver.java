package rtg.world.gen.surface.vanilla;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.ChunkPrimer;
import rtg.api.biome.BiomeConfig;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.surface.SurfaceBase;

import java.util.Random;

public class SurfaceVanillaRiver extends SurfaceBase {
    public SurfaceVanillaRiver(BiomeConfig config) {
        super(config, Blocks.grass, (byte) 0, Blocks.dirt, (byte) 0);
    }

    @Override
    public void paintTerrain(ChunkPrimer primer, int i, int j, int x, int y, int depth, World world, Random rand, OpenSimplexNoise simplex, CellNoise cell, float[] noise, float river, BiomeGenBase[] base) {
        if (river > 0.05f && river + (simplex.noise2(i / 10f, j / 10f) * 0.15f) > 0.8f) {
            Block b;
            for (int k = 255; k > -1; k--) {
                b = primer.getBlockState(x, 256 + k, y).getBlock();
                if (b == Blocks.air) {
                    depth = -1;
                } else if (b != Blocks.water) {
                    depth++;

                    if (depth == 0 && k > 61) {
                        primer.setBlockState(x, 256 + k, y, Blocks.grass.getDefaultState());
                    } else if (depth < 4) {
                        primer.setBlockState(x, 256 + k, y, Blocks.dirt.getDefaultState());
                    } else if (depth > 4) {
                        return;
                    }
                }
            }
        }
    }
}
