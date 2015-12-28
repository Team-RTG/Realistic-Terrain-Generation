package enhancedbiomes.items;

import cpw.mods.fml.common.registry.GameRegistry;
import enhancedbiomes.blocks.EnhancedBiomesBlocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;

public class EnhancedBiomesItems {
	
	public static Item saguaro;
	
	public static void load() {
		saguaro = new ItemSaguaro(4, 0.3F, false).setUnlocalizedName("saguaroFruit").setTextureName("enhancedbiomes:saguaro_fruit").setCreativeTab(EnhancedBiomesBlocks.tabEnhancedBiomesOrganic);
		inputItem(saguaro);
	}
	
	public static void inputItem(Item item)
	{
		GameRegistry.registerItem(item, "enhancedbiomes." + item.getUnlocalizedName());
	}
}
