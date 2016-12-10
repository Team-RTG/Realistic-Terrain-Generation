package exterminatorjeff.undergroundbiomes.api.common;

import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.IForgeRegistryEntry;

/**
 * Common interface for UB items.
 * 
 * @author LouisDB
 *
 */
public interface UBItem extends IForgeRegistryEntry<Item> {

	Item toItem();

}
