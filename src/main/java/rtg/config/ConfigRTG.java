package rtg.config;

import java.io.File;

import org.apache.logging.log4j.Level;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.config.Configuration;

public class ConfigRTG
{
	public static Configuration config;
	
	public static final int biomeWeightMin = 0;
	public static final int biomeWeightMax = 100;
	public static final int biomeWeightDefault = 10;
	
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
	
	public static int weightVanillaBeach = (int)Math.floor((double)(biomeWeightDefault * 0.8));
	public static int weightVanillaBirchForest = biomeWeightDefault;
	public static int weightVanillaBirchForestHills = (int)Math.floor((double)(biomeWeightDefault * 0.9));
	public static int weightVanillaColdBeach = (int)Math.floor((double)(biomeWeightDefault * 0.5));
	public static int weightVanillaColdTaiga = biomeWeightDefault;
	public static int weightVanillaColdTaigaHills = (int)Math.floor((double)(biomeWeightDefault * 0.9));
	public static int weightVanillaDeepOcean = (int)Math.floor((double)(biomeWeightDefault * 0.8));
	public static int weightVanillaDesert = biomeWeightDefault;
	public static int weightVanillaDesertHills = (int)Math.floor((double)(biomeWeightDefault * 0.9));
	public static int weightVanillaExtremeHills = biomeWeightDefault;
	public static int weightVanillaExtremeHillsPlus = (int)Math.floor((double)(biomeWeightDefault * 0.5));
	public static int weightVanillaForest = biomeWeightDefault;
	public static int weightVanillaForestHills = (int)Math.floor((double)(biomeWeightDefault * 0.9));
	public static int weightVanillaFrozenRiver = (int)Math.floor((double)(biomeWeightDefault * 0.5));
	public static int weightVanillaIceMountains = (int)Math.floor((double)(biomeWeightDefault * 0.5));
	public static int weightVanillaIcePlains = biomeWeightDefault;
	public static int weightVanillaJungle = biomeWeightDefault;
	public static int weightVanillaJungleEdge = (int)Math.floor((double)(biomeWeightDefault * 0.5));
	public static int weightVanillaJungleHills = (int)Math.floor((double)(biomeWeightDefault * 0.9));
	public static int weightVanillaMegaTaiga = (int)Math.floor((double)(biomeWeightDefault * 0.4));
	public static int weightVanillaMegaTaigaHills = (int)Math.floor((double)(biomeWeightDefault * 0.3));
	public static int weightVanillaMesa = (int)Math.floor((double)(biomeWeightDefault * 0.3));
	public static int weightVanillaMesaPlateau = (int)Math.floor((double)(biomeWeightDefault * 0.2));
	public static int weightVanillaMesaPlateau_F = (int)Math.floor((double)(biomeWeightDefault * 0.2));
	public static int weightVanillaMushroomIsland = (int)Math.floor((double)(biomeWeightDefault * 0.1));
	public static int weightVanillaMushroomIslandShore = (int)Math.floor((double)(biomeWeightDefault * 0.1));
	public static int weightVanillaOcean = (int)Math.floor((double)(biomeWeightDefault * 0.8));
	public static int weightVanillaPlains = biomeWeightDefault;
	public static int weightVanillaRiver = biomeWeightDefault;
	public static int weightVanillaRoofedForest = biomeWeightDefault;
	public static int weightVanillaSavanna = biomeWeightDefault;
	public static int weightVanillaSavannaPlateau = (int)Math.floor((double)(biomeWeightDefault * 0.9));
	public static int weightVanillaStoneBeach = (int)Math.floor((double)(biomeWeightDefault * 0.5));
	public static int weightVanillaSwampland = biomeWeightDefault;
	public static int weightVanillaTaiga = biomeWeightDefault;
	public static int weightVanillaTaigaHills = (int)Math.floor((double)(biomeWeightDefault * 0.9));
	
