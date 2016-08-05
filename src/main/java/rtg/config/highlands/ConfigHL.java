package rtg.config.highlands;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

import rtg.api.biome.highlands.config.BiomeConfigHL;
import rtg.config.BiomeConfigManager;
import rtg.util.Logger;

public class ConfigHL {

    public static Configuration config;

    public static void init(File configFile) {

        config = new Configuration(configFile);

        try {

            config.load();

            BiomeConfigManager.setBiomeConfigsFromUserConfigs(BiomeConfigHL.getBiomeConfigs(), config);

        }
        catch (Exception e) {

            Logger.error("RTG has had a problem loading Highlands configuration.");
        }
        finally {

            if (config.hasChanged()) {

                config.save();
            }
        }
    }
}
