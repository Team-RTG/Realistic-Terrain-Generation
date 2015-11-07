package rtg.config.buildcraft;

import java.io.File;

import org.apache.logging.log4j.Level;

import cpw.mods.fml.common.FMLLog;

import net.minecraftforge.common.config.Configuration;

public class ConfigBC
{
    
    public static Configuration config;
    
    public static final int biomeWeightMin = 0;
    public static final int biomeWeightMax = 100;
    public static final int biomeWeightDefault = 10;
    
    public static boolean generateBCBiomes = true;
    
    public static boolean generateBCDesertOilField = true;
    public static boolean generateBCOceanOilField = true;
    
    public static int weightBCDesertOilField = (int)Math.floor((double)(biomeWeightDefault * 0.5));
    public static int weightBCOceanOilField = (int)Math.floor((double)(biomeWeightDefault * 0.5));
    
    public static void init(File configFile)
    {
    
        config = new Configuration(configFile);
        
        try
        {
            config.load();
            
            generateBCBiomes = config.getBoolean("Generate Biomes", "Biomes", generateBCBiomes, "If TRUE, uses the individual biome settings below. If FALSE, disables all biomes from this mod.");
            
            generateBCDesertOilField = config.getBoolean("generateBCDesertOilField", "Biomes", generateBCDesertOilField, "");
            generateBCOceanOilField = config.getBoolean("generateBCOceanOilField", "Biomes", generateBCOceanOilField, "");
            
            weightBCDesertOilField =
                config.getInt("weightBCDesertOilField", "Weights", weightBCDesertOilField, biomeWeightMin, biomeWeightMax, "");
            weightBCOceanOilField =
                config.getInt("weightBCOceanOilField", "Weights", weightBCOceanOilField, biomeWeightMin, biomeWeightMax, "");
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
}
