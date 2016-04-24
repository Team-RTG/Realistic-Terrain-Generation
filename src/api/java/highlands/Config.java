package highlands;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

public class Config
{
	public static String CATEGORY_BIOME_ID = "Biomes.Biome IDs set to -1 to disable biome";
	//public static String CATEGORY_BIOME_GENERATE = "Biomes.Generate Biomes true/false?";
	
	public static String CATEGORY_SUBBIOME_ID = "Biomes.Sub-biome IDs  set to -1 to disable biome";
	//public static String CATEGORY_SUBBIOME_GENERATE = "Biomes.Generate Sub-biomes true/false?";
	
	public static String CATEGORY_BLOCK_ID = "Block IDs";
	
	public static String CATEGORY_VANILLABIOME_GENERATE = "Biomes.Generate Vanilla Biomes true/false?";
	
	
	//Biome ID Properties
	public static int woodlandMountainsID;
    public static int highlandsbID;
    public static int tundraID;
    public static int cliffsID;
    public static int pinelandsID;
    public static int autumnForestID;
    public static int alpsID;
    public static int tallPineForestID;
    public static int meadowID;
    public static int savannahID;
    public static int tropicsID;
    public static int outbackID;
    public static int woodlandsID;
    public static int bogID;
    public static int redwoodForestID;
    public static int dunesID;
    public static int lowlandsID;
    public static int sahelID;
    public static int birchHillsID;
    public static int tropicalIslandsID;
    public static int rainforestID;
    public static int estuaryID;
    public static int badlandsID;
    public static int flyingMountainsID;
    public static int snowMountainsID;
    public static int rockMountainsID;
    public static int desertMountainsID;
    public static int steppeID;
    public static int glacierID;
    //public static Property evergladesID;
    public static int ocean2ID;
    public static int forestIslandID;
    public static int jungleIslandID;
    public static int desertIslandID;
    public static int volcanoIslandID;
    public static int snowIslandID;
    public static int windyIslandID;
    public static int rockIslandID;
    public static int valleyID;
    public static int lakeID;
    public static int mesaID;
    public static int baldHillID;
    public static int oasisID;
    public static int canyonID;
    public static int shrublandID;
	
	public static Property plainsGenerate;
	public static Property desertGenerate;
	public static Property extremeHillsGenerate;
	public static Property forestGenerate;
	public static Property swamplandGenerate;
	public static Property jungleGenerate;
	public static Property icePlainsGenerate;
	public static Property taigaGenerate;
	
	//Settings Properties
	public static Property biomePrefix;
	public static Property moreOceans;
	public static Property islandRarity;
	public static Property biomeSize;
	public static Property LBbiomeSize;
	public static Property addBoPbiomes;
	public static Property skyColors;
	public static Property modWoodAndLeaves;
	public static Property smallPlants;
	public static Property genOre;
	public static Property mobModCompatibility;
	public static Property safeMode;

	private static Property ocean2Generate;
	
	
	
	
	
	public static void setUpConfig(Configuration config)
	{
		addBiomeEntries(config);
		addSettingsEntries(config);
	}
	
