package rtg.config.biomesoplenty;

import net.minecraftforge.common.config.Configuration;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOP;
import rtg.config.BiomeConfigManager;
import rtg.util.Logger;

import java.io.File;

public class ConfigBOP 
{
	public static Configuration config;

	public static void init(File configFile) 
	{
		config = new Configuration(configFile);
	
		try 
		{
			config.load();
			
            BiomeConfigManager.setBiomeConfigsFromUserConfigs(BiomeConfigBOP.getBiomeConfigs(), config);
		}
		catch (Exception e)
		{
		    Logger.error("RTG has had a problem loading BOP configuration.");
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
