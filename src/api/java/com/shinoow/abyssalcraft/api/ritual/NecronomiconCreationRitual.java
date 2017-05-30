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

import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * A Necronomicon Creation Ritual
 * @author shinoow
 *
 * @since 1.4
 */
public class NecronomiconCreationRitual extends NecronomiconRitual {

	private ItemStack item;

	/**
	 * A Necronomicon Creation Ritual
	 * @param unlocalizedName A string representing the ritual name
	 * @param bookType Necronomicon book type required
	 * @param dimension Dimension where the ritual can be performed
	 * @param requiredEnergy Amount of Potential Energy required to perform
	 * @param requiresSacrifice If the ritual requires a living sacrifice
	 * @param item The Item given from the ritual
	 * @param offerings Components used to perform the ritual, are consumed afterwards
	 */
	public NecronomiconCreationRitual(String unlocalizedName, int bookType, int dimension, float requiredEnergy, boolean requiresSacrifice, ItemStack item, Object...offerings) {
		super(unlocalizedName, bookType, dimension, requiredEnergy, requiresSacrifice, offerings);
		this.item = item;
		if(item.getCount() > 1)
			item.setCount(1);
	}

	/**
	 * A Necronomicon Creation Ritual
	 * @param unlocalizedName A string representing the ritual name
	 * @param bookType Necronomicon book type required
	 * @param dimension Dimension where the ritual can be peformed
	 * @param requiredEnergy Amount of Potential Energy required to perform
	 * @param item The Item given from the ritual
	 * @param offerings Components used to perform the ritual, are consumed afterwards
	 */
	public NecronomiconCreationRitual(String unlocalizedName, int bookType, int dimension, float requiredEnergy, ItemStack item, Object...offerings) {
		this(unlocalizedName, bookType, dimension, requiredEnergy, false, item, offerings);
	}

	/**
	 * A Necronomicon Creation Ritual
	 * @param unlocalizedName A string representing the ritual name
	 * @param bookType Necronomicon book type required
	 * @param requiredEnergy Amount of Potential Energy required to perform
	 * @param item The Item given from the ritual
	 * @param offerings Components used to perform the ritual, are consumed afterwards
	 */
	public NecronomiconCreationRitual(String unlocalizedName, int bookType, float requiredEnergy, ItemStack item, Object...offerings) {
		this(unlocalizedName, bookType, -1, requiredEnergy, item, offerings);
	}

	/**
	 * Getter for the item
	 * @return A ItemStack representing the ritual "reward"
	 */
	public ItemStack getItem(){
		return item;
	}

	@Override
	public boolean canCompleteRitual(World world, BlockPos pos, EntityPlayer player) {

		return true;
	}

	@Override
	protected void completeRitualServer(World world, BlockPos pos, EntityPlayer player){

		world.addWeatherEffect(new EntityLightningBolt(world, pos.getX(), pos.getY() + 1, pos.getZ(), false));

		TileEntity altar = world.getTileEntity(pos);

		NBTTagCompound compound = new NBTTagCompound();
		NBTTagCompound newItem = new NBTTagCompound();
		altar.writeToNBT(compound);
		NBTTagCompound nbtItem = compound.getCompoundTag("Item");
		ItemStack stack = new ItemStack(nbtItem);

		if(stack == null || !stack.isItemEqual(item)){
			item.writeToNBT(newItem);
			compound.setTag("Item", newItem);
		}
		altar.readFromNBT(compound);
	}

	@Override
	protected void completeRitualClient(World world, BlockPos pos, EntityPlayer player){

		TileEntity altar = world.getTileEntity(pos);

		NBTTagCompound compound = new NBTTagCompound();
		NBTTagCompound newItem = new NBTTagCompound();
		altar.writeToNBT(compound);
		NBTTagCompound nbtItem = compound.getCompoundTag("Item");
		ItemStack stack = new ItemStack(nbtItem);

		if(stack == null || !stack.isItemEqual(item)){
			item.writeToNBT(newItem);
			compound.setTag("Item", newItem);
		}
		altar.readFromNBT(compound);
	}
}
