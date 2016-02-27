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
package com.shinoow.abyssalcraft.api.internal;

import com.shinoow.abyssalcraft.api.necronomicon.NecroData;
import com.shinoow.abyssalcraft.api.necronomicon.NecroData.Chapter;
import com.shinoow.abyssalcraft.api.necronomicon.NecroData.Page;

public interface IInternalNecroDataHandler {

	/**
	 * Fetches an internal NecroData from the provided identifier
	 * @param identifier String representing the NecroData
	 */
	public NecroData getInternalNecroData(String identifier);

	/**
	 * Adds a Chapter to an internal NecroData
	 * @param chapter Chapter object
	 * @param identifier String representing the NecroData
	 */
	public void addChapter(Chapter chapter, String identifier);

	/**
	 * Removes a Chapter from an internal NecroData
	 * @param necroidentifier String representing the NecroData
	 * @param chapteridentifier String representing the Chapter
	 */
	public void removeChapter(String necroidentifier, String chapteridentifier);

	/**
	 * Adds a Page to a Chapter of an internal NecroData
	 * @param page Page object
	 * @param necroidentifier String representing the NecroData
	 * @param chapteridentifier String representing the Chapter
	 */
	public void addPage(Page page, String necroidentifier, String chapteridentifier);

	/**
	 * Removes a Page from a Chapter of an internal NecroData
	 * @param pageNum Page number
	 * @param necroidentifier String representing the NecroData
	 * @param chapteridentifier String representing the Chapter
	 */
	public void removePage(int pageNum, String necroidentifier, String chapteridentifier);

	/**
	 * Adds the internal pages to the internal NecroDatas.<br>
	 * This method is fired at Init.
	 */
	public void registerInternalPages();
}
