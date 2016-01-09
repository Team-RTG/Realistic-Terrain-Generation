package rtg.config.buildcraft;

import java.io.File;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.Level;

import rtg.world.biome.BiomeBase;
import cpw.mods.fml.common.FMLLog;

import net.minecraftforge.common.config.Configuration;

public class ConfigBC
{
    
    public static Configuration config;
    
    public static final int biomeWeightMin = BiomeBase.MIN_BIOME_WEIGHT;
    public static final int biomeWeightMax = BiomeBase.MAX_BIOME_WEIGHT;
    public static final int biomeWeightDefault = BiomeBase.DEFAULT_BIOME_WEIGHT;
    
    public static boolean generateBCBiomes = true;
    
    public static boolean generateBCDesertOilField = true;
    public static boolean generateBCOceanOilField = true;
    
    public static int weightBCDesertOilField = (int)Math.floor((double)(biomeWeightDefault * 0.1));
    public static int weightBCOceanOilField = (int)Math.floor((double)(biomeWeightDefault * 0.1));
    
    public static boolean villageBCDesertOilField = false;
    public static boolean villageBCOceanOilField = false;
    
    public static void init(File configFile)
    {
    
        config = new Configuration(configFile);
        
        try
        {
            config.load();
            
            generateBCBiomes = config.getBoolean("Allow biomes from this mod to generate", "Allow mod biomes", generateBCBiomes, "If TRUE, uses the individual biome settings below. If FALSE, disables all biomes from this mod." + Configuration.NEW_LINE);
            
            generateBCDesertOilField = config.getBoolean(formatConfig("generateBCDesertOilField"), "Biomes", generateBCDesertOilField, "");
            generateBCOceanOilField = config.getBoolean(formatConfig("generateBCOceanOilField"), "Biomes", generateBCOceanOilField, "");
            
            weightBCDesertOilField =
                config.getInt(formatConfig("weightBCDesertOilField"), "Weights", weightBCDesertOilField, biomeWeightMin, biomeWeightMax, "");
            weightBCOceanOilField =
                config.getInt(formatConfig("weightBCOceanOilField"), "Weights", weightBCOceanOilField, biomeWeightMin, biomeWeightMax, "");
            
            villageBCDesertOilField = config.getBoolean(formatConfig("villageBCDesertOilField"), "Villages", villageBCDesertOilField, "");
            villageBCOceanOilField = config.getBoolean(formatConfig("villageBCOceanOilField"), "Villages", villageBCOceanOilField, "");
            
        } catch (Exception e)
        {
            FMLLog.log(Level.ERROR, e, "RTG has had a problem loading BuildCraft configuration.");
        } finally
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
