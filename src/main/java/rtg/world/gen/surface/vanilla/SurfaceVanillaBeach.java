package rtg.world.gen.surface.vanilla;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;

import rtg.api.biome.BiomeConfig;
import rtg.util.CellNoise;
import rtg.util.CliffCalculator;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.surface.SurfaceBase;

public class SurfaceVanillaBeach extends SurfaceBase {

    private IBlockState cliffBlock1;
    private IBlockState cliffBlock2;
    private byte sandMetadata;
    private int cliffType;

    public SurfaceVanillaBeach(BiomeConfig config, IBlockState top, IBlockState filler, IBlockState cliff1, IBlockState cliff2, byte metadata, int cliff) {

        super(config, Blocks.DIRT, Blocks.DIRT);

        cliffBlock1 = Blocks.DIRT.getDefaultState();
        cliffBlock2 = Blocks.STONE.getDefaultState();
        sandMetadata = metadata;
        cliffType = cliff;
    }

    @SuppressWarnings("unused")
    @Override
    public void paintTerrain(ChunkPrimer primer, int i, int j, int x, int y, int depth, World world, Random rand, OpenSimplexNoise simplex, CellNoise cell, float[] noise, float river, Biome[] base) {

        float c = CliffCalculator.calc(x, y, noise);
        boolean cliff = c > 1.3f ? true : false;
        boolean dirt = false;

        for (int k = 255; k > -1; k--) {
            Block b = primer.getBlockState((y * 16 + x) * 256 + k).getBlock();
            if (b == Blocks.AIR) {
                depth = -1;
            }
            else if (b == Blocks.STONE) {
                depth++;

                if (cliff) {
                    if (cliffType == 1) {
                        if (depth < 6) {
                            primer.setBlockState((y * 16 + x) * 256 + k, cliffBlock1.getBlock().getStateFromMeta(14));
                        }
                    }
                    else {
                        if (depth > -1 && depth < 2) {
                            primer.setBlockState((y * 16 + x) * 256 + k, rand.nextInt(3) == 0 ? cliffBlock2 : cliffBlock1);
                        }
                        else if (depth < 10) {
                            primer.setBlockState((y * 16 + x) * 256 + k, cliffBlock1);
                        }
                    }
                }
                else if (depth < 6) {
                    if (depth == 0 && k > 61 && k < 64) {
                        //if(simplex.noise2(i / 12f, j / 12f) > -0.3f + ((k - 61f) / 15f))
                        if (false) {
                            dirt = true;
                            primer.setBlockState((y * 16 + x) * 256 + k, topBlock);
                        }
                        else {
                            primer.setBlockState((y * 16 + x) * 256 + k, Blocks.SAND.getStateFromMeta(sandMetadata));
                        }
                    }
                    else if (depth < 4) {
                        if (dirt) {
                            primer.setBlockState((y * 16 + x) * 256 + k, fillerBlock);
                        }
                        else {
                            if (k > 61 && k < 69) {
                                primer.setBlockState((y * 16 + x) * 256 + k, Blocks.SAND.getStateFromMeta(sandMetadata));
                            }
                        }
                    }
                    else if (!dirt) {
                        if (k > 56 && k < 68) { // one lower for under sand and 4 deeper
                            primer.setBlockState((y * 16 + x) * 256 + k, Blocks.SANDSTONE.getDefaultState());
                        }
                        else {
                            primer.setBlockState((y * 16 + x) * 256 + k, Blocks.STONE.getDefaultState());
                        }
                    }
                }
            }
        }
    }
}
