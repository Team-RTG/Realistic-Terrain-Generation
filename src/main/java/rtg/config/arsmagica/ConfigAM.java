package rtg.config.arsmagica;

import java.io.File;

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
            
            generateAMBiomes = config.getBoolean("Generate Biomes", "Biomes", generateAMBiomes, "If TRUE, uses the individual biome settings below. If FALSE, disables all biomes from this mod.");
            
            generateAMWitchwoodForest = config.getBoolean("generateAMWitchwoodForest", "Biomes", generateAMWitchwoodForest, "");
            
            weightAMWitchwoodForest = config.getInt("weightAMWitchwoodForest", "Weights", weightAMWitchwoodForest, biomeWeightMin, biomeWeightMax, "");
            
            villageAMWitchwoodForest = config.getBoolean("villageAMWitchwoodForest", "Villages", villageAMWitchwoodForest, "");
            
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
