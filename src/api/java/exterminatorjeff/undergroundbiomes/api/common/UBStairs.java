package exterminatorjeff.undergroundbiomes.api.common;

import net.minecraft.block.Block;
import net.minecraft.util.EnumFacing;

/**
 * Common interface for UB stairs.
 * 
 * @author LouisDB
 *
 */
public interface UBStairs extends UBItem, Variable {

	Block getBlock(EnumFacing facing);

}
