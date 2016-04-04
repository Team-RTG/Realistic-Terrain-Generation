package rtg.world.gen.surface.vanilla;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.ChunkPrimer;
import rtg.util.math.CliffCalculator;
import rtg.util.noise.CellNoise;
import rtg.util.noise.OpenSimplexNoise;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.gen.surface.SurfaceBase;

import java.util.Random;

public class SurfaceVanillaJungle extends SurfaceBase {

    private boolean beach;
    private float min;

    private float sCliff = 1.5f;
    private float sHeight = 60f;
    private float sStrength = 65f;
    private float cCliff = 1.5f;

    private float mixHeight;

    public SurfaceVanillaJungle(RealisticBiomeBase biome, boolean genBeach, float minCliff, float stoneCliff,
                                float stoneHeight, float stoneStrength, float clayCliff, float mixSize) {
        super(biome);
        beach = genBeach;
        min = minCliff;

        sCliff = stoneCliff;
        sHeight = stoneHeight;
        sStrength = stoneStrength;
        cCliff = clayCliff;

        mixHeight = mixSize;
    }

    @Override
    public void paintTerrain(ChunkPrimer primer, int i, int j, int x, int y, int depth, World world, Random rand,
                             OpenSimplexNoise simplex, CellNoise cell, float[] noise, float river, BiomeGenBase[] base) {

        float c = CliffCalculator.calc(x, y, noise);
        int cliff = 0;
        boolean gravel = false;
        boolean m = false;

        Block b;
        for (int k = 255; k > -1; k--) {
            b = primer.getBlockState(x, k, y).getBlock();
            if (b == Blocks.air) {
                depth = -1;
            } else if (b == Blocks.stone) {
                depth++;

                if (depth == 0) {
                    if (k < 63) {
                        if (beach) {
                            gravel = true;
                        }
                    }

                    float p = simplex.noise3(i / 8f, j / 8f, k / 8f) * 0.5f;
                    if (c > min && c > sCliff - ((k - sHeight) / sStrength) + p) {
                        cliff = 1;
                    }
                    if (c > cCliff) {
                        cliff = 2;
                    }

                    if (cliff == 1) {
                        if (rand.nextInt(3) == 0) {

                            primer.setBlockState(x, k, y, hcCobble());
                        } else {

                            primer.setBlockState(x, k, y, hcStone());
                        }
                    } else if (cliff == 2) {
                        primer.setBlockState(x, k, y, getShadowStoneBlock());
                    } else if (k < 63) {
                        if (beach) {
                            primer.setBlockState(x, k, y, biome.config.BEACH_BLOCK.get());
                            gravel = true;
                        } else if (k < 62) {
                            primer.setBlockState(x, k, y, biome.config.FILL_BLOCK.get());
                        } else {
                            primer.setBlockState(x, k, y, biome.config.TOP_BLOCK.get());
                        }
                    } else if (simplex.noise2(i / 12f, j / 12f) > mixHeight) {
                        primer.setBlockState(x, k, y, biome.config.MIX_BLOCK.get());
                        m = true;
                    } else {
                        primer.setBlockState(x, k, y, biome.config.TOP_BLOCK.get());
                    }
                } else if (depth < 6) {
                    if (cliff == 1) {
                        primer.setBlockState(x, k, y, hcStone());
                    } else if (cliff == 2) {
                        primer.setBlockState(x, k, y, getShadowStoneBlock());
                    } else if (gravel) {
                        primer.setBlockState(x, k, y, biome.config.BEACH_BLOCK.get());
                    } else if (m) {
                        primer.setBlockState(x, k, y, biome.config.MIX_BLOCK.get());
                    } else {
                        primer.setBlockState(x, k, y, biome.config.FILL_BLOCK.get());
                    }
                }
            }
        }
    }
}
