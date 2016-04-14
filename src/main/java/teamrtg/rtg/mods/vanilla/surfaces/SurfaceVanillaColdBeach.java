package teamrtg.rtg.mods.vanilla.surfaces;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.ChunkPrimer;
import teamrtg.rtg.util.math.CliffCalculator;
import teamrtg.rtg.util.noise.CellNoise;
import teamrtg.rtg.util.noise.OpenSimplexNoise;
import teamrtg.rtg.api.biome.RealisticBiomeBase;
import teamrtg.rtg.world.biome.surface.SurfaceBase;

import java.util.Random;

public class SurfaceVanillaColdBeach extends SurfaceBase {


    private byte sandMetadata = 0;
    private int cliffType = 1;

    public SurfaceVanillaColdBeach(RealisticBiomeBase biome) {
        super(biome);
    }

    @Override
    public void paintSurface(ChunkPrimer primer, int i, int j, int x, int y, int depth, World world, Random rand,
                             OpenSimplexNoise simplex, CellNoise cell, float[] noise, float river, BiomeGenBase[] base) {

        float c = CliffCalculator.calc(x, y, noise);
        boolean cliff = c > 1.3f ? true : false;
        boolean dirt = false;

        for (int k = 255; k > -1; k--) {
            Block b = primer.getBlockState(x, k, y).getBlock();
            if (b == Blocks.AIR) {
                depth = -1;
            } else if (b == Blocks.STONE) {
                depth++;

                if (cliff) {
                    if (cliffType == 1) {
                        if (depth < 6) {
                            primer.setBlockState(x, k, y, biome.config.CLIFF_BLOCK_1.get().getBlock().getStateFromMeta(14));
                        }
                    } else {
                        if (depth > -1 && depth < 2) {
                            primer.setBlockState(x, k, y, rand.nextInt(3) == 0 ? biome.config.CLIFF_BLOCK_2.get() : biome.config.CLIFF_BLOCK_1.get());
                        } else if (depth < 10) {
                            primer.setBlockState(x, k, y, biome.config.CLIFF_BLOCK_1.get());
                        }
                    }
                } else if (depth < 6) {
                    if (depth == 0 && k > 61) {
                        if (simplex.noise2(i / 12f, j / 12f) > -0.3f + ((k - 61f) / 15f)) {
                            dirt = true;
                            primer.setBlockState(x, k, y, biome.config.TOP_BLOCK.get());
                        } else {
                            if (k < 69) {
                                primer.setBlockState(x, k, y, Blocks.SAND.getStateFromMeta(sandMetadata));
                            } // else probably steep shore so leave stone

                        }
                    } else if (depth < 4) {
                        if (dirt) {
                            primer.setBlockState(x, k, y, biome.config.FILL_BLOCK.get());
                        } else {
                            if (k < 69) {
                                primer.setBlockState(x, k, y, Blocks.SAND.getStateFromMeta(sandMetadata));
                            }
                        }
                    } else if (!dirt) {
                        primer.setBlockState(x, k, y, Blocks.SANDSTONE.getDefaultState());
                    }
                }
            }
        }
    }
}
