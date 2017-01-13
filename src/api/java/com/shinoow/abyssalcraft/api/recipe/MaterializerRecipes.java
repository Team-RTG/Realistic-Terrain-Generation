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
package com.shinoow.abyssalcraft.api.recipe;

import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.oredict.OreDictionary;

import com.google.common.collect.Lists;
import com.shinoow.abyssalcraft.api.APIUtils;

public class MaterializerRecipes {

	private static final MaterializerRecipes materializerBase = new MaterializerRecipes();
	/** The list of materialization results. */
	private List<Materialization> materializationList = Lists.newArrayList();

	public static MaterializerRecipes instance()
	{
		return materializerBase;
	}

	private MaterializerRecipes()
	{

	}

	public void materialize(ItemStack[] input, ItemStack output){

		materialize(new Materialization(input, output));
	}

	public void materialize(Materialization materialization){
		materializationList.add(materialization);
	}

	/**
	 * Fetches a list of things that can be materialized based off of the Crystal Bag contents
	 * @param stack Crystal Bag to check for crystals
	 * @return A list of ItemStacks containing what can be materialized
	 */
	public List<ItemStack> getMaterializationResult(ItemStack stack){

		ItemStack[] inventory = extractItemsFromBag(stack);

		if(inventory == null) return null;

		List<ItemStack> displayList = Lists.newArrayList();

		for(Materialization mat : materializationList)
			if(arrayContainsOtherArray(inventory, mat.input))
				displayList.add(mat.output);

		return displayList;
	}

	/**
	 * Attempts to materialize an ItemStack
	 * @param output ItemStack to materialize
	 * @param bag Crystal Bag to use crystals from
	 */
	public void processMaterialization(ItemStack output, ItemStack bag){

		ItemStack[] inventory = extractItemsFromBag(bag);

		if(inventory == null) return;

		Materialization mat = getMaterializationFor(output);

		if(mat == null) return;

		List<ItemStack> recipe = Lists.newArrayList(mat.input);

		for(int i = 0; i < inventory.length; i++)
			for(ItemStack recipeItem : recipe){
				ItemStack invItem = inventory[i];
				if(areStacksEqual(invItem, recipeItem))
					if(invItem.getCount() >= recipeItem.getCount()){
						invItem.shrink(recipeItem.getCount());
						if(invItem.isEmpty()) inventory[i] = ItemStack.EMPTY;
						recipe.remove(recipeItem);
						break;
					} else {
						recipeItem.shrink(invItem.getCount());
						inventory[i] = ItemStack.EMPTY;
						break;
					}
			}

		if(recipe.isEmpty())
			replaceBagContents(bag, inventory);
	}

	/**
	 * Fetches the materialization recipe for a specific ItemStack
	 * @param output Recipe output to find a recipe for
	 * @return A materialization recipe if one exists, otherwise null
	 */
	private Materialization getMaterializationFor(ItemStack output){
		for(Materialization mat : materializationList)
			if(areStacksEqual(output, mat.output)) return mat;
		return null;
	}

	/**
	 * Helper method for fetching the stored content of a Crystal Bag
	 * @param bag Crystal Bag to extract crystals from
	 * @return An array of ItemStacks (provided the bag has an inventory, and it's contents are only crystals), otherwise null
	 */
	private ItemStack[] extractItemsFromBag(ItemStack bag){

		ItemStack[] inventory = null;

		if(bag.getTagCompound() == null)
			bag.setTagCompound(new NBTTagCompound());
		if(bag.getTagCompound().hasKey("ItemInventory")){
			NBTTagList items = bag.getTagCompound().getTagList("ItemInventory", 10);

			inventory = new ItemStack[items.tagCount()];
			for (int i = 0; i < items.tagCount(); ++i)
			{
				NBTTagCompound item = items.getCompoundTagAt(i);
				//				byte slot = item.getByte("Slot");

				inventory[i] = new ItemStack(item);
			}
		}

		if(inventory == null) return null;

		for(ItemStack item : inventory)
			if(!APIUtils.isCrystal(item)) return null;

		return inventory;
	}

	/**
	 * Updates the contents of the Crystal Bag (after consuming crystals to materialize stuff)
	 * @param bag Crystal Bag ItemStack
	 * @param inventory New inventory content to replace the old
	 */
	private void replaceBagContents(ItemStack bag, ItemStack[] inventory){
		if(!bag.hasTagCompound())
			bag.setTagCompound(new NBTTagCompound());
		NBTTagCompound tag = new NBTTagCompound();
		bag.writeToNBT(tag);

		NBTTagList items = new NBTTagList();

		for(int i = 0; i < inventory.length; i++)
			if(!inventory[i].isEmpty()){
				NBTTagCompound item = new NBTTagCompound();
				item.setInteger("Slot", i);
				inventory[i].writeToNBT(item);

				items.appendTag(item);
			}

		tag.setTag("ItemInventory", items);

		bag.deserializeNBT(tag);
	}

	private boolean areStacksEqual(ItemStack par1ItemStack, ItemStack par2ItemStack)
	{
		if(par1ItemStack.isEmpty() || par2ItemStack.isEmpty()) return false;
		return par2ItemStack.getItem() == par1ItemStack.getItem() && (par2ItemStack.getItemDamage() == OreDictionary.WILDCARD_VALUE || par2ItemStack.getItemDamage() == par1ItemStack.getItemDamage());
	}

	/**
	 * Compares two arrays, checking if the first one contains the contents of the second
	 * @param array1 First array
	 * @param array2 Second array
	 * @return True if the first array contains the contents of the second, otherwise false
	 */
	private boolean arrayContainsOtherArray(ItemStack[] array1, ItemStack[] array2){
		List<ItemStack> inventory = Lists.newArrayList(array1);
		List<ItemStack> recipe = Lists.newArrayList(array2);

		if(inventory.size() >= recipe.size())
			for(ItemStack invItem : inventory)
				for(ItemStack recipeItem : recipe)
					if(areStacksEqual(invItem, recipeItem))
						if(invItem.getCount() >= recipeItem.getCount()){
							invItem.shrink(recipeItem.getCount());
							if(invItem.isEmpty()) invItem = ItemStack.EMPTY;
							recipe.remove(recipeItem);
							break;
						} else {
							recipeItem.shrink(invItem.getCount());
							invItem = ItemStack.EMPTY;
							break;
						}

		return recipe.isEmpty();
	}

	public List<Materialization> getMaterializationList()
	{
		return materializationList;
	}
}
