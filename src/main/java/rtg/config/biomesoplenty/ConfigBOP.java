package rtg.config.biomesoplenty;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.FMLLog;
import org.apache.logging.log4j.Level;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOP;
import rtg.api.biome.vanilla.config.BiomeConfigVanilla;
import rtg.config.BiomeConfigManager;
import rtg.util.Logger;

import java.io.File;

public class ConfigBOP 
{
	public static Configuration config;
	public static Configuration villageConfig;

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
	public static void initVillage(File configFile) {
		villageConfig = new Configuration(configFile);

		try
		{
			villageConfig.load();

			BiomeConfigManager.setVillageConfigsFromUserConfigs(BiomeConfigVanilla.getBiomeConfigs(), villageConfig);
		}
		catch (Exception e)
		{
			Logger.error("RTG has had a problem loading BOP Village configuration. %s", e);
		}
		finally
		{
			if (villageConfig.hasChanged())
			{
				villageConfig.save();
			}
		}
	}

}
