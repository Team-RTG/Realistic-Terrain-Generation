package rtg.util;

import net.minecraft.block.BlockSapling;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;

public class SaplingUtil {

    public static int getMetaFromState(IBlockState state) {

        try {

            if (!(state.getBlock() instanceof BlockSapling)) {
                Logger.warn("Could not get sapling meta from non-sapling BlockState (%s).", state.getBlock().getLocalizedName());
                return 0;
            }

            return state.getValue(BlockSapling.TYPE).getMetadata();
        }
        catch (Exception e) {

            Logger.warn("Could not get sapling meta from state. Reason: " + e.getMessage());
            return 0;
        }
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