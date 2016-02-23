package rtg.config.vanilla;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.FMLLog;
import org.apache.logging.log4j.Level;
import rtg.api.biome.vanilla.config.BiomeConfigVanilla;
import rtg.config.BiomeConfigManager;

import java.io.File;

public class ConfigVanilla
{
	public static Configuration config;
	public static Configuration villageConfig;

	public static void init(File configFile)
	{
		config = new Configuration(configFile);
		
		try 
		{
			config.load();

            BiomeConfigManager.setBiomeConfigsFromUserConfigs(BiomeConfigVanilla.getBiomeConfigs(), config);
		}
		catch (Exception e) 
		{
			FMLLog.log(Level.ERROR, e, "RTG has had a problem loading Vanilla Biome configuration.");
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

			BiomeConfigManager.setBiomeConfigsFromUserConfigs(BiomeConfigVanilla.getBiomeConfigs(), villageConfig);
		}
		catch (Exception e)
		{
			FMLLog.log(Level.ERROR, e, "RTG has had a problem loading Vanilla Village configuration.");
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
