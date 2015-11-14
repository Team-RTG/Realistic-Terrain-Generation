package rtg.config.chromaticraft;

import java.io.File;

import org.apache.logging.log4j.Level;

import rtg.world.biome.BiomeBase;
import cpw.mods.fml.common.FMLLog;

import net.minecraftforge.common.config.Configuration;

public class ConfigCC 
{
	public static Configuration config;
	
    public static final int biomeWeightMin = BiomeBase.MIN_BIOME_WEIGHT;
    public static final int biomeWeightMax = BiomeBase.MAX_BIOME_WEIGHT;
    public static final int biomeWeightDefault = BiomeBase.DEFAULT_BIOME_WEIGHT;
	
	public static boolean generateCCBiomes = true;
			
	public static boolean generateCCEnderForest = true;
	public static boolean generateCCRainbowForest = true;
	
	public static int weightCCEnderForest = biomeWeightDefault;
	public static int weightCCRainbowForest = biomeWeightDefault;
		
	public static void init(File configFile) 
	{
		config = new Configuration(configFile);
	
		try 
		{
			config.load();
			
			generateCCBiomes = config.getBoolean("Generate Biomes", "Biomes", true, "If TRUE, uses the individual biome settings below. If FALSE, disables all biomes from this mod.");
			
			generateCCEnderForest = config.getBoolean("generateCCEnderForest", "Biomes", generateCCEnderForest, "");
			generateCCRainbowForest = config.getBoolean("generateCCRainbowForest", "Biomes", generateCCRainbowForest, "");
			
			weightCCEnderForest = config.getInt("weightCCEnderForest", "Weights", weightCCEnderForest, biomeWeightMin, biomeWeightMax, "");
			weightCCRainbowForest = config.getInt("weightCCRainbowForest", "Weights", weightCCRainbowForest, biomeWeightMin, biomeWeightMax, "");
		}
		catch (Exception e)
		{
			FMLLog.log(Level.ERROR, e, "RTG has had a problem loading ChromatiCraft configuration.");	
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
