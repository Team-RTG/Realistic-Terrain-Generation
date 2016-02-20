package rtg.config.enhancedbiomes;

import java.io.File;

import org.apache.logging.log4j.Level;

import rtg.api.biome.enhancedbiomes.config.BiomeConfigEB;
import rtg.config.BiomeConfigManager;
import net.minecraftforge.fml.common.FMLLog;

import net.minecraftforge.common.config.Configuration;

public class ConfigEB 
{
	public static Configuration config;

	public static void init(File configFile) 
	{
		config = new Configuration(configFile);
	
		try 
		{
			config.load();

            BiomeConfigManager.setBiomeConfigsFromUserConfigs(BiomeConfigEB.getBiomeConfigs(), config);
		}
		catch (Exception e)
		{
			FMLLog.log(Level.ERROR, e, "RTG has had a problem loading EB configuration.");	
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
