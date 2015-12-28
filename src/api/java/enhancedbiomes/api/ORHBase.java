package enhancedbiomes.api;

import net.minecraft.block.Block;
import net.minecraft.item.Item;

public abstract class ORHBase {
	public abstract void createOre(String modID, String oreName, String standardTexture, Block originalOre, 
			int miningLevel, boolean doesOreDropItself, Item itemDropped, int metaDropped, 
			int quantityDroppedMin, int quantityDroppedMax, int minXP, int maxXP, 
			int minHeight, int maxHeight, int rate, int quantity);
}
