package rtg.config.hotwatermod;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

import rtg.api.biome.hotwatermod.config.BiomeConfigHWM;
import rtg.config.BiomeConfigManager;
import rtg.util.Logger;

public class ConfigHWM
{
    
    public static Configuration config;

    public static void init(File configFile)
    {
    
        config = new Configuration(configFile);
        
        try
        {
            config.load();

            BiomeConfigManager.setBiomeConfigsFromUserConfigs(BiomeConfigHWM.getBiomeConfigs(), config);
            
        } catch (Exception e)
        {
            Logger.error("RTG had a problem loading Hot Water Mod configuration.");
        } finally
        {
            if (config.hasChanged())
            {
                config.save();
            }
        }
    }
}
