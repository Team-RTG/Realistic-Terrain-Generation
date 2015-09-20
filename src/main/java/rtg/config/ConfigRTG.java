package rtg.config;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.config.Configuration;

public class ConfigRTG
{
	public static Configuration config;
	
	public static int[] biomeIDs = new int[25];

	/*public static boolean generateRtgRiverIce = true;
	public static boolean generateRtgRiverCold = true;
	public static boolean generateRtgRiverTemperate = true;
	public static boolean generateRtgRiverHot = true;
	public static boolean generateRtgRiverWet = true;
	public static boolean generateRtgRiverOasis = true;
	public static boolean generateRtgOceanIce = true;
	public static boolean generateRtgOceanCold = true;
	public static boolean generateRtgOceanTemperate = true;
	public static boolean generateRtgOceanHot = true;
	public static boolean generateRtgOceanWet = true;
	public static boolean generateRtgOceanOasis = true;
	public static boolean generateRtgSnowDesert = true;
	public static boolean generateRtgSnowForest = true;
	public static boolean generateRtgColdPlains = true;
	public static boolean generateRtgColdForest = true;
	public static boolean generateRtgHotPlains = true;
	public static boolean generateRtgHotForest = true;
	public static boolean generateRtgHotDesert = true;
	public static boolean generateRtgPlains = true;
	public static boolean generateRtgTropicalIsland = true;
	public static boolean generateRtgRedwood = true;
	public static boolean generateRtgJungle = true;
	public static boolean generateRtgOasis = true;
	public static boolean generateRtgTemperateForest = true;*/
	
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
	
