package rtg.config.vanilla;

import java.io.File;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.Level;

import rtg.api.biome.vanilla.config.BiomeConfigVanilla;
import rtg.config.BiomeConfigManager;
import rtg.world.biome.BiomeBase;
import cpw.mods.fml.common.FMLLog;

import net.minecraftforge.common.config.Configuration;

public class ConfigVanilla
{
	public static Configuration config;
	
	public static boolean generateVanillaBiomes = true;
	
    public static boolean villageVanillaBeach = true;
    public static boolean villageVanillaBirchForest = true;
    public static boolean villageVanillaBirchForestHills = true;
    public static boolean villageVanillaBirchForestHillsM = true;
    public static boolean villageVanillaBirchForestM = true;
    public static boolean villageVanillaColdBeach = true;
    public static boolean villageVanillaColdTaiga = true;
    public static boolean villageVanillaColdTaigaHills = true;
    public static boolean villageVanillaColdTaigaM = true;
    public static boolean villageVanillaDeepOcean = false;
    public static boolean villageVanillaDesert = true;
    public static boolean villageVanillaDesertHills = true;
    public static boolean villageVanillaDesertM = true;
    public static boolean villageVanillaExtremeHills = true;
    public static boolean villageVanillaExtremeHillsEdge = true;
    public static boolean villageVanillaExtremeHillsM = true;
    public static boolean villageVanillaExtremeHillsPlus = true;
    public static boolean villageVanillaExtremeHillsPlusM = true;
    public static boolean villageVanillaFlowerForest = true;
    public static boolean villageVanillaForest = true;
    public static boolean villageVanillaForestHills = true;
    //public static boolean villageVanillaFrozenRiver = true;
    public static boolean villageVanillaFrozenOcean = false;
    public static boolean villageVanillaIceMountains = true;
    public static boolean villageVanillaIcePlains = true;
    public static boolean villageVanillaIcePlainsSpikes = true;
    public static boolean villageVanillaJungle = true;
    public static boolean villageVanillaJungleEdge = true;
    public static boolean villageVanillaJungleEdgeM = true;
    public static boolean villageVanillaJungleHills = true;
    public static boolean villageVanillaJungleM = true;
    public static boolean villageVanillaMegaSpruceTaiga = true;
    public static boolean villageVanillaMegaTaiga = true;
    public static boolean villageVanillaMegaTaigaHills = true;
    public static boolean villageVanillaMesa = true;
    public static boolean villageVanillaMesaBryce = true;
    public static boolean villageVanillaMesaPlateau = true;
    public static boolean villageVanillaMesaPlateau_F = true;
    public static boolean villageVanillaMesaPlateauFM = true;
    public static boolean villageVanillaMesaPlateauM = true;
    public static boolean villageVanillaMushroomIsland = true;
    public static boolean villageVanillaMushroomIslandShore = true;
    public static boolean villageVanillaOcean = false;
    public static boolean villageVanillaPlains = true;
    public static boolean villageVanillaRedwoodTaigaHills = true;
    //public static boolean villageVanillaRiver = true;
    public static boolean villageVanillaRoofedForest = true;
    public static boolean villageVanillaRoofedForestM = true;
    public static boolean villageVanillaSavanna = true;
    public static boolean villageVanillaSavannaM = true;
    public static boolean villageVanillaSavannaPlateau = true;
    public static boolean villageVanillaSavannaPlateauM = true;
    public static boolean villageVanillaStoneBeach = true;
    public static boolean villageVanillaSunflowerPlains = true;
    public static boolean villageVanillaSwampland = true;
    public static boolean villageVanillaSwamplandM = true;
    public static boolean villageVanillaTaiga = true;
    public static boolean villageVanillaTaigaHills = true;
    public static boolean villageVanillaTaigaM = true;
	
