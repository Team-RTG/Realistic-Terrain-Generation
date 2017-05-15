/*******************************************************************************
 * AbyssalCraft
 * Copyright (c) 2012 - 2016 Shinoow.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v3
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-3.0.txt
 * 
 * Contributors:
 *     Shinoow -  implementation
 ******************************************************************************/
package com.shinoow.abyssalcraft.api.necronomicon;

import com.shinoow.abyssalcraft.api.APIUtils;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

/**
 * Simple collection of ItemStacks used for displaying crafting recipes in the Necronomicon
 * @author shinoow
 *
 * @since 1.3.5
 */
public class CraftingStack {

	private ItemStack output;
	private ItemStack[] recipe = new ItemStack[9];

	/**
	 * Simple collection of Objects used to display a recipe
	 * @param output The Item/Block/ItemStack that's crafted
	 * @param recipe The Items/Blocks/ItemStacks used to craft said Item/Block/ItemStack (has to contain 9 elements, all of them can be null)
	 */
	public CraftingStack(Object output, Object...recipe){
		if(output != null){
			if(recipe != null){
				if(recipe.length == 9){
					this.output = APIUtils.convertToStack(output);
					for(int i = 0; i < 9; i++)
						this.recipe[i] = APIUtils.convertToStack(recipe[i]);
				} else throw new ArrayIndexOutOfBoundsException("The array must contain preciesly 9 elements, not "+recipe.length+"!");
			} else throw new NullPointerException("This array can't be empty!");
		} else throw new NullPointerException("Output can't be null!");
	}

	/**
	 * Simple collection of Objects used to display a recipe.<br>
	 * This version looks through the CraftingManager for the<br>
	 * Item/Block's Crafting Recipe.
	 * @param output The Item/Block/ItemStack that's crafted
	 */
	public CraftingStack(Object output){
		if(output != null){
			Object[] stuff = new Object[9];
			this.output = APIUtils.convertToStack(output);
			for(Object thing : CraftingManager.getInstance().getRecipeList())
				if(thing instanceof IRecipe){
					IRecipe recipe = (IRecipe)thing;
					if(recipe.getRecipeOutput() != null && recipe.getRecipeOutput().isItemEqual(this.output)){
						if(recipe instanceof ShapedRecipes)
							for(int i = 0; i < recipe.getRecipeSize(); i++)
								stuff[i] = ((ShapedRecipes) recipe).recipeItems[i];
						if(recipe instanceof ShapelessRecipes)
							for(int i = 0; i < recipe.getRecipeSize(); i++)
								stuff[i] = ((ShapelessRecipes) recipe).recipeItems.get(i);
						if(recipe instanceof ShapedOreRecipe)
							for(int i = 0; i < recipe.getRecipeSize(); i++)
								stuff[i] = ((ShapedOreRecipe) recipe).getInput()[i];
						if(recipe instanceof ShapelessOreRecipe)
							for(int i = 0; i < recipe.getRecipeSize(); i++)
								stuff[i] = ((ShapelessOreRecipe) recipe).getInput().get(i);

						if(recipe.getRecipeSize() == 4){
							Object[] copy = stuff.clone();
							stuff = new Object[9];
							for(int i = 0; i < 2; i++){
								stuff[i] = copy[i];
								stuff[i+3] = copy[i+2];
							}
						}
						this.output.stackSize = recipe.getRecipeOutput().stackSize;
					}
				}
			for(int i = 0; i < 9; i++)
				recipe[i] = APIUtils.convertToStack(stuff[i]);
		}

		for(ItemStack stack : recipe)
			if(stack != null && stack.getItemDamage() == OreDictionary.WILDCARD_VALUE)
				stack.setItemDamage(0);
	}

	/**
	 * Getter for the output ItemStack
	 * @return A ItemStack representing the output
	 */
	public ItemStack getOutput(){
		return output;
	}

	/**
	 * Getter for the ItemStack array containing the recipe
	 * @return An array of ItemStacks representing the crafting recipe
	 */
	public ItemStack[] getCraftingRecipe(){
		return recipe;
	}

	/**
	 * Getter for the first 3 positioned ItemStacks in the crafting grid
	 * @return An ItemStack array with 3 ItemStacks
	 */
	public ItemStack[] getFirstArray(){
		return new ItemStack[]{recipe[0], recipe[1], recipe[2]};
	}

	/**
	 * Getter for the second 3 positioned ItemStacks in the crafting grid
	 * @return An ItemStack array with 3 ItemStacks
	 */
	public ItemStack[] getSecondArray(){
		return new ItemStack[]{recipe[3], recipe[4], recipe[5]};
	}

	/**
	 * Getter for the third 3 positioned ItemStacks in the crafting grid
	 * @return An ItemStack array with 3 ItemStacks
	 */
	public ItemStack[] getThirdArray(){
		return new ItemStack[]{recipe[6], recipe[7], recipe[8]};
	}

	/**
	 * Easier way to make CraftingStack arrays
	 * @param stacks A bunch of CraftingStacks
	 * @return An Array with the CraftingStacks
	 */
	public static CraftingStack[] arrayFrom(CraftingStack...stacks){
		return stacks;
	}

	@Override
	public String toString(){
		return "CraftingStack{Output: "+ output.toString()+ ", Recipe: "+ recipe.toString() +"}";
	}
}
