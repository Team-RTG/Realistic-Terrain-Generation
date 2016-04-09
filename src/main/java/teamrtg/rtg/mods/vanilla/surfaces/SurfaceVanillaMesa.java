package teamrtg.rtg.mods.vanilla.surfaces;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.ChunkPrimer;
import teamrtg.rtg.util.math.CanyonColour;
import teamrtg.rtg.util.math.CliffCalculator;
import teamrtg.rtg.util.noise.CellNoise;
import teamrtg.rtg.util.noise.OpenSimplexNoise;
import teamrtg.rtg.api.biome.RealisticBiomeBase;
import teamrtg.rtg.world.biome.surface.SurfaceBase;

import java.util.Random;

public class SurfaceVanillaMesa extends SurfaceBase {
    public SurfaceVanillaMesa(RealisticBiomeBase biome) {
        super(biome);
    }

    @Override
    public void paintSurface(ChunkPrimer primer, int i, int j, int x, int y, int depth, World world, Random rand,
                             OpenSimplexNoise simplex, CellNoise cell, float[] noise, float river, BiomeGenBase[] base) {

        float c = CliffCalculator.calc(x, y, noise);
        boolean cliff = c > 1.3f;

        for (int k = 255; k > -1; k--) {
            Block b = primer.getBlockState(x, k, y).getBlock();
            if (b == Blocks.air) {
                depth = -1;
            } else if (b == Blocks.stone) {
                depth++;

                if (depth > -1 && depth < 12) {
                    if (cliff) {
                        primer.setBlockState(x, k, y, CanyonColour.MESA.getAt(i, k, j));
                    } else {
                        if (depth > 4) {
                            primer.setBlockState(x, k, y, CanyonColour.MESA.getAt(i, k, j));
                        } else if (k > 77) {
                            if (rand.nextInt(5) == 0) {
                                primer.setBlockState(x, k, y, Blocks.dirt.getDefaultState());
                            } else {
                                if (depth == 0) {
                                    primer.setBlockState(x, k, y, biome.config.TOP_BLOCK.get());
                                } else {
                                    primer.setBlockState(x, k, y, biome.config.FILL_BLOCK.get());
                                }
                            }
                        } else if (k < 62) {
                            primer.setBlockState(x, k, y, Blocks.dirt.getDefaultState());
                        } else if (k < 78) {
                            if (depth == 0) {
                                if (k < 72 && rand.nextInt(k - 62 + 1) == 0) {
                                    primer.setBlockState(x, k, y, Blocks.dirt.getDefaultState());
                                } else if (rand.nextInt(5) == 0) {
                                    primer.setBlockState(x, k, y, Blocks.dirt.getDefaultState());
                                } else {
                                    primer.setBlockState(x, k, y, biome.config.TOP_BLOCK.get());
                                }
                            } else {
                                primer.setBlockState(x, k, y, biome.config.FILL_BLOCK.get());
                            }
                        } else {
                            if (depth == 0) {
                                primer.setBlockState(x, k, y, biome.config.TOP_BLOCK.get());
                            } else {
                                primer.setBlockState(x, k, y, biome.config.FILL_BLOCK.get());
                            }
                        }
                    }
                } else if (k > 63) {
                    primer.setBlockState(x, k, y, CanyonColour.MESA.getAt(i, k, j));
                }
            }
        }
    }
}
