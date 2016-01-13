package rtg.config.extrabiomes;

import java.io.File;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.Level;

import rtg.api.biome.extrabiomes.config.BiomeConfigEBXL;
import rtg.config.BiomeConfigManager;
import rtg.world.biome.BiomeBase;
import cpw.mods.fml.common.FMLLog;

import net.minecraftforge.common.config.Configuration;

public class ConfigEBXL 
{
	public static Configuration config;
	
    public static final int biomeWeightMin = BiomeBase.MIN_BIOME_WEIGHT;
    public static final int biomeWeightMax = BiomeBase.MAX_BIOME_WEIGHT;
    public static final int biomeWeightDefault = BiomeBase.DEFAULT_BIOME_WEIGHT;
	
	public static boolean generateEBXLBiomes = true;
			
	public static boolean generateEBXLAlpine = true;
	public static boolean generateEBXLAutumnWoods = true;
	public static boolean generateEBXLBirchForest = true;
	public static boolean generateEBXLExtremeJungle = true;
	public static boolean generateEBXLForestedIsland = true;
	public static boolean generateEBXLForestedHills = true;
	public static boolean generateEBXLGlacier = true;
	public static boolean generateEBXLGreenHills = true;
	public static boolean generateEBXLIceWasteland = true;
	public static boolean generateEBXLGreenSwamp = true;
	public static boolean generateEBXLMarsh = true;
	public static boolean generateEBXLMeadow = true;
	public static boolean generateEBXLMiniJungle = true;
	public static boolean generateEBXLMountainDesert = true;
	public static boolean generateEBXLMountainRidge = true;
	public static boolean generateEBXLMountainTaiga = true;
	public static boolean generateEBXLPineForest = true;
	public static boolean generateEBXLRainforest = true;
	public static boolean generateEBXLRedwoodForest = true;
	public static boolean generateEBXLRedwoodLush = true;
	public static boolean generateEBXLSavanna = true;
	public static boolean generateEBXLShrubland = true;
	public static boolean generateEBXLSnowForest = true;
	public static boolean generateEBXLSnowyRainforest = true;
	public static boolean generateEBXLTemperateRainforest = true;
	public static boolean generateEBXLTundra = true;
	public static boolean generateEBXLWasteland = true;
	public static boolean generateEBXLWoodlands = true;

	public static int weightEBXLAlpine = biomeWeightDefault;
	public static int weightEBXLAutumnWoods = biomeWeightDefault;
	public static int weightEBXLBirchForest = biomeWeightDefault;
	public static int weightEBXLExtremeJungle = biomeWeightDefault;
	public static int weightEBXLForestedIsland = biomeWeightDefault;
	public static int weightEBXLForestedHills = biomeWeightDefault;
	public static int weightEBXLGlacier = biomeWeightDefault;
	public static int weightEBXLGreenHills = biomeWeightDefault;
	public static int weightEBXLIceWasteland = biomeWeightDefault;
	public static int weightEBXLGreenSwamp = biomeWeightDefault;
	public static int weightEBXLMarsh = biomeWeightDefault;
	public static int weightEBXLMeadow = biomeWeightDefault;
	public static int weightEBXLMiniJungle = biomeWeightDefault;
	public static int weightEBXLMountainDesert = biomeWeightDefault;
	public static int weightEBXLMountainRidge = biomeWeightDefault;
	public static int weightEBXLMountainTaiga = biomeWeightDefault;
	public static int weightEBXLPineForest = biomeWeightDefault;
	public static int weightEBXLRainforest = biomeWeightDefault;
	public static int weightEBXLRedwoodForest = biomeWeightDefault;
	public static int weightEBXLRedwoodLush = biomeWeightDefault;
	public static int weightEBXLSavanna = biomeWeightDefault;
	public static int weightEBXLShrubland = biomeWeightDefault;
	public static int weightEBXLSnowForest = biomeWeightDefault;
	public static int weightEBXLSnowyRainforest = biomeWeightDefault;
	public static int weightEBXLTemperateRainforest = biomeWeightDefault;
	public static int weightEBXLTundra = biomeWeightDefault;
	public static int weightEBXLWasteland = biomeWeightDefault;
	public static int weightEBXLWoodlands = biomeWeightDefault;
	
