package rtg.config.rtg;

import java.io.File;

import org.apache.logging.log4j.Level;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.config.Configuration;

public class ConfigRTG
{
	public static Configuration config;
	
	public static boolean generateEmeralds = true;
	
    public static boolean enableCobblestoneBoulders = true;
    public static int cobblestoneBoulderChance = 1;
    
    public static boolean enableWaterLakes = true;
    public static int waterLakeChance = 10;
    public static boolean enableLavaLakes = true;
    public static int lavaLakeChance = 18;
	
	public static boolean showDebugInfo = false;
	public static boolean enableDebugging = false;

	public static int biomeSize = 4;
	
	public static void init(File configFile) 
	{
		config = new Configuration(configFile);
		
		try 
		{
			config.load();
			
			generateEmeralds = config.getBoolean("Generate Emeralds", "World Gen", true, "");
			
            enableCobblestoneBoulders = config.getBoolean("Enable Cobblestone Boulders", "World Gen", true, "");
            cobblestoneBoulderChance = config.getInt("1/x chance that Cobblestone Boulders will generate if given the opportunity to do so during world gen", "World Gen", cobblestoneBoulderChance, 1, 100, "1 = Always generate if possible; 2 = 50% chance; 4 = 25% chance");
            
            enableWaterLakes = config.getBoolean("Enable Water Lakes", "World Gen", true, "");
            waterLakeChance = config.getInt("1/x chance that Water Lakes will generate if given the opportunity to do so during world gen", "World Gen", waterLakeChance, 1, 100, "1 = Always generate if possible; 2 = 50% chance; 4 = 25% chance");
            
            enableLavaLakes = config.getBoolean("Enable Lava Lakes", "World Gen", true, "");
            lavaLakeChance = config.getInt("1/x chance that Lava Lakes will generate if given the opportunity to do so during world gen", "World Gen", lavaLakeChance, 1, 100, "1 = Always generate if possible; 2 = 50% chance; 4 = 25% chance");
			
			showDebugInfo = config.getBoolean("Show Debug Info in F3 Screen", "Debugging", false, "");
			enableDebugging = config.getBoolean("Enable Debugging", "Debugging", false, "WARNING: This should only be enabled if you know what you're doing.");
			
			biomeSize = config.getInt("Size of Biomes", "World Gen", 4, 4, 6, "COMING SOON!!! 4 = Default World Type; 6 = Large Biomes World Type");
		}
		catch (Exception e) 
		{
			FMLLog.log(Level.ERROR, e, "RTG has had a problem loading RTG configuration.");	
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
