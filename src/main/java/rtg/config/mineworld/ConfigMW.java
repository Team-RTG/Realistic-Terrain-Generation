package rtg.config.mineworld;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

import rtg.api.biome.mineworld.BiomeConfigMW;
import rtg.config.BiomeConfigManager;
import rtg.util.Logger;

public class ConfigMW {

    public static Configuration config;

    public static void init(File configFile) {

        config = new Configuration(configFile);

        try {
            config.load();

            BiomeConfigManager.setBiomeConfigsFromUserConfigs(BiomeConfigMW.getBiomeConfigs(), config);
        }
        catch (Exception e) {
            Logger.error("RTG had a problem loading MW configuration.");
        }
        finally {
            if (config.hasChanged()) {
                config.save();
            }
        }
    }
}