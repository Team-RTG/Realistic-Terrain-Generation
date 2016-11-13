package rtg.world.gen.surface;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;

import rtg.config.BiomeConfig;
import rtg.util.CellNoise;
import rtg.util.CliffCalculator;
import rtg.util.OpenSimplexNoise;

public class SurfaceGrasslandMix1 extends SurfaceBase {

    private IBlockState mixBlock;
    private IBlockState cliffBlock1;
    private IBlockState cliffBlock2;
    private float width;
    private float height;

    public SurfaceGrasslandMix1(BiomeConfig config, IBlockState top, IBlockState filler, IBlockState mix, IBlockState cliff1, IBlockState cliff2, float mixWidth, float mixHeight) {

        super(config, top, filler);

        mixBlock = mix;
        cliffBlock1 = cliff1;
        cliffBlock2 = cliff2;

        width = mixWidth;
        height = mixHeight;
    }

    @Override
    public void paintTerrain(ChunkPrimer primer, int i, int j, int x, int y, int depth, World world, Random rand, OpenSimplexNoise simplex, CellNoise cell, float[] noise, float river, Biome[] base) {

        float c = CliffCalculator.calc(x, y, noise);
        boolean cliff = c > 1.4f ? true : false;

        for (int k = 255; k > -1; k--) {
            Block b = primer.getBlockState(x, k, y).getBlock();
            if (b == Blocks.AIR) {
                depth = -1;
            }
            else if (b == Blocks.STONE) {
                depth++;

                if (cliff) {
                    if (depth > -1 && depth < 2) {
                        primer.setBlockState(x, k, y, rand.nextInt(3) == 0 ? cliffBlock2 : cliffBlock1);
                    }
                    else if (depth < 10) {
                        primer.setBlockState(x, k, y, cliffBlock1);
                    }
                }
                else {
                    if (depth == 0 && k > 61) {
                        if (simplex.noise2(i / width, j / width) > height) // > 0.27f, i / 12f
                        {
                            primer.setBlockState(x, k, y, mixBlock);
                        }
                        else {
                            primer.setBlockState(x, k, y, topBlock);
                        }
                    }
                    else if (depth < 4) {
                        primer.setBlockState(x, k, y, fillerBlock);
                    }
                }
            }
        }
    }
}