	private static void addBiomeEntries(Configuration config) 
	{
		alpsID = config.get(CATEGORY_BIOME_ID, "Alps ID", 200).getInt();
		autumnForestID = config.get(CATEGORY_BIOME_ID, "Autumn Forest ID", 201).getInt();
		badlandsID = config.get(CATEGORY_BIOME_ID, "Badlands ID", 202).getInt();
		birchHillsID = config.get(CATEGORY_BIOME_ID, "Birch Hills ID", 203).getInt();
		bogID = config.get(CATEGORY_BIOME_ID, "Bog ID", 204).getInt();
		cliffsID = config.get(CATEGORY_BIOME_ID, "Cliffs ID", 205).getInt();
		desertMountainsID = config.get(CATEGORY_BIOME_ID, "Desert Mountains ID", 206).getInt();
		dunesID = config.get(CATEGORY_BIOME_ID, "Dunes ID", 207).getInt();
		estuaryID = config.get(CATEGORY_BIOME_ID, "Estuary ID", 208).getInt();
		flyingMountainsID = config.get(CATEGORY_BIOME_ID, "Flying Mountains ID", 209).getInt();
		glacierID = config.get(CATEGORY_BIOME_ID, "Glacier ID", 210).getInt();
		highlandsbID = config.get(CATEGORY_BIOME_ID, "Highlands ID", 211).getInt();
		lowlandsID = config.get(CATEGORY_BIOME_ID, "Lowlands ID", 212).getInt();
		meadowID = config.get(CATEGORY_BIOME_ID, "Meadow ID", 213).getInt();
		outbackID = config.get(CATEGORY_BIOME_ID, "Outback ID", 214).getInt();
		pinelandsID = config.get(CATEGORY_BIOME_ID, "Pinelands ID", 215).getInt();
		rainforestID = config.get(CATEGORY_BIOME_ID, "Rainforest ID", 216).getInt();
		redwoodForestID = config.get(CATEGORY_BIOME_ID, "Redwood Forest ID", 217).getInt();
		rockMountainsID = config.get(CATEGORY_BIOME_ID, "Rock Mountains ID", 218).getInt();
		sahelID = config.get(CATEGORY_BIOME_ID, "Sahel ID", 219).getInt();
		savannahID = config.get(CATEGORY_BIOME_ID, "Savannah ID", 220).getInt();
		steppeID = config.get(CATEGORY_BIOME_ID, "Steppe ID", 221).getInt();
		snowMountainsID = config.get(CATEGORY_BIOME_ID, "Snow Mountains ID", 222).getInt();
		tallPineForestID = config.get(CATEGORY_BIOME_ID, "Tall Pine Forest ID", 223).getInt();
		tropicsID = config.get(CATEGORY_BIOME_ID, "Tropics ID", 224).getInt();
		tropicalIslandsID = config.get(CATEGORY_BIOME_ID, "Tropical Islands ID", 225).getInt();
		tundraID = config.get(CATEGORY_BIOME_ID, "Tundra ID", 226).getInt();
		woodlandsID = config.get(CATEGORY_BIOME_ID, "Woodlands ID", 227).getInt();
		woodlandMountainsID = config.get(CATEGORY_BIOME_ID, "Woodland Mountains ID", 228).getInt();
		
		ocean2ID = config.get(CATEGORY_BIOME_ID, "Improved Oceans ID", 229).getInt();
		//ocean2Generate = config.get(config.CATEGORY_GENERAL, "Improved Oceans Generate", true);
		//ocean2Generate.comment = "If this is false you will have regular Minecraft oceans instead.";
		
		desertIslandID = config.get(CATEGORY_SUBBIOME_ID, "DesertIsland ID", 230).getInt();
		forestIslandID = config.get(CATEGORY_SUBBIOME_ID, "Forest Island ID", 231).getInt();
		jungleIslandID = config.get(CATEGORY_SUBBIOME_ID, "Jungle Island ID", 232).getInt();
		volcanoIslandID = config.get(CATEGORY_SUBBIOME_ID, "Volcano Island ID", 233).getInt();
		snowIslandID = config.get(CATEGORY_SUBBIOME_ID, "Snow Island ID", 234).getInt();
		rockIslandID = config.get(CATEGORY_SUBBIOME_ID, "Rock Island ID", 235).getInt();
		windyIslandID = config.get(CATEGORY_SUBBIOME_ID, "Windy Island ID", 236).getInt();
		lakeID = config.get(CATEGORY_SUBBIOME_ID, "Lake ID", 237).getInt();
		baldHillID = config.get(CATEGORY_SUBBIOME_ID, "Bald Hill ID", 238).getInt();
		mesaID = config.get(CATEGORY_SUBBIOME_ID, "Mesa ID", 239).getInt();
		valleyID = config.get(CATEGORY_SUBBIOME_ID, "Valley ID", 240).getInt();
		oasisID = config.get(CATEGORY_SUBBIOME_ID, "Oasis ID", 241).getInt();
		canyonID = config.get(CATEGORY_SUBBIOME_ID, "Canyon ID", 242).getInt();
		shrublandID = config.get(CATEGORY_SUBBIOME_ID, "Shrublands ID", 243).getInt();
		
		plainsGenerate = config.get(CATEGORY_VANILLABIOME_GENERATE, "Plains Generate", true);
		desertGenerate = config.get(CATEGORY_VANILLABIOME_GENERATE, "Desert Generate", true);
		extremeHillsGenerate = config.get(CATEGORY_VANILLABIOME_GENERATE, "Extreme Hills Generate", true);
		forestGenerate = config.get(CATEGORY_VANILLABIOME_GENERATE, "Forest Generate", true);
		swamplandGenerate = config.get(CATEGORY_VANILLABIOME_GENERATE, "Swampland Generate", true);
		jungleGenerate = config.get(CATEGORY_VANILLABIOME_GENERATE, "Jungle Generate", true);
		icePlainsGenerate = config.get(CATEGORY_VANILLABIOME_GENERATE, "Ice Plains Generate", true);
		taigaGenerate = config.get(CATEGORY_VANILLABIOME_GENERATE, "Taiga Generate", true);
	}

