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

import java.util.*;
import java.util.Map.Entry;

import net.minecraft.item.*;
import net.minecraftforge.oredict.OreDictionary;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.shinoow.abyssalcraft.api.item.*;

public class EngraverRecipes {

	private static final EngraverRecipes engravingBase = new EngraverRecipes();

	private List<ItemStack> coins = Lists.newArrayList();
	private Map<ItemEngraving, ItemStack> engravings = Maps.newHashMap();
	private Map<ItemStack, ItemStack> engravingList = Maps.newHashMap();
	private Map<ItemStack, Float> experienceList = Maps.newHashMap();

	public static EngraverRecipes instance()
	{
		return engravingBase;
	}

	private EngraverRecipes(){}

	public void addCoin(Item coin){
		addCoin(new ItemStack(coin, 1, OreDictionary.WILDCARD_VALUE));
	}

	public void addCoin(ItemStack coin){
		coins.add(coin);
	}

	public void addEngraving(Item coin, ItemEngraving engraving, float xp){
		addEngraving(new ItemStack(coin), engraving, xp);
	}

	public void addEngraving(ItemStack coin, ItemEngraving engraving, float xp){
		engravings.put(engraving, coin);
		engravingList.put(new ItemStack(engraving), coin);
		experienceList.put(coin, xp);
	}

	/**
	 * Returns the engraving result of an item. This method doesn't do shit tbh
	 */
	public ItemStack getEngravingResult(ItemStack par1ItemStack)
	{
		if(coins.contains(par1ItemStack))
			return par1ItemStack;

		return null;
	}

	/**
	 * Returns the engraving result of an item.
	 */
	public ItemStack getEngravingResult(ItemStack par2, ItemEngraving par1)
	{
		for(ItemStack stack : coins)
			if(areStacksEqual(par2, stack))
				if(engravings.get(par1) != null)
					if(par2.getItem() != ACItems.coin && par1 == ACItems.blank_engraving ||
					par2.getItem() == ACItems.coin && par1 != ACItems.blank_engraving)
						return engravings.get(par1);

		return null;
	}

	private boolean areStacksEqual(ItemStack par1ItemStack, ItemStack par2ItemStack)
	{
		return par2ItemStack.getItem() == par1ItemStack.getItem() && (par2ItemStack.getItemDamage() == OreDictionary.WILDCARD_VALUE || par2ItemStack.getItemDamage() == par1ItemStack.getItemDamage());
	}

	/**
	 * Returns the coin list
	 */
	public List<ItemStack> getCoinList(){
		return coins;
	}

	/**
	 * Returns the actual engraving list, with engravings and coins
	 */
	public Map<ItemEngraving, ItemStack> getEngravings(){
		return engravings;
	}

	/**
	 * Returns a ItemStack version of the engraving list
	 */
	public Map<ItemStack, ItemStack> getEngravingList(){
		return engravingList;
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
