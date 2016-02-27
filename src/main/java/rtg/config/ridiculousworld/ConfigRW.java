package rtg.config.ridiculousworld;

import java.io.File;

import org.apache.logging.log4j.Level;

import rtg.api.biome.ridiculousworld.config.BiomeConfigRW;
import rtg.config.BiomeConfigManager;
import net.minecraftforge.fml.common.FMLLog;

import net.minecraftforge.common.config.Configuration;

public class ConfigRW
{
    
    public static Configuration config;
    
    public static void init(File configFile)
    {
    
        config = new Configuration(configFile);
        
        try
        {
            config.load();
            
            BiomeConfigManager.setBiomeConfigsFromUserConfigs(BiomeConfigRW.getBiomeConfigs(), config);
            
        } catch (Exception e)
        {
            FMLLog.log(Level.ERROR, e, "RTG has had a problem loading RW configuration.");
        } finally
        {
            if (config.hasChanged())
            {
                config.save();
            }
        }
    }
}
