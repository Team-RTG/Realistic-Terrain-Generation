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
package com.shinoow.abyssalcraft.api.entity;

import java.util.List;
import java.util.UUID;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.launchwrapper.Launch;
import net.minecraft.potion.Potion;

import com.google.common.collect.Lists;
import com.shinoow.abyssalcraft.api.AbyssalCraftAPI;
import com.shinoow.abyssalcraft.api.item.ACItems;

/**
 * Utility class for handling things regarding entities.<br>
 * Also handles the Lesser Shoggoth Food list.
 * @author shinoow
 * 
 * @since 1.7.5
 *
 */
public final class EntityUtil {

	private EntityUtil(){}

	private static List<Class<? extends EntityLivingBase>> shoggothFood = Lists.newArrayList();

	/**
	 * Checks if the Entity is immune to the Coralium Plague
	 * @param par1 The Entity to check
	 * @return True if the Entity is immune, otherwise false
	 */
	public static boolean isEntityCoralium(EntityLivingBase par1){
		return par1 instanceof ICoraliumEntity || par1 instanceof EntityPlayer && isPlayerCoralium((EntityPlayer)par1);
	}

	/**
	 * Checks if a Player has a certain name, and nulls the Coralium Plague if they do
	 * @param par1 The Player to check
	 * @return True if the Player has a certain name, otherwise false
	 */
	public static final boolean isPlayerCoralium(EntityPlayer par1){
		if(Vars.dev)
			return par1.getName().equals("shinoow") || par1.getName().equals("Oblivionaire");
		else return par1.getUniqueID().equals(Vars.uuid1) || par1.getUniqueID().equals(Vars.uuid2);
	}

	/**
	 * Checks if the Entity is immune to the Dread Plague
	 * @param par1 The Entity to check
	 * @return True if the Entity is immune, otherwise false
	 */
	public static boolean isEntityDread(EntityLivingBase par1){
		return par1 instanceof IDreadEntity;
	}

	/**
	 * Checks if the Entity is immune to Antimatter
	 * @param par1 The Entity to check
	 * @return True if the Entity is immune, otherwise false
	 */
	public static boolean isEntityAnti(EntityLivingBase par1){
		return par1 instanceof IAntiEntity;
	}

	/**
	 * Checks if a Player has a Necronomicon
	 * @param player The Player to check
	 * @return True if the Player has a Necronomicon, otherwise false
	 */
	public static boolean hasNecronomicon(EntityPlayer player){
		return player.inventory.hasItemStack(new ItemStack(ACItems.necronomicon)) || player.inventory.hasItemStack(new ItemStack(ACItems.abyssal_wasteland_necronomicon))
				|| player.inventory.hasItemStack(new ItemStack(ACItems.dreadlands_necronomicon)) || player.inventory.hasItemStack(new ItemStack(ACItems.omothol_necronomicon))
				|| player.inventory.hasItemStack(new ItemStack(ACItems.abyssalnomicon));
	}

	/**
	 * Simplified way to crosscheck if a Entity is immune to a Potion before trying to apply it<br>
	 * (applies to AbyssalCraft Potions only)
	 * @param entity The Entity to check
	 * @param potion The Potion to check
	 * @return True if the Entity is immune, otherwise false
	 */
	public static boolean isEntityImmune(EntityLivingBase entity, Potion potion){
		return potion == AbyssalCraftAPI.coralium_plague && isEntityCoralium(entity) ||
				potion == AbyssalCraftAPI.dread_plague && isEntityDread(entity) ||
				potion == AbyssalCraftAPI.antimatter_potion && isEntityAnti(entity);
	}

	/**
	 * Adds the entity to a list of entities that the Lesser Shoggoth eats
	 * (Note: It's useless to add your entity here if it extends {@link EntityAnimal}, {@link EntityAmbientCreature}, {@link EntityWaterMob} or {@link EntityTameable}).
	 * If your Entity's superclass is a subclass of EntityTameable, you will need to add the superclass.
	 * @param clazz The potential "food" for the Lesser Shoggoth
	 */
	public static void addShoggothFood(Class<? extends EntityLivingBase> clazz){
		shoggothFood.add(clazz);
	}

	/**
	 * Used by the Lesser Shoggoth to fetch a list of things to eat
	 * @return An ArrayList containing Entity classes
	 */
	public static List<Class<? extends EntityLivingBase>> getShoggothFood(){
		return shoggothFood;
	}

	/**
	 * Checks if the Entity class, it's superclass or it's superclass' superclass is food
	 * @param entity The Entity to check
	 * @return true if the Entity is food, otherwise false
	 */
	public static boolean isShoggothFood(EntityLivingBase entity){
		return shoggothFood.contains(entity.getClass()) ? true : shoggothFood.contains(entity.getClass().getSuperclass()) ? true :
			shoggothFood.contains(entity.getClass().getSuperclass().getSuperclass()) ? true : false;
	}

	static class Vars{
		static boolean dev = (Boolean)Launch.blackboard.get("fml.deobfuscatedEnvironment");
		static UUID uuid1 = UUID.fromString("a5d8abca-0979-4bb0-825a-f1ccda0b350b");
		static UUID uuid2 = UUID.fromString("08f3211c-d425-47fd-afd8-f0e7f94152c4");
	}
}
