package rtg.config.rtg;

import java.io.File;

import org.apache.logging.log4j.Level;

import cpw.mods.fml.common.FMLLog;

import net.minecraftforge.common.config.Configuration;

public class ConfigRTG
{
	public static Configuration config;
	
	public static int generateOnlyThisBiomeId = -1;
	
    public static String shadowStoneBlockId = "minecraft:stained_hardened_clay";
    public static int shadowStoneBlockByte = 9;
    
    public static String shadowDesertBlockId = "minecraft:stained_hardened_clay";
    public static int shadowDesertBlockByte = 8;
    
    public static String volcanoBlockId = "minecraft:obsidian";
    public static int volcanoBlockByte = 0;
	
    public static boolean generateOreCoal = true;
    public static boolean generateOreIron = true;
    public static boolean generateOreGold = true;
    public static boolean generateOreRedstone = true;
    public static boolean generateOreLapis = true;
    public static boolean generateOreDiamond = true;
    public static boolean generateOreEmerald = true;
	
    public static boolean enableCobblestoneBoulders = true;
    public static int cobblestoneBoulderChance = 1;
    
    public static boolean enableWaterLakes = true;
    public static int waterLakeChance = 10;
    
    public static boolean enableLavaLakes = true;
    public static int lavaLakeChance = 10;
    
    public static boolean generateMineshafts = true;
    public static boolean generateStrongholds = true;
    public static boolean generateVillages = true;
    public static boolean generateScatteredFeatures = true;
    public static boolean generateDungeons = true;
    
    public static boolean enableCaves = true;
    public static int caveDensity = 8;
    public static int caveFrequency = 12;
	
    public static boolean enableSnowLayers = true;
    
    public static int flatBedrockLayers = 0;
    
	public static boolean showDebugInfo = false;
	public static boolean enableDebugging = false;

	public static int biomeSize = 1;
	
    public static int minDistanceScatteredFeatures = 8;
    public static int maxDistanceScatteredFeatures = 32;
    
    public static int villageSize = 0;
    public static int minDistanceVillages = 8;
    public static int maxDistanceVillages = 32;
	
