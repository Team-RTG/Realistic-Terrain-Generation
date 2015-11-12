package rtg.config.atg;

import java.io.File;

import org.apache.logging.log4j.Level;

import rtg.world.biome.BiomeBase;
import cpw.mods.fml.common.FMLLog;

import net.minecraftforge.common.config.Configuration;

public class ConfigATG 
{
	public static Configuration config;
	
    public static final int biomeWeightMin = BiomeBase.MIN_BIOME_WEIGHT;
    public static final int biomeWeightMax = BiomeBase.MAX_BIOME_WEIGHT;
    public static final int biomeWeightDefault = BiomeBase.DEFAULT_BIOME_WEIGHT;
	
	public static boolean generateATGBiomes = true;
			
    public static boolean generateATGGravelBeach = true;
    public static boolean generateATGSnowyGravelBeach = true;
    public static boolean generateATGShrubland = true;
    public static boolean generateATGRockySteppe = true;
    public static boolean generateATGTropicalShrubland = true;
    public static boolean generateATGTundra = true;
    public static boolean generateATGVolcano = true;
    public static boolean generateATGWoodland = true;
	
    public static int weightATGGravelBeach = biomeWeightDefault;
    public static int weightATGSnowyGravelBeach = biomeWeightDefault;
    public static int weightATGShrubland = biomeWeightDefault;
    public static int weightATGRockySteppe = biomeWeightDefault;
    public static int weightATGTropicalShrubland = biomeWeightDefault;
    public static int weightATGTundra = biomeWeightDefault;
    public static int weightATGVolcano = biomeWeightDefault;
    public static int weightATGWoodland = biomeWeightDefault;
		
	public static void init(File configFile) 
	{
		config = new Configuration(configFile);
	
		try 
		{
			config.load();
			
			generateATGBiomes = config.getBoolean("Generate Biomes", "Biomes", generateATGBiomes, "If TRUE, uses the individual biome settings below. If FALSE, disables all biomes from this mod.");
			
            generateATGGravelBeach = config.getBoolean("generateATGGravelBeach", "Biomes", generateATGGravelBeach, "");
            generateATGSnowyGravelBeach = config.getBoolean("generateATGSnowyGravelBeach", "Biomes", generateATGSnowyGravelBeach, "");
            generateATGShrubland = config.getBoolean("generateATGShrubland", "Biomes", generateATGShrubland, "");
            generateATGRockySteppe = config.getBoolean("generateATGRockySteppe", "Biomes", generateATGRockySteppe, "");
            generateATGTropicalShrubland = config.getBoolean("generateATGTropicalShrubland", "Biomes", generateATGTropicalShrubland, "");
            generateATGTundra = config.getBoolean("generateATGTundra", "Biomes", generateATGTundra, "");
            generateATGVolcano = config.getBoolean("generateATGVolcano", "Biomes", generateATGVolcano, "");
            generateATGWoodland = config.getBoolean("generateATGWoodland", "Biomes", generateATGWoodland, "");
			
            weightATGGravelBeach = config.getInt("weightATGGravelBeach", "Weights", weightATGGravelBeach, biomeWeightMin, biomeWeightMax, "");
            weightATGSnowyGravelBeach = config.getInt("weightATGSnowyGravelBeach", "Weights", weightATGSnowyGravelBeach, biomeWeightMin, biomeWeightMax, "");
            weightATGShrubland = config.getInt("weightATGShrubland", "Weights", weightATGShrubland, biomeWeightMin, biomeWeightMax, "");
            weightATGRockySteppe = config.getInt("weightATGRockySteppe", "Weights", weightATGRockySteppe, biomeWeightMin, biomeWeightMax, "");
            weightATGTropicalShrubland = config.getInt("weightATGTropicalShrubland", "Weights", weightATGTropicalShrubland, biomeWeightMin, biomeWeightMax, "");
            weightATGTundra = config.getInt("weightATGTundra", "Weights", weightATGTundra, biomeWeightMin, biomeWeightMax, "");
            weightATGVolcano = config.getInt("weightATGVolcano", "Weights", weightATGVolcano, biomeWeightMin, biomeWeightMax, "");
            weightATGWoodland = config.getInt("weightATGWoodland", "Weights", weightATGWoodland, biomeWeightMin, biomeWeightMax, "");
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
