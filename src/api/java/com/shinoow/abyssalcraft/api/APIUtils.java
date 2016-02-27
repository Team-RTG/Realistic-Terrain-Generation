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

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import com.shinoow.abyssalcraft.api.item.ACItems;
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
			return crystal.isItemEqual(item);
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
	 * Checks if the Player has a Necronomicon<br>
	 * (Copy of EntityUtil.hasNecronomicon())
	 * @param player The Player to check
	 * @return True if the Player has a Necronomicon, otherwise false
	 * 
	 * @since 1.5
	 */
	public static boolean hasNecronomicon(EntityPlayer player){
		return player.inventory.hasItem(ACItems.necronomicon) || player.inventory.hasItem(ACItems.abyssal_wasteland_necronomicon)
				|| player.inventory.hasItem(ACItems.dreadlands_necronomicon) || player.inventory.hasItem(ACItems.omothol_necronomicon)
				|| player.inventory.hasItem(ACItems.abyssalnomicon);
	}
}
