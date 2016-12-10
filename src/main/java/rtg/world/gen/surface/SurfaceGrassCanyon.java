package rtg.world.gen.surface;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;

import rtg.api.world.RTGWorld;
import rtg.api.config.BiomeConfig;
import rtg.api.util.BlockUtil;
import rtg.api.util.CliffCalculator;

public class SurfaceGrassCanyon extends SurfaceBase {

    private byte claycolor;

    public SurfaceGrassCanyon(BiomeConfig config, IBlockState top, IBlockState fill, byte clayByte) {

        super(config, top, fill);
        claycolor = clayByte;
    }

    @Override
    public void paintTerrain(ChunkPrimer primer, int i, int j, int x, int z, int depth, RTGWorld rtgWorld, float[] noise, float river, Biome[] base) {

        Random rand = rtgWorld.rand;
        float c = CliffCalculator.calc(x, z, noise);
        boolean cliff = c > 1.3f ? true : false;

        for (int k = 255; k > -1; k--) {
            Block b = primer.getBlockState(x, k, z).getBlock();
            if (b == Blocks.AIR) {
                depth = -1;
            }
            else if (b == Blocks.STONE) {
                depth++;

                if (depth > -1 && depth < 12) {
                    if (cliff) {
                        primer.setBlockState(x, k, z, BlockUtil.getStateClay(claycolor));
                    }
                    else {
                        if (depth > 4) {
                            primer.setBlockState(x, k, z, BlockUtil.getStateClay(claycolor));
                        }
                        else {
                            if (depth == 0) {
                                primer.setBlockState(x, k, z, topBlock);
                            }
                            else {
                                primer.setBlockState(x, k, z, fillerBlock);
                            }
                        }
                    }
                }
                else if (k > 63) {
                    primer.setBlockState(x, k, z, BlockUtil.getStateClay(claycolor));
                }
            }
        }
    }
}
