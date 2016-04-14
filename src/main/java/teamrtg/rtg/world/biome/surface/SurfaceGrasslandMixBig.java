package teamrtg.rtg.world.biome.surface;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.ChunkPrimer;
import teamrtg.rtg.util.math.CliffCalculator;
import teamrtg.rtg.util.noise.CellNoise;
import teamrtg.rtg.util.noise.OpenSimplexNoise;
import teamrtg.rtg.api.biome.RealisticBiomeBase;

import java.util.Random;

public class SurfaceGrasslandMixBig extends SurfaceBase {

    private float width;
    private float height;
    private float smallW;
    private float smallS;

    public SurfaceGrasslandMixBig(RealisticBiomeBase biome, float mixWidth, float mixHeight, float smallWidth, float smallStrength) {
        super(biome);

        width = mixWidth;
        height = mixHeight;
        smallW = smallWidth;
        smallS = smallStrength;
    }

    @Override
    public void paintSurface(ChunkPrimer primer, int i, int j, int x, int y, int depth, World world, Random rand, OpenSimplexNoise simplex, CellNoise cell, float[] noise, float river, BiomeGenBase[] base) {
        float c = CliffCalculator.calc(x, y, noise);
        boolean cliff = c > 1.4f ? true : false;
        boolean mix = false;

        for (int k = 255; k > -1; k--) {
            Block b = primer.getBlockState(x, k, y).getBlock();
            if (b == Blocks.AIR) {
                depth = -1;
            } else if (b == Blocks.STONE) {
                depth++;

                if (cliff) {
                    if (depth > -1 && depth < 2) {
                        primer.setBlockState(x, k, y, rand.nextInt(3) == 0 ? biome.config.CLIFF_BLOCK_2.get() : biome.config.CLIFF_BLOCK_1.get());
                    } else if (depth < 10) {
                        primer.setBlockState(x, k, y, biome.config.CLIFF_BLOCK_1.get());
                    }
                } else {
                    if (depth == 0 && k > 61) {
                        if (simplex.noise2(i / width, j / width) + simplex.noise2(i / smallW, j / smallW) * smallS > height) {
                            primer.setBlockState(x, k, y, biome.config.MIX_BLOCK_TOP.get());
                            mix = true;
                        } else {
                            primer.setBlockState(x, k, y, biome.config.TOP_BLOCK.get());
                        }
                    } else if (depth < 4) {
                        if (mix) {
                            primer.setBlockState(x, k, y, biome.config.MIX_BLOCK_FILL.get());
                        } else {
                            primer.setBlockState(x, k, y, biome.config.FILL_BLOCK.get());
                        }
                    }
                }
            }
        }
    }
}
