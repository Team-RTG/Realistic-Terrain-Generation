package rtg.config.forgottennature;

import java.io.File;

import org.apache.logging.log4j.Level;

import rtg.api.biome.forgottennature.config.BiomeConfigFN;
import rtg.config.BiomeConfigManager;
import cpw.mods.fml.common.FMLLog;

import net.minecraftforge.common.config.Configuration;

public class ConfigFN
{
    
    public static Configuration config;
    
    public static void init(File configFile)
    {
    
        config = new Configuration(configFile);
        
        try
        {
            config.load();
            
            BiomeConfigManager.setBiomeConfigsFromUserConfigs(BiomeConfigFN.getBiomeConfigs(), config);
            
        } catch (Exception e)
        {
            FMLLog.log(Level.ERROR, e, "RTG has had a problem loading FN configuration.");
        } finally
        {
            if (config.hasChanged())
            {
                config.save();
            }
        }
    }
}
