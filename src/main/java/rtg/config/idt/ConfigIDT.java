package rtg.config.idt;

import java.io.File;

import net.minecraftforge.common.config.Configuration;
import rtg.api.biome.idt.config.BiomeConfigIDT;
import rtg.config.BiomeConfigManager;
import rtg.util.Logger;

public class ConfigIDT
{
    
    public static Configuration config;
    
    public static void init(File configFile)
    {
    
        config = new Configuration(configFile);
        
        try
        {
            config.load();
            
            BiomeConfigManager.setBiomeConfigsFromUserConfigs(BiomeConfigIDT.getBiomeConfigs(), config);
            
        } catch (Exception e)
        {
            Logger.error("RTG has had a problem loading IDT configuration.");
        } finally
        {
            if (config.hasChanged())
            {
                config.save();
            }
        }
    }
}
