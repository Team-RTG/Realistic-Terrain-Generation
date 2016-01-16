package rtg.config.thaumcraft;

import java.io.File;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.Level;

import rtg.api.biome.thaumcraft.config.BiomeConfigTC;
import rtg.config.BiomeConfigManager;
import rtg.world.biome.BiomeBase;
import cpw.mods.fml.common.FMLLog;

import net.minecraftforge.common.config.Configuration;

public class ConfigTC 
{
	public static Configuration config;

	public static boolean generateTCBiomes = true;

    public static boolean villageTCTaintedLand = false;
    public static boolean villageTCMagicalForest = false;
    public static boolean villageTCEerie = false;
		
	public static void init(File configFile) 
	{
		config = new Configuration(configFile);
	
		try 
		{
			config.load();
			
			generateTCBiomes = config.getBoolean("Allow biomes from this mod to generate", "Allow mod biomes", true, "If TRUE, uses the individual biome settings below. If FALSE, disables all biomes from this mod." + Configuration.NEW_LINE);

            villageTCTaintedLand = config.getBoolean(formatConfig("villageTCTaintedLand"), "Villages", villageTCTaintedLand, "");
            villageTCMagicalForest = config.getBoolean(formatConfig("villageTCMagicalForest"), "Villages", villageTCMagicalForest, "");
            villageTCEerie = config.getBoolean(formatConfig("villageTCEerie"), "Villages", villageTCEerie, "");
            
            BiomeConfigManager.setBiomeConfigsFromUserConfigs(BiomeConfigTC.getBiomeConfigs(), config);
		}
		catch (Exception e)
		{
			FMLLog.log(Level.ERROR, e, "RTG has had a problem loading Thaumcraft configuration.");	
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