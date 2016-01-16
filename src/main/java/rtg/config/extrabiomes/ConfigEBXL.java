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
	
	public static boolean generateEBXLBiomes = true;

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

        if (s.startsWith("village")) {
            returnString = StringUtils.replace(returnString, "village", "Allow villages to generate in", 1);
        }
        
        return returnString;
    }
}
