package rtg.api.util;

import net.minecraft.block.BlockSapling;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;

import rtg.api.util.BlockUtil;
import rtg.api.util.Logger;

public class SaplingUtil {

    public static int getMetaFromState(IBlockState state) {

        try {

            if (!(state.getBlock() instanceof BlockSapling)) {
                Logger.debug("Could not get sapling meta from non-sapling BlockState (%s).", state.getBlock().getLocalizedName());
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
        else if (leavesBlock == BlockUtil.getStateLeaf(1)) {
            return BlockUtil.getStateSapling(1);
        }
        else if (leavesBlock == BlockUtil.getStateLeaf(2)) {
            return BlockUtil.getStateSapling(2);
        }
        else if (leavesBlock == BlockUtil.getStateLeaf(3)) {
            return BlockUtil.getStateSapling(3);
        }
        else if (leavesBlock == Blocks.LEAVES2.getDefaultState()) {
            return BlockUtil.getStateSapling(4);
        }
        else if (leavesBlock == BlockUtil.getStateLeaf2(1)) {
            return BlockUtil.getStateSapling(5);
        }
        else {
            return Blocks.SAPLING.getDefaultState();
        }
    }
}