	public static boolean generateEmeralds = true;
	
	public static boolean enableCobblestoneBoulders = true;
	public static int cobblestoneBoulderChance = 1;
	
	public static boolean showDebugInfo = false;
	public static boolean enableDebugging = false;

	public static int biomeSize = 4;
	
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
					"https://github.com/MinecraftForge/MinecraftForge/blob/master/src/main/java/net/minecraftforge/common/BiomeDictionary.java#L468" +
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
			
			weightVanillaBeach = config.getInt("weightVanillaBeach", "Vanilla Biome Weights", weightVanillaBeach, biomeWeightMin, biomeWeightMax, "");
			weightVanillaBirchForest = config.getInt("weightVanillaBirchForest", "Vanilla Biome Weights", weightVanillaBirchForest, biomeWeightMin, biomeWeightMax, "");
			weightVanillaBirchForestHills = config.getInt("weightVanillaBirchForestHills", "Vanilla Biome Weights", weightVanillaBirchForestHills, biomeWeightMin, biomeWeightMax, "");
			weightVanillaColdBeach = config.getInt("weightVanillaColdBeach", "Vanilla Biome Weights", weightVanillaColdBeach, biomeWeightMin, biomeWeightMax, "");
			weightVanillaColdTaiga = config.getInt("weightVanillaColdTaiga", "Vanilla Biome Weights", weightVanillaColdTaiga, biomeWeightMin, biomeWeightMax, "");
			weightVanillaColdTaigaHills = config.getInt("weightVanillaColdTaigaHills", "Vanilla Biome Weights", weightVanillaColdTaigaHills, biomeWeightMin, biomeWeightMax, "");
			weightVanillaDeepOcean = config.getInt("weightVanillaDeepOcean", "Vanilla Biome Weights", weightVanillaDeepOcean, biomeWeightMin, biomeWeightMax, "");
			weightVanillaDesert = config.getInt("weightVanillaDesert", "Vanilla Biome Weights", weightVanillaDesert, biomeWeightMin, biomeWeightMax, "");
			weightVanillaDesertHills = config.getInt("weightVanillaDesertHills", "Vanilla Biome Weights", weightVanillaDesertHills, biomeWeightMin, biomeWeightMax, "");
			weightVanillaExtremeHills = config.getInt("weightVanillaExtremeHills", "Vanilla Biome Weights", weightVanillaExtremeHills, biomeWeightMin, biomeWeightMax, "");
			weightVanillaExtremeHillsPlus = config.getInt("weightVanillaExtremeHillsPlus", "Vanilla Biome Weights", weightVanillaExtremeHillsPlus, biomeWeightMin, biomeWeightMax, "");
			weightVanillaForest = config.getInt("weightVanillaForest", "Vanilla Biome Weights", weightVanillaForest, biomeWeightMin, biomeWeightMax, "");
			weightVanillaForestHills = config.getInt("weightVanillaForestHills", "Vanilla Biome Weights", weightVanillaForestHills, biomeWeightMin, biomeWeightMax, "");
			weightVanillaFrozenRiver = config.getInt("weightVanillaFrozenRiver", "Vanilla Biome Weights", weightVanillaFrozenRiver, biomeWeightMin, biomeWeightMax, "");
			weightVanillaIceMountains = config.getInt("weightVanillaIceMountains", "Vanilla Biome Weights", weightVanillaIceMountains, biomeWeightMin, biomeWeightMax, "");
			weightVanillaIcePlains = config.getInt("weightVanillaIcePlains", "Vanilla Biome Weights", weightVanillaIcePlains, biomeWeightMin, biomeWeightMax, "");
			weightVanillaJungle = config.getInt("weightVanillaJungle", "Vanilla Biome Weights", weightVanillaJungle, biomeWeightMin, biomeWeightMax, "");
			weightVanillaJungleEdge = config.getInt("weightVanillaJungleEdge", "Vanilla Biome Weights", weightVanillaJungleEdge, biomeWeightMin, biomeWeightMax, "");
			weightVanillaJungleHills = config.getInt("weightVanillaJungleHills", "Vanilla Biome Weights", weightVanillaJungleHills, biomeWeightMin, biomeWeightMax, "");
			weightVanillaMegaTaiga = config.getInt("weightVanillaMegaTaiga", "Vanilla Biome Weights", weightVanillaMegaTaiga, biomeWeightMin, biomeWeightMax, "");
			weightVanillaMegaTaigaHills = config.getInt("weightVanillaMegaTaigaHills", "Vanilla Biome Weights", weightVanillaMegaTaigaHills, biomeWeightMin, biomeWeightMax, "");
			weightVanillaMesa = config.getInt("weightVanillaMesa", "Vanilla Biome Weights", weightVanillaMesa, biomeWeightMin, biomeWeightMax, "");
			weightVanillaMesaPlateau = config.getInt("weightVanillaMesaPlateau", "Vanilla Biome Weights", weightVanillaMesaPlateau, biomeWeightMin, biomeWeightMax, "");
			weightVanillaMesaPlateau_F = config.getInt("weightVanillaMesaPlateau_F", "Vanilla Biome Weights", weightVanillaMesaPlateau_F, biomeWeightMin, biomeWeightMax, "");
			weightVanillaMushroomIsland = config.getInt("weightVanillaMushroomIsland", "Vanilla Biome Weights", weightVanillaMushroomIsland, biomeWeightMin, biomeWeightMax, "");
			weightVanillaMushroomIslandShore = config.getInt("weightVanillaMushroomIslandShore", "Vanilla Biome Weights", weightVanillaMushroomIslandShore, biomeWeightMin, biomeWeightMax, "");
			weightVanillaOcean = config.getInt("weightVanillaOcean", "Vanilla Biome Weights", weightVanillaOcean, biomeWeightMin, biomeWeightMax, "");
			weightVanillaPlains = config.getInt("weightVanillaPlains", "Vanilla Biome Weights", weightVanillaPlains, biomeWeightMin, biomeWeightMax, "");
			weightVanillaRiver = config.getInt("weightVanillaRiver", "Vanilla Biome Weights", weightVanillaRiver, biomeWeightMin, biomeWeightMax, "");
			weightVanillaRoofedForest = config.getInt("weightVanillaRoofedForest", "Vanilla Biome Weights", weightVanillaRoofedForest, biomeWeightMin, biomeWeightMax, "");
			weightVanillaSavanna = config.getInt("weightVanillaSavanna", "Vanilla Biome Weights", weightVanillaSavanna, biomeWeightMin, biomeWeightMax, "");
			weightVanillaSavannaPlateau = config.getInt("weightVanillaSavannaPlateau", "Vanilla Biome Weights", weightVanillaSavannaPlateau, biomeWeightMin, biomeWeightMax, "");
			weightVanillaStoneBeach = config.getInt("weightVanillaStoneBeach", "Vanilla Biome Weights", weightVanillaStoneBeach, biomeWeightMin, biomeWeightMax, "");
			weightVanillaSwampland = config.getInt("weightVanillaSwampland", "Vanilla Biome Weights", weightVanillaSwampland, biomeWeightMin, biomeWeightMax, "");
			weightVanillaTaiga = config.getInt("weightVanillaTaiga", "Vanilla Biome Weights", weightVanillaTaiga, biomeWeightMin, biomeWeightMax, "");
			weightVanillaTaigaHills = config.getInt("weightVanillaTaigaHills", "Vanilla Biome Weights", weightVanillaTaigaHills, biomeWeightMin, biomeWeightMax, "");
			
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
			
			biomeSize = config.getInt("Size of Biomes", "Miscellaneous", 4, 4, 6, "COMING SOON!!! 4 = Default World Type; 6 = Large Biomes World Type");
			
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
