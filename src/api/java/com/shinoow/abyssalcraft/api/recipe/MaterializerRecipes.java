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

import java.util.Iterator;
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

	public List<ItemStack> getMaterializationResult(ItemStack stack){

		ItemStack[] inventory = null;

		if(stack.stackTagCompound == null)
			stack.stackTagCompound = new NBTTagCompound();
		if(stack.stackTagCompound.hasKey("ItemInventory")){
			NBTTagList items = stack.stackTagCompound.getTagList("ItemInventory", 10);

			inventory = new ItemStack[items.tagCount()];
			for (int i = 0; i < items.tagCount(); ++i)
			{
				NBTTagCompound item = items.getCompoundTagAt(i);
				byte slot = item.getByte("Slot");

				inventory[slot] = ItemStack.loadItemStackFromNBT(item);
			}
		}

		if(inventory == null) return null;

		for(ItemStack item : inventory)
			if(!APIUtils.isCrystal(item)) return null;

		List<ItemStack> displayList = Lists.newArrayList();

		Iterator<Materialization> iterator = materializationList.iterator();
		Materialization entry;

		do
		{
			if(!iterator.hasNext())
				return displayList;

			entry = iterator.next();
			if(arrayContainsOtherArray(inventory, entry.input))
				displayList.add(entry.output);
		}
		while(!arrayContainsOtherArray(inventory, entry.input));

		//		for(Materialization mat : materializationList){
		//			if(arrayContainsOtherArray(inventory, mat.input))
		//				displayList.add(mat.output);
		//		}

		return displayList;
	}

	private boolean areStacksEqual(ItemStack par1ItemStack, ItemStack par2ItemStack)
	{
		if(par1ItemStack == null || par2ItemStack == null) return false;
		return par2ItemStack.getItem() == par1ItemStack.getItem() && (par2ItemStack.getItemDamage() == OreDictionary.WILDCARD_VALUE || par2ItemStack.getItemDamage() == par1ItemStack.getItemDamage());
	}

	private boolean arrayContainsOtherArray(ItemStack[] array1, ItemStack[] array2){
		List<ItemStack> inventory = Lists.newArrayList(array1);
		List<ItemStack> recipe = Lists.newArrayList(array2);

		if(inventory.size() >= recipe.size())
			for(ItemStack invItem : inventory)
				for(ItemStack recipeItem : recipe)
					if(areStacksEqual(invItem, recipeItem) && invItem.stackSize >= recipeItem.stackSize){
						recipe.remove(recipeItem);
						break;
					}

		return recipe.isEmpty();
	}

	public List<Materialization> getMaterializationList()
	{
		return materializationList;
	}
}
