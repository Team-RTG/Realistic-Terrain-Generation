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

        if (leavesBlock == Blocks.LEAVES.getDefaultState()) {
            return Blocks.SAPLING.getDefaultState();
        }
        else if (leavesBlock == Blocks.LEAVES.getStateFromMeta(1)) {
            return Blocks.SAPLING.getStateFromMeta(1);
        }
        else if (leavesBlock == Blocks.LEAVES.getStateFromMeta(2)) {
            return Blocks.SAPLING.getStateFromMeta(2);
        }
        else if (leavesBlock == Blocks.LEAVES.getStateFromMeta(3)) {
            return Blocks.SAPLING.getStateFromMeta(3);
        }
        else if (leavesBlock == Blocks.LEAVES2.getDefaultState()) {
            return Blocks.SAPLING.getStateFromMeta(4);
        }
        else if (leavesBlock == Blocks.LEAVES2.getStateFromMeta(1)) {
            return Blocks.SAPLING.getStateFromMeta(5);
        }
        else {
            return Blocks.SAPLING.getDefaultState();
        }
    }
}