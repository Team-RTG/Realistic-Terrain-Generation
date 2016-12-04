/*
 */

package exterminatorjeff.undergroundbiomes.api;

import net.minecraft.block.state.IBlockState;

/**
 *
 * @author Zeno410
 */
public interface UBStrataColumn {
    public IBlockState stone(int height);
    public IBlockState cobblestone(int height);
    public IBlockState stone();
    public IBlockState cobblestone();

}