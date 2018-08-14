package rtg.api.util;

import net.minecraft.block.BlockPlanks.EnumType;
import net.minecraft.block.BlockSapling;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;


@UtilityClass
public final class SaplingUtil {

    private SaplingUtil() {

    }

    public static int getMetaFromState(IBlockState state) {

        try {

            if (!(state.getBlock() instanceof BlockSapling)) {
                Logger.rtgDebug("Could not get sapling meta from non-sapling BlockState (%s).", state.getBlock().getLocalizedName());
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
        else if (leavesBlock == BlockUtil.getStateLeaf(EnumType.SPRUCE)) {
            return BlockUtil.getStateSapling(EnumType.SPRUCE);
        }
        else if (leavesBlock == BlockUtil.getStateLeaf(EnumType.BIRCH)) {
            return BlockUtil.getStateSapling(EnumType.BIRCH);
        }
        else if (leavesBlock == BlockUtil.getStateLeaf(EnumType.JUNGLE)) {
            return BlockUtil.getStateSapling(EnumType.JUNGLE);
        }
        else if (leavesBlock == BlockUtil.getStateLeaf(EnumType.ACACIA)) {
            return BlockUtil.getStateSapling(EnumType.ACACIA);
        }
        else if (leavesBlock == BlockUtil.getStateLeaf(EnumType.DARK_OAK)) {
            return BlockUtil.getStateSapling(EnumType.DARK_OAK);
        }
        else {
            return Blocks.SAPLING.getDefaultState();
        }
    }
}