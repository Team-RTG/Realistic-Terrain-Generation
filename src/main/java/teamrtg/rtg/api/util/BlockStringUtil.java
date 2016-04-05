package teamrtg.rtg.api.util;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import teamrtg.rtg.api.util.debug.RTGException;

import java.util.ArrayList;
import java.util.List;

/**
 * @author topisani
 */
public class BlockStringUtil {

    public static String stateToString(IBlockState blockState) {
        return blockState.getBlock().getRegistryName() + "/" + blockState.getBlock().getMetaFromState(blockState);
    }

    public static String[] statesToStrings(IBlockState[] blockStates, boolean preserveMeta) {
        List<String> s = new ArrayList<String>();
        for (int i = 0; i < blockStates.length; i++) {
            int meta = (preserveMeta) ? -1 : blockStates[i].getBlock().getMetaFromState(blockStates[i]);
            s.add(blockStates[i].getBlock().getRegistryName() + "/" + meta);
        }
        return s.toArray(new String[s.size()]);
    }

    public static IBlockState[] stringsToStates(String[] strings) throws RTGException {
        List<IBlockState> s = new ArrayList<IBlockState>();
        for (int i = 0; i < strings.length; i++) {
            s.add(stringToState(strings[0]));
        }
        return s.toArray(new IBlockState[s.size()]);
    }

    public static IBlockState stringToState(String string) throws RTGException {
        String[] s = string.split("/");
        Block b = Block.getBlockFromName(s[0]);
        IBlockState bs;
        if (b == null)
            throw new RTGException(RTGException.Type.CONFIG_SYNTAX, "Expected 'modID:blockId/metaValue', found " + string);
        try {
            bs = b.getStateFromMeta(Integer.valueOf(s[1]));
        } catch (Exception e) {
            bs = b.getDefaultState();
        }
        return bs;
    }
}
