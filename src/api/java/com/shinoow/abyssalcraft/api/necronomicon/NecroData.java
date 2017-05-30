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
package com.shinoow.abyssalcraft.api.necronomicon;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import java.util.NavigableMap;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.FMLLog;

import org.apache.logging.log4j.Level;

import com.google.common.collect.Maps;
import com.shinoow.abyssalcraft.api.AbyssalCraftAPI;

/**
 * Base data structure for Necronomicon information pages
 * @author shinoow
 *
 * @since 1.2
 */
public class NecroData {

	private String identifier;
	private String title;
	private String information;
	private Chapter[] chapters;

	/**
	 * The base data structure for Necronomicon information pages
	 * @param title Title to display on the "Index" for the information page
	 * @param info Optional text to write beside buttons for sub-category pages
	 * @param chapters Chapters for sub-category pages
	 */
	public NecroData(String identifier, String title, String info,Chapter...chapters){
		this.identifier = identifier;
		this.title = title;
		this.chapters = chapters;
		information = info;
	}

	/**
	 * The base data structure for Necronomicon information pages
	 * @param title Title to display on the "Index" for the information page
	 * @param chapters Chapters for sub-category pages
	 */
	public NecroData(String identifier, String title,Chapter...chapters){
		this(identifier, title, null, chapters);
	}

	/**
	 * Getter for the NecroData title
	 * @return A string representing the Title of the information page
	 */
	public String getTitle(){
		return title;
	}

	/**
	 * Getter for the category information
	 * @return A specified string used to display on the information page
	 */
	public String getInformation(){
		return information;
	}

	/**
	 * Getter for the NecroData identifier
	 * @return A unique string representing this NecroData
	 */
	public String getIdentifier(){
		return identifier;
	}

	/**
	 * Getter for the Chapter array
	 * @return An array of stored Chapters
	 */
	public Chapter[] getChapters(){
		return chapters;
	}

	/**
	 * Method for getting the title of a specific sub-category
	 * @param index Position in the index to check
	 * @return A string representing the Chapter title
	 */
	public String getChapterTitle(int index){
		return chapters[index].title;
	}

	/**
	 * Adds a Chapter (or overrides one that already exists)
	 * @param chapter Chapter to add
	 */
	public void addChapter(Chapter chapter){
		for(Chapter chap : chapters)
			if(chap.identifier.equals(chapter.identifier)){
				chap = chapter;
				return;
			}

		if(chapters.length < 7){
			Chapter[] newchap = new Chapter[chapters.length + 1];
			for(int i = 0; i < chapters.length; i++)
				newchap[i] = chapters[i];

			newchap[chapters.length] = chapter;

			chapters = newchap;
		} else FMLLog.log("AbyssalCraftAPI", Level.ERROR, "NecroData instance is already full, can't add a new Chapter!");
	}

	/**
	 * Removes a Chapter (if it exists)
	 * @param identifier Chapter identifier
	 */
	public void removeChapter(String identifier){
		Chapter[] newchap = new Chapter[chapters.length -1];
		Chapter[] oldchap = chapters.clone();

		for(Chapter chap : oldchap)
			if(chap.identifier.equals(identifier)){
				for(Chapter chapnew : newchap)
					for(Chapter chap2 : oldchap)
						if(chap2 != null && !chap2.identifier.equals(identifier)){
							chapnew = chap2;
							chap2 = null;
							break;
						}
				chapters = newchap;
				return;
			}
	}

	/**
	 * A Necronomicon Chapter (collection of pages)
	 * @author shinoow
	 *
	 * @since 1.6
	 */
	public static class Chapter{
		private NavigableMap<Integer, Page> pages = Maps.newTreeMap((o1, o2) -> {
			if(o1 > o2)
				return 1;
			if(o1 < o2)
				return -1;
			return 0;
		});
		private String identifier;
		private String title;

		/**
		 * A Necronomicon Chapter
		 * @param identifier Identifier (used to locate the chapter, should be unique for every NecroData)
		 * @param title Title to display on pages in the Chapter
		 */
		public Chapter(String identifier, String title){
			this.identifier = identifier;
			this.title = title;
		}

