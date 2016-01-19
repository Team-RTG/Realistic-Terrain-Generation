package rtg.config.atg;

import java.io.File;

import org.apache.logging.log4j.Level;

import rtg.api.biome.atg.config.BiomeConfigATG;
import rtg.config.BiomeConfigManager;
import cpw.mods.fml.common.FMLLog;

import net.minecraftforge.common.config.Configuration;

public class ConfigATG 
{
	public static Configuration config;
	
	public static void init(File configFile) 
	{
		config = new Configuration(configFile);
	
		try 
		{
			config.load();

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
}
