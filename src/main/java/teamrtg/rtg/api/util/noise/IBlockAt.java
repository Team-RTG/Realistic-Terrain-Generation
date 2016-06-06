package teamrtg.rtg.api.util.noise;

import net.minecraft.block.state.IBlockState;
import teamrtg.rtg.api.util.math.CanyonColour;
import teamrtg.rtg.api.world.RTGWorld;

/**
 * Gets a block for specific coordinates
 * Useful for something like {@link CanyonColour}
 * @author topisani
 */
public interface IBlockAt {
    IBlockState getAt(int x, int y, int z, RTGWorld rtgWorld);
}
