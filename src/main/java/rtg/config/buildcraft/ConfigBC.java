package rtg.config.buildcraft;

import java.io.File;

import rtg.api.biome.buildcraft.config.BiomeConfigBC;
import rtg.config.BiomeConfigManager;
import rtg.util.Logger;

import net.minecraftforge.common.config.Configuration;

public class ConfigBC
{
    
    public static Configuration config;

    public static void init(File configFile)
    {
    
        config = new Configuration(configFile);
        
        try
        {
            config.load();

            BiomeConfigManager.setBiomeConfigsFromUserConfigs(BiomeConfigBC.getBiomeConfigs(), config);
            
        } catch (Exception e)
        {
            Logger.error("RTG has had a problem loading BuildCraft configuration.");
        } finally
        {
            if (config.hasChanged())
            {
                config.save();
            }
        }
    }
}