	public static void init(File configFile) 
	{
		config = new Configuration(configFile);
		
		try 
		{
			config.load();

			generateVanillaBiomes = config.getBoolean("Allow vanilla biomes to generate", "Allow vanilla biomes", generateVanillaBiomes, "If TRUE, uses the individual biome settings below. If FALSE, disables all vanilla biomes." + Configuration.NEW_LINE);
            
            villageVanillaBeach = config.getBoolean(formatConfig("villageVanillaBeach"), "Villages", villageVanillaBeach, "");
            villageVanillaBirchForest = config.getBoolean(formatConfig("villageVanillaBirchForest"), "Villages", villageVanillaBirchForest, "");
            villageVanillaBirchForestHills = config.getBoolean(formatConfig("villageVanillaBirchForestHills"), "Villages", villageVanillaBirchForestHills, "");
            villageVanillaBirchForestHillsM = config.getBoolean(formatConfig("villageVanillaBirchForestHillsM"), "Villages", villageVanillaBirchForestHillsM, "");
            villageVanillaBirchForestM = config.getBoolean(formatConfig("villageVanillaBirchForestM"), "Villages", villageVanillaBirchForestM, "");
            villageVanillaColdBeach = config.getBoolean(formatConfig("villageVanillaColdBeach"), "Villages", villageVanillaColdBeach, "");
            villageVanillaColdTaiga = config.getBoolean(formatConfig("villageVanillaColdTaiga"), "Villages", villageVanillaColdTaiga, "");
            villageVanillaColdTaigaHills = config.getBoolean(formatConfig("villageVanillaColdTaigaHills"), "Villages", villageVanillaColdTaigaHills, "");
            villageVanillaColdTaigaM = config.getBoolean(formatConfig("villageVanillaColdTaigaM"), "Villages", villageVanillaColdTaigaM, "");
            villageVanillaDeepOcean = config.getBoolean(formatConfig("villageVanillaDeepOcean"), "Villages", villageVanillaDeepOcean, "");
            villageVanillaDesert = config.getBoolean(formatConfig("villageVanillaDesert"), "Villages", villageVanillaDesert, "");
            villageVanillaDesertHills = config.getBoolean(formatConfig("villageVanillaDesertHills"), "Villages", villageVanillaDesertHills, "");
            villageVanillaDesertM = config.getBoolean(formatConfig("villageVanillaDesertM"), "Villages", villageVanillaDesertM, "");
            villageVanillaExtremeHills = config.getBoolean(formatConfig("villageVanillaExtremeHills"), "Villages", villageVanillaExtremeHills, "");
            villageVanillaExtremeHillsEdge = config.getBoolean(formatConfig("villageVanillaExtremeHillsEdge"), "Villages", villageVanillaExtremeHillsEdge, "");
            villageVanillaExtremeHillsM = config.getBoolean(formatConfig("villageVanillaExtremeHillsM"), "Villages", villageVanillaExtremeHillsM, "");
            villageVanillaExtremeHillsPlus = config.getBoolean(formatConfig("villageVanillaExtremeHillsPlus"), "Villages", villageVanillaExtremeHillsPlus, "");
            villageVanillaExtremeHillsPlusM = config.getBoolean(formatConfig("villageVanillaExtremeHillsPlusM"), "Villages", villageVanillaExtremeHillsPlusM, "");
            villageVanillaFlowerForest = config.getBoolean(formatConfig("villageVanillaFlowerForest"), "Villages", villageVanillaFlowerForest, "");
            villageVanillaForest = config.getBoolean(formatConfig("villageVanillaForest"), "Villages", villageVanillaForest, "");
            villageVanillaForestHills = config.getBoolean(formatConfig("villageVanillaForestHills"), "Villages", villageVanillaForestHills, "");
            //villageVanillaFrozenRiver = config.getBoolean(formatConfig("villageVanillaFrozenRiver"), "Villages", villageVanillaFrozenRiver, "This setting is ignored. Frozen Rivers will always generate, even if set to false.");
            villageVanillaFrozenOcean = config.getBoolean(formatConfig("villageVanillaFrozenOcean"), "Villages", villageVanillaFrozenOcean, "");
            villageVanillaIceMountains = config.getBoolean(formatConfig("villageVanillaIceMountains"), "Villages", villageVanillaIceMountains, "");
            villageVanillaIcePlains = config.getBoolean(formatConfig("villageVanillaIcePlains"), "Villages", villageVanillaIcePlains, "");
            villageVanillaIcePlainsSpikes = config.getBoolean(formatConfig("villageVanillaIcePlainsSpikes"), "Villages", villageVanillaIcePlainsSpikes, "");
            villageVanillaJungle = config.getBoolean(formatConfig("villageVanillaJungle"), "Villages", villageVanillaJungle, "");
            villageVanillaJungleEdge = config.getBoolean(formatConfig("villageVanillaJungleEdge"), "Villages", villageVanillaJungleEdge, "");
            villageVanillaJungleEdgeM = config.getBoolean(formatConfig("villageVanillaJungleEdgeM"), "Villages", villageVanillaJungleEdgeM, "");
            villageVanillaJungleHills = config.getBoolean(formatConfig("villageVanillaJungleHills"), "Villages", villageVanillaJungleHills, "");
            villageVanillaJungleM = config.getBoolean(formatConfig("villageVanillaJungleM"), "Villages", villageVanillaJungleM, "");
            villageVanillaMegaSpruceTaiga = config.getBoolean(formatConfig("villageVanillaMegaSpruceTaiga"), "Villages", villageVanillaMegaSpruceTaiga, "");
            villageVanillaMegaTaiga = config.getBoolean(formatConfig("villageVanillaMegaTaiga"), "Villages", villageVanillaMegaTaiga, "");
            villageVanillaMegaTaigaHills = config.getBoolean(formatConfig("villageVanillaMegaTaigaHills"), "Villages", villageVanillaMegaTaigaHills, "");
            villageVanillaMesa = config.getBoolean(formatConfig("villageVanillaMesa"), "Villages", villageVanillaMesa, "");
            villageVanillaMesaBryce = config.getBoolean(formatConfig("villageVanillaMesaBryce"), "Villages", villageVanillaMesaBryce, "");
            villageVanillaMesaPlateau = config.getBoolean(formatConfig("villageVanillaMesaPlateau"), "Villages", villageVanillaMesaPlateau, "");
            villageVanillaMesaPlateau_F = config.getBoolean(formatConfig("villageVanillaMesaPlateau_F"), "Villages", villageVanillaMesaPlateau_F, "");
            villageVanillaMesaPlateauFM = config.getBoolean(formatConfig("villageVanillaMesaPlateauFM"), "Villages", villageVanillaMesaPlateauFM, "");
            villageVanillaMesaPlateauM = config.getBoolean(formatConfig("villageVanillaMesaPlateauM"), "Villages", villageVanillaMesaPlateauM, "");
            villageVanillaMushroomIsland = config.getBoolean(formatConfig("villageVanillaMushroomIsland"), "Villages", villageVanillaMushroomIsland, "");
            villageVanillaMushroomIslandShore = config.getBoolean(formatConfig("villageVanillaMushroomIslandShore"), "Villages", villageVanillaMushroomIslandShore, "");
            villageVanillaOcean = config.getBoolean(formatConfig("villageVanillaOcean"), "Villages", villageVanillaOcean, "");
            villageVanillaPlains = config.getBoolean(formatConfig("villageVanillaPlains"), "Villages", villageVanillaPlains, "");
            //villageVanillaRiver = config.getBoolean(formatConfig("villageVanillaRiver"), "Villages", villageVanillaRiver, "This setting is ignored. Rivers will always generate, even if set to false.");
            villageVanillaRedwoodTaigaHills = config.getBoolean(formatConfig("villageVanillaRedwoodTaigaHills"), "Villages", villageVanillaRedwoodTaigaHills, "");
            villageVanillaRoofedForest = config.getBoolean(formatConfig("villageVanillaRoofedForest"), "Villages", villageVanillaRoofedForest, "");
            villageVanillaRoofedForestM = config.getBoolean(formatConfig("villageVanillaRoofedForestM"), "Villages", villageVanillaRoofedForestM, "");
            villageVanillaSavanna = config.getBoolean(formatConfig("villageVanillaSavanna"), "Villages", villageVanillaSavanna, "");
            villageVanillaSavannaM = config.getBoolean(formatConfig("villageVanillaSavannaM"), "Villages", villageVanillaSavannaM, "");
            villageVanillaSavannaPlateau = config.getBoolean(formatConfig("villageVanillaSavannaPlateau"), "Villages", villageVanillaSavannaPlateau, "");
            villageVanillaSavannaPlateauM = config.getBoolean(formatConfig("villageVanillaSavannaPlateauM"), "Villages", villageVanillaSavannaPlateauM, "");
            villageVanillaStoneBeach = config.getBoolean(formatConfig("villageVanillaStoneBeach"), "Villages", villageVanillaStoneBeach, "");
            villageVanillaSunflowerPlains = config.getBoolean(formatConfig("villageVanillaSunflowerPlains"), "Villages", villageVanillaSunflowerPlains, "");
            villageVanillaSwampland = config.getBoolean(formatConfig("villageVanillaSwampland"), "Villages", villageVanillaSwampland, "");
            villageVanillaSwamplandM = config.getBoolean(formatConfig("villageVanillaSwamplandM"), "Villages", villageVanillaSwamplandM, "");
            villageVanillaTaiga = config.getBoolean(formatConfig("villageVanillaTaiga"), "Villages", villageVanillaTaiga, "");
            villageVanillaTaigaHills = config.getBoolean(formatConfig("villageVanillaTaigaHills"), "Villages", villageVanillaTaigaHills, "");
            villageVanillaTaigaM = config.getBoolean(formatConfig("villageVanillaTaigaM"), "Villages", villageVanillaTaigaM, "");
            
            BiomeConfigManager.setBiomeConfigsFromUserConfigs(BiomeConfigVanilla.getBiomeConfigs(), config);
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
	
    private static String formatConfig(String s)
    {
        String returnString = s;        
        
        returnString = StringUtils.replace(returnString, "VanillaMesaPlateau_F", "VanillaMesaPlateauF");
        returnString = StringUtils.join(StringUtils.splitByCharacterTypeCamelCase(returnString), " ");

        if (s.startsWith("village")) {
            returnString = StringUtils.replace(returnString, "village", "Allow villages to generate in", 1);
        }
        
        return returnString;
    }
}
