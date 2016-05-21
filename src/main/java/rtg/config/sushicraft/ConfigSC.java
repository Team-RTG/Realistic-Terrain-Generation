package rtg.config.sushicraft;

import net.minecraftforge.common.config.Configuration;
import rtg.api.biome.growthcraft.config.BiomeConfigGC;
import rtg.api.biome.sushicraft.config.BiomeConfigSC;
import rtg.config.BiomeConfigManager;
import rtg.util.Logger;

import java.io.File;

/**
 * Created by VelocityRa on 15/4/2016.
 */

public class ConfigSC
{
    public static Configuration config;

    public static void init(File configFile)
    {

        config = new Configuration(configFile);

        try
        {
            config.load();

            BiomeConfigManager.setBiomeConfigsFromUserConfigs(BiomeConfigSC.getBiomeConfigs(), config);

        } catch (Exception e)
        {
            Logger.error("RTG has had a problem loading SC configuration.");
        } finally
        {
            if (config.hasChanged())
            {
                config.save();
            }
        }
    }
}
