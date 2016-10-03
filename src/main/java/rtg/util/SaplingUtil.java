package rtg.util;

import net.minecraft.block.BlockNewLeaf;
import net.minecraft.block.BlockPlanks;
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
        else if (leavesBlock == Blocks.LEAVES.getDefaultState().withProperty(BlockNewLeaf.VARIANT, BlockPlanks.EnumType.SPRUCE)) {
            return Blocks.SAPLING.getDefaultState().withProperty(BlockSapling.TYPE, BlockPlanks.EnumType.SPRUCE);
        }
        else if (leavesBlock == Blocks.LEAVES.getDefaultState().withProperty(BlockNewLeaf.VARIANT, BlockPlanks.EnumType.BIRCH)) {
            return Blocks.SAPLING.getDefaultState().withProperty(BlockSapling.TYPE, BlockPlanks.EnumType.BIRCH);
        }
        else if (leavesBlock == Blocks.LEAVES.getDefaultState().withProperty(BlockNewLeaf.VARIANT, BlockPlanks.EnumType.JUNGLE)) {
            return Blocks.SAPLING.getDefaultState().withProperty(BlockSapling.TYPE, BlockPlanks.EnumType.JUNGLE);
        }
        else if (leavesBlock == Blocks.LEAVES2.getDefaultState()) {
            return Blocks.SAPLING.getDefaultState().withProperty(BlockSapling.TYPE, BlockPlanks.EnumType.ACACIA);
        }
        else if (leavesBlock == Blocks.LEAVES2.getDefaultState().withProperty(BlockNewLeaf.VARIANT, BlockPlanks.EnumType.DARK_OAK)) {
            return Blocks.SAPLING.getDefaultState().withProperty(BlockSapling.TYPE, BlockPlanks.EnumType.DARK_OAK);
        }
        else {
            return Blocks.SAPLING.getDefaultState();
        }
    }
}