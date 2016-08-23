package rtg.config.sugiforest;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

import rtg.api.biome.sugiforest.config.BiomeConfigSF;
import rtg.config.BiomeConfigManager;
import rtg.util.Logger;

public class ConfigSF {

    public static Configuration config;

    public static void init(File configFile) {

        config = new Configuration(configFile);

        try {
            config.load();

            BiomeConfigManager.setBiomeConfigsFromUserConfigs(BiomeConfigSF.getBiomeConfigs(), config);
        }
        catch (Exception e) {
            Logger.error("RTG had a problem loading SF configuration.");
        }
        finally {
            if (config.hasChanged()) {
                config.save();
            }
        }
    }
}