    public static boolean villageEBXLAlpine = true;
    public static boolean villageEBXLAutumnWoods = true;
    public static boolean villageEBXLBirchForest = true;
    public static boolean villageEBXLExtremeJungle = true;
    public static boolean villageEBXLForestedIsland = true;
    public static boolean villageEBXLForestedHills = true;
    public static boolean villageEBXLGlacier = true;
    public static boolean villageEBXLGreenHills = true;
    public static boolean villageEBXLIceWasteland = true;
    public static boolean villageEBXLGreenSwamp = true;
    public static boolean villageEBXLMarsh = true;
    public static boolean villageEBXLMeadow = true;
    public static boolean villageEBXLMiniJungle = true;
    public static boolean villageEBXLMountainDesert = true;
    public static boolean villageEBXLMountainRidge = true;
    public static boolean villageEBXLMountainTaiga = true;
    public static boolean villageEBXLPineForest = true;
    public static boolean villageEBXLRainforest = true;
    public static boolean villageEBXLRedwoodForest = true;
    public static boolean villageEBXLRedwoodLush = true;
    public static boolean villageEBXLSavanna = true;
    public static boolean villageEBXLShrubland = true;
    public static boolean villageEBXLSnowForest = true;
    public static boolean villageEBXLSnowyRainforest = true;
    public static boolean villageEBXLTemperateRainforest = true;
    public static boolean villageEBXLTundra = true;
    public static boolean villageEBXLWasteland = true;
    public static boolean villageEBXLWoodlands = true;
		
