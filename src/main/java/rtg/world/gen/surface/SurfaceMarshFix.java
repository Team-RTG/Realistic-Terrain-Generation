package rtg.world.gen.surface;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.ChunkPrimer;
import rtg.api.biome.BiomeConfig;
import rtg.util.CellNoise;
import rtg.util.CliffCalculator;
import rtg.util.OpenSimplexNoise;

import java.util.Random;

public class SurfaceMarshFix extends SurfaceBase {
    private IBlockState cliffBlock1;
    private IBlockState cliffBlock2;

    public SurfaceMarshFix(BiomeConfig config, IBlockState top, IBlockState filler, IBlockState cliff1, IBlockState cliff2) {
        super(config, top, filler);

        cliffBlock1 = cliff1;
        cliffBlock2 = cliff2;
    }

    @Override
    public void paintTerrain(ChunkPrimer primer, int i, int j, int x, int y, int depth, World world, Random rand, OpenSimplexNoise simplex, CellNoise cell, float[] noise, float river, BiomeGenBase[] base) {
        float c = CliffCalculator.calc(x, y, noise);
        boolean cliff = c > 1.4f ? true : false;

        for (int k = 255; k > -1; k--) {
            Block b = primer.getBlockState(x, 256 + k, y).getBlock();
            if (b == Blocks.air) {
                depth = -1;
            } else if (b == Blocks.stone) {
                depth++;

                if (cliff && k > 64) {
                    if (depth > -1 && depth < 2) {
                        primer.setBlockState(x, 256 + k, y, rand.nextInt(3) == 0 ? cliffBlock2 : cliffBlock1);
                    } else if (depth < 10) {
                        primer.setBlockState(x, 256 + k, y, cliffBlock1);
                    }
                } else {
                    if (depth == 0 && k > 61) {
                        primer.setBlockState(x, 256 + k, y, topBlock);
                    } else if (depth < 4) {
                        primer.setBlockState(x, 256 + k, y, fillerBlock);
                    }
                }
            }
        }
    }
}
