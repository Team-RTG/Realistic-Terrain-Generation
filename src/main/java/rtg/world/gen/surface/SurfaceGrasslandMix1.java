package rtg.world.gen.surface;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;

import rtg.api.util.noise.OpenSimplexNoise;
import rtg.api.world.RTGWorld;
import rtg.api.config.BiomeConfig;
import rtg.api.util.CliffCalculator;

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
    public void paintTerrain(ChunkPrimer primer, int i, int j, int x, int z, int depth, RTGWorld rtgWorld, float[] noise, float river, Biome[] base) {

        Random rand = rtgWorld.rand;
        OpenSimplexNoise simplex = rtgWorld.simplex;
        float c = CliffCalculator.calc(x, z, noise);
        boolean cliff = c > 1.4f ? true : false;

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
                        if (simplex.noise2(i / width, j / width) > height) // > 0.27f, i / 12f
                        {
                            primer.setBlockState(x, k, z, mixBlock);
                        }
                        else {
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
