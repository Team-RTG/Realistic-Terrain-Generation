package rtg.config;

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
    
    public static int weightBCDesertOilField = biomeWeightDefault;
    public static int weightBCOceanOilField = (int)Math.floor((double)(biomeWeightDefault * 0.8));
    
    public static void init(File configFile)
    {
    
        config = new Configuration(configFile);
        
        try
        {
            config.load();
            
            generateBCBiomes = config.getBoolean("Generate BuildCraft Biomes", "BuildCraft Biomes", true, "");
            
            generateBCDesertOilField = config.getBoolean("generateBCDesertOilField", "BuildCraft Biomes", true, "");
            generateBCOceanOilField = config.getBoolean("generateBCOceanOilField", "BuildCraft Biomes", true, "");
            
            weightBCDesertOilField =
                config.getInt("weightBCDesertOilField", "BuildCraft Biome Weights", weightBCDesertOilField, biomeWeightMin, biomeWeightMax, "");
            weightBCOceanOilField =
                config.getInt("weightBCOceanOilField", "BuildCraft Biome Weights", weightBCOceanOilField, biomeWeightMin, biomeWeightMax, "");
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
