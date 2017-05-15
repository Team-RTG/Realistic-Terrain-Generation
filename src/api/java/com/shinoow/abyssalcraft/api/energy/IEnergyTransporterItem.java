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
 * Interface to use on items that can transport Potential Energy externally (WIP)<br>
 * WARNING: Any methods in this interface might end up getting removed,<br>
 * so I would suggest not using it until this text (and the WIP part) is removed.
 * 
 * @author shinoow
 *
 * @since 1.7.5
 */
public interface IEnergyTransporterItem extends IEnergyContainerItem {

	/**
	 * Returns whether or not the item can accept Potential Energy externally<br>
	 * (eg. a PE manipulator that transfers PE to a player's held item)
	 * @param stack ItemStack containing the item
	 */
	public boolean canAcceptPEExternally(ItemStack stack);

	/**
	 * Returns whether or not the item can transfer Potential Energy externally<br>
	 * (eg. a Ritual Altar draining PE from a Necronomicon during a ritual)
	 * @param stack ItemStack containing the item
	 */
	public boolean canTransferPEExternally(ItemStack stack);
}