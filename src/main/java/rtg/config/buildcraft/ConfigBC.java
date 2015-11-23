package rtg.config.buildcraft;

import java.io.File;

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
            
            generateBCBiomes = config.getBoolean("Generate Biomes", "Biomes", generateBCBiomes, "If TRUE, uses the individual biome settings below. If FALSE, disables all biomes from this mod.");
            
            generateBCDesertOilField = config.getBoolean("generateBCDesertOilField", "Biomes", generateBCDesertOilField, "");
            generateBCOceanOilField = config.getBoolean("generateBCOceanOilField", "Biomes", generateBCOceanOilField, "");
            
            weightBCDesertOilField =
                config.getInt("weightBCDesertOilField", "Weights", weightBCDesertOilField, biomeWeightMin, biomeWeightMax, "");
            weightBCOceanOilField =
                config.getInt("weightBCOceanOilField", "Weights", weightBCOceanOilField, biomeWeightMin, biomeWeightMax, "");
            
            villageBCDesertOilField = config.getBoolean("villageBCDesertOilField", "Villages", villageBCDesertOilField, "");
            villageBCOceanOilField = config.getBoolean("villageBCOceanOilField", "Villages", villageBCOceanOilField, "");
            
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
