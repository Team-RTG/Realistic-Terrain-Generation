package rtg.world.gen.surface.vanilla;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.ChunkPrimer;

import rtg.api.biome.BiomeConfig;
import rtg.util.CanyonColour;
import rtg.util.CellNoise;
import rtg.util.CliffCalculator;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.surface.SurfaceBase;

public class SurfaceVanillaSavannaPlateau extends SurfaceBase {

    private int grassRaise = 0;

    public SurfaceVanillaSavannaPlateau(BiomeConfig config, IBlockState top, IBlockState fill, int grassHeight) {

        super(config, top, fill);
        grassRaise = grassHeight;
    }

    @Override
    public void paintTerrain(ChunkPrimer primer, int i, int j, int x, int y, int depth, World world, Random rand, OpenSimplexNoise simplex, CellNoise cell, float[] noise, float river, BiomeGenBase[] base) {

        float c = CliffCalculator.calc(x, y, noise);
        boolean cliff = c > 1.3f;

        for (int k = 255; k > -1; k--) {
            Block b = primer.getBlockState((y * 16 + x) * 256 + k).getBlock();
            if (b == Blocks.air) {
                depth = -1;
            }
            else if (b == Blocks.stone) {
                depth++;

                if (depth > -1 && depth < 12) {
                    if (cliff) {
                        primer.setBlockState((y * 16 + x) * 256 + k, CanyonColour.SAVANNA.getForHeight(i, k, j));
                    }
                    else {
                        if (depth > 4) {
                            primer.setBlockState((y * 16 + x) * 256 + k, CanyonColour.SAVANNA.getForHeight(i, k, j));
                        }
                        else if (k > 74 + grassRaise) {
                            if (rand.nextInt(5) == 0) {
                                primer.setBlockState((y * 16 + x) * 256 + k, Blocks.dirt.getStateFromMeta(1));
                            }
                            else {
                                if (depth == 0) {
                                    primer.setBlockState((y * 16 + x) * 256 + k, topBlock);
                                }
                                else {
                                    primer.setBlockState((y * 16 + x) * 256 + k, fillerBlock);
                                }
                            }
                        }
                        else if (k < 62) {
                            primer.setBlockState((y * 16 + x) * 256 + k, Blocks.dirt.getDefaultState());
                        }
                        else if (k < 62 + grassRaise) {
                            if (depth == 0) {
                                primer.setBlockState((y * 16 + x) * 256 + k, Blocks.grass.getDefaultState());
                            }
                            else {
                                primer.setBlockState((y * 16 + x) * 256 + k, Blocks.dirt.getDefaultState());
                            }
                        }
                        else if (k < 75 + grassRaise) {
                            if (depth == 0) {
                                int r = (int) ((k - (62 + grassRaise)) / 2f);
                                if (rand.nextInt(r + 1) == 0) {
                                    primer.setBlockState((y * 16 + x) * 256 + k, Blocks.grass.getDefaultState());
                                }
                                else if (rand.nextInt((int) (r / 2f) + 1) == 0) {
                                    primer.setBlockState((y * 16 + x) * 256 + k, Blocks.dirt.getStateFromMeta(1));
                                }
                                else {
                                    primer.setBlockState((y * 16 + x) * 256 + k, topBlock);
                                }
                            }
                            else {
                                primer.setBlockState((y * 16 + x) * 256 + k, fillerBlock);
                            }
                        }
                        else {
                            if (depth == 0) {
                                primer.setBlockState((y * 16 + x) * 256 + k, topBlock);
                            }
                            else {
                                primer.setBlockState((y * 16 + x) * 256 + k, fillerBlock);
                            }
                        }
                    }
                }
                else if (k > 63) {
                    primer.setBlockState((y * 16 + x) * 256 + k, CanyonColour.SAVANNA.getForHeight(i, k, j));
                }
            }
        }
    }
}