package rtg.config.chromaticraft;

import java.io.File;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.Level;

import rtg.api.biome.chromaticraft.config.BiomeConfigCC;
import rtg.config.BiomeConfigManager;
import rtg.world.biome.BiomeBase;
import cpw.mods.fml.common.FMLLog;

import net.minecraftforge.common.config.Configuration;

public class ConfigCC 
{
	public static Configuration config;
	
    public static final int biomeWeightMin = BiomeBase.MIN_BIOME_WEIGHT;
    public static final int biomeWeightMax = BiomeBase.MAX_BIOME_WEIGHT;
    public static final int biomeWeightDefault = BiomeBase.DEFAULT_BIOME_WEIGHT;
	
	public static boolean generateCCBiomes = true;
	
	public static int weightCCEnderForest = biomeWeightDefault;
	public static int weightCCRainbowForest = biomeWeightDefault;
	
    public static boolean villageCCEnderForest = false;
    public static boolean villageCCRainbowForest = false;
		
	public static void init(File configFile) 
	{
		config = new Configuration(configFile);
	
		try 
		{
			config.load();
			
			generateCCBiomes = config.getBoolean("Allow biomes from this mod to generate", "Allow mod biomes", generateCCBiomes, "If TRUE, uses the individual biome settings below. If FALSE, disables all biomes from this mod." + Configuration.NEW_LINE);

			weightCCEnderForest = config.getInt(formatConfig("weightCCEnderForest"), "Weights", weightCCEnderForest, biomeWeightMin, biomeWeightMax, "");
			weightCCRainbowForest = config.getInt(formatConfig("weightCCRainbowForest"), "Weights", weightCCRainbowForest, biomeWeightMin, biomeWeightMax, "");
			
            villageCCEnderForest = config.getBoolean(formatConfig("villageCCEnderForest"), "Villages", villageCCEnderForest, "");
            villageCCRainbowForest = config.getBoolean(formatConfig("villageCCRainbowForest"), "Villages", villageCCRainbowForest, "");
            
            BiomeConfigManager.setBiomeConfigsFromUserConfigs(BiomeConfigCC.getBiomeConfigs(), config);
		}
		catch (Exception e)
		{
			FMLLog.log(Level.ERROR, e, "RTG has had a problem loading ChromatiCraft configuration.");	
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
