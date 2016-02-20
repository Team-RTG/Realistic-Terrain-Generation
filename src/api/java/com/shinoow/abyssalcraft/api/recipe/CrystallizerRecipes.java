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

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class CrystallizerRecipes {

	private static final CrystallizerRecipes crystallizationBase = new CrystallizerRecipes();
	/** The list of crystallization results. */
	private Map<ItemStack, ItemStack[]> crystallizationList = new HashMap<ItemStack, ItemStack[]>();
	private Map<ItemStack, Float> experienceList = new HashMap<ItemStack, Float>();

	public static CrystallizerRecipes instance()
	{
		return crystallizationBase;
	}

	private CrystallizerRecipes()
	{

	}

	public void crystallize(Block input, ItemStack output1, ItemStack output2, float xp)
	{
		crystallize(Item.getItemFromBlock(input), output1, output2, xp);
	}

	public void crystallize(Item input, ItemStack output1, ItemStack output2, float xp)
	{
		crystallize(new ItemStack(input, 1, OreDictionary.WILDCARD_VALUE), output1, output2, xp);
	}

	public void crystallize(ItemStack input, ItemStack output1, ItemStack output2, float xp)
	{
		crystallizationList.put(input, new ItemStack[]{output1, output2});
		experienceList.put(output1, Float.valueOf(xp));
	}

	/**
	 * Returns the crystallization result of an item.
	 */
	public ItemStack[] getCrystallizationResult(ItemStack par1ItemStack)
	{
		Iterator<?> iterator = crystallizationList.entrySet().iterator();
		Entry<?, ?> entry;

		do
		{
			if (!iterator.hasNext())
				return null;

			entry = (Entry<?, ?>)iterator.next();
		}
		while (!areStacksEqual(par1ItemStack, (ItemStack)entry.getKey()));

		return (ItemStack[])entry.getValue();
	}

	private boolean areStacksEqual(ItemStack par1ItemStack, ItemStack par2ItemStack)
	{
		return par2ItemStack.getItem() == par1ItemStack.getItem() && (par2ItemStack.getItemDamage() == OreDictionary.WILDCARD_VALUE|| par2ItemStack.getItemDamage() == par1ItemStack.getItemDamage());
	}

	public Map<ItemStack, ItemStack[]> getCrystallizationList()
	{
		return crystallizationList;
	}

	public float getExperience(ItemStack par1ItemStack)
	{
		float ret = par1ItemStack.getItem().getSmeltingExperience(par1ItemStack);
		if (ret != -1) return ret;

		Iterator<?> iterator = experienceList.entrySet().iterator();
		Entry<?, ?> entry;

		do
		{
			if (!iterator.hasNext())
				return 0.0F;

			entry = (Entry<?, ?>)iterator.next();
		}
		while (!areStacksEqual(par1ItemStack, (ItemStack)entry.getKey()));

		return ((Float)entry.getValue()).floatValue();
	}
}
