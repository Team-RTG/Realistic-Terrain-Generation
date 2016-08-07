package rtg.config.minestrappolation;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

import rtg.api.biome.minestrappolation.config.BiomeConfigMS;
import rtg.config.BiomeConfigManager;
import rtg.util.Logger;

public class ConfigMS {

    public static Configuration config;

    public static void init(File configFile) {

        config = new Configuration(configFile);

        try {
            config.load();

            BiomeConfigManager.setBiomeConfigsFromUserConfigs(BiomeConfigMS.getBiomeConfigs(), config);

        }
        catch (Exception e) {
            Logger.error("RTG has had a problem loading Minestrappolation configuration.");
        }
        finally {
            if (config.hasChanged()) {
                config.save();
            }
        }
    }
}
