package enhancedbiomes.api;

import cpw.mods.fml.common.Loader;
import net.minecraft.block.Block;
import net.minecraft.item.Item;

public class EnhancedBiomesApi {

	public static ORHBase orh;

	/**
	 * This should handle everything needed to set up the new ore variants. It will add the block, register it, set up its smelting recipes and generate the ore.
	 * Because it handles ore generation, you should disable your own generation if Enhanced Biomes is loaded.
	 * 
	 * You must also have an overlay for the ore in the format 'overlay_oreName.png' and set up the block name in the lang file.
	 * 
	 * @param modID The mod ID of your mod. Used for texture locations.
	 * @param oreName The name used when registering the ore with the ore dictionary.
	 * @param standardTexture The texture of the standard ore. Used on fast graphics and for rendering in the inventory.
	 * @param originalOre The standard ore itself. Used in world generation.
	 * @param miningLevel The level of pick that is required to mine the ore.
	 * @param doesOreDropItself Whether the ore is like iron or gold and drops itself. If it does, the next two parameters and the minXP parameter are used for the results of smelting the ore.
	 * @param itemDropped The item dropped / result of smelting;
	 * @param metaDropped The meta of above item.
	 * @param quantityDroppedMin Minimum quantity dropped. Unused if doesOreDropItself is true.
	 * @param quantityDroppedMax Maximum quantity dropped. Unused if doesOreDropItself is true.
	 * @param minXP Minimum XP on mining / Exact XP on smelting the ore.
	 * @param maxXP Maximum XP on mining the ore. Unused if doesOreDropItself is true.
	 * @param minHeight Minimum height for generation.
	 * @param maxHeight Maximum height for generation.
	 * @param rate Frequency for generation.
	 * @param quantity Maximum ore blocks in a vein.
	 */

	public static void createOre(String modID, String oreName, String standardTexture, Block originalOre, 
			int miningLevel, boolean doesOreDropItself, Item itemDropped, int metaDropped, 
			int quantityDroppedMin, int quantityDroppedMax, int minXP, int maxXP, 
			int minHeight, int maxHeight, int rate, int quantity) {		
		if(Loader.isModLoaded("enhancedbiomes")) orh.createOre(modID, oreName, standardTexture, originalOre, 
				miningLevel, doesOreDropItself, itemDropped, metaDropped, 
				quantityDroppedMin, quantityDroppedMax, minXP, maxXP, 
				minHeight, maxHeight, rate, quantity);
	}
}
