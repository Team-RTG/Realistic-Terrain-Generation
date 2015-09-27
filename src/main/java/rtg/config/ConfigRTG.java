package rtg.config;

import java.io.File;

import org.apache.logging.log4j.Level;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.config.Configuration;

public class ConfigRTG
{
	public static Configuration config;
		
	public static boolean generateVanillaBiomes = true;
	
	public static String generateOnlyThisVanillaBiome = "";
	
	public static boolean generateVanillaBeach = true;
	public static boolean generateVanillaBirchForest = true;
	public static boolean generateVanillaBirchForestHills = true;
	public static boolean generateVanillaColdBeach = true;
	public static boolean generateVanillaColdTaiga = true;
	public static boolean generateVanillaColdTaigaHills = true;
	public static boolean generateVanillaDeepOcean = true;
	public static boolean generateVanillaDesert = true;
	public static boolean generateVanillaDesertHills = true;
	public static boolean generateVanillaExtremeHills = true;
	public static boolean generateVanillaExtremeHillsPlus = true;
	public static boolean generateVanillaForest = true;
	public static boolean generateVanillaForestHills = true;
	public static boolean generateVanillaFrozenRiver = true;
	public static boolean generateVanillaIceMountains = true;
	public static boolean generateVanillaIcePlains = true;
	public static boolean generateVanillaJungle = true;
	public static boolean generateVanillaJungleEdge = true;
	public static boolean generateVanillaJungleHills = true;
	public static boolean generateVanillaMegaTaiga = true;
	public static boolean generateVanillaMegaTaigaHills = true;
	public static boolean generateVanillaMesa = true;
	public static boolean generateVanillaMesaPlateau = true;
	public static boolean generateVanillaMesaPlateau_F = true;
	public static boolean generateVanillaMushroomIsland = true;
	public static boolean generateVanillaMushroomIslandShore = true;
	public static boolean generateVanillaOcean = true;
	public static boolean generateVanillaPlains = true;
	public static boolean generateVanillaRiver = true;
	public static boolean generateVanillaRoofedForest = true;
	public static boolean generateVanillaSavanna = true;
	public static boolean generateVanillaSavannaPlateau = true;
	public static boolean generateVanillaStoneBeach = true;
	public static boolean generateVanillaSwampland = true;
	public static boolean generateVanillaTaiga = true;
	public static boolean generateVanillaTaigaHills = true;
	
	public static boolean generateEmeralds = true;
	
	public static boolean enableCobblestoneBoulders = true;
	public static int cobblestoneBoulderChance = 1;
	
	public static boolean showDebugInfo = false;
	public static boolean enableDebugging = false;
	
	public static int biomeSize;
	