	public static void init(FMLPreInitializationEvent event)
	{
		config = new Configuration(event.getSuggestedConfigurationFile());

		for(int c = 0; c < biomeIDs.length; c++)
		{
			biomeIDs[c] = 200 + c;
		}
		
		try 
		{
			config.load();
			
			/*
			####################################################################################################
			# START BIOME IDS
			####################################################################################################
			*/
			
			biomeIDs[0] = config.get("Biome IDs", "00 rtg_riverIce", 200).getInt();
			biomeIDs[1] = config.get("Biome IDs", "01 rtg_riverCold", 201).getInt();
			biomeIDs[2] = config.get("Biome IDs", "02 rtg_riverTemperate", 202).getInt();
			biomeIDs[3] = config.get("Biome IDs", "03 rtg_riverHot", 203).getInt();
			biomeIDs[4] = config.get("Biome IDs", "04 rtg_riverWet", 204).getInt();
			biomeIDs[5] = config.get("Biome IDs", "05 rtg_riverOasis", 205).getInt();
			
			biomeIDs[6] = config.get("Biome IDs", "06 rtg_oceanIce", 206).getInt();
			biomeIDs[7] = config.get("Biome IDs", "07 rtg_oceanCold", 207).getInt();
			biomeIDs[8] = config.get("Biome IDs", "08 rtg_oceanTemperate", 208).getInt();
			biomeIDs[9] = config.get("Biome IDs", "09 rtg_oceanHot", 209).getInt();
			biomeIDs[10] = config.get("Biome IDs", "10 rtg_oceanWet", 210).getInt();
			biomeIDs[11] = config.get("Biome IDs", "11 rtg_oceanOasis", 211).getInt();
			
			biomeIDs[12] = config.get("Biome IDs", "12 rtg_snowDesert", 212).getInt();
			biomeIDs[13] = config.get("Biome IDs", "13 rtg_snowForest", 213).getInt();
			biomeIDs[14] = config.get("Biome IDs", "14 rtg_coldPlains", 214).getInt();
			biomeIDs[15] = config.get("Biome IDs", "15 rtg_coldForest", 215).getInt();
			biomeIDs[16] = config.get("Biome IDs", "16 rtg_hotPlains", 216).getInt();
			biomeIDs[17] = config.get("Biome IDs", "17 rtg_hotForest", 217).getInt();
			biomeIDs[18] = config.get("Biome IDs", "18 rtg_hotDesert", 218).getInt();
			biomeIDs[19] = config.get("Biome IDs", "19 rtg_plains", 219).getInt();
			biomeIDs[20] = config.get("Biome IDs", "20 rtg_tropical", 220).getInt();
			biomeIDs[21] = config.get("Biome IDs", "21 rtg_redwood", 221).getInt();
			biomeIDs[22] = config.get("Biome IDs", "22 rtg_jungle", 222).getInt();
			biomeIDs[23] = config.get("Biome IDs", "23 rtg_oasis", 223).getInt();
			biomeIDs[24] = config.get("Biome IDs", "24 rtg_temperateForest", 224).getInt();

			/*
			####################################################################################################
			# END BIOME IDS
			####################################################################################################
			*/
			
			/*
			####################################################################################################
			# START RTG BIOMES
			####################################################################################################
			*/
			
			/*generateRtgRiverIce = config.getBoolean("generateRtgRiverIce", "RTG Biomes", true, "");
			generateRtgRiverCold = config.getBoolean("generateRtgRiverCold", "RTG Biomes", true, "");
			generateRtgRiverTemperate = config.getBoolean("generateRtgRiverTemperate", "RTG Biomes", true, "");
			generateRtgRiverHot = config.getBoolean("generateRtgRiverHot", "RTG Biomes", true, "");
			generateRtgRiverWet = config.getBoolean("generateRtgRiverWet", "RTG Biomes", true, "");
			generateRtgRiverOasis = config.getBoolean("generateRtgRiverOasis", "RTG Biomes", true, "");
			generateRtgOceanIce = config.getBoolean("generateRtgOceanIce", "RTG Biomes", true, "");
			generateRtgOceanCold = config.getBoolean("generateRtgOceanCold", "RTG Biomes", true, "");
			generateRtgOceanTemperate = config.getBoolean("generateRtgOceanTemperate", "RTG Biomes", true, "");
			generateRtgOceanHot = config.getBoolean("generateRtgOceanHot", "RTG Biomes", true, "");
			generateRtgOceanWet = config.getBoolean("generateRtgOceanWet", "RTG Biomes", true, "");
			generateRtgOceanOasis = config.getBoolean("generateRtgOceanOasis", "RTG Biomes", true, "");
			generateRtgSnowDesert = config.getBoolean("generateRtgSnowDesert", "RTG Biomes", true, "");
			generateRtgSnowForest = config.getBoolean("generateRtgSnowForest", "RTG Biomes", true, "");
			generateRtgColdPlains = config.getBoolean("generateRtgColdPlains", "RTG Biomes", true, "");
			generateRtgColdForest = config.getBoolean("generateRtgColdForest", "RTG Biomes", true, "");
			generateRtgHotPlains = config.getBoolean("generateRtgHotPlains", "RTG Biomes", true, "");
			generateRtgHotForest = config.getBoolean("generateRtgHotForest", "RTG Biomes", true, "");
			generateRtgHotDesert = config.getBoolean("generateRtgHotDesert", "RTG Biomes", true, "");
			generateRtgPlains = config.getBoolean("generateRtgPlains", "RTG Biomes", true, "");
			generateRtgTropicalIsland = config.getBoolean("generateRtgTropicalIsland", "RTG Biomes", true, "");
			generateRtgRedwood = config.getBoolean("generateRtgRedwood", "RTG Biomes", true, "");
			generateRtgJungle = config.getBoolean("generateRtgJungle", "RTG Biomes", true, "");
			generateRtgOasis = config.getBoolean("generateRtgOasis", "RTG Biomes", true, "");
			generateRtgTemperateForest = config.getBoolean("generateRtgTemperateForest", "RTG Biomes", true, "");*/
				
			/*
			####################################################################################################
			# END RTG BIOMES
			####################################################################################################
			*/
			
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
			generateVanillaRiver = config.getBoolean("generateVanillaRiver", "Vanilla Biomes", true, "");
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
			
			/*
			####################################################################################################
			# END MISCELLANEOUS
			####################################################################################################
			*/
		}
		catch (Exception e) 
		{
			for(int c = 0; c < biomeIDs.length; c++)
			{
				biomeIDs[c] = 200 + c;
			}
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