	public static void init(File configFile) 
	{
		config = new Configuration(configFile);
	
		try 
		{
			config.load();
			
			//EBXL
			generateEBXLBiomes = config.getBoolean("Allow biomes from this mod to generate", "Allow mod biomes", generateEBXLBiomes, "If TRUE, uses the individual biome settings below. If FALSE, disables all biomes from this mod." + Configuration.NEW_LINE);
			
			//Generations
			generateEBXLAlpine = config.getBoolean(formatConfig("generateEBXLAlpine"), "Biomes", generateEBXLAlpine, "");
			generateEBXLAutumnWoods = config.getBoolean(formatConfig("generateEBXLAutumnWoods"), "Biomes", generateEBXLAutumnWoods, "");
			generateEBXLBirchForest = config.getBoolean(formatConfig("generateEBXLBirchForest"), "Biomes", generateEBXLBirchForest, "");
			generateEBXLExtremeJungle = config.getBoolean(formatConfig("generateEBXLExtremeJungle"), "Biomes", generateEBXLExtremeJungle, "");
			generateEBXLForestedIsland = config.getBoolean(formatConfig("generateEBXLForestedIsland"), "Biomes", generateEBXLForestedIsland, "");
			generateEBXLForestedHills = config.getBoolean(formatConfig("generateEBXLForestedHills"), "Biomes", generateEBXLForestedHills, "");
			generateEBXLGlacier = config.getBoolean(formatConfig("generateEBXLGlacier"), "Biomes", generateEBXLGlacier, "");
			generateEBXLGreenHills = config.getBoolean(formatConfig("generateEBXLGreenHills"), "Biomes", generateEBXLGreenHills, "");
			generateEBXLIceWasteland = config.getBoolean(formatConfig("generateEBXLIceWasteland"), "Biomes", generateEBXLIceWasteland, "");
			generateEBXLGreenSwamp = config.getBoolean(formatConfig("generateEBXLGreenSwamp"), "Biomes", generateEBXLGreenSwamp, "");
			generateEBXLMarsh = config.getBoolean(formatConfig("generateEBXLMarsh"), "Biomes", generateEBXLMarsh, "");
			generateEBXLMeadow = config.getBoolean(formatConfig("generateEBXLMeadow"), "Biomes", generateEBXLMeadow, "");
			generateEBXLMiniJungle = config.getBoolean(formatConfig("generateEBXLMiniJungle"), "Biomes", generateEBXLMiniJungle, "");
			generateEBXLMountainDesert = config.getBoolean(formatConfig("generateEBXLMountainDesert"), "Biomes", generateEBXLMountainDesert, "");
			generateEBXLMountainRidge = config.getBoolean(formatConfig("generateEBXLMountainRidge"), "Biomes", generateEBXLMountainRidge, "");
			generateEBXLMountainTaiga = config.getBoolean(formatConfig("generateEBXLMountainTaiga"), "Biomes", generateEBXLMountainTaiga, "");
			generateEBXLPineForest = config.getBoolean(formatConfig("generateEBXLPineForest"), "Biomes", generateEBXLPineForest, "");
			generateEBXLRainforest = config.getBoolean(formatConfig("generateEBXLRainforest"), "Biomes", generateEBXLRainforest, "");
			generateEBXLRedwoodForest = config.getBoolean(formatConfig("generateEBXLRedwoodForest"), "Biomes", generateEBXLRedwoodForest, "");
			generateEBXLRedwoodLush = config.getBoolean(formatConfig("generateEBXLRedwoodLush"), "Biomes", generateEBXLRedwoodLush, "");
			generateEBXLSavanna = config.getBoolean(formatConfig("generateEBXLSavanna"), "Biomes", generateEBXLSavanna, "");
			generateEBXLShrubland = config.getBoolean(formatConfig("generateEBXLShrubland"), "Biomes", generateEBXLShrubland, "");
			generateEBXLSnowForest = config.getBoolean(formatConfig("generateEBXLSnowForest"), "Biomes", generateEBXLSnowForest, "");
			generateEBXLSnowyRainforest = config.getBoolean(formatConfig("generateEBXLSnowyRainforest"), "Biomes", generateEBXLSnowyRainforest, "");
			generateEBXLTemperateRainforest = config.getBoolean(formatConfig("generateEBXLTemperateRainforest"), "Biomes", generateEBXLTemperateRainforest, "");
			generateEBXLTundra = config.getBoolean(formatConfig("generateEBXLTundra"), "Biomes", generateEBXLTundra, "");
			generateEBXLWasteland = config.getBoolean(formatConfig("generateEBXLWasteland"), "Biomes", generateEBXLWasteland, "");
			generateEBXLWoodlands = config.getBoolean(formatConfig("generateEBXLWoodlands"), "Biomes", generateEBXLWoodlands, "");
			
			//Weights
			weightEBXLAlpine = config.getInt(formatConfig("weightEBXLAlpine"), "Weights", weightEBXLAlpine, biomeWeightMin, biomeWeightMax, "");
			weightEBXLAutumnWoods = config.getInt(formatConfig("weightEBXLAutumnWoods"), "Weights", weightEBXLAutumnWoods, biomeWeightMin, biomeWeightMax, "");
			weightEBXLBirchForest = config.getInt(formatConfig("weightEBXLBirchForest"), "Weights", weightEBXLBirchForest, biomeWeightMin, biomeWeightMax, "");
			weightEBXLExtremeJungle = config.getInt(formatConfig("weightEBXLExtremeJungle"), "Weights", weightEBXLExtremeJungle, biomeWeightMin, biomeWeightMax, "");
			weightEBXLForestedIsland = config.getInt(formatConfig("weightEBXLForestedIsland"), "Weights", weightEBXLForestedIsland, biomeWeightMin, biomeWeightMax, "");
			weightEBXLForestedHills = config.getInt(formatConfig("weightEBXLForestedHills"), "Weights", weightEBXLForestedHills, biomeWeightMin, biomeWeightMax, "");
			weightEBXLGlacier = config.getInt(formatConfig("weightEBXLGlacier"), "Weights", weightEBXLGlacier, biomeWeightMin, biomeWeightMax, "");
			weightEBXLGreenHills = config.getInt(formatConfig("weightEBXLGreenHills"), "Weights", weightEBXLGreenHills, biomeWeightMin, biomeWeightMax, "");
			weightEBXLIceWasteland = config.getInt(formatConfig("weightEBXLIceWasteland"), "Weights", weightEBXLIceWasteland, biomeWeightMin, biomeWeightMax, "");
			weightEBXLGreenSwamp = config.getInt(formatConfig("weightEBXLGreenSwamp"), "Weights", weightEBXLGreenSwamp, biomeWeightMin, biomeWeightMax, "");
			weightEBXLMarsh = config.getInt(formatConfig("weightEBXLMarsh"), "Weights", weightEBXLMarsh, biomeWeightMin, biomeWeightMax, "");
			weightEBXLMeadow = config.getInt(formatConfig("weightEBXLMeadow"), "Weights", weightEBXLMeadow, biomeWeightMin, biomeWeightMax, "");
			weightEBXLMiniJungle = config.getInt(formatConfig("weightEBXLMiniJungle"), "Weights", weightEBXLMiniJungle, biomeWeightMin, biomeWeightMax, "");
			weightEBXLMountainDesert = config.getInt(formatConfig("weightEBXLMountainDesert"), "Weights", weightEBXLMountainDesert, biomeWeightMin, biomeWeightMax, "");
			weightEBXLMountainRidge = config.getInt(formatConfig("weightEBXLMountainRidge"), "Weights", weightEBXLMountainRidge, biomeWeightMin, biomeWeightMax, "");
			weightEBXLMountainTaiga = config.getInt(formatConfig("weightEBXLMountainTaiga"), "Weights", weightEBXLMountainTaiga, biomeWeightMin, biomeWeightMax, "");
			weightEBXLPineForest = config.getInt(formatConfig("weightEBXLPineForest"), "Weights", weightEBXLPineForest, biomeWeightMin, biomeWeightMax, "");
			weightEBXLRainforest = config.getInt(formatConfig("weightEBXLRainforest"), "Weights", weightEBXLRainforest, biomeWeightMin, biomeWeightMax, "");
			weightEBXLRedwoodForest = config.getInt(formatConfig("weightEBXLRedwoodForest"), "Weights", weightEBXLRedwoodForest, biomeWeightMin, biomeWeightMax, "");
			weightEBXLRedwoodLush = config.getInt(formatConfig("weightEBXLRedwoodLush"), "Weights", weightEBXLRedwoodLush, biomeWeightMin, biomeWeightMax, "");
			weightEBXLSavanna = config.getInt(formatConfig("weightEBXLSavanna"), "Weights", weightEBXLSavanna, biomeWeightMin, biomeWeightMax, "");
			weightEBXLShrubland = config.getInt(formatConfig("weightEBXLShrubland"), "Weights", weightEBXLShrubland, biomeWeightMin, biomeWeightMax, "");
			weightEBXLSnowForest = config.getInt(formatConfig("weightEBXLSnowForest"), "Weights", weightEBXLSnowForest, biomeWeightMin, biomeWeightMax, "");
			weightEBXLSnowyRainforest = config.getInt(formatConfig("weightEBXLSnowyRainforest"), "Weights", weightEBXLSnowyRainforest, biomeWeightMin, biomeWeightMax, "");
			weightEBXLTemperateRainforest = config.getInt(formatConfig("weightEBXLTemperateRainforest"), "Weights", weightEBXLTemperateRainforest, biomeWeightMin, biomeWeightMax, "");
			weightEBXLTundra = config.getInt(formatConfig("weightEBXLTundra"), "Weights", weightEBXLTundra, biomeWeightMin, biomeWeightMax, "");
			weightEBXLWasteland = config.getInt(formatConfig("weightEBXLWasteland"), "Weights", weightEBXLWasteland, biomeWeightMin, biomeWeightMax, "");
			weightEBXLWoodlands = config.getInt(formatConfig("weightEBXLWoodlands"), "Weights", weightEBXLWoodlands, biomeWeightMin, biomeWeightMax, "");
			
            //Villages
            villageEBXLAlpine = config.getBoolean(formatConfig("villageEBXLAlpine"), "Villages", villageEBXLAlpine, "");
            villageEBXLAutumnWoods = config.getBoolean(formatConfig("villageEBXLAutumnWoods"), "Villages", villageEBXLAutumnWoods, "");
            villageEBXLBirchForest = config.getBoolean(formatConfig("villageEBXLBirchForest"), "Villages", villageEBXLBirchForest, "");
            villageEBXLExtremeJungle = config.getBoolean(formatConfig("villageEBXLExtremeJungle"), "Villages", villageEBXLExtremeJungle, "");
            villageEBXLForestedIsland = config.getBoolean(formatConfig("villageEBXLForestedIsland"), "Villages", villageEBXLForestedIsland, "");
            villageEBXLForestedHills = config.getBoolean(formatConfig("villageEBXLForestedHills"), "Villages", villageEBXLForestedHills, "");
            villageEBXLGlacier = config.getBoolean(formatConfig("villageEBXLGlacier"), "Villages", villageEBXLGlacier, "");
            villageEBXLGreenHills = config.getBoolean(formatConfig("villageEBXLGreenHills"), "Villages", villageEBXLGreenHills, "");
            villageEBXLIceWasteland = config.getBoolean(formatConfig("villageEBXLIceWasteland"), "Villages", villageEBXLIceWasteland, "");
            villageEBXLGreenSwamp = config.getBoolean(formatConfig("villageEBXLGreenSwamp"), "Villages", villageEBXLGreenSwamp, "");
            villageEBXLMarsh = config.getBoolean(formatConfig("villageEBXLMarsh"), "Villages", villageEBXLMarsh, "");
            villageEBXLMeadow = config.getBoolean(formatConfig("villageEBXLMeadow"), "Villages", villageEBXLMeadow, "");
            villageEBXLMiniJungle = config.getBoolean(formatConfig("villageEBXLMiniJungle"), "Villages", villageEBXLMiniJungle, "");
            villageEBXLMountainDesert = config.getBoolean(formatConfig("villageEBXLMountainDesert"), "Villages", villageEBXLMountainDesert, "");
            villageEBXLMountainRidge = config.getBoolean(formatConfig("villageEBXLMountainRidge"), "Villages", villageEBXLMountainRidge, "");
            villageEBXLMountainTaiga = config.getBoolean(formatConfig("villageEBXLMountainTaiga"), "Villages", villageEBXLMountainTaiga, "");
            villageEBXLPineForest = config.getBoolean(formatConfig("villageEBXLPineForest"), "Villages", villageEBXLPineForest, "");
            villageEBXLRainforest = config.getBoolean(formatConfig("villageEBXLRainforest"), "Villages", villageEBXLRainforest, "");
            villageEBXLRedwoodForest = config.getBoolean(formatConfig("villageEBXLRedwoodForest"), "Villages", villageEBXLRedwoodForest, "");
            villageEBXLRedwoodLush = config.getBoolean(formatConfig("villageEBXLRedwoodLush"), "Villages", villageEBXLRedwoodLush, "");
            villageEBXLSavanna = config.getBoolean(formatConfig("villageEBXLSavanna"), "Villages", villageEBXLSavanna, "");
            villageEBXLShrubland = config.getBoolean(formatConfig("villageEBXLShrubland"), "Villages", villageEBXLShrubland, "");
            villageEBXLSnowForest = config.getBoolean(formatConfig("villageEBXLSnowForest"), "Villages", villageEBXLSnowForest, "");
            villageEBXLSnowyRainforest = config.getBoolean(formatConfig("villageEBXLSnowyRainforest"), "Villages", villageEBXLSnowyRainforest, "");
            villageEBXLTemperateRainforest = config.getBoolean(formatConfig("villageEBXLTemperateRainforest"), "Villages", villageEBXLTemperateRainforest, "");
            villageEBXLTundra = config.getBoolean(formatConfig("villageEBXLTundra"), "Villages", villageEBXLTundra, "");
            villageEBXLWasteland = config.getBoolean(formatConfig("villageEBXLWasteland"), "Villages", villageEBXLWasteland, "");
            villageEBXLWoodlands = config.getBoolean(formatConfig("villageEBXLWoodlands"), "Villages", villageEBXLWoodlands, "");
            
            BiomeConfigManager.setBiomeConfigsFromUserConfigs(BiomeConfigEBXL.getBiomeConfigs(), config);
		}
		catch (Exception e)
		{
			FMLLog.log(Level.ERROR, e, "RTG has had a problem loading EBXL configuration.");	
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
        
        returnString = StringUtils.join(StringUtils.splitByCharacterTypeCamelCase(returnString), " ");

        if (s.startsWith("generate")) {
            returnString = StringUtils.replace(returnString, "generate", "Generate", 1);
        }
        else if (s.startsWith("village")) {
            returnString = StringUtils.replace(returnString, "village", "Allow villages to generate in", 1);
        }
        else if (s.startsWith("weight")) {
            returnString = StringUtils.replace(returnString, "weight", "Weight of", 1);
        }
        
        return returnString;
    }
}
