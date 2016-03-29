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

import java.lang.reflect.InvocationTargetException;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

/**
 * A Necronomicon Summoning Ritual
 * @author shinoow
 *
 * @since 1.4
 */
public class NecronomiconSummonRitual extends NecronomiconRitual {

	private Class<? extends EntityLivingBase> entity;

	/**
	 * A Necronomicon Ritual
	 * @param unlocalizedName A string representing the ritual name
	 * @param bookType Necronomicon book type required
	 * @param dimension Dimension where the ritual can be peformed
	 * @param requiredEnergy Amount of Potential Energy required to perform
	 * @param remnantHelp If Remnants can aid you when performing the ritual
	 * @param entity Class representing the entity this ritual summons
	 * @param offerings Components used to perform the ritual, are consumed afterwards
	 */
	public NecronomiconSummonRitual(String unlocalizedName, int bookType, int dimension, float requiredEnergy, boolean remnantHelp, Class<? extends EntityLivingBase> entity, Object...offerings) {
		super(unlocalizedName, bookType, dimension, requiredEnergy, remnantHelp, offerings);
		this.entity = entity;
	}

	/**
	 * A Necronomicon Ritual
	 * @param unlocalizedName A string representing the ritual name
	 * @param bookType Necronomicon book type required
	 * @param dimension Dimension where the ritual can be peformed
	 * @param requiredEnergy Amount of Potential Energy required to perform
	 * @param entity Class representing the entity this ritual summons
	 * @param offerings Components used to perform the ritual, are consumed afterwards
	 */
	public NecronomiconSummonRitual(String unlocalizedName, int bookType, int dimension, float requiredEnergy, Class<? extends EntityLivingBase> entity, Object...offerings) {
		this(unlocalizedName, bookType, dimension, requiredEnergy, false, entity, offerings);
	}

	/**
	 * A Necronomicon Ritual
	 * @param unlocalizedName A string representing the ritual name
	 * @param bookType Necronomicon book type required
	 * @param requiredEnergy Amount of Potential Energy required to perform
	 * @param entity Class representing the entity this ritual summons
	 * @param offerings Components used to perform the ritual, are consumed afterwards
	 */
	public NecronomiconSummonRitual(String unlocalizedName, int bookType, float requiredEnergy, Class<? extends EntityLivingBase> entity, Object...offerings) {
		this(unlocalizedName, bookType, -1, requiredEnergy, entity, offerings);
	}

	/**
	 * Getter for the entity
	 * @return A Class that represents the entity this ritual summons
	 */
	public Class<? extends EntityLivingBase> getEntity(){
		return entity;
	}

	@Override
	public boolean canCompleteRitual(World world, BlockPos pos, EntityPlayer player) {

		return true;
	}

	@Override
	protected void completeRitualServer(World world, BlockPos pos, EntityPlayer player){

		EntityLivingBase entityliving = null;
		try {
			entityliving = entity.getConstructor(World.class).newInstance(world);
		} catch (InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		if(entityliving != null){
			entityliving.setLocationAndAngles(pos.getX(), pos.getY() + 1, pos.getZ(), entityliving.rotationYaw, entityliving.rotationPitch);
			((EntityLiving) entityliving).onInitialSpawn(world.getDifficultyForLocation(pos.up()), (IEntityLivingData)null);
			world.spawnEntityInWorld(entityliving);
		}
	}

	@Override
	protected void completeRitualClient(World world, BlockPos pos, EntityPlayer player){
		EntityLivingBase entityliving = null;
		try {
			entityliving = entity.getConstructor(World.class).newInstance(world);
		} catch (InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		if(entityliving == null) player.addChatMessage(new TextComponentString("The Entity could not be summoned."));

	}
}