	public static void init(File configFile) 
	{
		config = new Configuration(configFile);
		
		try 
		{
			config.load();
			
            generateOnlyThisBiomeId = config.getInt(
                "Generate only this biome ID", 
                "Biomes", 
                generateOnlyThisBiomeId, 
                -1, 255, 
                "If you enter a biome ID here, the whole world will consist of only that biome (and rivers). Set to -1 to generate the world normally." +
                Configuration.NEW_LINE +
                "Vanilla biome IDs can be found here: http://goo.gl/WqlAfV" +
                Configuration.NEW_LINE +
                "For modded biome IDs, use NEI and go [Options] > [Tools] > [Data Dumps] > Biomes > [Dump], and then refer to the 'biome.csv' file which can be found in your '/.minecraft/dumps' folder."
            );
   
            shadowStoneBlockId = config.getString(
                "Stone shadow block ID",
                "Terrain shadowing", 
                shadowStoneBlockId,
                "The block to use for stone terrain shadowing, typically seen on the cliffs of stone mountains." +
                Configuration.NEW_LINE +
                "Defaults to stained hardened clay."
            );
            
            shadowStoneBlockByte = config.getInt("Stone shadow block meta value", "Terrain shadowing", shadowStoneBlockByte, 0, 15, "The meta value of the shadow block for stone structures. Defaults to " + shadowStoneBlockByte +  " (cyan).");
            
            shadowDesertBlockId = config.getString(
                "Desert shadow block ID",
                "Terrain shadowing", 
                shadowDesertBlockId,
                "The block to use for desert terrain shadowing, typically seen on the cliffs of desert mountains." +
                Configuration.NEW_LINE +
                "Defaults to stained hardened clay."
            );
            
            shadowDesertBlockByte = config.getInt("Desert shadow block meta value", "Terrain shadowing", shadowDesertBlockByte, 0, 15, "The meta value of the shadow block for desert structures. Defaults to " + shadowDesertBlockByte +  " (light gray).");
            
            volcanoBlockId = config.getString(
                "Volcano block ID",
                "Volcanoes", 
                volcanoBlockId,
                "The block to use for top of the volcano." +
                Configuration.NEW_LINE +
                "Defaults to Obsidian."
            );
            
            volcanoBlockByte = config.getInt("Volcano block meta value", "Volcanoes", volcanoBlockByte, 0, 15, "The meta value of the volcano block. Defaults to " + volcanoBlockByte +  ".");
            
			generateOreCoal = config.getBoolean("Generate Coal Ore", "Ore Gen", generateOreCoal, "");
			generateOreIron = config.getBoolean("Generate Iron Ore", "Ore Gen", generateOreIron, "");
			generateOreGold = config.getBoolean("Generate Gold Ore", "Ore Gen", generateOreGold, "");
			generateOreRedstone = config.getBoolean("Generate Redstone Ore", "Ore Gen", generateOreRedstone, "");
			generateOreLapis = config.getBoolean("Generate Lapis Lazuli Ore", "Ore Gen", generateOreLapis, "");
			generateOreDiamond = config.getBoolean("Generate Diamond Ore", "Ore Gen", generateOreDiamond, "");
			generateOreEmerald = config.getBoolean("Generate Emerald Ore", "Ore Gen", generateOreEmerald, "");
			
            enableCobblestoneBoulders = config.getBoolean("Enable Cobblestone Boulders", "Boulders", enableCobblestoneBoulders, "");
            cobblestoneBoulderChance = config.getInt("1/x chance that Cobblestone Boulders will generate if given the opportunity to do so during world gen", "Boulders", cobblestoneBoulderChance, 1, 100, "1 = Always generate if possible; 2 = 50% chance; 4 = 25% chance");
            
            enableWaterLakes = config.getBoolean("Enable Water Lakes", "Lakes", enableWaterLakes, "");
            waterLakeChance = config.getInt("1/x chance that Water Lakes will generate if given the opportunity to do so during world gen", "Lakes", waterLakeChance, 1, 100, "1 = Always generate if possible; 2 = 50% chance; 4 = 25% chance");
            
            enableLavaLakes = config.getBoolean("Enable Lava Lakes", "Lakes", enableLavaLakes, "");
            lavaLakeChance = config.getInt("1/x chance that Lava Lakes will generate if given the opportunity to do so during world gen", "Lakes", lavaLakeChance, 1, 100, "1 = Always generate if possible; 2 = 50% chance; 4 = 25% chance");
			
            generateMineshafts = config.getBoolean("Generate Mineshafts", "Mineshafts", generateMineshafts, "");
            generateStrongholds = config.getBoolean("Generate Strongholds", "Strongholds", generateStrongholds, "");
            generateScatteredFeatures = config.getBoolean("Generate Scattered Features", "Scattered Features", generateScatteredFeatures, "");
            generateDungeons = config.getBoolean("Generate Dungeons", "Dungeons", generateDungeons, "");
            
            generateVillages = config.getBoolean("Generate Villages", "Villages", generateVillages, "");
            villageSize = config.getInt("Size of villages", "Villages", villageSize, 0, 10, "Higher values = bigger villages; 0 = Vanilla");
            minDistanceVillages = config.getInt("Minimum distance between villages", "Villages", minDistanceVillages, 1, Integer.MAX_VALUE, "Higher values = villages further apart; 8 = Vanilla");
            maxDistanceVillages = config.getInt("Maximum distance between villages", "Villages", maxDistanceVillages, 1, Integer.MAX_VALUE, "Lower values = villages closer together; 32 = Vanilla");
            
            enableCaves = config.getBoolean("Enable Caves", "Caves", enableCaves, "");
            caveDensity = config.getInt("Cave Density", "Caves", caveDensity, 1, 40, "This setting controls the size of caves." + Configuration.NEW_LINE + "HIGHER values = BIGGER caves & MORE lag. (14 = vanilla cave density)" + Configuration.NEW_LINE);
            caveFrequency = config.getInt("Cave Frequency", "Caves", caveFrequency, 1, 40, "This setting controls the number of caves that generate." + Configuration.NEW_LINE + "LOWER values = MORE caves & MORE lag. (6 = vanilla cave frequency)" + Configuration.NEW_LINE);
            
            enableSnowLayers = config.getBoolean("Enable Snow Layers", "Snow", enableSnowLayers, "This applies to newly-generated chunks only. Snow layers will still appear in cold/snowy biomes after it snows.");
            
            flatBedrockLayers = config.getInt("Number of flat bedrock layers", "Bedrock", flatBedrockLayers, 0, 5, "0 = Normal bedrock (rough pattern); 1-5 = Number of flat bedrock layers to generate");
            
			showDebugInfo = config.getBoolean("Show Debug Info in F3 Screen", "Debugging", showDebugInfo, "");
			enableDebugging = config.getBoolean("Enable Debugging", "Debugging", enableDebugging, "WARNING: This should only be enabled if you know what you're doing.");
			
			biomeSize = config.getInt("Size of Biomes", "Biomes", biomeSize, 1, 5, "Lower values = smaller biomes; Higher values = larger biomes");
			
            minDistanceScatteredFeatures = config.getInt("Minimum distance between scattered features", "Scattered Features", minDistanceScatteredFeatures, 1, Integer.MAX_VALUE, "Scattered features = desert temples, jungle temples, and witch huts.");
            maxDistanceScatteredFeatures = config.getInt("Maximum distance between scattered features", "Scattered Features", maxDistanceScatteredFeatures, 1, Integer.MAX_VALUE, "Scattered features = desert temples, jungle temples, and witch huts.");
		}
		catch (Exception e) 
		{
			FMLLog.log(Level.ERROR, e, "RTG has had a problem loading RTG configuration.");	
		}
		finally 
		{
			if (config.hasChanged())
			{
				config.save();
			}
		}
	}
}
