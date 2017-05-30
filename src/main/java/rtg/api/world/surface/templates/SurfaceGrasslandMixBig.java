package rtg.api.world.surface.templates;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;

import rtg.api.config.BiomeConfig;
import rtg.api.util.CliffCalculator;
import rtg.api.util.noise.OpenSimplexNoise;
import rtg.api.world.RTGWorld;
import rtg.api.world.surface.SurfaceBase;

public class SurfaceGrasslandMixBig extends SurfaceBase {

    private IBlockState mixBlockTop;
    private IBlockState mixBlockFill;
    private IBlockState cliffBlock1;
    private IBlockState cliffBlock2;
    private float width;
    private float height;
    private float smallW;
    private float smallS;

    public SurfaceGrasslandMixBig(BiomeConfig config, IBlockState top, IBlockState filler, IBlockState mixTop, IBlockState mixFill, IBlockState cliff1, IBlockState cliff2, float mixWidth, float mixHeight, float smallWidth, float smallStrength) {

        super(config, top, filler);

        mixBlockTop = mixTop;
        mixBlockFill = mixFill;
        cliffBlock1 = cliff1;
        cliffBlock2 = cliff2;

        width = mixWidth;
        height = mixHeight;
        smallW = smallWidth;
        smallS = smallStrength;
    }

    @Override
    public void paintTerrain(ChunkPrimer primer, int i, int j, int x, int z, int depth, RTGWorld rtgWorld, float[] noise, float river, Biome[] base) {

        Random rand = rtgWorld.rand;
        OpenSimplexNoise simplex = rtgWorld.simplex;
        float c = CliffCalculator.calc(x, z, noise);
        boolean cliff = c > 1.4f ? true : false;
        boolean mix = false;

        for (int k = 255; k > -1; k--) {
            Block b = primer.getBlockState(x, k, z).getBlock();
            if (b == Blocks.AIR) {
                depth = -1;
            }
            else if (b == Blocks.STONE) {
                depth++;

                if (cliff) {
                    if (depth > -1 && depth < 2) {
                        primer.setBlockState(x, k, z, rand.nextInt(3) == 0 ? cliffBlock2 : cliffBlock1);
                    }
                    else if (depth < 10) {
                        primer.setBlockState(x, k, z, cliffBlock1);
                    }
                }
                else {
                    if (depth == 0 && k > 61) {
                        if (simplex.noise2(i / width, j / width) + simplex.noise2(i / smallW, j / smallW) * smallS > height) {
                            primer.setBlockState(x, k, z, mixBlockTop);
                            mix = true;
                        }
                        else {
                            primer.setBlockState(x, k, z, topBlock);
                        }
                    }
                    else if (depth < 4) {
                        if (mix) {
                            primer.setBlockState(x, k, z, mixBlockFill);
                        }
                        else {
                            primer.setBlockState(x, k, z, fillerBlock);
                        }
                    }
                }
            }
        }
    }
}
