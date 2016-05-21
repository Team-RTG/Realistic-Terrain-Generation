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

/**
 * Simple interface for handling integrations. Should be used together with the<br>
 * {@literal @}{@link ACPlugin} annotation.<br>
 * The integration plugins follow the standard FML lifecycle (apart from the pre-init<br>
 * method not being called by AbyssalCraft, so that you can call that yourself if<br>
 * the plugin adds any new Item/Block/Entity).
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
	 * Determines whether or not this plugin can be loaded.<br>
	 * Should normally just return true, but can have a different<br>
	 * return value depending on factors (like a config option to load<br>
	 * the plugin, or just checking if the mod the plugin's for is present).
	 * @return True if the plugin can be loaded, otherwise false.
	 */
	public boolean canLoad();

	/**
	 * Won't be called by AbyssalCraft, allowing you to register any new Item/Block/Entity<br>
	 * your plugin might add. Should be called at the pre-init stage.
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
