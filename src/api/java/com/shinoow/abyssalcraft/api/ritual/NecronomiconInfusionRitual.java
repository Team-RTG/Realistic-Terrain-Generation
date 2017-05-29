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

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * A Necronomicon Infusion Ritual
 * @author shinoow
 *
 * @since 1.4
 */
public class NecronomiconInfusionRitual extends NecronomiconCreationRitual {

	/**
	 * A Necronomicon Infusion Ritual
	 * @param unlocalizedName A string representing the ritual name
	 * @param bookType Necronomicon book type required
	 * @param dimension Dimension where the ritual can be peformed
	 * @param requiredEnergy Amount of Potential Energy required to perform
	 * @param requiresSacrifice If the ritual requires a living sacrifice
	 * @param item The Item given from the ritual
	 * @param sacrifice Item to upgrade
	 * @param offerings Components used to perform the ritual, are consumed afterwards
	 */
	public NecronomiconInfusionRitual(String unlocalizedName, int bookType, int dimension, float requiredEnergy,
			boolean requiresSacrifice, ItemStack item, Object sacrifice, Object...offerings) {
		super(unlocalizedName, bookType, dimension, requiredEnergy, requiresSacrifice, item, offerings);
		this.sacrifice = sacrifice;
	}

	/**
	 * A Necronomicon Infusion Ritual
	 * @param unlocalizedName A string representing the ritual name
	 * @param bookType Necronomicon book type required
	 * @param dimension Dimension where the ritual can be peformed
	 * @param requiredEnergy Amount of Potential Energy required to perform
	 * @param item The Item given from the ritual
	 * @param sacrifice Item to upgrade
	 * @param offerings Components used to perform the ritual, are consumed afterwards
	 */
	public NecronomiconInfusionRitual(String unlocalizedName, int bookType, int dimension, float requiredEnergy,
			ItemStack item, Object sacrifice, Object...offerings) {
		this(unlocalizedName, bookType, dimension, requiredEnergy, false, item, sacrifice, offerings);

	}

	/**
	 * A Necronomicon Infusion Ritual
	 * @param unlocalizedName A string representing the ritual name
	 * @param bookType Necronomicon book type required
	 * @param requiredEnergy Amount of Potential Energy required to perform
	 * @param item The Item given from the ritual
	 * @param sacrifice Item to upgrade
	 * @param offerings Components used to perform the ritual, are consumed afterwards
	 */
	public NecronomiconInfusionRitual(String unlocalizedName, int bookType, float requiredEnergy, ItemStack item, Object sacrifice, Object...offerings) {
		this(unlocalizedName, bookType, -1, requiredEnergy, item, sacrifice, offerings);
	}

	@Override
	public boolean canCompleteRitual(World world, BlockPos pos, EntityPlayer player) {

		TileEntity altar = world.getTileEntity(pos);

		NBTTagCompound compound = new NBTTagCompound();
		altar.writeToNBT(compound);
		NBTTagCompound nbtItem = compound.getCompoundTag("Item");

		return RitualRegistry.instance().areObjectsEqual(ItemStack.loadItemStackFromNBT(nbtItem), sacrifice);
	}

	@Override
	protected void completeRitualServer(World world, BlockPos pos, EntityPlayer player){
		if(canCompleteRitual(world, pos, player)) super.completeRitualServer(world, pos, player);
	}

	@Override
	protected void completeRitualClient(World world, BlockPos pos, EntityPlayer player){
		if(canCompleteRitual(world, pos, player)) super.completeRitualClient(world, pos, player);
	}
}
