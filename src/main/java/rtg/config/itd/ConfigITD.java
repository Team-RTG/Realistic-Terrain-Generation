package rtg.config.itd;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

import rtg.api.biome.itd.config.BiomeConfigITD;
import rtg.config.BiomeConfigManager;
import rtg.util.Logger;

public class ConfigITD
{
    public static Configuration config;

    public static void init(File configFile)
    {

        config = new Configuration(configFile);

        try
        {
            config.load();

            BiomeConfigManager.setBiomeConfigsFromUserConfigs(BiomeConfigITD.getBiomeConfigs(), config);

        } catch (Exception e)
        {
            Logger.error("RTG had a problem loading ITD configuration.");
        } finally
        {
            if (config.hasChanged())
            {
                config.save();
            }
        }
    }
}
