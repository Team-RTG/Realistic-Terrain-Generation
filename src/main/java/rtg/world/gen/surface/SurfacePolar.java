package rtg.world.gen.surface;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;

import rtg.api.util.noise.OpenSimplexNoise;
import rtg.api.world.RTGWorld;
import rtg.config.BiomeConfig;
import rtg.util.SnowHeightCalculator;

public class SurfacePolar extends SurfaceBase {

    public SurfacePolar(BiomeConfig config, IBlockState top, IBlockState fill) {

        super(config, top, fill);
    }

    @Override
    public void paintTerrain(ChunkPrimer primer, int i, int j, int x, int z, int depth, RTGWorld rtgWorld, float[] noise, float river, Biome[] base) {

        Random rand = rtgWorld.rand;
        OpenSimplexNoise simplex = rtgWorld.simplex;
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
            b = primer.getBlockState(x, k, z).getBlock();
            if (b == Blocks.AIR) {
                depth = -1;
            }
            else if (b == Blocks.STONE) {
                depth++;

                if (riverPaint) {
                    if (grass && depth < 4) {
                        primer.setBlockState(x, k, z, fillerBlock);
                    }
                    else if (depth == 0) {
                        if (rand.nextInt(2) == 0) {

                            primer.setBlockState(x, k, z, hcStone(rtgWorld, i, j, x, z, k));
                        }
                        else {

                            primer.setBlockState(x, k, z, hcCobble(rtgWorld, i, j, x, z, k));
                        }
                    }
                }
                else if (depth > -1 && depth < 9) {
                    primer.setBlockState(x, k, z, Blocks.SNOW.getDefaultState());
                    if (depth == 0 && k > 61 && k < 254) {
                        SnowHeightCalculator.calc(x, k, z, primer, noise);
                    }
                }
            }
            else if (!water && b == Blocks.WATER) {
                primer.setBlockState(x, k, z, Blocks.ICE.getDefaultState());
                water = true;
            }
        }
    }
}
