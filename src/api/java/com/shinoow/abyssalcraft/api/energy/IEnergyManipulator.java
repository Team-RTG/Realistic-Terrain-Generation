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

import com.shinoow.abyssalcraft.api.energy.EnergyEnum.AmplifierType;
import com.shinoow.abyssalcraft.api.energy.EnergyEnum.DeityType;

/**
 * Interface to use on tile entities that can manipulate Ley Lines (WIP)<br>
 * WARNING: Any methods in this interface might end up getting removed,<br>
 * so I would suggest not using it until this text (and the WIP part) is removed.
 * 
 * @author shinoow
 * 
 * @since 1.4.5
 */
public interface IEnergyManipulator {

	/**
	 * Gets the quanta of Potential Energy that the tile entity can drain
	 */
	public float getEnergyQuanta();

	/**
	 * Activates the Amplifier boost
	 * @param amp Current Amplifier
	 * @param deity Current Deity
	 */
	public void setActive(AmplifierType amp, DeityType deity);

	/**
	 * Checks if the Amplifier boost is active
	 */
	public boolean isActive();

	/**
	 * Returns the Deity associated with this manipulator (can be null)
	 */
	public DeityType getDeity();

	/**
	 * Used to calculate Amplification through Charms.<br>
	 * Should be called whenever something that can be amplified is calculated.
	 * @param type Type to check
	 */
	public float getAmplifier(AmplifierType type);

	/**
	 * Returns the Active Deity (assigned in {@link #setActive(AmplifierType, DeityType)})
	 */
	public DeityType getActiveDeity();

	/**
	 * Returns the active Deity (assigned in {@link #setActive(AmplifierType, DeityType)})
	 */
	public AmplifierType getActiveAmplifier();

	/**
	 * Sets the Active Deity
	 * @param deity Deity Type
	 */
	public void setActiveDeity(DeityType deity);

	/**
	 * Sets the Active Amplifier
	 * @param amplifier Amplifier Type
	 */
	public void setActiveAmplifier(AmplifierType amplifier);

	/**
	 * Something bad that has the potential of happening
	 * @param factor A random occasion (could be 5% trigger chance if activated etc)
	 */
	public void disrupt(boolean factor);
}