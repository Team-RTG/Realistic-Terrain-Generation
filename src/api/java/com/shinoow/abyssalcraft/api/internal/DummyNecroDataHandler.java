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

public class DummyNecroDataHandler implements IInternalNecroDataHandler {

	@Override
	public NecroData getInternalNecroData(String identifier){ return null; }

	@Override
	public void addChapter(Chapter chapter, String identifier) {}

	@Override
	public void removeChapter(String necroidentifier, String chapteridentifier) {}

	@Override
	public void addPage(Page page, String necroidentifier, String chapteridentifier) {}

	@Override
	public void removePage(int pageNum, String necroidentifier, String chapteridentifier) {}

	@Override
	public void registerInternalPages() {}
}
