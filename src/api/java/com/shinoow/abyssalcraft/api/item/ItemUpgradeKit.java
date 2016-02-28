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
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Use this class to create Upgrade Kits for tools/armor.
 * You will need to create a crafting recipe for the upgrade:
 * GameRegistry.addRecipe(new ItemStack(NewItem, 1),  new Object [] {"#", "@", '#', OldItem, '@', UpgradeKit);
 * 
 * @author shinoow
 *
 * @since 1.0
 */
public class ItemUpgradeKit extends Item {

	public final String typeName;
	public final String typeName2;

	/**
	 * The Strings are only for display.
	 * Remember to create a crafting recipe for the upgrade:
	 * GameRegistry.addRecipe(new ItemStack(NewItem, 1),  new Object [] {"#", "@", '#', OldItem, '@', UpgradeKit);
	 * @param par2Str The old material
	 * @param par3Str The new material
	 */
	public ItemUpgradeKit(String par2Str, String par3Str){
		super();
		typeName = par2Str;
		typeName2 = par3Str;
		maxStackSize = 16;
	}

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4)
	{
		par3List.add(typeName + " To " + typeName2);
	}
}
