package rtg.config.tofucraft;

import java.io.File;

import org.apache.logging.log4j.Level;

import rtg.api.biome.tofucraft.config.BiomeConfigTOFU;
import rtg.config.BiomeConfigManager;
import cpw.mods.fml.common.FMLLog;

import net.minecraftforge.common.config.Configuration;

public class ConfigTOFU 
{
	public static Configuration config;
	
	public static void init(File configFile) 
	{
		config = new Configuration(configFile);
	
		try 
		{
			config.load();

            BiomeConfigManager.setBiomeConfigsFromUserConfigs(BiomeConfigTOFU.getBiomeConfigs(), config);
		}
		catch (Exception e)
		{
			FMLLog.log(Level.ERROR, e, "RTG has had a problem loading TOFU configuration.");	
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
