package rtg.config.arsmagica;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.FMLLog;
import org.apache.logging.log4j.Level;
import rtg.api.biome.arsmagica.config.BiomeConfigAM;
import rtg.config.BiomeConfigManager;

import java.io.File;

public class ConfigAM
{
    
    public static Configuration config;
    
    public static void init(File configFile)
    {
    
        config = new Configuration(configFile);
        
        try
        {
            config.load();
            
            BiomeConfigManager.setBiomeConfigsFromUserConfigs(BiomeConfigAM.getBiomeConfigs(), config);
            
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
