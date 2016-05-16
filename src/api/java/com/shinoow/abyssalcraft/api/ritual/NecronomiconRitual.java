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
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;

/**
 * Base Necronomicon Ritual.<br>
 * Extend to make your own rituals.
 * @author shinoow
 *
 * @since 1.4
 */
public abstract class NecronomiconRitual {

	private Object[] offerings = new Object[8];
	private boolean requiresSacrifice;
	private int bookType;
	private int dimension;
	private String unlocalizedName;
	private float requiredEnergy;
	protected Object sacrifice;

	/**
	 * A Necronomicon Ritual
	 * @param unlocalizedName A string representing the ritual name
	 * @param bookType Necronomicon book type required
	 * @param dimension Dimension where the ritual can be performed
	 * @param requiredEnergy Amount of Potential Energy required to perform
	 * @param requiresSacrifice If the ritual requires a living sacrifice
	 * @param offerings Components used to perform the ritual, are consumed afterwards
	 */
	public NecronomiconRitual(String unlocalizedName, int bookType, int dimension, float requiredEnergy, boolean requiresSacrifice, Object...offerings){
		this.unlocalizedName = unlocalizedName;
		this.bookType = bookType;
		this.dimension = dimension;
		this.requiredEnergy = requiredEnergy;
		this.requiresSacrifice = requiresSacrifice;
		if(offerings.length < 8){
			this.offerings = new Object[offerings.length];
			for(int i = 0; i < offerings.length; i++)
				this.offerings[i] = offerings[i];
		}
		else this.offerings = offerings;
	}

	/**
	 * A Necronomicon Ritual
	 * @param unlocalizedName A string representing the ritual name
	 * @param bookType Necronomicon book type required
	 * @param dimension Dimension where the ritual can be peformed
	 * @param requiredEnergy Amount of Potential Energy required to perform
	 * @param offerings Components used to perform the ritual, are consumed afterwards
	 */
	public NecronomiconRitual(String unlocalizedName, int bookType, int dimension, float requiredEnergy, Object...offerings){
		this(unlocalizedName, bookType, dimension, requiredEnergy, false, offerings);
	}

	/**
	 * A Necronomicon Ritual
	 * @param unlocalizedName A string representing the ritual name
	 * @param bookType Necronomicon book type required
	 * @param requiredEnergy Amount of Potential Energy required to perform
	 * @param offerings Components used to perform the ritual, are consumed afterwards
	 */
	public NecronomiconRitual(String unlocalizedName, int bookType, float requiredEnergy, Object...offerings){
		this(unlocalizedName, bookType, -1, requiredEnergy, offerings);
	}

	/**
	 * Used to fetch the offerings
	 * @return An array of ItemStacks representing offerings
	 */
	public Object[] getOfferings(){
		return offerings;
	}

	/**
	 * Used to see if Remnants can aid in the ritual
	 * @return True if Remnants can aid you, false if they can't
	 * 
	 * @deprecated Has been replaced by a living sacrifice
	 */
	@Deprecated
	public boolean canRemnantAid(){
		return false;
	}

	/**
	 * Used to see if this ritual requires a living sacrifice
	 * @return True if the ritual requires a living sacrifice to be present
	 */
	public boolean requiresSacrifice(){
		return requiresSacrifice;
	}

	/**
	 * Used to fetch the required book type
	 * @return A Integer representing the book type required for the ritual
	 */
	public int getBookType(){
		return bookType;
	}

	/**
	 * Used to fetch the dimension ID (if -1, it can be performed anywhere)
	 * @return A Integer representing the ID for the dimension where the ritual can be performed
	 */
	public int getDimension(){
		return dimension;
	}

	/**
	 * Used to fetch the required Potential Energy for the ritual
	 * @return A Float representing the amount of Potential Energy required to perform the ritual
	 */
	public float getReqEnergy(){
		return requiredEnergy;
	}

	/**
	 * Used to fetch the unlocalized name for a ritual
	 * @return A string prefixed by "ac.ritual."
	 */
	public String getUnlocalizedName(){
		return "ac.ritual." + unlocalizedName;
	}

	/**
	 * Used to fetch the localized name for a ritual
	 * @return A localized string representing a name
	 */
	public String getLocalizedName(){
		return I18n.translateToLocal(getUnlocalizedName());
	}

	/**
	 * Used to fetch the description for the ritual
	 * @return A localized string representing a description
	 */
	public String getDescription(){
		return I18n.translateToLocal(getUnlocalizedName() + ".desc");
	}

	/**
	 * Getter for the sacrifice (mainly used by Infusion Rituals)
	 * @return An Object representing an item placed on the altar
	 * (should be removed/replaced in both completeRitual methods, like
	 * {@link NecronomiconCreationRitual#completeRitualClient(World, int, int, int, EntityPlayer)})
	 */
	public Object getSacrifice(){
		return sacrifice;
	}

	/**
	 * Override this to ensure that the ritual can be completed
	 * @param world Current World
	 * @param x X coordinate
	 * @param y Y coordinate
	 * @param z Z coordinate
	 * @param player Player performing the ritual
	 * @return True if all conditions are met, otherwise false
	 */
	public abstract boolean canCompleteRitual(World world, BlockPos pos, EntityPlayer player);

	/**
	 * Called when a ritual is completed
	 * @param world Current World
	 * @param x X coordinate
	 * @param y Y coordinate
	 * @param z Z coordinate
	 * @param player Player who performed the ritual
	 */
	public void completeRitual(World world, BlockPos pos, EntityPlayer player){
		if(!world.isRemote) completeRitualServer(world, pos, player);
		if(world.isRemote) completeRitualClient(world, pos, player);
	}

	/**
	 * Override this to do something client-side when the ritual is completed
	 * @param world Current World
	 * @param x X coordinate
	 * @param y Y coordinate
	 * @param z Z coordinate
	 * @param player Player who performed the ritual
	 */
	protected abstract void completeRitualClient(World world, BlockPos pos, EntityPlayer player);

	/**
	 * Override this to do something server-side when the ritual is completed
	 * @param world Current World
	 * @param x X coordinate
	 * @param y Y coordinate
	 * @param z Z coordinate
	 * @param player Player who performed the ritual
	 */
	protected abstract void completeRitualServer(World world, BlockPos pos, EntityPlayer player);
}
