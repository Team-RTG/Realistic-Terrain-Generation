package rtg.world.gen.surface;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.ChunkPrimer;
import rtg.util.math.CliffCalculator;
import rtg.util.noise.CellNoise;
import rtg.util.noise.OpenSimplexNoise;
import rtg.world.biome.realistic.RealisticBiomeBase;

import java.util.Random;

public class SurfaceDesertOasis extends SurfaceBase {


    private byte sandMetadata;
    private int cliffType;

    public SurfaceDesertOasis(RealisticBiomeBase biome, byte metadata, int cliff) {
        super(biome);



        sandMetadata = metadata;
        cliffType = cliff;
    }

    @Override
    public void paintTerrain(ChunkPrimer primer, int i, int j, int x, int y, int depth, World world, Random rand, OpenSimplexNoise simplex, CellNoise cell, float[] noise, float river, BiomeGenBase[] base) {
        float c = CliffCalculator.calc(x, y, noise);
        boolean cliff = c > 1.3f;
        boolean dirt = false;

        for (int k = 255; k > -1; k--) {
            Block b = primer.getBlockState(x, k, y).getBlock();
            if (b == Blocks.air) {
                depth = -1;
            } else if (b == Blocks.stone) {
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
                            primer.setBlockState(x, k, y, Blocks.sand.getStateFromMeta(sandMetadata));
                        }
                    } else if (depth < 4) {
                        if (dirt) {
                            primer.setBlockState(x, k, y, biome.config.FILL_BLOCK.get());
                        } else {
                            primer.setBlockState(x, k, y, Blocks.sand.getStateFromMeta(sandMetadata));
                        }
                    } else if (!dirt) {
                        primer.setBlockState(x, k, y, Blocks.sandstone.getDefaultState());
                    }
                }
            }
        }
    }
}
