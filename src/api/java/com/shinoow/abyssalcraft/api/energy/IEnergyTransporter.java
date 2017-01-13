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
package com.shinoow.abyssalcraft.api.energy;

import net.minecraft.util.EnumFacing;

/**
 * Interface to use on tile entities that can transport Potential Energy
 *
 * @author shinoow
 *
 * @since 1.7.5
 */
public interface IEnergyTransporter extends IEnergyContainer {

	/**
	 * Attempts to transfer PE in a given direction
	 * @param facing Direction to transfer
	 * @param energy PE quanta to transfer
	 * @return True if the transfer succeeded, otherwise false
	 */
	public void transferPE(EnumFacing facing, float energy);
}
