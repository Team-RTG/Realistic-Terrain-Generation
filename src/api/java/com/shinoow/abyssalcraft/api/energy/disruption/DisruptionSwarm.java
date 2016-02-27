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
import java.util.Random;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

import com.shinoow.abyssalcraft.api.energy.EnergyEnum.DeityType;

/**
 * A Swarm Disruption Entry
 * @author shinoow
 *
 * @since 1.5
 */
public class DisruptionSwarm extends DisruptionEntry {

	private Class<? extends EntityLivingBase>[] entities;

	/**
	 * A Spawning Disruption Entry
	 * @param unlocalizedName A String representing the disruption name (mainly used for Necronomicon categorization)
	 * @param deity Deity whose image must be present for this to happen
	 * @param entities An array of entities to spawn
	 */
	public DisruptionSwarm(String unlocalizedName, DeityType deity, Class<? extends EntityLivingBase>...entities) {
		super(unlocalizedName, deity);
		this.entities = entities;
	}

	private int randomNum(Random rand){
		return rand.nextBoolean() ? 3 : -3;
	}

	@Override
	public void disrupt(World world, BlockPos pos, List<EntityPlayer> players) {

		if(!world.isRemote)
			for(Class<? extends EntityLivingBase> clazz : entities)
				for(int i = 0; i < 4; i++)
					try {
						EntityLivingBase entity = clazz.getConstructor(World.class).newInstance(world);
						BlockPos pos1 = new BlockPos(pos.getX() + randomNum(world.rand), pos.getY() + 1, pos.getZ() + randomNum(world.rand));
						entity.setLocationAndAngles(pos1.getX(), pos1.getY(), pos1.getZ(), entity.rotationYaw, entity.rotationPitch);
						((EntityLiving) entity).onInitialSpawn(world.getDifficultyForLocation(pos1), (IEntityLivingData)null);
						world.spawnEntityInWorld(entity);
					} catch (InstantiationException | IllegalAccessException
							| IllegalArgumentException
							| InvocationTargetException | NoSuchMethodException
							| SecurityException e) {
						e.printStackTrace();
					}
	}
}
