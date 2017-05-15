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

/**
 * Interface to use on blocks that can amplify a {@link IEnergyManipulator} (WIP)<br>
 * WARNING: Any methods in this interface might end up getting removed,<br>
 * so I would suggest not using it until this text (and the WIP part) is removed.
 * 
 * @author shinoow
 *
 * @since 1.5
 */
public interface IEnergyAmplifier {

	/**
	 * Gets the amplifier type associated with the block
	 */
	public AmplifierType getAmplifierType();
}
