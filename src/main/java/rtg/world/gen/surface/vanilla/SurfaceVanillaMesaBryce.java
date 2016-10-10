package rtg.world.gen.surface.vanilla;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;
import rtg.api.biome.BiomeConfig;
import rtg.util.*;
import rtg.world.gen.surface.SurfaceBase;

import java.util.Random;

public class SurfaceVanillaMesaBryce extends SurfaceBase {

    private int grassRaise = 0;

    public SurfaceVanillaMesaBryce(BiomeConfig config, IBlockState top, IBlockState fill, int grassHeight) {

        super(config, top, fill);
        grassRaise = grassHeight;
    }

    @Override
    public void paintTerrain(ChunkPrimer primer, int i, int j, int x, int z, int depth, World world, Random rand, OpenSimplexNoise simplex, CellNoise cell, float[] noise, float river, Biome[] base) {

        float c = CliffCalculator.calc(x, z, noise);
        boolean cliff = c > 1.3f;

        for (int k = 255; k > -1; k--) {
            Block b = primer.getBlockState(x, k, z).getBlock();
            if (b == Blocks.AIR) {
                depth = -1;
            }
            else if (b == Blocks.STONE) {
                depth++;

                if (depth > -1 && depth < 12) {
                    if (cliff) {
                        primer.setBlockState(x, k, z, CanyonColour.MESA_BRYCE.getBlockForHeight(i, k, j));
                    }
                    else {
                        if (depth > 4) {
                            primer.setBlockState(x, k, z, CanyonColour.MESA_BRYCE.getBlockForHeight(i, k, j));
                        }
                        else if (k > 74 + grassRaise) {
                            if (rand.nextInt(5) == 0) {
                                primer.setBlockState(x, k, z, BlockUtil.getStateDirt(1));
                            }
                            else {
                                if (depth == 0) {
                                    primer.setBlockState(x, k, z, topBlock);
                                }
                                else {
                                    primer.setBlockState(x, k, z, fillerBlock);
                                }
                            }
                        }
                        else if (k < 62) {
                            primer.setBlockState(x, k, z, Blocks.DIRT.getDefaultState());
                        }
                        else if (k < 62 + grassRaise) {
                            if (depth == 0) {
                                primer.setBlockState(x, k, z, Blocks.GRASS.getDefaultState());
                            }
                            else {
                                primer.setBlockState(x, k, z, Blocks.DIRT.getDefaultState());
                            }
                        }
                        else if (k < 75 + grassRaise) {
                            if (depth == 0) {
                                int r = (int) ((k - (62 + grassRaise)) / 2f);
                                if (rand.nextInt(r + 1) == 0) {
                                    primer.setBlockState(x, k, z, Blocks.GRASS.getDefaultState());
                                }
                                else if (rand.nextInt((int) (r / 2f) + 1) == 0) {
                                    primer.setBlockState(x, k, z, BlockUtil.getStateDirt(1));
                                }
                                else {
                                    primer.setBlockState(x, k, z, topBlock);
                                }
                            }
                            else {
                                primer.setBlockState(x, k, z, fillerBlock);
                            }
                        }
                        else {
                            if (depth == 0) {
                                primer.setBlockState(x, k, z, topBlock);
                            }
                            else {
                                primer.setBlockState(x, k, z, fillerBlock);
                            }
                        }
                    }
                }
                else if (k > 63) {
                    primer.setBlockState(x, k, z, CanyonColour.MESA_BRYCE.getBlockForHeight(i, k, j));
                }
            }
        }
    }
}
