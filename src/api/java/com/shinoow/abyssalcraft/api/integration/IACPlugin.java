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
package com.shinoow.abyssalcraft.api.integration;

import com.shinoow.abyssalcraft.api.AbyssalCraftAPI;

/**
 * Simple interface to handle integrations. If used by another mod, register the
 * file implementing this interface in {@link AbyssalCraftAPI} or through a IMC message
 * 
 * @author shinoow
 *
 * @since 1.3
 */
public interface IACPlugin {

	/**
	 * Used to fetch the mod name
	 * @return A String representing the mod's name
	 */
	public String getModName();

	/**
	 * Will be called at the end of the pre-init stage
	 */
	public void preInit();

	/**
	 * Will be called at the end of the init stage
	 */
	public void init();

	/**
	 * Will be called at the post-init stage
	 */
	public void postInit();
}
