package com.sdj64.highlands;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

public class Config
{
	public static String CATEGORY_BIOME_ID = "Biome IDs";
	public static String CATEGORY_BIOME_GENERATE = "Generate Biomes true/false?";
	
	public static String CATEGORY_SUBBIOME_ID = "Sub-biome IDs";
	public static String CATEGORY_SUBBIOME_GENERATE = "Generate Sub-biomes true/false?";
	
	//public static String CATEGORY_VANILLABIOME_GENERATE = "Biomes.Generate Vanilla Biomes true/false?";
	
	
	//Biome ID Properties
    public static Property highlandsbID;
    public static Property pinelandsID;
    public static Property alpsID;
    public static Property meadowID;
    public static Property redwoodForestID;
    public static Property lowlandsID;
    public static Property mojaveID;
    public static Property poplarHillsID;
    public static Property tropicalIslandsID;
    public static Property badlandsID;
    public static Property greyMtnsID;
    public static Property lakeID;
    public static Property baldHillID;
    public static Property tropHillsID;
    public static Property dryForestID;
    public static Property adirondackID;
    public static Property bambooForestID;
    public static Property dunesID;
    
    //Biome Generate Properties
	public static Property highlandsbGenerate;
	public static Property pinelandsGenerate;
	public static Property alpsGenerate;
	public static Property meadowGenerate;
	public static Property redwoodForestGenerate;
	public static Property lowlandsGenerate;
	public static Property mojaveGenerate;
	public static Property poplarHillsGenerate;
	public static Property tropicalIslandsGenerate;
	public static Property badlandsGenerate;
	public static Property greyMtnsGenerate;
	public static Property lakeGenerate;
	public static Property baldHillGenerate;
	public static Property tropHillsGenerate;
	public static Property dryForestGenerate;
	public static Property adirondackGenerate;
    public static Property bambooForestGenerate;
    public static Property dunesGenerate;
	
	//Settings Properties
	public static Property biomePrefix;
	public static Property biomeSize;
	public static Property LBbiomeSize;
	//public static Property genDefault;
	public static Property genOre;
	public static Property vanillaBiomeChanges;
	
	
	
	
	
	public static void setUpConfig(Configuration config)
	{
		addBiomeEntries(config);
		addSettingsEntries(config);
	}
	
	private static void addBiomeEntries(Configuration config) 
	{
		alpsID = config.get(CATEGORY_BIOME_ID, "Alps ID", 50);
		alpsGenerate = config.get(CATEGORY_BIOME_GENERATE, "Alps Generate", true);
		badlandsID = config.get(CATEGORY_BIOME_ID, "Badlands ID", 51);
		badlandsGenerate = config.get(CATEGORY_BIOME_GENERATE, "Badlands Generate", true);
		poplarHillsID = config.get(CATEGORY_BIOME_ID, "Poplar Hills ID", 52);
		poplarHillsGenerate = config.get(CATEGORY_BIOME_GENERATE, "Poplar Hills Generate", true);
		highlandsbID = config.get(CATEGORY_BIOME_ID, "Highlands ID", 53);
		highlandsbGenerate = config.get(CATEGORY_BIOME_GENERATE, "Highlands Generate", true);
		lowlandsID = config.get(CATEGORY_BIOME_ID, "Lowlands ID", 54);
		lowlandsGenerate = config.get(CATEGORY_BIOME_GENERATE, "Lowlands Generate", true);
		meadowID = config.get(CATEGORY_BIOME_ID, "Meadow ID", 55);
		meadowGenerate = config.get(CATEGORY_BIOME_GENERATE, "Meadow Generate", true);
		pinelandsID = config.get(CATEGORY_BIOME_ID, "Pinelands ID", 56);
		pinelandsGenerate = config.get(CATEGORY_BIOME_GENERATE, "Pinelands Generate", true);
		redwoodForestID = config.get(CATEGORY_BIOME_ID, "Redwood Forest ID", 57);
		redwoodForestGenerate = config.get(CATEGORY_BIOME_GENERATE, "Redwood Forest Generate", true);
		mojaveID = config.get(CATEGORY_BIOME_ID, "Mojave ID", 58);
		mojaveGenerate = config.get(CATEGORY_BIOME_GENERATE, "Mojave Generate", true);
		greyMtnsID = config.get(CATEGORY_BIOME_ID, "Grey Mountains ID", 59);
		greyMtnsGenerate = config.get(CATEGORY_BIOME_GENERATE, "Grey Mountains Generate", true);
		tropicalIslandsID = config.get(CATEGORY_BIOME_ID, "Tropical Islands ID", 60);
		tropicalIslandsGenerate = config.get(CATEGORY_BIOME_GENERATE, "Tropical Islands Generate", true);
		lakeID = config.get(CATEGORY_SUBBIOME_ID, "Lake ID", 61);
		lakeGenerate = config.get(CATEGORY_SUBBIOME_GENERATE, "Lake Generate", true);
		baldHillID = config.get(CATEGORY_SUBBIOME_ID, "Bald Hill ID", 62);
		baldHillGenerate = config.get(CATEGORY_SUBBIOME_GENERATE, "Bald Hill Generate", true);
		tropHillsID = config.get(CATEGORY_BIOME_ID, "Tropical Hills ID", 63);
		tropHillsGenerate = config.get(CATEGORY_BIOME_GENERATE, "Tropical Hills Generate", true);
		dryForestID = config.get(CATEGORY_BIOME_ID, "Dry Forest ID", 64);
		dryForestGenerate = config.get(CATEGORY_BIOME_GENERATE, "Dry Forest Generate", true);
		adirondackID = config.get(CATEGORY_BIOME_ID, "Adirondacks ID", 65);
		adirondackGenerate = config.get(CATEGORY_BIOME_GENERATE, "Adirondacks Generate", true);
		bambooForestID = config.get(CATEGORY_BIOME_ID, "Bamboo Forest ID", 66);
		bambooForestGenerate = config.get(CATEGORY_BIOME_GENERATE, "Bamboo Forest Generate", true);
		dunesID = config.get(CATEGORY_BIOME_ID, "Dunes ID", 67);
		dunesGenerate = config.get(CATEGORY_BIOME_GENERATE, "Dunes Generate", true);
	}

	
	private static void addSettingsEntries(Configuration config) 
	{
		biomePrefix = config.get(config.CATEGORY_GENERAL, "Biome Prefix", false);
		biomePrefix.comment = "use a prefix of \"Highlands_\" + biome name for all biomes?";
		biomeSize = config.get(config.CATEGORY_GENERAL, "Biome Size", 4);
		biomeSize.comment = "Biome size in Highlands worlds - 4 is default (same as Default worldtype)";
		LBbiomeSize = config.get(config.CATEGORY_GENERAL, "Large Biomes Biome Size", 6);
		LBbiomeSize.comment = "Biome size in Highlands LB(Large Biome) worlds - 6 is default (same as Large Biomes worldtype)";
		//genDefault = config.get(config.CATEGORY_GENERAL, "Highands biomes in Default worlds", false);
		//genDefault.comment = "Should Highlands biomes generate in the Default and Large Biomes worldtype (for compatibilty with other biome and worldgen mods)";
		genOre = config.get(config.CATEGORY_GENERAL, "Generate Biome-specific Ores", true);
		genOre.comment = "Set to false to disable extra ores of different types in different biomes.";
		vanillaBiomeChanges = config.get(config.CATEGORY_GENERAL, "Add modifications to vanilla biomes", true);
		vanillaBiomeChanges.comment = "Set to false to disable Highlands trees and small plants in vanilla biomes.";
	}
}
