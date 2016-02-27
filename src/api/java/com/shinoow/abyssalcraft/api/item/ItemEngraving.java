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
package com.shinoow.abyssalcraft.api.item;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * The "fuel" in Coin Engraving. Use this class if you want to make your own engravings.
 * 
 * @author shinoow
 *
 * @since 1.1
 */
public class ItemEngraving extends Item {

	/**
	 * The "fuel" in Coin Engraving. Use this class if you want to make your own engravings.
	 * @param par1 The unlocalized name, will be prefixed by "engraving_"
	 * @param par2 The item damage, used as a durability check
	 */
	public ItemEngraving(String par1, int par2){
		super();
		setUnlocalizedName("engraving_" + par1);
		setMaxDamage(par2);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void addInformation(ItemStack is, EntityPlayer player, List l, boolean B){
		l.add(getMaxDamage() - getDamage(is) +"/"+ is.getMaxDamage());
	}
}
