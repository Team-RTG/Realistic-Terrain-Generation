package rtg.config.highlands;

import java.io.File;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.Level;

import rtg.api.biome.highlands.config.BiomeConfigHL;
import rtg.config.BiomeConfigManager;
import rtg.world.biome.BiomeBase;
import cpw.mods.fml.common.FMLLog;

import net.minecraftforge.common.config.Configuration;

public class ConfigHL 
{
	public static Configuration config;

	public static boolean generateHLBiomes = true;
	
    /*
     * ######################################################################################
     */
    
    //Main biomes
    public static boolean villageHLAlps = true;
    public static boolean villageHLAutumnForest = true;
    public static boolean villageHLBadlands = true;
    public static boolean villageHLBirchHills = true;
    public static boolean villageHLBog = true;
    public static boolean villageHLCliffs = true;
    public static boolean villageHLDesertMountains = true;
    public static boolean villageHLDunes = true;
    public static boolean villageHLEstuary = true;
    public static boolean villageHLFlyingMountains = true;
    public static boolean villageHLGlacier = true;
    public static boolean villageHLHighlandsB = true;
    public static boolean villageHLLowlands = true;
    public static boolean villageHLMeadow = true;
    public static boolean villageHLOutback = true;
    public static boolean villageHLPinelands = true;
    public static boolean villageHLRainforest = true;
    public static boolean villageHLRedwoodForest = true;
    public static boolean villageHLRockMountains = true;
    public static boolean villageHLSahel = true;
    public static boolean villageHLSavannah = true;
    public static boolean villageHLSnowMountains = true;
    public static boolean villageHLSteppe = true;
    public static boolean villageHLTallPineForest = true;
    public static boolean villageHLTropicalIslands = true;
    public static boolean villageHLTropics = true;
    public static boolean villageHLTundra = true;
    public static boolean villageHLWoodlands = true;
    public static boolean villageHLWoodsMountains = true;
    
    //Sub biomes
    public static boolean villageHLBaldHill = true;
    public static boolean villageHLCanyon = true;
    public static boolean villageHLDesertIsland = true;
    public static boolean villageHLForestIsland = true;
    public static boolean villageHLJungleIsland = true;
    public static boolean villageHLLake = true;
    public static boolean villageHLMesa = true;
    public static boolean villageHLOasis = true;
    public static boolean villageHLRockIsland = true;
    public static boolean villageHLSnowIsland = true;
    public static boolean villageHLValley = true;
    public static boolean villageHLVolcanoIsland = false;
    public static boolean villageHLWindyIsland = true;
    
    //Border biomes
    public static boolean villageHLShrubland = true;
    
	/*
	 * ######################################################################################
	 */
	
