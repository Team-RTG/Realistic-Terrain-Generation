package rtg.util;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSand;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;

public class BlockUtil {

    public static IBlockState getState(Block block, int meta) {

        if (block == Blocks.SAND) {
            switch (meta) {
                case 0:
                    return Blocks.SAND.getDefaultState();
                case 1:
                    return Blocks.SAND.getStateFromMeta(BlockSand.EnumType.RED_SAND.getMetadata());
                default:
                    return block.getStateFromMeta(meta);
            }
        }
        else if (block == Blocks.DOUBLE_PLANT) {
            return block.getStateFromMeta(meta);
        }
        else if (block == Blocks.STAINED_HARDENED_CLAY) {
            return block.getStateFromMeta(meta);
        }
        else if (block == Blocks.RED_FLOWER) {
            return block.getStateFromMeta(meta);
        }

        return block.getStateFromMeta(meta);
    }

    public static IBlockState getState(Block block, byte meta) {
        return BlockUtil.getState(block, (int) meta);
    }
}