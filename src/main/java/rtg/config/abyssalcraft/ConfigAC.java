package rtg.config.abyssalcraft;

import net.minecraftforge.common.config.Configuration;
import rtg.api.biome.abyssalcraft.config.BiomeConfigAC;
import rtg.config.BiomeConfigManager;
import rtg.util.Logger;

import java.io.File;

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
            Logger.error("RTG has had a problem loading AC configuration.");
        } finally
        {
            if (config.hasChanged())
            {
                config.save();
            }
        }
    }
}
