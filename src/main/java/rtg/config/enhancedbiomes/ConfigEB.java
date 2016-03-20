package rtg.config.enhancedbiomes;

import net.minecraftforge.common.config.Configuration;
import rtg.api.biome.enhancedbiomes.config.BiomeConfigEB;
import rtg.config.BiomeConfigManager;
import rtg.util.Logger;

import java.io.File;

public class ConfigEB {
    public static Configuration config;

    public static void init(File configFile) {
        config = new Configuration(configFile);

        try {
            config.load();

            BiomeConfigManager.setBiomeConfigsFromUserConfigs(BiomeConfigEB.getBiomeConfigs(), config);
        } catch (Exception e) {
            Logger.error("RTG has had a problem loading EB configuration.");
        } finally {
            if (config.hasChanged()) {
                config.save();
            }
        }
    }
}
