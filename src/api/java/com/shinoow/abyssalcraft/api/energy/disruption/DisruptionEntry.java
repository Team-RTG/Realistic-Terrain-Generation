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

import java.util.List;

import com.shinoow.abyssalcraft.api.energy.EnergyEnum.DeityType;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

/**
 * A Disruption Entry
 * @author shinoow
 *
 * @since 1.5
 */
public abstract class DisruptionEntry {

	private DeityType deity;
	private String unlocalizedName;

	/**
	 * A Disruption Entry
	 * @param unlocalizedName A String representing the disruption name (mainly used for Necronomicon categorization)
	 * @param deity Deity whose image must be present for this to happen
	 */
	public DisruptionEntry(String unlocalizedName, DeityType deity){
		this.deity = deity;
		this.unlocalizedName = unlocalizedName;
	}

	/**
	 * Fetches the Deity this disruption is limited to.<br>
	 * (If null, there's no limit)
	 */
	public DeityType getDeity(){
		return deity;
	}

	/**
	 * Used to fetch the unlocalized name for a disruption
	 * @return A string prefixed by "ac.disruption."
	 */
	public String getUnlocalizedName(){
		return "ac.disruption." + unlocalizedName;
	}

	/**
	 * Used to fetch the localized name for a disruption
	 * @return A localized string representing a name
	 */
	public String getLocalizedName(){
		return StatCollector.translateToLocal(getUnlocalizedName());
	}

	/**
	 * Used to fetch the description for the disruption
	 * @return A localized string representing a description
	 */
	public String getDescrption(){
		return StatCollector.translateToLocal(getUnlocalizedName() + ".desc");
	}

	/**
	 * This is where all the evil things happen
	 * @param world Current World
	 * @param x X-Coordinate
	 * @param y Y-Coordinate
	 * @param z Z-Coodrinate
	 * @param players Nearby Players (16 block range or larger)
	 */
	public abstract void disrupt(World world, int x, int y, int z, List<EntityPlayer> players);
}
