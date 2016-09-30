package rtg.config.agriculturalrevolution;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

import rtg.api.biome.agriculturalrevolution.config.BiomeConfigAR;
import rtg.config.BiomeConfigManager;
import rtg.util.Logger;

public class ConfigAR {

    public static Configuration config;

    public static void init(File configFile) {

        config = new Configuration(configFile);

        try {
            config.load();

            BiomeConfigManager.setBiomeConfigsFromUserConfigs(BiomeConfigAR.getBiomeConfigs(), config);
        }
        catch (Exception e) {
            Logger.error("RTG had a problem loading AR configuration.");
        }
        finally {
            if (config.hasChanged()) {
                config.save();
            }
        }
    }
}