package rtg.api.world.surface.templates;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;

import rtg.api.config.BiomeConfig;
import rtg.api.util.BlockUtil;
import rtg.api.util.CliffCalculator;
import rtg.api.world.RTGWorld;
import rtg.api.world.surface.SurfaceBase;

public class SurfaceRedDesert extends SurfaceBase {

    private IBlockState cliffBlock1;
    private IBlockState bottomBlock;

    public SurfaceRedDesert(BiomeConfig config) {

        super(config, BlockUtil.getStateSand(1), BlockUtil.getStateSand(1));

        bottomBlock = Blocks.SANDSTONE.getDefaultState();
        cliffBlock1 = BlockUtil.getStateClay(14);
    }

    @Override
    public void paintTerrain(ChunkPrimer primer, int i, int j, int x, int z, int depth, RTGWorld rtgWorld, float[] noise, float river, Biome[] base) {

        Random rand = rtgWorld.rand;
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
                    if (depth < 6) {
                        primer.setBlockState(x, k, z, cliffBlock1);
                    }
                }
                else if (depth < 6) {
                    if (depth == 0 && k > 61) {
                        primer.setBlockState(x, k, z, topBlock);
                    }
                    else if (depth < 4) {
                        primer.setBlockState(x, k, z, fillerBlock);
                    }
                    else {
                        primer.setBlockState(x, k, z, bottomBlock.getBlock().getDefaultState());
                    }
                }
            }
        }
    }
}
