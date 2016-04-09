package teamrtg.rtg.util;

import net.minecraft.block.state.IBlockState;
import teamrtg.rtg.util.math.CanyonColour;

/**
 * Gets a block for specific coordinates
 * Useful for something like {@link CanyonColour}
 * @author topisani
 */
public interface IBlockAt {
    IBlockState getAt(int x, int y, int z);
}
