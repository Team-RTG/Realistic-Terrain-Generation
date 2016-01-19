package rtg.config.vanilla;

import java.io.File;

import org.apache.logging.log4j.Level;

import rtg.api.biome.vanilla.config.BiomeConfigVanilla;
import rtg.config.BiomeConfigManager;
import cpw.mods.fml.common.FMLLog;

import net.minecraftforge.common.config.Configuration;

public class ConfigVanilla
{
	public static Configuration config;

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
			FMLLog.log(Level.ERROR, e, "RTG has had a problem loading Vanilla configuration.");	
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
