package rtg.config.arsmagica;

import java.io.File;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.Level;

import rtg.world.biome.BiomeBase;
import cpw.mods.fml.common.FMLLog;

import net.minecraftforge.common.config.Configuration;

public class ConfigAM
{
    
    public static Configuration config;
    
    public static final int biomeWeightMin = BiomeBase.MIN_BIOME_WEIGHT;
    public static final int biomeWeightMax = BiomeBase.MAX_BIOME_WEIGHT;
    public static final int biomeWeightDefault = BiomeBase.DEFAULT_BIOME_WEIGHT;
    
    public static boolean generateAMBiomes = true;
    
    public static boolean generateAMWitchwoodForest = true;
    
    public static int weightAMWitchwoodForest = (int)Math.floor((double)(biomeWeightDefault * 0.5));
    
    public static boolean villageAMWitchwoodForest = false;
    
    public static void init(File configFile)
    {
    
        config = new Configuration(configFile);
        
        try
        {
            config.load();
            
            generateAMBiomes = config.getBoolean("Allow biomes from this mod to generate", "Allow mod biomes", generateAMBiomes, "If TRUE, uses the individual biome settings below. If FALSE, disables all biomes from this mod." + Configuration.NEW_LINE);
            
            generateAMWitchwoodForest = config.getBoolean(formatConfig("generateAMWitchwoodForest"), "Biomes", generateAMWitchwoodForest, "");
            
            weightAMWitchwoodForest = config.getInt(formatConfig("weightAMWitchwoodForest"), "Weights", weightAMWitchwoodForest, biomeWeightMin, biomeWeightMax, "");
            
            villageAMWitchwoodForest = config.getBoolean(formatConfig("villageAMWitchwoodForest"), "Villages", villageAMWitchwoodForest, "");
            
        } catch (Exception e)
        {
            FMLLog.log(Level.ERROR, e, "RTG has had a problem loading AM configuration.");
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
