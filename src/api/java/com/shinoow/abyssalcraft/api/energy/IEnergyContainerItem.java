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

/**
 * Interface to use on items that can hold Potential Energy (WIP)<br>
 * WARNING: Any methods in this interface might end up getting removed,<br>
 * so I would suggest not using it until this text (and the WIP part) is removed.
 * 
 * @author shinoow
 *
 * @since 1.7.5
 */
public interface IEnergyContainerItem {

	/**
	 * Gets the Potential Energy contained within the item
	 * @param stack ItemStack containing the item
	 */
	public float getContainedEnergy(ItemStack stack);

	/**
	 * Gets the maximum Potential Energy the item can hold
	 * @param stack ItemStack containing the item
	 */
	public int getMaxEnergy(ItemStack stack);

	/**
	 * Adds Potential Energy to the item
	 * @param stack ItemStack containing the item
	 * @param energy Energy quanta to add
	 */
	public void addEnergy(ItemStack stack, float energy);

	/**
	 * Consumes (removes) Potential Energy from the item
	 * @param stack ItemStack containing the item
	 * @param energy Energy quanta to consume
	 */
	public void consumeEnergy(ItemStack stack, float energy);

	/**
	 * Returns Whether or not this item can accept Potential Energy
	 */
	public boolean canAcceptPE(ItemStack stack);

	/**
	 * Returns Whether or not this item can transfer Potential Energy
	 */
	public boolean canTransferPE(ItemStack stack);
}
