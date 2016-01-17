package rtg.config.vanilla;

import java.io.File;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.Level;

import rtg.api.biome.vanilla.config.BiomeConfigVanilla;
import rtg.config.BiomeConfigManager;
import rtg.world.biome.BiomeBase;
import cpw.mods.fml.common.FMLLog;

import net.minecraftforge.common.config.Configuration;

public class ConfigVanilla
{
	public static Configuration config;
	
	public static boolean generateVanillaBiomes = true;
	
	public static void init(File configFile) 
	{
		config = new Configuration(configFile);
		
		try 
		{
			config.load();

			generateVanillaBiomes = config.getBoolean("Allow vanilla biomes to generate", "Allow vanilla biomes", generateVanillaBiomes, "If TRUE, uses the individual biome settings below. If FALSE, disables all vanilla biomes." + Configuration.NEW_LINE);
            
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
