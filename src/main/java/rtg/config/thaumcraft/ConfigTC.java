package rtg.config.thaumcraft;

import java.io.File;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.config.Configuration;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

import org.apache.logging.log4j.Level;

public class ConfigTC 
{
	public static Configuration config;
	
	public static final int biomeWeightMin = 0;
	public static final int biomeWeightMax = 100;
	public static final int biomeWeightDefault = 10;
	
	public static boolean generateTCBiomes = true;
			
	public static boolean generateTCTaintedLand = true;
	public static boolean generateTCMagicalForest = true;
	
	public static int weightTCTaintedLand = biomeWeightDefault;
	public static int weightTCMagicalForest = biomeWeightDefault;
		
	public static void init(File configFile) 
	{
		config = new Configuration(configFile);
	
		try 
		{
			config.load();
			
			generateTCBiomes = config.getBoolean("Generate Biomes", "Biomes", true, "If TRUE, uses the individual biome settings below. If FALSE, disables all biomes from this mod.");
			
			generateTCTaintedLand = config.getBoolean("generateTCTaintedLand", "Biomes", true, "");
			generateTCMagicalForest = config.getBoolean("generateTCMagicalForest", "Biomes", true, "");
			
			weightTCTaintedLand = config.getInt("weightTCTaintedLand", "Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightTCMagicalForest = config.getInt("weightTCMagicalForest", "Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
		}
		catch (Exception e)
		{
			FMLLog.log(Level.ERROR, e, "RTG has had a problem loading Thaumcraft configuration.");	
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
