package rtg.config.eccentricbiomes;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

import rtg.api.biome.eccentricbiomes.config.BiomeConfigECC;
import rtg.config.BiomeConfigManager;
import rtg.util.Logger;

public class ConfigECC
{
    
    public static Configuration config;

    public static void init(File configFile)
    {
    
        config = new Configuration(configFile);
        
        try
        {
            config.load();

            BiomeConfigManager.setBiomeConfigsFromUserConfigs(BiomeConfigECC.getBiomeConfigs(), config);
            
        } catch (Exception e)
        {
            Logger.error("RTG had a problem loading Eccentric Biomes configuration.");
        } finally
        {
            if (config.hasChanged())
            {
                config.save();
            }
        }
    }
}
