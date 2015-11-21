package rtg.config.thaumcraft;

import java.io.File;

import org.apache.logging.log4j.Level;

import rtg.world.biome.BiomeBase;
import cpw.mods.fml.common.FMLLog;

import net.minecraftforge.common.config.Configuration;

public class ConfigTC 
{
	public static Configuration config;
	
    public static final int biomeWeightMin = BiomeBase.MIN_BIOME_WEIGHT;
    public static final int biomeWeightMax = BiomeBase.MAX_BIOME_WEIGHT;
    public static final int biomeWeightDefault = BiomeBase.DEFAULT_BIOME_WEIGHT;
	
	public static boolean generateTCBiomes = true;
			
	public static boolean generateTCTaintedLand = true;
	public static boolean generateTCMagicalForest = true;
	
	public static int weightTCTaintedLand = (int)Math.floor((double)(biomeWeightDefault * 0.5));
	public static int weightTCMagicalForest = (int)Math.floor((double)(biomeWeightDefault * 1.5));
		
	public static void init(File configFile) 
	{
		config = new Configuration(configFile);
	
		try 
		{
			config.load();
			
			generateTCBiomes = config.getBoolean("Generate Biomes", "Biomes", true, "If TRUE, uses the individual biome settings below. If FALSE, disables all biomes from this mod.");
			
			generateTCTaintedLand = config.getBoolean("generateTCTaintedLand", "Biomes", generateTCTaintedLand, "");
			generateTCMagicalForest = config.getBoolean("generateTCMagicalForest", "Biomes", generateTCMagicalForest, "");
			
			weightTCTaintedLand = config.getInt("weightTCTaintedLand", "Weights", weightTCTaintedLand, biomeWeightMin, biomeWeightMax, "");
			weightTCMagicalForest = config.getInt("weightTCMagicalForest", "Weights", weightTCMagicalForest, biomeWeightMin, biomeWeightMax, "");
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
