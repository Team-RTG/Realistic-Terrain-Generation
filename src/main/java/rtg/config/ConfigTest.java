package rtg.config;

import java.io.File;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

import org.apache.logging.log4j.Level;

import cpw.mods.fml.common.FMLLog;

public class ConfigTest
{
	public static Configuration config;

	public static void init(File configFile)
	{
		config = new Configuration(configFile);
		
		try
		{
			config.load();
			
		}
		catch (Exception e)
		{
			FMLLog.log(Level.ERROR, "RTG has had a problem loading its configuration", e);
		}
		finally
		{
			if (config.hasChanged()) {
				config.save();
			}
		}
	}
}
