package rtg.config;

import java.io.File;

import org.apache.logging.log4j.Level;

import cpw.mods.fml.common.FMLLog;

import net.minecraftforge.common.config.Configuration;

public class ConfigAM
{
    
    public static Configuration config;
    
    public static final int biomeWeightMin = 0;
    public static final int biomeWeightMax = 100;
    public static final int biomeWeightDefault = 10;
    
    public static boolean generateAMBiomes = true;
    public static boolean generateAMWitchwoodForest = true;
    public static int weightAMWitchwoodForest = biomeWeightDefault;
    
    public static void init(File configFile)
    {
    
        config = new Configuration(configFile);
        
        try
        {
            config.load();
            
            generateAMBiomes = config.getBoolean("Generate AM Biomes", "AM Biomes", generateAMBiomes, "");
            generateAMWitchwoodForest = config.getBoolean("generateAMWitchwoodForest", "AM Biomes", generateAMWitchwoodForest, "");
            weightAMWitchwoodForest = config.getInt("weightAMWitchwoodForest", "AM Biome Weights", weightAMWitchwoodForest, biomeWeightMin, biomeWeightMax, "");
            
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
}
