package rtg.world.gen.surface;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;

import rtg.config.BiomeConfig;
import rtg.util.CellNoise;
import rtg.api.util.noise.OpenSimplexNoise;
import rtg.util.SnowHeightCalculator;

public class SurfacePolar extends SurfaceBase {

    public SurfacePolar(BiomeConfig config, IBlockState top, IBlockState fill) {

        super(config, top, fill);
    }

    @Override
    public void paintTerrain(ChunkPrimer primer, int i, int j, int x, int y, int depth, World world, Random rand, OpenSimplexNoise simplex, CellNoise cell, float[] noise, float river, Biome[] base) {

        boolean water = false;
        boolean riverPaint = false;
        boolean grass = false;

        if (river > 0.05f && river + (simplex.noise2(i / 10f, j / 10f) * 0.1f) > 0.86f) {
            riverPaint = true;

            if (simplex.noise2(i / 12f, j / 12f) > 0.25f) {
                grass = true;
            }
        }

        Block b;
        for (int k = 255; k > -1; k--) {
            b = primer.getBlockState(x, k, y).getBlock();
            if (b == Blocks.AIR) {
                depth = -1;
            }
            else if (b == Blocks.STONE) {
                depth++;

                if (riverPaint) {
                    if (grass && depth < 4) {
                        primer.setBlockState(x, k, y, fillerBlock);
                    }
                    else if (depth == 0) {
                        if (rand.nextInt(2) == 0) {

                            primer.setBlockState(x, k, y, hcStone(world, i, j, x, y, k));
                        }
                        else {

                            primer.setBlockState(x, k, y, hcCobble(world, i, j, x, y, k));
                        }
                    }
                }
                else if (depth > -1 && depth < 9) {
                    primer.setBlockState(x, k, y, Blocks.SNOW.getDefaultState());
                    if (depth == 0 && k > 61 && k < 254) {
                        SnowHeightCalculator.calc(x, k, y, primer, noise);
                    }
                }
            }
            else if (!water && b == Blocks.WATER) {
                primer.setBlockState(x, k, y, Blocks.ICE.getDefaultState());
                water = true;
            }
        }
    }
}
