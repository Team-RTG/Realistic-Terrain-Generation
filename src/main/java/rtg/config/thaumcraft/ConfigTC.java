package rtg.config.thaumcraft;

import net.minecraftforge.common.config.Configuration;
import rtg.api.biome.thaumcraft.config.BiomeConfigTC;
import rtg.api.biome.vanilla.config.BiomeConfigVanilla;
import rtg.config.BiomeConfigManager;
import rtg.util.Logger;

import java.io.File;

public class ConfigTC 
{
	public static Configuration config;
	public static Configuration villageConfig;

	public static void init(File configFile) 
	{
		config = new Configuration(configFile);
	
		try 
		{
			config.load();

            BiomeConfigManager.setBiomeConfigsFromUserConfigs(BiomeConfigTC.getBiomeConfigs(), config);
		}
		catch (Exception e)
		{
			Logger.error("RTG has had a problem loading Thaumcraft configuration. %s", e);
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
			Logger.error("RTG has had a problem loading Thaumcraft village configuration. %s", e);
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