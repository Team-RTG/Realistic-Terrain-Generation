package exterminatorjeff.undergroundbiomes.api.common;

import net.minecraft.block.Block;

/**
 * Allows mods to modify the drops of UB stones.
 * 
 * @author LouisDB
 *
 */
public interface UBDropsRegistry {

	/**
	 * Add a {@link DropSource} for the given stone.
	 * 
	 * @param stone
	 * @param source
	 */
	void registerDropSourceFor(Block stone, DropSource source);

}
