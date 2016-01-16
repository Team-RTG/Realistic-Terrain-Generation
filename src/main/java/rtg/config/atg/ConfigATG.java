package rtg.config.atg;

import java.io.File;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.Level;

import rtg.api.biome.atg.config.BiomeConfigATG;
import rtg.config.BiomeConfigManager;
import rtg.world.biome.BiomeBase;
import cpw.mods.fml.common.FMLLog;

import net.minecraftforge.common.config.Configuration;

public class ConfigATG 
{
	public static Configuration config;
	
	public static boolean generateATGBiomes = true;

    public static boolean villageATGGravelBeach = false;
    public static boolean villageATGSnowyGravelBeach = false;
    public static boolean villageATGShrubland = true;
    public static boolean villageATGRockySteppe = true;
    public static boolean villageATGTropicalShrubland = true;
    public static boolean villageATGTundra = true;
    public static boolean villageATGVolcano = false;
    public static boolean villageATGWoodland = true;
		
	public static void init(File configFile) 
	{
		config = new Configuration(configFile);
	
		try 
		{
			config.load();
			
			generateATGBiomes = config.getBoolean("Allow biomes from this mod to generate", "Allow mod biomes", generateATGBiomes, "If TRUE, uses the individual biome settings below. If FALSE, disables all biomes from this mod." + Configuration.NEW_LINE);
 
            villageATGGravelBeach = config.getBoolean(formatConfig("villageATGGravelBeach"), "Villages", villageATGGravelBeach, "");
            villageATGSnowyGravelBeach = config.getBoolean(formatConfig("villageATGSnowyGravelBeach"), "Villages", villageATGSnowyGravelBeach, "");
            villageATGShrubland = config.getBoolean(formatConfig("villageATGShrubland"), "Villages", villageATGShrubland, "");
            villageATGRockySteppe = config.getBoolean(formatConfig("villageATGRockySteppe"), "Villages", villageATGRockySteppe, "");
            villageATGTropicalShrubland = config.getBoolean(formatConfig("villageATGTropicalShrubland"), "Villages", villageATGTropicalShrubland, "");
            villageATGTundra = config.getBoolean(formatConfig("villageATGTundra"), "Villages", villageATGTundra, "");
            villageATGVolcano = config.getBoolean(formatConfig("villageATGVolcano"), "Villages", villageATGVolcano, "");
            villageATGWoodland = config.getBoolean(formatConfig("villageATGWoodland"), "Villages", villageATGWoodland, "");
            
            BiomeConfigManager.setBiomeConfigsFromUserConfigs(BiomeConfigATG.getBiomeConfigs(), config);
		}
		catch (Exception e)
		{
			FMLLog.log(Level.ERROR, e, "RTG has had a problem loading ATG configuration.");	
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
