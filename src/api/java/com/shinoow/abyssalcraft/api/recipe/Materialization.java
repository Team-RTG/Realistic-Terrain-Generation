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

import com.shinoow.abyssalcraft.api.APIUtils;
import com.shinoow.abyssalcraft.api.AbyssalCraftAPI;
import com.shinoow.abyssalcraft.api.item.ICrystal;

import net.minecraft.item.ItemStack;

/**
 * A Materializer recipe
 * @author shinoow
 *
 * @since 1.5
 */
public class Materialization {

	public final ItemStack output;
	public final ItemStack[] input;

	/**
	 * A Materializer recipe.<br>
	 * Note: all inputs has to be either {@link ICrystal}s or be registered in the Crystal List {@link AbyssalCraftAPI#addCrystal(ItemStack)}
	 * @param input An array of ItemStacks (maximum is 5)
	 * @param output The output
	 */
	public Materialization(ItemStack[] input, ItemStack output){

		if(input.length > 5) throw new ArrayIndexOutOfBoundsException("Array is too large ("+input.length+")! Maximum size is 5!");
		for(ItemStack item : input)
			if(!APIUtils.isCrystal(item)) throw new ClassCastException("All of the input items has to be Crystals!");

		this.output = output;
		this.input = input;
	}
}
