package rtg.world.gen.surface.vanilla;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.ChunkPrimer;
import rtg.api.biome.BiomeConfig;
import rtg.util.noise.CellNoise;
import rtg.util.math.CliffCalculator;
import rtg.util.noise.OpenSimplexNoise;
import rtg.world.gen.surface.SurfaceBase;

import java.util.Random;

public class SurfaceVanillaMegaTaiga extends SurfaceBase {

    public SurfaceVanillaMegaTaiga(BiomeConfig config, IBlockState top, IBlockState fill) {

        super(config, top, fill);
    }

    @Override
    public void paintTerrain(ChunkPrimer primer, int i, int j, int x, int y, int depth, World world, Random rand,
                             OpenSimplexNoise simplex, CellNoise cell, float[] noise, float river, BiomeGenBase[] base) {

        float p = simplex.noise2(i / 8f, j / 8f) * 0.5f;
        float c = CliffCalculator.calc(x, y, noise);
        int cliff = 0;
        boolean gravel = false;

        Block b;
        for (int k = 255; k > -1; k--) {
            b = primer.getBlockState(x, k, y).getBlock();
            if (b == Blocks.air) {
                depth = -1;
            } else if (b == Blocks.stone) {
                depth++;

                if (depth == 0) {
                    if (k < 63) {
                        gravel = true;
                    }

                    if (c > 0.45f && c > 1.5f - ((k - 60f) / 65f) + p) {
                        cliff = 1;
                    }
                    if (c > 1.5f) {
                        cliff = 2;
                    }
                    if (k > 110 + (p * 4) && c < 0.3f + ((k - 100f) / 50f) + p) {
                        cliff = 3;
                    }

                    if (cliff == 1) {
                        if (rand.nextInt(3) == 0) {

                            primer.setBlockState(x, k, y, hcCobble(world, i, j, x, y, k));
                        } else {

                            primer.setBlockState(x, k, y, hcStone(world, i, j, x, y, k));
                        }
                    } else if (cliff == 2) {
                        primer.setBlockState(x, k, y, getShadowStoneBlock(world, i, j, x, y, k));
                    } else if (cliff == 3) {
                        primer.setBlockState(x, k, y, Blocks.snow.getDefaultState());
                    } else if (simplex.noise2(i / 50f, j / 50f) + p * 0.6f > 0.24f) {
                        primer.setBlockState(x, k, y, Blocks.dirt.getStateFromMeta(2));
                    } else if (k < 63) {
                        primer.setBlockState(x, k, y, Blocks.gravel.getDefaultState());
                        gravel = true;
                    } else {
                        primer.setBlockState(x, k, y, Blocks.grass.getDefaultState());
                    }
                } else if (depth < 6) {
                    if (cliff == 1) {
                        primer.setBlockState(x, k, y, hcStone(world, i, j, x, y, k));
                    } else if (cliff == 2) {
                        primer.setBlockState(x, k, y, getShadowStoneBlock(world, i, j, x, y, k));
                    } else if (cliff == 3) {
                        primer.setBlockState(x, k, y, Blocks.snow.getDefaultState());
                    } else if (gravel) {
                        primer.setBlockState(x, k, y, Blocks.gravel.getDefaultState());
                    } else {
                        primer.setBlockState(x, k, y, Blocks.dirt.getDefaultState());
                    }
                }
            }
        }
    }
}
