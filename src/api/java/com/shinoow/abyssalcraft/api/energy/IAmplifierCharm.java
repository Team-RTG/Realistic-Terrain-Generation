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

import net.minecraft.item.ItemStack;

import com.shinoow.abyssalcraft.api.energy.EnergyEnum.AmplifierType;
import com.shinoow.abyssalcraft.api.energy.EnergyEnum.DeityType;

/**
 * Interface to use on items that can amplify a {@link IEnergyManipulator} (WIP)<br>
 * WARNING: Any methods in this interface might end up getting removed,<br>
 * so I would suggest not using it until this text (and the WIP part) is removed.
 * 
 * @author shinoow
 *
 * @since 1.5
 */
public interface IAmplifierCharm {

	/**
	 * Gets the amplifier associated with the item
	 * @param stack ItemStack containing the item
	 */
	public AmplifierType getAmplifier(ItemStack stack);

	/**
	 * Gets the deity associated with the item (can be null)
	 * @param stack ItemStack containing the item
	 */
	public DeityType getDeity(ItemStack stack);
}
