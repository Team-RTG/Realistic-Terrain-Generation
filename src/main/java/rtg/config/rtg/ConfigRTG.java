package rtg.config.rtg;

import java.io.File;

import org.apache.logging.log4j.Level;

import net.minecraft.init.Blocks;
import net.minecraft.world.gen.feature.WorldGenMinable;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

import net.minecraftforge.common.config.Configuration;

public class ConfigRTG
{
	public static Configuration config;
	
	public static int generateOnlyThisBiomeId = -1;
	
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
    public static int lavaLakeChance = 18;
	
    public static int flatBedrockLayers = 0;
    
	public static boolean showDebugInfo = false;
	public static boolean enableDebugging = false;

	public static int biomeSize = 1;
	public static int minDistanceScatteredFeatures = 8;
	public static int maxDistanceScatteredFeatures = 32;
	
	public static void init(File configFile) 
	{
		config = new Configuration(configFile);
		
		try 
		{
			config.load();
			
            generateOnlyThisBiomeId = config.getInt("Generate only this biome ID", "Biomes", generateOnlyThisBiomeId, -1, 255, "If you enter a biome ID here, the whole world will consist of only that biome (and rivers). Set to -1 to generate the world normally.");
            
			generateOreCoal = config.getBoolean("Generate Coal Ore", "Ore Gen", generateOreCoal, "");
			generateOreIron = config.getBoolean("Generate Iron Ore", "Ore Gen", generateOreIron, "");
			generateOreGold = config.getBoolean("Generate Gold Ore", "Ore Gen", generateOreGold, "");
			generateOreRedstone = config.getBoolean("Generate Redstone Ore", "Ore Gen", generateOreRedstone, "");
			generateOreLapis = config.getBoolean("Generate Lapis Lazuli Ore", "Ore Gen", generateOreLapis, "");
			generateOreDiamond = config.getBoolean("Generate Diamond Ore", "Ore Gen", generateOreDiamond, "");
			generateOreEmerald = config.getBoolean("Generate Emerald Ore", "Ore Gen", generateOreEmerald, "");
			
            enableCobblestoneBoulders = config.getBoolean("Enable Cobblestone Boulders", "World Gen", enableCobblestoneBoulders, "");
            cobblestoneBoulderChance = config.getInt("1/x chance that Cobblestone Boulders will generate if given the opportunity to do so during world gen", "World Gen", cobblestoneBoulderChance, 1, 100, "1 = Always generate if possible; 2 = 50% chance; 4 = 25% chance");
            
            enableWaterLakes = config.getBoolean("Enable Water Lakes", "Lakes", enableWaterLakes, "");
            waterLakeChance = config.getInt("1/x chance that Water Lakes will generate if given the opportunity to do so during world gen", "Lakes", waterLakeChance, 1, 100, "1 = Always generate if possible; 2 = 50% chance; 4 = 25% chance");
            
            enableLavaLakes = config.getBoolean("Enable Lava Lakes", "Lakes", enableLavaLakes, "");
            lavaLakeChance = config.getInt("1/x chance that Lava Lakes will generate if given the opportunity to do so during world gen", "Lakes", lavaLakeChance, 1, 100, "1 = Always generate if possible; 2 = 50% chance; 4 = 25% chance");
			
            flatBedrockLayers = config.getInt("Number of flat bedrock layers", "World Gen", flatBedrockLayers, 0, 5, "0 = Normal bedrock (rough pattern); 1-5 = Number of flat bedrock layers to generate");
            
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