	public static boolean useVillageMods;
	public static int villageDistance;
	public static int villageSize;
	
	
	public static void init(File configFile) 
	{
		config = new Configuration(configFile);
		
		try 
		{
			config.load();
			
			/*
			####################################################################################################
			# START VANILLA BIOMES
			####################################################################################################
			*/
			
			generateVanillaBiomes = config.getBoolean("Generate Vanilla Biomes", "Vanilla Biomes", true, "");
			
			generateOnlyThisVanillaBiome = 
				config.getString(
					"Generate only this vanilla biome", 
					"Vanilla Biomes", 
					new String(), 
					"Must correspond to one of the vanilla biome variables found in the registerVanillaBiomes() method here:" + 
					Configuration.NEW_LINE +
					"(e.g. desert, extremeHills, forest, taiga, taigaHills, etc.)"
				);
			
			generateVanillaBeach = config.getBoolean("generateVanillaBeach", "Vanilla Biomes", true, "");
			generateVanillaStoneBeach = config.getBoolean("generateVanillaStoneBeach", "Vanilla Biomes", true, "");
			generateVanillaColdBeach = config.getBoolean("generateVanillaColdBeach", "Vanilla Biomes", true, "");
			generateVanillaDesert = config.getBoolean("generateVanillaDesert", "Vanilla Biomes", true, "");
			generateVanillaDesertHills = config.getBoolean("generateVanillaDesertHills", "Vanilla Biomes", true, "");
			generateVanillaExtremeHills = config.getBoolean("generateVanillaExtremeHills", "Vanilla Biomes", true, "");
			generateVanillaExtremeHillsPlus = config.getBoolean("generateVanillaExtremeHillsPlus", "Vanilla Biomes", true, "");
			generateVanillaForest = config.getBoolean("generateVanillaForest", "Vanilla Biomes", true, "");
			generateVanillaForestHills = config.getBoolean("generateVanillaForestHills", "Vanilla Biomes", true, "");
			generateVanillaBirchForest = config.getBoolean("generateVanillaBirchForest", "Vanilla Biomes", true, "");
			generateVanillaBirchForestHills = config.getBoolean("generateVanillaBirchForestHills", "Vanilla Biomes", true, "");
			generateVanillaRoofedForest = config.getBoolean("generateVanillaRoofedForest", "Vanilla Biomes", true, "");
			generateVanillaIcePlains = config.getBoolean("generateVanillaIcePlains", "Vanilla Biomes", true, "");
			generateVanillaIceMountains = config.getBoolean("generateVanillaIceMountains", "Vanilla Biomes", true, "");
			generateVanillaJungle = config.getBoolean("generateVanillaJungle", "Vanilla Biomes", true, "");
			generateVanillaJungleHills = config.getBoolean("generateVanillaJungleHills", "Vanilla Biomes", true, "");
			generateVanillaJungleEdge = config.getBoolean("generateVanillaJungleEdge", "Vanilla Biomes", true, "");
			generateVanillaMesa = config.getBoolean("generateVanillaMesa", "Vanilla Biomes", true, "");
			generateVanillaMesaPlateau = config.getBoolean("generateVanillaMesaPlateau", "Vanilla Biomes", true, "");
			generateVanillaMesaPlateau_F = config.getBoolean("generateVanillaMesaPlateau_F", "Vanilla Biomes", true, "");
			generateVanillaMushroomIsland = config.getBoolean("generateVanillaMushroomIsland", "Vanilla Biomes", true, "");
			generateVanillaMushroomIslandShore = config.getBoolean("generateVanillaMushroomIslandShore", "Vanilla Biomes", true, "");
			generateVanillaOcean = config.getBoolean("generateVanillaOcean", "Vanilla Biomes", true, "");
			generateVanillaDeepOcean = config.getBoolean("generateVanillaDeepOcean", "Vanilla Biomes", true, "");
			generateVanillaPlains = config.getBoolean("generateVanillaPlains", "Vanilla Biomes", true, "");
			generateVanillaRiver = config.getBoolean("generateVanillaRiver", "Vanilla Biomes", true, "This setting is ignored. Rivers will always generate, even if set to false.");
			generateVanillaFrozenRiver = config.getBoolean("generateVanillaFrozenRiver", "Vanilla Biomes", true, "");
			generateVanillaSavanna = config.getBoolean("generateVanillaSavanna", "Vanilla Biomes", true, "");
			generateVanillaSavannaPlateau = config.getBoolean("generateVanillaSavannaPlateau", "Vanilla Biomes", true, "");
			generateVanillaSwampland = config.getBoolean("generateVanillaSwampland", "Vanilla Biomes", true, "");
			generateVanillaTaiga = config.getBoolean("generateVanillaTaiga", "Vanilla Biomes", true, "");
			generateVanillaTaigaHills = config.getBoolean("generateVanillaTaigaHills", "Vanilla Biomes", true, "");
			generateVanillaColdTaiga = config.getBoolean("generateVanillaColdTaiga", "Vanilla Biomes", true, "");
			generateVanillaColdTaigaHills = config.getBoolean("generateVanillaColdTaigaHills", "Vanilla Biomes", true, "");
			generateVanillaMegaTaiga = config.getBoolean("generateVanillaMegaTaiga", "Vanilla Biomes", true, "");
			generateVanillaMegaTaigaHills = config.getBoolean("generateVanillaMegaTaigaHills", "Vanilla Biomes", true, "");
			
			/*
			####################################################################################################
			# END VANILLA BIOMES
			####################################################################################################
			*/
			
			/*
			####################################################################################################
			# START MISCELLANEOUS
			####################################################################################################
			*/
			
			generateEmeralds = config.getBoolean("Generate Emeralds", "Miscellaneous", true, "");
			
			enableCobblestoneBoulders = config.getBoolean("Enable Cobblestone Boulders", "Miscellaneous", true, "");
			cobblestoneBoulderChance = config.getInt("1/x chance that Cobblestone Boulders will generate if given the opportunity to do so during world gen", "Miscellaneous", 1, 1, 100, "1 = Always generate if possible; 2 = 50% chance; 4 = 25% chance");
			
			showDebugInfo = config.getBoolean("Show Debug Info in F3 Screen", "Miscellaneous", false, "");
			enableDebugging = config.getBoolean("Enable Debugging", "Miscellaneous", false, "WARNING: This should only be enabled if you know what you're doing.");
				
			biomeSize = config.get(config.CATEGORY_GENERAL, "Size of biomes", 4, "Normal is 4, large biomes is 6, but other sizes can be chosen").getInt();
			
			useVillageMods = config.get(config.CATEGORY_GENERAL, "Enable the modifications to villages", true, "Disabling this will solve issues with other mods which edit the way villages generate").getBoolean(true);
			villageDistance = config.get(config.CATEGORY_GENERAL, "Distance between villages", 32, "Normal is 32").getInt();
			villageSize = config.get(config.CATEGORY_GENERAL, "Size of villages", 0, "Normal is 0").getInt();
			
			/*
			####################################################################################################
			# END MISCELLANEOUS
			####################################################################################################
			*/
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
