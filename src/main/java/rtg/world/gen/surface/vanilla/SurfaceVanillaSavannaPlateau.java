package rtg.world.gen.surface.vanilla;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;
import rtg.api.biome.BiomeConfig;
import rtg.config.rtg.ConfigRTG;
import rtg.util.*;
import rtg.world.gen.surface.SurfaceBase;

import java.util.Random;

public class SurfaceVanillaSavannaPlateau extends SurfaceBase {

    private int grassRaise = 0;

    public SurfaceVanillaSavannaPlateau(BiomeConfig config, IBlockState top, IBlockState fill, int grassHeight) {

        super(config, top, fill);
        grassRaise = grassHeight;
    }

    @Override
    public void paintTerrain(ChunkPrimer primer, int i, int j, int x, int z, int depth, World world, Random rand, OpenSimplexNoise simplex, CellNoise cell, float[] noise, float river, Biome[] base) {

        float c = CliffCalculator.calc(x, z, noise);
        boolean cliff = c > 1.3f;
        Block b;

        for(int k = 255; k > -1; k--)
        {
            b = primer.getBlockState(x, k, z).getBlock();
            if(b == Blocks.AIR)
            {
                depth = -1;
            }
            else if (b == Blocks.STONE) {
                depth++;

                if (cliff) {
                    if (!ConfigRTG.stoneSavannas) {
                        primer.setBlockState(x, k, z, CanyonColour.SAVANNA.getBlockForHeight(i, k, j));
                    }
                    else {
                        if (depth > -1 && depth < 2) {
                            if (rand.nextInt(3) == 0) {

                                primer.setBlockState(x, k, z, hcCobble(world, i, j, x, z, k));
                            }
                            else {

                                primer.setBlockState(x, k, z, hcStone(world, i, j, x, z, k));
                            }
                        }
                        else if (depth < 10) {
                            primer.setBlockState(x, k, z, hcStone(world, i, j, x, z, k));
                        }
                    }
                }
                else {

                    if (k > 74 + grassRaise)
                    {
                        if (depth == 0) {
                            if (rand.nextInt(5) == 0) {
                                primer.setBlockState(x, k, z, BlockUtil.getStateDirt(1));
                            }
                            else {
                                primer.setBlockState(x, k, z, topBlock);
                            }
                        }
                        else if (depth < 4) {
                            primer.setBlockState(x, k, z, fillerBlock);
                        }
                    }
                    else if (depth == 0 && k > 61) {
                        int r = (int)((k - (62 + grassRaise)) / 2f);
                        if(rand.nextInt(r + 2) == 0)
                        {
                            primer.setBlockState(x, k, z, Blocks.GRASS.getDefaultState());
                        }
                        else if(rand.nextInt((int)(r / 2f) + 2) == 0)
                        {
                            primer.setBlockState(x, k, z, BlockUtil.getStateDirt(1));
                        }
                        else
                        {
                            primer.setBlockState(x, k, z, topBlock);
                        }
                    }
                    else if (depth < 4) {
                        primer.setBlockState(x, k, z, fillerBlock);
                    }
                }
            }
        }
    }
}
