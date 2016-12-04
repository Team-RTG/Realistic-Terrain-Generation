package exterminatorjeff.undergroundbiomes.api.common;

import java.util.List;

import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * A {@link FunctionalInterface} used to get the drops of an UB stone.
 * 
 * @author LouisDB
 * 
 * @see UBDropsRegistry
 *
 */
@FunctionalInterface
public interface DropSource {

	/**
	 * Add drops to the list based on coordinates (usually height), state,
	 * fortune level and randomness.
	 * 
	 * @param drops
	 * @param world
	 * @param pos
	 * @param state
	 * @param fortune
	 */
	void addDrops(List<ItemStack> drops, World world, BlockPos pos, IBlockState state, int fortune);

}