	public static void init(File configFile) 
	{
		config = new Configuration(configFile);
	
		try 
		{
			config.load();
			
			//HL
			generateHLBiomes = config.getBoolean("Allow biomes from this mod to generate", "Allow mod biomes", true, "If TRUE, uses the individual biome settings below. If FALSE, disables all biomes from this mod." + Configuration.NEW_LINE);
			
            /*
             * ######################################################################################
             */
			
            //Villages

            //Main biomes
            villageHLAlps = config.getBoolean(formatConfig("villageHLAlps"), "Villages", villageHLAlps, "");
            villageHLAutumnForest = config.getBoolean(formatConfig("villageHLAutumnForest"), "Villages", villageHLAutumnForest, "");
            villageHLBadlands = config.getBoolean(formatConfig("villageHLBadlands"), "Villages", villageHLBadlands, "");
            villageHLBirchHills = config.getBoolean(formatConfig("villageHLBirchHills"), "Villages", villageHLBirchHills, "");
            villageHLBog = config.getBoolean(formatConfig("villageHLBog"), "Villages", villageHLBog, "");
            villageHLCliffs = config.getBoolean(formatConfig("villageHLCliffs"), "Villages", villageHLCliffs, "");
            villageHLDesertMountains = config.getBoolean(formatConfig("villageHLDesertMountains"), "Villages", villageHLDesertMountains, "");
            villageHLDunes = config.getBoolean(formatConfig("villageHLDunes"), "Villages", villageHLDunes, "");
            villageHLEstuary = config.getBoolean(formatConfig("villageHLEstuary"), "Villages", villageHLEstuary, "");
            villageHLFlyingMountains = config.getBoolean(formatConfig("villageHLFlyingMountains"), "Villages", villageHLFlyingMountains, "");
            villageHLGlacier = config.getBoolean(formatConfig("villageHLGlacier"), "Villages", villageHLGlacier, "");
            villageHLHighlandsB = config.getBoolean(formatConfig("villageHLHighlandsB"), "Villages", villageHLHighlandsB, "");
            villageHLLowlands = config.getBoolean(formatConfig("villageHLLowlands"), "Villages", villageHLLowlands, "");
            villageHLMeadow = config.getBoolean(formatConfig("villageHLMeadow"), "Villages", villageHLMeadow, "");
            villageHLOutback = config.getBoolean(formatConfig("villageHLOutback"), "Villages", villageHLOutback, "");
            villageHLPinelands = config.getBoolean(formatConfig("villageHLPinelands"), "Villages", villageHLPinelands, "");
            villageHLRainforest = config.getBoolean(formatConfig("villageHLRainforest"), "Villages", villageHLRainforest, "");
            villageHLRedwoodForest = config.getBoolean(formatConfig("villageHLRedwoodForest"), "Villages", villageHLRedwoodForest, "");
            villageHLRockMountains = config.getBoolean(formatConfig("villageHLRockMountains"), "Villages", villageHLRockMountains, "");
            villageHLSahel = config.getBoolean(formatConfig("villageHLSahel"), "Villages", villageHLSahel, "");
            villageHLSavannah = config.getBoolean(formatConfig("villageHLSavannah"), "Villages", villageHLSavannah, "");
            villageHLSnowMountains = config.getBoolean(formatConfig("villageHLSnowMountains"), "Villages", villageHLSnowMountains, "");
            villageHLSteppe = config.getBoolean(formatConfig("villageHLSteppe"), "Villages", villageHLSteppe, "");
            villageHLTallPineForest = config.getBoolean(formatConfig("villageHLTallPineForest"), "Villages", villageHLTallPineForest, "");
            villageHLTropicalIslands = config.getBoolean(formatConfig("villageHLTropicalIslands"), "Villages", villageHLTropicalIslands, "");
            villageHLTropics = config.getBoolean(formatConfig("villageHLTropics"), "Villages", villageHLTropics, "");
            villageHLTundra = config.getBoolean(formatConfig("villageHLTundra"), "Villages", villageHLTundra, "");
            villageHLWoodlands = config.getBoolean(formatConfig("villageHLWoodlands"), "Villages", villageHLWoodlands, "");
            villageHLWoodsMountains = config.getBoolean(formatConfig("villageHLWoodsMountains"), "Villages", villageHLWoodsMountains, "");

            //Sub biomes
            villageHLBaldHill = config.getBoolean(formatConfig("villageHLBaldHill"), "Villages", villageHLBaldHill, "");
            villageHLCanyon = config.getBoolean(formatConfig("villageHLCanyon"), "Villages", villageHLCanyon, "");
            villageHLDesertIsland = config.getBoolean(formatConfig("villageHLDesertIsland"), "Villages", villageHLDesertIsland, "");
            villageHLForestIsland = config.getBoolean(formatConfig("villageHLForestIsland"), "Villages", villageHLForestIsland, "");
            villageHLJungleIsland = config.getBoolean(formatConfig("villageHLJungleIsland"), "Villages", villageHLJungleIsland, "");
            villageHLLake = config.getBoolean(formatConfig("villageHLLake"), "Villages", villageHLLake, "");
            villageHLMesa = config.getBoolean(formatConfig("villageHLMesa"), "Villages", villageHLMesa, "");
            villageHLOasis = config.getBoolean(formatConfig("villageHLOasis"), "Villages", villageHLOasis, "");
            villageHLRockIsland = config.getBoolean(formatConfig("villageHLRockIsland"), "Villages", villageHLRockIsland, "");
            villageHLSnowIsland = config.getBoolean(formatConfig("villageHLSnowIsland"), "Villages", villageHLSnowIsland, "");
            villageHLValley = config.getBoolean(formatConfig("villageHLValley"), "Villages", villageHLValley, "");
            villageHLVolcanoIsland = config.getBoolean(formatConfig("villageHLVolcanoIsland"), "Villages", villageHLVolcanoIsland, "");
            villageHLWindyIsland = config.getBoolean(formatConfig("villageHLWindyIsland"), "Villages", villageHLWindyIsland, "");
                
            //Border Biomes
            villageHLShrubland = config.getBoolean(formatConfig("villageHLShrubland"), "Villages", villageHLShrubland, "");
            
            BiomeConfigManager.setBiomeConfigsFromUserConfigs(BiomeConfigHL.getBiomeConfigs(), config);
		}
		catch (Exception e)
		{
			FMLLog.log(Level.ERROR, e, "RTG has had a problem loading HL configuration.");	
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
