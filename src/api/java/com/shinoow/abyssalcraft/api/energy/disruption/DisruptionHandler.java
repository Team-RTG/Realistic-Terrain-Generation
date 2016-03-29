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

import java.util.Iterator;
import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLLog;

import org.apache.logging.log4j.Level;

import com.google.common.collect.Lists;
import com.shinoow.abyssalcraft.api.energy.EnergyEnum.DeityType;

/**
 * Handler for disruptions (when something bad happens during Potential Energy manipulation)
 * @author shinoow
 *
 * @since 1.5
 */
public class DisruptionHandler {

	private List<DisruptionEntry> disruptions = Lists.newArrayList();

	private static final DisruptionHandler instance = new DisruptionHandler();

	public static DisruptionHandler instance(){
		return instance;
	}

	/**
	 * Registers a Disruption Entry
	 * @param disruption The Disruption
	 * 
	 * @since 1.5
	 */
	public void registerDisruption(DisruptionEntry disruption){
		Iterator<DisruptionEntry> iter = disruptions.iterator();
		DisruptionEntry compare;
		do {
			if(!iter.hasNext()){
				disruptions.add(disruption);
				return;
			}
			compare = iter.next();
			if(disruption.getUnlocalizedName().equals(compare.getUnlocalizedName())){
				FMLLog.log("DisruptionHandler", Level.ERROR, "Disruption Entry already registered: %s", disruption.getUnlocalizedName());
				return;
			}
		} while (!disruption.getUnlocalizedName().equals(compare.getUnlocalizedName()));
	}

	/**
	 * Used to fetch a list of disruptions
	 * @return An ArrayList containing all registered Disruptions
	 * 
	 * @since 1.5
	 */
	public List<DisruptionEntry> getDisruptions(){
		return disruptions;
	}

	/**
	 * Generates a Disruption
	 * @param deity Deity tied to the manipulator
	 * @param world Current World
	 * @param pos Current BlockPos
	 * @param players Nearby players (16 block radius or larger)
	 * 
	 * @since 1.5
	 */
	public void generateDisruption(DeityType deity, World world, BlockPos pos, List<EntityPlayer> players){
		List<DisruptionEntry> dis = Lists.newArrayList();

		if(deity == null){
			for(DisruptionEntry entry : disruptions)
				if(entry.getDeity() == null)
					dis.add(entry);
		} else
			for(DisruptionEntry entry : disruptions)
				if(entry.getDeity() == deity || entry.getDeity() == null)
					dis.add(entry);

		dis.get(world.rand.nextInt(dis.size())).disrupt(world, pos, players);
	}
}
