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

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import com.shinoow.abyssalcraft.api.entity.EntityUtil;

/**
 * A Necronomicon Area-of-Effect Ritual
 * @author shinoow
 *
 * @since 1.4
 */
public class NecronomiconPotionAoERitual extends NecronomiconRitual {

	private Object potion;

	/**
	 * A Necronomicon Potion Area-of-Effect Ritual
	 * @param unlocalizedName A string representing the ritual name
	 * @param bookType Necronomicon book type required
	 * @param dimension Dimension where the ritual can be performed
	 * @param requiredEnergy Amount of Potential Energy required to perform
	 * @param requiresSacrifice If the ritual requires a living sacrifice
	 * @param potions Either a Potion effect or a Potion ID (will last for 20 seconds)
	 * @param offerings Components used to perform the ritual, are consumed afterwards
	 */
	public NecronomiconPotionAoERitual(String unlocalizedName, int bookType, int dimension, float requiredEnergy, boolean requiresSacrifice, Object potion, Object...offerings) {
		super(unlocalizedName, bookType, dimension, requiredEnergy, requiresSacrifice, offerings);
		this.potion = potion;
	}

	/**
	 * A Necronomicon Potion Area-of-Effect Ritual
	 * @param unlocalizedName A string representing the ritual name
	 * @param bookType Necronomicon book type required
	 * @param dimension Dimension where the ritual can be peformed
	 * @param requiredEnergy Amount of Potential Energy required to perform
	 * @param potions Either a Potion effect or a Potion ID (will last for 20 seconds)
	 * @param offerings Components used to perform the ritual, are consumed afterwards
	 */
	public NecronomiconPotionAoERitual(String unlocalizedName, int bookType, int dimension, float requiredEnergy, Object potion, Object...offerings) {
		this(unlocalizedName, bookType, dimension, requiredEnergy, false, potion, offerings);
	}

	/**
	 * A Necronomicon Potion Area-of-Effect Ritual
	 * @param unlocalizedName A string representing the ritual name
	 * @param bookType Necronomicon book type required
	 * @param requiredEnergy Amount of Potential Energy required to perform
	 * @param potions Either a Potion effect or a Potion ID (will last for 20 seconds)
	 * @param offerings Components used to perform the ritual, are consumed afterwards
	 */
	public NecronomiconPotionAoERitual(String unlocalizedName, int bookType, float requiredEnergy, Object potion, Object...offerings) {
		this(unlocalizedName, bookType, -1, requiredEnergy, potion, offerings);
	}

	/**
	 * A getter for the Potion Effect
	 * @return Either a Potion Effect or null
	 */
	public Potion getPotionEffect(){
		if(potion instanceof Potion)
			return (Potion) potion;
		if(potion instanceof Integer)
			return Potion.getPotionById((int) potion);
		return null;
	}

	@Override
	public boolean canCompleteRitual(World world, BlockPos pos, EntityPlayer player) {

		return true;
	}

	@Override
	protected void completeRitualServer(World world, BlockPos pos, EntityPlayer player){

		List<Entity> entities = world.getEntitiesWithinAABBExcludingEntity(player, new AxisAlignedBB(pos).expand(16, 3, 16));

		if(!entities.isEmpty())
			for(Entity entity : entities)
				if(entity instanceof EntityLivingBase && !entity.isDead)
					if(!EntityUtil.isEntityImmune((EntityLivingBase) entity, getPotionEffect()))
						((EntityLiving)entity).addPotionEffect(new PotionEffect(getPotionEffect(), 400));
	}

	@Override
	protected void completeRitualClient(World world, BlockPos pos, EntityPlayer player){}
}
