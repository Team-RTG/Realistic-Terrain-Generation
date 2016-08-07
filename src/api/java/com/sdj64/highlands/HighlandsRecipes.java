package com.sdj64.highlands;

import com.sdj64.highlands.block.HighlandsBlocks;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class HighlandsRecipes {

	
	public static void init(){
		
		for(int i = 0; i < HighlandsBlocks.NUM_TREE_TYPES; i++){
			if(HighlandsBlocks.planks[i] != null && HighlandsBlocks.woods[i] != null){
				//Bamboo stem only crafts into one plank
				if(i == HighlandsBlocks.EnumTypeTree.BAMBOO.getMetadata())
					GameRegistry.addShapelessRecipe(new ItemStack(HighlandsBlocks.planks[i], 1), new ItemStack(HighlandsBlocks.woods[i]));
				else
					GameRegistry.addShapelessRecipe(new ItemStack(HighlandsBlocks.planks[i], 4), new ItemStack(HighlandsBlocks.woods[i]));
			
				//placeholder - recipes for wooden stairs and half slabs
				GameRegistry.addRecipe(new ItemStack(Blocks.oak_stairs, 4), "x  ", "xx ", "xxx", 'x', HighlandsBlocks.planks[i]);
				GameRegistry.addRecipe(new ItemStack(Blocks.oak_stairs, 4), "  x", " xx", "xxx", 'x', HighlandsBlocks.planks[i]);
				GameRegistry.addRecipe(new ItemStack(Blocks.wooden_slab, 6), "   ", "   ", "xxx", 'x', HighlandsBlocks.planks[i]);
				
				//wood smelts into coal
				GameRegistry.addSmelting(HighlandsBlocks.woods[i], new ItemStack(Items.coal, 1, 1), 0.15F);
			}
		}
		
		//Plants recipes
		GameRegistry.addShapelessRecipe(new ItemStack(Items.dye, 1, 6), new ItemStack(HighlandsBlocks.plants[0]));
		GameRegistry.addRecipe(new ItemStack(Items.paper, 3), "   ", "xxx", "   ", 'x', new ItemStack(HighlandsBlocks.plants[1]));
		GameRegistry.addShapelessRecipe(new ItemStack(Items.string, 1), new ItemStack(HighlandsBlocks.plants[2]));
		//GameRegistry.addShapelessRecipe(new ItemStack(Items.dye, 1, 12), new ItemStack(HighlandsBlocks.plants[3]));
		//GameRegistry.addShapelessRecipe(new ItemStack(Items.dye, 1, 12), new ItemStack(HighlandsBlocks.plants[4]));
		//GameRegistry.addShapelessRecipe(new ItemStack(Items.dye, 1, 12), new ItemStack(HighlandsBlocks.plants[5]));
		GameRegistry.addShapelessRecipe(new ItemStack(Items.dye, 1, 5), new ItemStack(HighlandsBlocks.plants[6]));
		GameRegistry.addShapelessRecipe(new ItemStack(Items.dye, 1, 10), new ItemStack(HighlandsBlocks.plants[7]));
		//GameRegistry.addShapelessRecipe(new ItemStack(Items.dye, 1, 12), new ItemStack(HighlandsBlocks.plants[8]));
		
		
	}
}
