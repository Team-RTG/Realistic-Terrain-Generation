package rtg.config.abyssalcraft;

import java.io.File;

import org.apache.logging.log4j.Level;

import rtg.api.biome.abyssalcraft.config.BiomeConfigAC;
import rtg.config.BiomeConfigManager;
import net.minecraftforge.fml.common.FMLLog;

import net.minecraftforge.common.config.Configuration;

public class ConfigAC
{
    
    public static Configuration config;
    
    public static void init(File configFile)
    {
    
        config = new Configuration(configFile);
        
        try
        {
            config.load();
            
            BiomeConfigManager.setBiomeConfigsFromUserConfigs(BiomeConfigAC.getBiomeConfigs(), config);
            
        } catch (Exception e)
        {
            FMLLog.log(Level.ERROR, e, "RTG has had a problem loading AC configuration.");
        } finally
        {
            if (config.hasChanged())
            {
                config.save();
            }
        }
    }
}
