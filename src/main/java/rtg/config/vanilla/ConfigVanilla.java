package rtg.config.vanilla;

import java.io.File;

import org.apache.logging.log4j.Level;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.config.Configuration;

public class ConfigVanilla
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
	
	public static int weightVanillaBeach = (int)Math.floor((double)(biomeWeightDefault * 0.4));
	public static int weightVanillaBirchForest = biomeWeightDefault;
	public static int weightVanillaBirchForestHills = (int)Math.floor((double)(biomeWeightDefault * 0.8));
	public static int weightVanillaColdBeach = (int)Math.floor((double)(biomeWeightDefault * 0.4));
	public static int weightVanillaColdTaiga = biomeWeightDefault;
	public static int weightVanillaColdTaigaHills = (int)Math.floor((double)(biomeWeightDefault * 0.8));
	public static int weightVanillaDeepOcean = (int)Math.floor((double)(biomeWeightDefault * 0.8));
	public static int weightVanillaDesert = biomeWeightDefault;
	public static int weightVanillaDesertHills = (int)Math.floor((double)(biomeWeightDefault * 0.8));
	public static int weightVanillaExtremeHills = biomeWeightDefault;
	public static int weightVanillaExtremeHillsPlus = (int)Math.floor((double)(biomeWeightDefault * 0.2));
	public static int weightVanillaForest = biomeWeightDefault;
	public static int weightVanillaForestHills = (int)Math.floor((double)(biomeWeightDefault * 0.8));
	public static int weightVanillaFrozenRiver = (int)Math.floor((double)(biomeWeightDefault * 0.1));
	public static int weightVanillaIceMountains = (int)Math.floor((double)(biomeWeightDefault * 0.2));
	public static int weightVanillaIcePlains = biomeWeightDefault;
	public static int weightVanillaJungle = biomeWeightDefault;
	public static int weightVanillaJungleEdge = (int)Math.floor((double)(biomeWeightDefault * 0.5));
	public static int weightVanillaJungleHills = (int)Math.floor((double)(biomeWeightDefault * 0.8));
	public static int weightVanillaMegaTaiga = (int)Math.floor((double)(biomeWeightDefault * 0.8));
	public static int weightVanillaMegaTaigaHills = (int)Math.floor((double)(biomeWeightDefault * 0.3));
	public static int weightVanillaMesa = (int)Math.floor((double)(biomeWeightDefault * 0.2));
	public static int weightVanillaMesaPlateau = (int)Math.floor((double)(biomeWeightDefault * 0.1));
	public static int weightVanillaMesaPlateau_F = (int)Math.floor((double)(biomeWeightDefault * 0.1));
	public static int weightVanillaMushroomIsland = (int)Math.floor((double)(biomeWeightDefault * 0.1));
	public static int weightVanillaMushroomIslandShore = (int)Math.floor((double)(biomeWeightDefault * 0.1));
	public static int weightVanillaOcean = (int)Math.floor((double)(biomeWeightDefault * 0.8));
	public static int weightVanillaPlains = biomeWeightDefault;
	public static int weightVanillaRiver = (int)Math.floor((double)(biomeWeightDefault * 0.1));
	public static int weightVanillaRoofedForest = (int)Math.floor((double)(biomeWeightDefault * 0.8));
	public static int weightVanillaSavanna = (int)Math.floor((double)(biomeWeightDefault * 0.8));
	public static int weightVanillaSavannaPlateau = (int)Math.floor((double)(biomeWeightDefault * 0.7));
	public static int weightVanillaStoneBeach = (int)Math.floor((double)(biomeWeightDefault * 0.4));
	public static int weightVanillaSwampland = biomeWeightDefault;
	public static int weightVanillaTaiga = biomeWeightDefault;
	public static int weightVanillaTaigaHills = (int)Math.floor((double)(biomeWeightDefault * 0.8));
	
	public static void init(File configFile) 
	{
		config = new Configuration(configFile);
		
		try 
		{
			config.load();

			generateVanillaBiomes = config.getBoolean("Generate Biomes", "Biomes", true, "If TRUE, uses the individual biome settings below. If FALSE, disables all biomes from this mod.");
			
			generateOnlyThisVanillaBiome = 
				config.getString(
					"Generate only this vanilla biome", 
					"Biomes", 
					new String(), 
					"Must correspond to one of the vanilla biome variables found in the registerVanillaBiomes() method here:" + 
					Configuration.NEW_LINE +
					"https://github.com/MinecraftForge/MinecraftForge/blob/master/src/main/java/net/minecraftforge/common/BiomeDictionary.java#L468" +
					Configuration.NEW_LINE +
					"(e.g. desert, extremeHills, forest, taiga, taigaHills, etc.)"
				);
			
			generateVanillaBeach = config.getBoolean("generateVanillaBeach", "Biomes", true, "");
			generateVanillaStoneBeach = config.getBoolean("generateVanillaStoneBeach", "Biomes", true, "");
			generateVanillaColdBeach = config.getBoolean("generateVanillaColdBeach", "Biomes", true, "");
			generateVanillaDesert = config.getBoolean("generateVanillaDesert", "Biomes", true, "");
			generateVanillaDesertHills = config.getBoolean("generateVanillaDesertHills", "Biomes", true, "");
			generateVanillaExtremeHills = config.getBoolean("generateVanillaExtremeHills", "Biomes", true, "");
			generateVanillaExtremeHillsPlus = config.getBoolean("generateVanillaExtremeHillsPlus", "Biomes", true, "");
			generateVanillaForest = config.getBoolean("generateVanillaForest", "Biomes", true, "");
			generateVanillaForestHills = config.getBoolean("generateVanillaForestHills", "Biomes", true, "");
			generateVanillaBirchForest = config.getBoolean("generateVanillaBirchForest", "Biomes", true, "");
			generateVanillaBirchForestHills = config.getBoolean("generateVanillaBirchForestHills", "Biomes", true, "");
			generateVanillaRoofedForest = config.getBoolean("generateVanillaRoofedForest", "Biomes", true, "");
			generateVanillaIcePlains = config.getBoolean("generateVanillaIcePlains", "Biomes", true, "");
			generateVanillaIceMountains = config.getBoolean("generateVanillaIceMountains", "Biomes", true, "");
			generateVanillaJungle = config.getBoolean("generateVanillaJungle", "Biomes", true, "");
			generateVanillaJungleHills = config.getBoolean("generateVanillaJungleHills", "Biomes", true, "");
			generateVanillaJungleEdge = config.getBoolean("generateVanillaJungleEdge", "Biomes", true, "");
			generateVanillaMesa = config.getBoolean("generateVanillaMesa", "Biomes", true, "");
			generateVanillaMesaPlateau = config.getBoolean("generateVanillaMesaPlateau", "Biomes", true, "");
			generateVanillaMesaPlateau_F = config.getBoolean("generateVanillaMesaPlateau_F", "Biomes", true, "");
			generateVanillaMushroomIsland = config.getBoolean("generateVanillaMushroomIsland", "Biomes", true, "");
			generateVanillaMushroomIslandShore = config.getBoolean("generateVanillaMushroomIslandShore", "Biomes", true, "");
			generateVanillaOcean = config.getBoolean("generateVanillaOcean", "Biomes", true, "");
			generateVanillaDeepOcean = config.getBoolean("generateVanillaDeepOcean", "Biomes", true, "");
			generateVanillaPlains = config.getBoolean("generateVanillaPlains", "Biomes", true, "");
			generateVanillaRiver = config.getBoolean("generateVanillaRiver", "Biomes", true, "This setting is ignored. Rivers will always generate, even if set to false.");
			generateVanillaFrozenRiver = config.getBoolean("generateVanillaFrozenRiver", "Biomes", true, "This setting is ignored. Frozen Rivers will always generate, even if set to false.");
			generateVanillaSavanna = config.getBoolean("generateVanillaSavanna", "Biomes", true, "");
			generateVanillaSavannaPlateau = config.getBoolean("generateVanillaSavannaPlateau", "Biomes", true, "");
			generateVanillaSwampland = config.getBoolean("generateVanillaSwampland", "Biomes", true, "");
			generateVanillaTaiga = config.getBoolean("generateVanillaTaiga", "Biomes", true, "");
			generateVanillaTaigaHills = config.getBoolean("generateVanillaTaigaHills", "Biomes", true, "");
			generateVanillaColdTaiga = config.getBoolean("generateVanillaColdTaiga", "Biomes", true, "");
			generateVanillaColdTaigaHills = config.getBoolean("generateVanillaColdTaigaHills", "Biomes", true, "");
			generateVanillaMegaTaiga = config.getBoolean("generateVanillaMegaTaiga", "Biomes", true, "");
			generateVanillaMegaTaigaHills = config.getBoolean("generateVanillaMegaTaigaHills", "Biomes", true, "");
			
			weightVanillaBeach = config.getInt("weightVanillaBeach", "Weights", weightVanillaBeach, biomeWeightMin, biomeWeightMax, "");
			weightVanillaBirchForest = config.getInt("weightVanillaBirchForest", "Weights", weightVanillaBirchForest, biomeWeightMin, biomeWeightMax, "");
			weightVanillaBirchForestHills = config.getInt("weightVanillaBirchForestHills", "Weights", weightVanillaBirchForestHills, biomeWeightMin, biomeWeightMax, "");
			weightVanillaColdBeach = config.getInt("weightVanillaColdBeach", "Weights", weightVanillaColdBeach, biomeWeightMin, biomeWeightMax, "");
			weightVanillaColdTaiga = config.getInt("weightVanillaColdTaiga", "Weights", weightVanillaColdTaiga, biomeWeightMin, biomeWeightMax, "");
			weightVanillaColdTaigaHills = config.getInt("weightVanillaColdTaigaHills", "Weights", weightVanillaColdTaigaHills, biomeWeightMin, biomeWeightMax, "");
			weightVanillaDeepOcean = config.getInt("weightVanillaDeepOcean", "Weights", weightVanillaDeepOcean, biomeWeightMin, biomeWeightMax, "");
			weightVanillaDesert = config.getInt("weightVanillaDesert", "Weights", weightVanillaDesert, biomeWeightMin, biomeWeightMax, "");
			weightVanillaDesertHills = config.getInt("weightVanillaDesertHills", "Weights", weightVanillaDesertHills, biomeWeightMin, biomeWeightMax, "");
			weightVanillaExtremeHills = config.getInt("weightVanillaExtremeHills", "Weights", weightVanillaExtremeHills, biomeWeightMin, biomeWeightMax, "");
			weightVanillaExtremeHillsPlus = config.getInt("weightVanillaExtremeHillsPlus", "Weights", weightVanillaExtremeHillsPlus, biomeWeightMin, biomeWeightMax, "");
			weightVanillaForest = config.getInt("weightVanillaForest", "Weights", weightVanillaForest, biomeWeightMin, biomeWeightMax, "");
			weightVanillaForestHills = config.getInt("weightVanillaForestHills", "Weights", weightVanillaForestHills, biomeWeightMin, biomeWeightMax, "");
			weightVanillaFrozenRiver = config.getInt("weightVanillaFrozenRiver", "Weights", weightVanillaFrozenRiver, biomeWeightMin, biomeWeightMax, "");
			weightVanillaIceMountains = config.getInt("weightVanillaIceMountains", "Weights", weightVanillaIceMountains, biomeWeightMin, biomeWeightMax, "");
			weightVanillaIcePlains = config.getInt("weightVanillaIcePlains", "Weights", weightVanillaIcePlains, biomeWeightMin, biomeWeightMax, "");
			weightVanillaJungle = config.getInt("weightVanillaJungle", "Weights", weightVanillaJungle, biomeWeightMin, biomeWeightMax, "");
			weightVanillaJungleEdge = config.getInt("weightVanillaJungleEdge", "Weights", weightVanillaJungleEdge, biomeWeightMin, biomeWeightMax, "");
			weightVanillaJungleHills = config.getInt("weightVanillaJungleHills", "Weights", weightVanillaJungleHills, biomeWeightMin, biomeWeightMax, "");
			weightVanillaMegaTaiga = config.getInt("weightVanillaMegaTaiga", "Weights", weightVanillaMegaTaiga, biomeWeightMin, biomeWeightMax, "");
			weightVanillaMegaTaigaHills = config.getInt("weightVanillaMegaTaigaHills", "Weights", weightVanillaMegaTaigaHills, biomeWeightMin, biomeWeightMax, "");
			weightVanillaMesa = config.getInt("weightVanillaMesa", "Weights", weightVanillaMesa, biomeWeightMin, biomeWeightMax, "");
			weightVanillaMesaPlateau = config.getInt("weightVanillaMesaPlateau", "Weights", weightVanillaMesaPlateau, biomeWeightMin, biomeWeightMax, "");
			weightVanillaMesaPlateau_F = config.getInt("weightVanillaMesaPlateau_F", "Weights", weightVanillaMesaPlateau_F, biomeWeightMin, biomeWeightMax, "");
			weightVanillaMushroomIsland = config.getInt("weightVanillaMushroomIsland", "Weights", weightVanillaMushroomIsland, biomeWeightMin, biomeWeightMax, "");
			weightVanillaMushroomIslandShore = config.getInt("weightVanillaMushroomIslandShore", "Weights", weightVanillaMushroomIslandShore, biomeWeightMin, biomeWeightMax, "");
			weightVanillaOcean = config.getInt("weightVanillaOcean", "Weights", weightVanillaOcean, biomeWeightMin, biomeWeightMax, "");
			weightVanillaPlains = config.getInt("weightVanillaPlains", "Weights", weightVanillaPlains, biomeWeightMin, biomeWeightMax, "");
			weightVanillaRiver = config.getInt("weightVanillaRiver", "Weights", weightVanillaRiver, biomeWeightMin, biomeWeightMax, "");
			weightVanillaRoofedForest = config.getInt("weightVanillaRoofedForest", "Weights", weightVanillaRoofedForest, biomeWeightMin, biomeWeightMax, "");
			weightVanillaSavanna = config.getInt("weightVanillaSavanna", "Weights", weightVanillaSavanna, biomeWeightMin, biomeWeightMax, "");
			weightVanillaSavannaPlateau = config.getInt("weightVanillaSavannaPlateau", "Weights", weightVanillaSavannaPlateau, biomeWeightMin, biomeWeightMax, "");
			weightVanillaStoneBeach = config.getInt("weightVanillaStoneBeach", "Weights", weightVanillaStoneBeach, biomeWeightMin, biomeWeightMax, "");
			weightVanillaSwampland = config.getInt("weightVanillaSwampland", "Weights", weightVanillaSwampland, biomeWeightMin, biomeWeightMax, "");
			weightVanillaTaiga = config.getInt("weightVanillaTaiga", "Weights", weightVanillaTaiga, biomeWeightMin, biomeWeightMax, "");
			weightVanillaTaigaHills = config.getInt("weightVanillaTaigaHills", "Weights", weightVanillaTaigaHills, biomeWeightMin, biomeWeightMax, "");
		}
		catch (Exception e) 
		{
			FMLLog.log(Level.ERROR, e, "RTG has had a problem loading Vanilla configuration.");	
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
