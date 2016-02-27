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
package com.shinoow.abyssalcraft.api.energy.disruption;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

import com.shinoow.abyssalcraft.api.energy.EnergyEnum.DeityType;

/**
 * A Spawning Disruption Entry
 * @author shinoow
 *
 * @since 1.5
 */
public class DisruptionSpawn extends DisruptionEntry {

	private Class<? extends EntityLivingBase> entity;

	/**
	 * A Spawning Disruption Entry
	 * @param unlocalizedName A String representing the disruption name (mainly used for Necronomicon categorization)
	 * @param deity Deity whose image must be present for this to happen
	 * @param entity The Entity to spawn
	 */
	public DisruptionSpawn(String unlocalizedName, DeityType deity, Class<? extends EntityLivingBase> entity) {
		super(unlocalizedName, deity);
		this.entity = entity;
	}

	@Override
	public void disrupt(World world, BlockPos pos, List<EntityPlayer> players) {

		if(!world.isRemote){
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
	}
}