	private static void addSettingsEntries(Configuration config) 
	{
		biomePrefix = config.get(config.CATEGORY_GENERAL, "Biome Prefix", false);
		biomePrefix.comment = "use a prefix of \"Highlands_\" + biome name for all biomes?";
		moreOceans = config.get(config.CATEGORY_GENERAL, "More Oceans", 1);
		moreOceans.comment = "How often oceans appear in your world (not less than 0).  1 is default. ";
		islandRarity = config.get(config.CATEGORY_GENERAL, "Island Rarity", 14);
		islandRarity.comment = "How often should Islands spawn in the ocean.  Higher = less islands. ";
		biomeSize = config.get(config.CATEGORY_GENERAL, "Biome Size", 4);
		biomeSize.comment = "Biome size in Highlands worlds - 4 is default (same as Default worldtype)";
		LBbiomeSize = config.get(config.CATEGORY_GENERAL, "Large Biomes Biome Size", 6);
		LBbiomeSize.comment = "Biome size in Highlands LB worlds - 6 is default (same as Large Biomes worldtype)";
		addBoPbiomes = config.get(config.CATEGORY_GENERAL, "Add BoP biomes to Highlands worlds", false);
		addBoPbiomes.comment = "Generate Biomes o' Plenty biomes in Highlands worlds";
		
		skyColors = config.get(config.CATEGORY_GENERAL, "Sky Colors", false);
		skyColors.comment = "Use custom sky colors?";
		modWoodAndLeaves = config.get(config.CATEGORY_GENERAL, "Highlands Wood and Leaves", true);
		modWoodAndLeaves.comment = "Set to false to use vanilla wood and leaves instead of this mod's wood and leaves.";
		smallPlants = config.get(config.CATEGORY_GENERAL, "Generate Small Plants", true);
		smallPlants.comment = "Set to false to disable this mod's small plants.";
		genOre = config.get(config.CATEGORY_GENERAL, "Generate Biome-specific Ores", true);
		genOre.comment = "Set to false to disable extra ores of different types in different biomes.";
		mobModCompatibility = config.get(config.CATEGORY_GENERAL, "Mob Mod Compatiblity", false);
		mobModCompatibility.comment = "DOES NOT WORK WITH MO CREATURES.  See Readme for more information.";
		safeMode = config.get(config.CATEGORY_GENERAL, "Safe Mode", false);
		safeMode.comment = "Disables sub-biomes, borders, and improved oceans. Untested in 1.7.2.";
	}
}
