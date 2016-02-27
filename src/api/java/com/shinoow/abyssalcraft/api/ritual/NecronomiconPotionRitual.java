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

import java.util.Random;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

/**
 * A Necronomicon Potion Ritual
 * @author shinoow
 *
 * @since 1.4
 */
public class NecronomiconPotionRitual extends NecronomiconRitual {

	private Object potion;

	/**
	 * A Necronomicon Potion Ritual
	 * @param unlocalizedName A string representing the ritual name
	 * @param bookType Necronomicon book type required
	 * @param dimension Dimension where the ritual can be peformed
	 * @param requiredEnergy Amount of Potential Energy required to perform
	 * @param remnantHelp If Remnants can aid you when performing the ritual
	 * @param potions Either a Potion effect or a Potion ID (will last for 2 minutes)
	 * @param offerings Components used to perform the ritual, are consumed afterwards
	 */
	public NecronomiconPotionRitual(String unlocalizedName, int bookType, int dimension, float requiredEnergy, boolean remnantHelp,
			Object potion, Object...offerings) {
		super(unlocalizedName, bookType, dimension, requiredEnergy, remnantHelp, offerings);
		this.potion = potion;
	}

	/**
	 * A Necronomicon Potion Ritual
	 * @param unlocalizedName A string representing the ritual name
	 * @param bookType Necronomicon book type required
	 * @param dimension Dimension where the ritual can be peformed
	 * @param requiredEnergy Amount of Potential Energy required to perform
	 * @param potions Either a Potion effect or a Potion ID (will last for 2 minutes)
	 * @param offerings Components used to perform the ritual, are consumed afterwards
	 */
	public NecronomiconPotionRitual(String unlocalizedName, int bookType, int dimension, float requiredEnergy, Object potion,
			Object...offerings) {
		this(unlocalizedName, bookType, dimension, requiredEnergy, false, potion, offerings);
	}

	/**
	 * A Necronomicon Potion Ritual
	 * @param unlocalizedName A string representing the ritual name
	 * @param bookType Necronomicon book type required
	 * @param requiredEnergy Amount of Potential Energy required to perform
	 * @param potions Either a Potion effect or a Potion ID (will last for 2 minutes)
	 * @param offerings Components used to perform the ritual, are consumed afterwards
	 */
	public NecronomiconPotionRitual(String unlocalizedName, int bookType, float requiredEnergy, Object potion, Object...offerings) {
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
			return Potion.potionTypes[(int) potion];
		return null;
	}

	private int getBadEffect(Random rand){
		switch(rand.nextInt(5)){
		case 0:
			return Potion.blindness.id;
		case 1:
			return Potion.confusion.id;
		case 2:
			return Potion.digSlowdown.id;
		case 3:
			return Potion.hunger.id;
		case 4:
			return Potion.weakness.id;
		case 5:
			return Potion.moveSlowdown.id;
		default:
			return getBadEffect(rand);
		}
	}

	@Override
	public boolean canCompleteRitual(World world, BlockPos pos, EntityPlayer player) {

		return true;
	}

	@Override
	protected void completeRitualServer(World world, BlockPos pos, EntityPlayer player){

		player.addPotionEffect(new PotionEffect(getPotionEffect().id, 2400));
		player.addPotionEffect(new PotionEffect(getBadEffect(world.rand), 600));
	}

	@Override
	protected void completeRitualClient(World world, BlockPos pos, EntityPlayer player){}
}
