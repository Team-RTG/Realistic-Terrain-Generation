package teamrtg.rtg.world.biome.surface;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.ChunkPrimer;
import teamrtg.rtg.util.math.SnowHeightCalculator;
import teamrtg.rtg.util.noise.CellNoise;
import teamrtg.rtg.util.noise.OpenSimplexNoise;
import teamrtg.rtg.api.biome.RealisticBiomeBase;

import java.util.Random;

public class SurfacePolar extends SurfaceBase {
    public SurfacePolar(RealisticBiomeBase biome) {
        super(biome);
    }

    @Override
    public void paintTerrain(ChunkPrimer primer, int i, int j, int x, int y, int depth, World world, Random rand, OpenSimplexNoise simplex, CellNoise cell, float[] noise, float river, BiomeGenBase[] base) {
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
            if (b == Blocks.air) {
                depth = -1;
            } else if (b == Blocks.stone) {
                depth++;

                if (riverPaint) {
                    if (grass && depth < 4) {
                        primer.setBlockState(x, k, y, Blocks.dirt.getDefaultState());
                    } else if (depth == 0) {
                        if (rand.nextInt(2) == 0) {

                            primer.setBlockState(x, k, y, hcStone());
                        } else {

                            primer.setBlockState(x, k, y, hcCobble());
                        }
                    }
                } else if (depth > -1 && depth < 9) {
                    primer.setBlockState(x, k, y, Blocks.snow.getDefaultState());
                    if (depth == 0 && k > 61 && k < 254) {
                        SnowHeightCalculator.calc(x, y, k, primer, noise);
                    }
                }
            } else if (!water && b == Blocks.water) {
                primer.setBlockState(x, k, y, Blocks.ice.getDefaultState());
                water = true;
            }
        }
    }
}
