package rtg.config.thaumcraft;

import java.io.File;

import org.apache.commons.lang3.StringUtils;
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
	
    public static boolean villageTCTaintedLand = false;
    public static boolean villageTCMagicalForest = false;
		
	public static void init(File configFile) 
	{
		config = new Configuration(configFile);
	
		try 
		{
			config.load();
			
			generateTCBiomes = config.getBoolean("Allow biomes from this mod to generate", "Allow mod biomes", true, "If TRUE, uses the individual biome settings below. If FALSE, disables all biomes from this mod." + Configuration.NEW_LINE);
			
			generateTCTaintedLand = config.getBoolean(formatConfig("generateTCTaintedLand"), "Biomes", generateTCTaintedLand, "");
			generateTCMagicalForest = config.getBoolean(formatConfig("generateTCMagicalForest"), "Biomes", generateTCMagicalForest, "");
			
			weightTCTaintedLand = config.getInt(formatConfig("weightTCTaintedLand"), "Weights", weightTCTaintedLand, biomeWeightMin, biomeWeightMax, "");
			weightTCMagicalForest = config.getInt(formatConfig("weightTCMagicalForest"), "Weights", weightTCMagicalForest, biomeWeightMin, biomeWeightMax, "");
			
            villageTCTaintedLand = config.getBoolean(formatConfig("villageTCTaintedLand"), "Villages", villageTCTaintedLand, "");
            villageTCMagicalForest = config.getBoolean(formatConfig("villageTCMagicalForest"), "Villages", villageTCMagicalForest, "");
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
