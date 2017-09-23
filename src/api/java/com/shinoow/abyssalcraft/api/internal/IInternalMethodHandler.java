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
package com.shinoow.abyssalcraft.api.internal;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import com.shinoow.abyssalcraft.api.energy.EnergyEnum.DeityType;

public interface IInternalMethodHandler {

	/**
	 * Fires a message to the client triggering a Disruption<br>
	 * <b><i>You should probably NEVER ever call this method at all, ever.<br>
	 * Seriously, this method is called in the DisruptionHandler to send<br>
	 * a Disruption to the client while firing it server-side.</i></b>
	 * @param deity Deity Type
	 * @param name Disruption Unlocalized Name
	 * @param pos BlockPos
	 * @param id Dimension ID
	 */
	public void sendDisruption(DeityType deity, String name, BlockPos pos, int id);

	/**
	 * Spawns a particle
	 * @param particleName Particle name
	 * @param world Current World
	 * @param posX X Coordinate
	 * @param posY Y Coordinate
	 * @param posZ Z Coordinate
	 * @param velX X velocity
	 * @param velY Y velocity
	 * @param velZ Z velocity
	 */
	public void spawnParticle(String particleName, World world, double posX, double posY, double posZ, double velX, double velY, double velZ);

	/**
	 * Attempts to generate a Darklands Structure.<br>
	 * This should be called during world generation for the result.
	 * @param type Structure type
	 * <ul>
	 * <li>0 = Any structure</li>
	 * <li>1 = Shrine structures</li>
	 * <li>2 = Ritual Ground structures</li>
	 * <li>3 = Buildings</li>
	 * <li>4 = Misc structures</li>
	 * </ul>
	 * @param world Current World
	 * @param random Random instance
	 * @param pos A BlockPos <br>
	 * <i>world.getHeight(new BlockPos(chunkX*16 + random.nextInt(16) + rand.nextInt(5) * (random.nextBoolean() ? -1 : 1),
	 * 0, chunkZ*16 + random.nextInt(16) rand.nextInt(5) * (random.nextBoolean() ? -1 : 1))<br></i>
	 * is what's used for the Darklands
	 */
	public void generateDarklandsStructure(int type, World world, Random random, BlockPos pos);

	/**
	 * Attempts to generate a Darklands Structure.<br>
	 * This should be called during world generation for the result.
	 * @param type Structure type
	 * <ul>
	 * <li>0 = Any structure</li>
	 * <li>1 = Shrine structures</li>
	 * <li>2 = Ritual Ground structures</li>
	 * <li>3 = Buildings</li>
	 * <li>4 = Misc structures</li>
	 * </ul>
	 * @param world Current World
	 * @param random Random instance
	 * @param pos A BlockPos <br>
	 * <i>world.getHeight(new BlockPos(chunkX*16 + random.nextInt(16) + rand.nextInt(5) * (random.nextBoolean() ? -1 : 1),
	 * 0, chunkZ*16 + random.nextInt(16) rand.nextInt(5) * (random.nextBoolean() ? -1 : 1))<br></i>
	 * is what's used for the Darklands
	 * @param spawnBlock BlockState that the structures can generate on
	 * @param extra (OPTIONAL) Additional BlockStates the structure can generate on
	 */
	public void generateDarklandsStructure(int type, World world, Random random, BlockPos pos, IBlockState spawnBlock, IBlockState...extra);
}
