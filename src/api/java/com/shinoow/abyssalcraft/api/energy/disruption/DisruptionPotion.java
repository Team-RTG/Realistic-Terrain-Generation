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

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

import com.shinoow.abyssalcraft.api.AbyssalCraftAPI.ACPotions;
import com.shinoow.abyssalcraft.api.energy.EnergyEnum.DeityType;

/**
 * A Potion Disruption Entry
 * @author shinoow
 *
 * @since 1.5
 */
public class DisruptionPotion extends DisruptionEntry {

	private Potion potion;

	/**
	 * A Potion Disruption Entry
	 * @param unlocalizedName A String representing the disruption name (mainly used for Necronomicon categorization)
	 * @param deity Deity whose image must be present for this to happen
	 * @param potion A Potion Effect
	 */
	public DisruptionPotion(String unlocalizedName, DeityType deity, Potion potion) {
		super(unlocalizedName, deity);
		this.potion = potion;
	}

	private boolean isEntityImmune(Potion potion, Entity entity){
		boolean result = false;
		try {
			Class utilClass = Class.forName("com.shinoow.abyssalcraft.common.util.EntityUtil");

			result = potion == ACPotions.Coralium_plague && (Boolean)utilClass.getDeclaredMethod("isEntityCoralium", EntityLivingBase.class).invoke(null, (EntityLivingBase)entity) ||
					potion == ACPotions.Dread_plague && (Boolean)utilClass.getDeclaredMethod("isEntityDread", EntityLivingBase.class).invoke(null, (EntityLivingBase)entity) ||
					potion == ACPotions.Antimatter && (Boolean)utilClass.getDeclaredMethod("isEntityAnti", EntityLivingBase.class).invoke(null, (EntityLivingBase)entity);

		} catch (ClassNotFoundException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public void disrupt(World world, int x, int y, int z, List<EntityPlayer> players) {

		if(!world.isRemote){
			List<EntityLivingBase> entities = world.getEntitiesWithinAABB(EntityLivingBase.class, AxisAlignedBB.getBoundingBox(x, y, z, x + 1, y + 1, z + 1).expand(16, 16, 16));

			for(EntityLivingBase entity : entities)
				if(!isEntityImmune(potion, entity))
					entity.addPotionEffect(new PotionEffect(potion.id, 600));
		}
	}
}
