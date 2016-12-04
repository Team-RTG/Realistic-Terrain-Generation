package exterminatorjeff.undergroundbiomes.api.common;

import net.minecraft.block.Block;
import net.minecraft.util.EnumFacing;

/**
 * Common interface for UB buttons.
 * 
 * @author LouisDB
 *
 */
public interface UBButton extends UBItem, Variable {

	Block getBlock(EnumFacing facing);

}
