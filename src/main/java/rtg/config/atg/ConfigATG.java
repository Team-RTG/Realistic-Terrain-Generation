package rtg.config.atg;

import java.io.File;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.Level;

import rtg.api.biome.BiomeConfig;
import rtg.api.biome.atg.config.BiomeConfigATG;
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
	
    public static int weightATGGravelBeach = (int)Math.floor((double)(biomeWeightDefault * 0.5));
    public static int weightATGSnowyGravelBeach = (int)Math.floor((double)(biomeWeightDefault * 0.5));
    public static int weightATGShrubland = biomeWeightDefault;
    public static int weightATGRockySteppe = biomeWeightDefault;
    public static int weightATGTropicalShrubland = biomeWeightDefault;
    public static int weightATGTundra = biomeWeightDefault;
    public static int weightATGVolcano = (int)Math.floor((double)(biomeWeightDefault * 0.1));
    public static int weightATGWoodland = biomeWeightDefault;
    
    public static boolean villageATGGravelBeach = false;
    public static boolean villageATGSnowyGravelBeach = false;
    public static boolean villageATGShrubland = true;
    public static boolean villageATGRockySteppe = true;
    public static boolean villageATGTropicalShrubland = true;
    public static boolean villageATGTundra = true;
    public static boolean villageATGVolcano = false;
    public static boolean villageATGWoodland = true;
		
	public static void init(File configFile) 
	{
		config = new Configuration(configFile);
	
		try 
		{
			config.load();
			
			generateATGBiomes = config.getBoolean("Allow biomes from this mod to generate", "Allow mod biomes", generateATGBiomes, "If TRUE, uses the individual biome settings below. If FALSE, disables all biomes from this mod." + Configuration.NEW_LINE);
			
            generateATGGravelBeach = config.getBoolean(formatConfig("generateATGGravelBeach"), "Biomes", generateATGGravelBeach, "");
            generateATGSnowyGravelBeach = config.getBoolean(formatConfig("generateATGSnowyGravelBeach"), "Biomes", generateATGSnowyGravelBeach, "");
            generateATGShrubland = config.getBoolean(formatConfig("generateATGShrubland"), "Biomes", generateATGShrubland, "");
            generateATGRockySteppe = config.getBoolean(formatConfig("generateATGRockySteppe"), "Biomes", generateATGRockySteppe, "");
            generateATGTropicalShrubland = config.getBoolean(formatConfig("generateATGTropicalShrubland"), "Biomes", generateATGTropicalShrubland, "");
            generateATGTundra = config.getBoolean(formatConfig("generateATGTundra"), "Biomes", generateATGTundra, "");
            generateATGVolcano = config.getBoolean(formatConfig("generateATGVolcano"), "Biomes", generateATGVolcano, "");
            generateATGWoodland = config.getBoolean(formatConfig("generateATGWoodland"), "Biomes", generateATGWoodland, "");
			
            weightATGGravelBeach = config.getInt(formatConfig("weightATGGravelBeach"), "Weights", weightATGGravelBeach, biomeWeightMin, biomeWeightMax, "");
            weightATGSnowyGravelBeach = config.getInt(formatConfig("weightATGSnowyGravelBeach"), "Weights", weightATGSnowyGravelBeach, biomeWeightMin, biomeWeightMax, "");
            weightATGShrubland = config.getInt(formatConfig("weightATGShrubland"), "Weights", weightATGShrubland, biomeWeightMin, biomeWeightMax, "");
            weightATGRockySteppe = config.getInt(formatConfig("weightATGRockySteppe"), "Weights", weightATGRockySteppe, biomeWeightMin, biomeWeightMax, "");
            weightATGTropicalShrubland = config.getInt(formatConfig("weightATGTropicalShrubland"), "Weights", weightATGTropicalShrubland, biomeWeightMin, biomeWeightMax, "");
            weightATGTundra = config.getInt(formatConfig("weightATGTundra"), "Weights", weightATGTundra, biomeWeightMin, biomeWeightMax, "");
            weightATGVolcano = config.getInt(formatConfig("weightATGVolcano"), "Weights", weightATGVolcano, biomeWeightMin, biomeWeightMax, "");
            weightATGWoodland = config.getInt(formatConfig("weightATGWoodland"), "Weights", weightATGWoodland, biomeWeightMin, biomeWeightMax, "");
            
            villageATGGravelBeach = config.getBoolean(formatConfig("villageATGGravelBeach"), "Villages", villageATGGravelBeach, "");
            villageATGSnowyGravelBeach = config.getBoolean(formatConfig("villageATGSnowyGravelBeach"), "Villages", villageATGSnowyGravelBeach, "");
            villageATGShrubland = config.getBoolean(formatConfig("villageATGShrubland"), "Villages", villageATGShrubland, "");
            villageATGRockySteppe = config.getBoolean(formatConfig("villageATGRockySteppe"), "Villages", villageATGRockySteppe, "");
            villageATGTropicalShrubland = config.getBoolean(formatConfig("villageATGTropicalShrubland"), "Villages", villageATGTropicalShrubland, "");
            villageATGTundra = config.getBoolean(formatConfig("villageATGTundra"), "Villages", villageATGTundra, "");
            villageATGVolcano = config.getBoolean(formatConfig("villageATGVolcano"), "Villages", villageATGVolcano, "");
            villageATGWoodland = config.getBoolean(formatConfig("villageATGWoodland"), "Villages", villageATGWoodland, "");
            
            BiomeConfig[] biomeConfigs = BiomeConfigATG.getBiomeConfigs();
            String categoryName;
            
            for (int i = 0; i < biomeConfigs.length; i++) {
                
                categoryName = "biome." + biomeConfigs[i].modSlug + "." + biomeConfigs[i].biomeSlug;
                
                biomeConfigs[i].enableBiome = config.getBoolean(
                    "enableBiome",
                    categoryName,
                    biomeConfigs[i].enableBiome,
                    ""
                );
                
                biomeConfigs[i].biomeWeight = config.getInt(
                    "biomeWeight",
                    categoryName,
                    biomeConfigs[i].biomeWeight,
                    biomeWeightMin,
                    biomeWeightMax,
                    ""
                );
                
                biomeConfigs[i].villageBiome = config.getBoolean(
                    "villageBiome",
                    categoryName,
                    biomeConfigs[i].villageBiome,
                    ""
                );
            }
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
	
    private static String formatConfig(String s)
    {
        String returnString = s;        
        
        returnString = StringUtils.join(StringUtils.splitByCharacterTypeCamelCase(returnString), " ");

        if (s.startsWith("generate")) {
            returnString = StringUtils.replace(returnString, "generate", "Generate", 1);
        }
        else if (s.startsWith("village")) {
            returnString = StringUtils.replace(returnString, "village", "Allow villages to generate in", 1);
        }
        else if (s.startsWith("weight")) {
            returnString = StringUtils.replace(returnString, "weight", "Weight of", 1);
        }
        
        return returnString;
    }
}