		/**
		 * A Necronomicon Chapter
		 * @param identifier Identifier (used to locate the chapter, should be unique for every NecroData)
		 * @param title Title to display on pages in the Chapter
		 * @param pages an array of Pages (it is optional to do it this way)
		 */
		public Chapter(String identifier, String title, Page...pages){
			this(identifier, title);
			for(Page page : pages)
				addPage(page);
		}

		/**
		 * Getter for the Chapter identifier
		 */
		public String getIdentifier(){
			return identifier;
		}

		/**
		 * Getter for the Chapter title
		 */
		public String getTitle(){
			return title;
		}

		/**
		 * Fetches a unmodifiable Map of all the Pages contained in this Chapter
		 */
		public Map<Integer, Page> getPages(){
			return Collections.unmodifiableMap(pages);
		}

		/**
		 * Getter for the page amount
		 */
		public int getPageAmount(){
			return pages.size();
		}

		/**
		 * Getter for the turn-up amount (last page number evenly divided by 2)
		 */
		public int getTurnupAmount(){
			return pages.lastKey() / 2 + (pages.lastKey() % 2 == 0 ? 0 : 1);
		}

		/**
		 * Adds a page to the Chapter
		 * @param page Page to add
		 */
		public void addPage(Page page){
			pages.put(page.pageNum, page);
		}

		/**
		 * Removes a Page (if it exists)
		 * @param pageNum Page number
		 */
		public void removePage(int pageNum){
			pages.remove(pageNum);
		}

		/**
		 * Fetches a page (if it exists)
		 * @param pageNum Page number
		 */
		public Page getPage(int pageNum){
			return pages.get(pageNum);
		}

		/**
		 * Checks if a Page exists
		 * @param pageNum Page number
		 */
		public boolean hasPage(int pageNum){
			return pages.containsKey(pageNum);
		}
	}

	/**
	 * A Necronomicon Page
	 * @author shinoow
	 *
	 * @since 1.6
	 */
	public static class Page{
		private Object icon;
		private int pageNum;
		private String text;

		/**
		 * A Necronomicon Page
		 * @param pageNum Page number
		 * @param text Text to display on the Page
		 */
		public Page(int pageNum, String text){
			this(pageNum, null, text);
		}

		/**
		 * A Necronomicon Page
		 * @param pageNum Page number
		 * @param icon ResourceLocation/ItemStack/CraftingStack to display on the Page
		 * @param text Text to display on the Page
		 */
		public Page(int pageNum, Object icon, String text){
			if(pageNum == 0) throw new ArithmeticException("The Page number can't be zero");
			this.pageNum = pageNum;
			if(icon != null)
				if(!(icon instanceof ResourceLocation) && !(icon instanceof ItemStack) && !(icon instanceof CraftingStack) && !(icon instanceof String))
					throw new IllegalArgumentException("Icon isn't a ResourceLocation, ItemStack, CraftingStack or URL String!");
			this.icon = verify(icon);
			this.text = text;
		}

		private Object verify(Object obj){
			if(obj instanceof String) AbyssalCraftAPI.getInternalNDHandler().verifyImageURL((String)obj);
			if(!(obj instanceof ResourceLocation)) return obj;
			if(FMLCommonHandler.instance().getSide().isServer()) return obj;
			ResourceLocation res = (ResourceLocation)obj;
			if(res.toString().equals("abyssalcraft:textures/gui/necronomicon/missing.png")) return obj;
			try {
				TextureUtil.readBufferedImage(Minecraft.getMinecraft().getResourceManager().getResource(res).getInputStream());
			} catch (IOException e) {
				return new ResourceLocation("abyssalcraft", "textures/gui/necronomicon/missing.png");
			}
			return res;
		}

		/**
		 * Fetches the page's number (used for ordering and overriding/removing/replacing pages).
		 */
		public int getPageNumber(){
			return pageNum;
		}

		/**
		 * Fetches the ResourceLocation/ItemStack/CraftingStack to display on the page (if any exist).
		 */
		public Object getIcon(){
			return icon;
		}

		/**
		 * Fetches the text to display on the page.
		 */
		public String getText(){
			return text;
		}

		@Override
		public boolean equals(Object obj){
			if(!(obj instanceof Page)) return false;

			Page page = (Page)obj;

			boolean test1 = page.pageNum == pageNum;
			boolean test2 = page.icon == null && icon == null || page.icon.equals(icon);
			boolean test3 = page.text.equals(text);

			return test1 && test2 && test3;
		}
	}
}
