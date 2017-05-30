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
package com.shinoow.abyssalcraft.api;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import com.shinoow.abyssalcraft.api.item.ICrystal;
import com.shinoow.abyssalcraft.api.recipe.EngraverRecipes;

/**
 * Utilities for the AbyssalCraft API
 * @author shinoow
 *
 * @since 1.4
 */
public class APIUtils {

	/**
	 * Checks if the ItemStack is a Crystal
	 * @param item ItemStack to check
	 * @return True if the ItemStack is a Crystal, otherwise false
	 *
	 * @since 1.4
	 */
	public static boolean isCrystal(ItemStack item){
		if(item.getItem() instanceof ICrystal)
			return true;
		for(ItemStack crystal: AbyssalCraftAPI.getCrystals())
			if(crystal.getItem() == item.getItem() && (crystal.getItemDamage() == OreDictionary.WILDCARD_VALUE
			|| crystal.getItemDamage() == item.getItemDamage()))
				return true;
		return false;
	}

	/**
	 * Checks if the ItemStack is a Coin
	 * @param item ItemStack to check
	 * @return True if the ItemStack is a Coin, otherwise false
	 *
	 * @since 1.5
	 */
	public static boolean isCoin(ItemStack item){
		for(ItemStack coin : EngraverRecipes.instance().getCoinList())
			if(coin.getItem() == item.getItem() && (coin.getItemDamage() == OreDictionary.WILDCARD_VALUE
			|| coin.getItemDamage() == item.getItemDamage()))
				return true;
		return false;
	}

	/**
	 * Converts an Object to an ItemStack, if possible
	 * @param obj Object to convert
	 * @return An ItemStack, or a ClassCastException if not possible
	 */
	public static ItemStack convertToStack(Object obj){
		if(obj == null)
			return ItemStack.EMPTY;
		else if(obj instanceof ItemStack)
			return ((ItemStack)obj).copy();
		else if(obj instanceof Item)
			return new ItemStack((Item)obj);
		else if(obj instanceof Block)
			return new ItemStack((Block)obj);
		else if(obj instanceof ItemStack[])
			return ((ItemStack[])obj)[0].copy();
		else if(obj instanceof String)
			return OreDictionary.getOres((String)obj).get(0).copy();
		else if(obj instanceof List)
			return ((ItemStack)((List) obj).get(0)).copy();
		else throw new ClassCastException("Not a Item, Block, ItemStack, Array of ItemStacks, String or List of ItemStacks!");
	}
}