package rtg.util;

import net.minecraft.block.BlockPlanks;
import net.minecraft.block.BlockSapling;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;

public class SaplingUtil {

    public static int getMetaFromState(IBlockState state) {

        return ((BlockPlanks.EnumType)state.getValue(BlockSapling.TYPE)).getMetadata();
    }

    public static IBlockState getSaplingFromLeaves(IBlockState leavesBlock) {

        if (leavesBlock == Blocks.leaves.getDefaultState()) {
            return Blocks.sapling.getDefaultState();
        }
        else if (leavesBlock == Blocks.leaves.getStateFromMeta(1)) {
            return Blocks.sapling.getStateFromMeta(1);
        }
        else if (leavesBlock == Blocks.leaves.getStateFromMeta(2)) {
            return Blocks.sapling.getStateFromMeta(2);
        }
        else if (leavesBlock == Blocks.leaves.getStateFromMeta(3)) {
            return Blocks.sapling.getStateFromMeta(3);
        }
        else if (leavesBlock == Blocks.leaves2.getDefaultState()) {
            return Blocks.sapling.getStateFromMeta(4);
        }
        else if (leavesBlock == Blocks.leaves2.getStateFromMeta(1)) {
            return Blocks.sapling.getStateFromMeta(5);
        }
        else {
            return Blocks.sapling.getDefaultState();
        }
    }
}