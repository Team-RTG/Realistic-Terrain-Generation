package rtg.config.icmod;

import net.minecraftforge.common.config.Configuration;
import rtg.api.biome.icmod.config.BiomeConfigIC;
import rtg.config.BiomeConfigManager;
import rtg.util.Logger;

import java.io.File;

/**
 * Created by VelocityRa on 16/4/2016.
 */
public class ConfigIC {

    public static Configuration config;

    public static void init(File configFile)
    {

        config = new Configuration(configFile);

        try
        {
            config.load();

            BiomeConfigManager.setBiomeConfigsFromUserConfigs(BiomeConfigIC.getBiomeConfigs(), config);

        } catch (Exception e)
        {
            Logger.error("RTG has had a problem loading ICMod configuration.");
        } finally
        {
            if (config.hasChanged())
            {
                config.save();
            }
        }
    }
}
