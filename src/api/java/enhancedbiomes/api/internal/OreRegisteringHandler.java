package enhancedbiomes.api.internal;

import static net.minecraft.block.Block.soundTypePiston;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import enhancedbiomes.EnhancedBiomesMod;
import enhancedbiomes.api.ORHBase;
import enhancedbiomes.blocks.BlockStoneEB;
import enhancedbiomes.blocks.EnhancedBiomesBlocks;
import enhancedbiomes.items.ItemBlockEnhancedBiomes;

public class OreRegisteringHandler extends ORHBase
{
	public void createOre(String modID, String oreName, String standardTexture, Block originalOre, 
			int miningLevel, boolean doesOreDropItself, Item itemDropped, int metaDropped, 
			int quantityDroppedMin, int quantityDroppedMax, int minXP, int maxXP, 
			int minHeight, int maxHeight, int rate, int quantity) {
	   
		Block ore = (new BlockModOre(modID, oreName, standardTexture, doesOreDropItself, itemDropped, metaDropped, quantityDroppedMin, quantityDroppedMax, minXP, maxXP)).setHardness(3.0F).setResistance(5.0F).setStepSound(soundTypePiston).setBlockName(oreName + "EB").setCreativeTab(EnhancedBiomesBlocks.tabEnhancedBiomesMineral);
		GameRegistry.registerBlock(ore, ItemBlockEnhancedBiomes.class, "enhancedbiomes." + oreName);
		
		OreDictionary.registerOre(oreName, new ItemStack(ore, 1, OreDictionary.WILDCARD_VALUE));
		ore.setHarvestLevel("pickaxe", miningLevel);
		
		for(int x = 0; x < BlockStoneEB.stones.length + BlockStoneEB.stones2.length; x++) {
			GameRegistry.addSmelting(new ItemStack(ore, 1, x), new ItemStack(itemDropped, 1, metaDropped), minXP);
		}
	    
	    if(EnhancedBiomesMod.modOreList.size() == 0) RenderingRegistry.registerBlockHandler(new BlockModOreRenderer());	    
	    EnhancedBiomesMod.modOreList.add(new OreGenEntry(ore, originalOre, minHeight, maxHeight, rate, quantity));
	}
}
