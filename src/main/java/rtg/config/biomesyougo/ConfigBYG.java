package rtg.config.biomesyougo;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

import rtg.api.biome.biomesyougo.config.BiomeConfigBYG;
import rtg.config.BiomeConfigManager;
import rtg.util.Logger;

public class ConfigBYG {

    public static Configuration config;

    public static void init(File configFile) {

        config = new Configuration(configFile);

        try {
            config.load();

            BiomeConfigManager.setBiomeConfigsFromUserConfigs(BiomeConfigBYG.getBiomeConfigs(), config);
        }
        catch (Exception e) {
            Logger.error("RTG had a problem loading BYG configuration.");
        }
        finally {
            if (config.hasChanged()) {
                config.save();
            }
        }
    }
}