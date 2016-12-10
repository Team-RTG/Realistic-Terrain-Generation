package exterminatorjeff.undergroundbiomes.api.common;

import net.minecraft.block.BlockSlab;

/**
 * Common interface for UB slabs.
 * 
 * @author LouisDB
 *
 */
public interface UBSlab extends UBItem, Variable {

	BlockSlab getHalfSlab();

	BlockSlab getFullSlab();

}
