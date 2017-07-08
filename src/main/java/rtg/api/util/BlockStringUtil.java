package rtg.api.util;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;

/**
 * @author topisani
 */
public class BlockStringUtil {

    public static String stateToString(IBlockState blockState) {
        return blockState.getBlock().getRegistryName() + ":" + blockState.getBlock().getMetaFromState(blockState);
    }

    public static IBlockState stringToState(String string) throws RuntimeException {
        String[] s = string.split(":");
        Block b = Block.getBlockFromName(s[0] + ":" + s[1]);
        IBlockState bs;
        if (b == null)
            throw new RuntimeException("Expected 'modID:blockId:metaValue', found " + string);
        try {
            bs = b.getStateFromMeta(Integer.valueOf(s[2]));
        } catch (Exception e) {
            bs = b.getDefaultState();
        }
        return bs;
    }
}