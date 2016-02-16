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