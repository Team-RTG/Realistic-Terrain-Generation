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

/**
 * Interface to use on tile entities that can hold Potential Energy (WIP)<br>
 * WARNING: Any methods in this interface might end up getting removed,<br>
 * so I would suggest not using it until this text (and the WIP part) is removed.
 * 
 * @author shinoow
 *
 * @since 1.4.5
 */
public interface IEnergyContainer {

	/**
	 * Gets the Potential Energy contained within the tile entity
	 */
	public float getContainedEnergy();

	/**
	 * Gets the maximum Potential Energy the tile entity can hold
	 */
	public int getMaxEnergy();

	/**
	 * Adds Potential Energy to the tile entity
	 * @param energy Energy quanta to add
	 */
	public void addEnergy(float energy);

	/**
	 * Consumes (removes) Potential Energy from the tile entity
	 * @param energy Energy quanta to consume
	 */
	public void consumeEnergy(float energy);

	/**
	 * Returns Whether or not this container can accept Potential Energy
	 */
	public boolean canAcceptPE();
}
