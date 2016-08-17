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
package com.shinoow.abyssalcraft.api.ritual;

import java.util.Map;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * A Necronomicon Enchantment Ritual
 * @author shinoow
 *
 * @since 1.7.5
 */
public class NecronomiconEnchantmentRitual extends NecronomiconRitual {

	private EnchantmentData enchantment;

	/**
	 * A Necronomicon Creation Ritual
	 * @param unlocalizedName A string representing the ritual name
	 * @param bookType Necronomicon book type required
	 * @param dimension Dimension where the ritual can be peformed
	 * @param requiredEnergy Amount of Potential Energy required to perform
	 * @param requiresSacrifice If the ritual requires a living sacrifice
	 * @param enchantment EnchantmentData for the Enchantment applied through the ritual
	 * @param offerings Components used to perform the ritual, are consumed afterwards
	 */
	public NecronomiconEnchantmentRitual(String unlocalizedName, int bookType, int dimension, float requiredEnergy, boolean requiresSacrifice, EnchantmentData enchantment, Object...offerings) {
		super(unlocalizedName, bookType, dimension, requiredEnergy, requiresSacrifice, offerings);
		this.enchantment = enchantment;
	}

	/**
	 * A Necronomicon Creation Ritual
	 * @param unlocalizedName A string representing the ritual name
	 * @param bookType Necronomicon book type required
	 * @param dimension Dimension where the ritual can be peformed
	 * @param requiredEnergy Amount of Potential Energy required to perform
	 * @param enchantment EnchantmentData for the Enchantment applied through the ritual
	 * @param offerings Components used to perform the ritual, are consumed afterwards
	 */
	public NecronomiconEnchantmentRitual(String unlocalizedName, int bookType, int dimension, float requiredEnergy, EnchantmentData enchantment, Object...offerings) {
		this(unlocalizedName, bookType, dimension, requiredEnergy, false, enchantment, offerings);
	}

	/**
	 * A Necronomicon Creation Ritual
	 * @param unlocalizedName A string representing the ritual name
	 * @param bookType Necronomicon book type required
	 * @param requiredEnergy Amount of Potential Energy required to perform
	 * @param enchantment EnchantmentData for the Enchantment applied through the ritual
	 * @param offerings Components used to perform the ritual, are consumed afterwards
	 */
	public NecronomiconEnchantmentRitual(String unlocalizedName, int bookType, float requiredEnergy, EnchantmentData enchantment, Object...offerings) {
		this(unlocalizedName, bookType, -1, requiredEnergy, enchantment, offerings);
	}

	@Override
	public boolean requiresItemSacrifice(){
		return true;
	}

	/**
	 * Getter for the EnchantmentData
	 * @return EnchantmentData for the Enchantment applied through the ritual
	 */
	public EnchantmentData getEnchantment(){
		return enchantment;
	}

	@Override
	public boolean canCompleteRitual(World world, BlockPos pos, EntityPlayer player) {

		TileEntity altar = world.getTileEntity(pos);

		NBTTagCompound compound = new NBTTagCompound();
		altar.writeToNBT(compound);
		NBTTagCompound nbtItem = compound.getCompoundTag("Item");

		return canEnchant(ItemStack.loadItemStackFromNBT(nbtItem));
	}

	private boolean canEnchant(ItemStack stack){
		if(stack == null) return false;
		if(stack.isItemEnchanted()){
			Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(stack);
			for(Enchantment ench : enchantments.keySet())
				if(!ench.canApplyTogether(enchantment.enchantmentobj) || !enchantment.enchantmentobj.canApplyTogether(ench))
					return false;
			return enchantment.enchantmentobj.canApply(stack);
		}
		return enchantment.enchantmentobj.canApply(stack);
	}

	@Override
	protected void completeRitualClient(World world, BlockPos pos, EntityPlayer player) {
		TileEntity altar = world.getTileEntity(pos);

		NBTTagCompound compound = new NBTTagCompound();
		altar.writeToNBT(compound);
		NBTTagCompound nbtItem = compound.getCompoundTag("Item");

		if(canEnchant(ItemStack.loadItemStackFromNBT(nbtItem))){
			ItemStack item = ItemStack.loadItemStackFromNBT(nbtItem);
			item.addEnchantment(enchantment.enchantmentobj, enchantment.enchantmentLevel);
			item.writeToNBT(nbtItem);
			compound.setTag("Item", nbtItem);
		}
		altar.readFromNBT(compound);
	}

	@Override
	protected void completeRitualServer(World world, BlockPos pos, EntityPlayer player) {
		TileEntity altar = world.getTileEntity(pos);

		NBTTagCompound compound = new NBTTagCompound();
		altar.writeToNBT(compound);
		NBTTagCompound nbtItem = compound.getCompoundTag("Item");

		if(canEnchant(ItemStack.loadItemStackFromNBT(nbtItem))){
			ItemStack item = ItemStack.loadItemStackFromNBT(nbtItem);
			item.addEnchantment(enchantment.enchantmentobj, enchantment.enchantmentLevel);
			item.writeToNBT(nbtItem);
			compound.setTag("Item", nbtItem);
		}
		altar.readFromNBT(compound);
	}
}
