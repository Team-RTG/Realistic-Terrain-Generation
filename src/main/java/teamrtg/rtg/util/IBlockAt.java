package teamrtg.rtg.util;

import net.minecraft.block.state.IBlockState;

/**
 * Gets a block for specific coordinates
 * Useful for something like {@link teamrtg.rtg.util.math.CanyonColour}
 * @author topisani
 */
public interface IBlockAt {
    IBlockState getAt(int x, int y, int z);
}
