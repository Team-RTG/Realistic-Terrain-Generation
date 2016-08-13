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

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLLog;

import org.apache.logging.log4j.Level;

import com.google.common.collect.Lists;
import com.shinoow.abyssalcraft.api.energy.EnergyEnum.DeityType;
import com.shinoow.abyssalcraft.api.event.ACEvents.DisruptionEvent;

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
		for(DisruptionEntry entry : disruptions)
			if(disruption.getUnlocalizedName().equals(entry.getUnlocalizedName())){
				FMLLog.log("DisruptionHandler", Level.ERROR, "Disruption Entry already registered: %s", disruption.getUnlocalizedName());
				return;
			}
		disruptions.add(disruption);
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

		DisruptionEntry disruption = dis.get(world.rand.nextInt(dis.size()));

		if(!MinecraftForge.EVENT_BUS.post(new DisruptionEvent(deity, world, pos, players, disruption)))
			disruption.disrupt(world, pos, players);
	}
}