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
import java.util.Map;
import java.util.Map.Entry;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class TransmutatorRecipes {

	private static final TransmutatorRecipes transmutationBase = new TransmutatorRecipes();
	/** The list of transmutation results. */
	private Map<ItemStack, ItemStack> transmutationList = new HashMap<ItemStack, ItemStack>();
	private Map<ItemStack, Float> experienceList = new HashMap<ItemStack, Float>();

	public static TransmutatorRecipes instance()
	{
		return transmutationBase;
	}

	private TransmutatorRecipes()
	{
		transmute(Items.diamond, new ItemStack(Items.coal, 64), 0.2F);
		transmute(Items.water_bucket, new ItemStack(Blocks.water, 1), 0.0F);
		transmute(Items.lava_bucket, new ItemStack(Blocks.lava, 1), 0.0F);
		transmute(Blocks.wool, new ItemStack(Items.string, 4), 0.0F);
		transmute(Blocks.gravel, new ItemStack(Items.flint, 2), 0.0F);
		transmute(Blocks.quartz_block, new ItemStack(Items.quartz, 4), 0.0F);
		transmute(Blocks.nether_brick, new ItemStack(Items.netherbrick, 4), 0.0F);
		transmute(Items.netherbrick, new ItemStack(Blocks.netherrack), 0.0F);
		transmute(Blocks.water, new ItemStack(Blocks.ice, 8), 0.0F);
		transmute(Items.wheat, new ItemStack(Items.wheat_seeds), 0.0F);
		transmute(Items.wheat_seeds, new ItemStack(Items.wheat), 0.0F);
	}

	public void transmute(Block input, ItemStack output, float xp)
	{
		transmute(Item.getItemFromBlock(input), output, xp);
	}

	public void transmute(Item input, ItemStack output, float xp)
	{
		transmute(new ItemStack(input, 1, OreDictionary.WILDCARD_VALUE), output, xp);
	}

	public void transmute(ItemStack input, ItemStack output, float xp)
	{
		transmutationList.put(input, output);
		experienceList.put(output, Float.valueOf(xp));
	}

	/**
	 * Returns the transmutation result of an item.
	 */
	public ItemStack getTransmutationResult(ItemStack par1ItemStack)
	{
		for(Entry<ItemStack, ItemStack> entry : transmutationList.entrySet())
			if(areStacksEqual(par1ItemStack, entry.getKey()))
				return entry.getValue();

		return null;
	}

	private boolean areStacksEqual(ItemStack par1ItemStack, ItemStack par2ItemStack)
	{
		return par2ItemStack.getItem() == par1ItemStack.getItem() && (par2ItemStack.getItemDamage() == OreDictionary.WILDCARD_VALUE || par2ItemStack.getItemDamage() == par1ItemStack.getItemDamage());
	}

	public Map<ItemStack, ItemStack> getTransmutationList()
	{
		return transmutationList;
	}

	public float getExperience(ItemStack par1ItemStack)
	{
		float ret = par1ItemStack.getItem().getSmeltingExperience(par1ItemStack);
		if (ret != -1) return ret;

		for (Entry<ItemStack, Float> entry : experienceList.entrySet())
			if (areStacksEqual(par1ItemStack, entry.getKey()))
				return entry.getValue().floatValue();

		return 0.0F;
	}
}