package rtg.config.lotsomobs;

import net.minecraftforge.common.config.Configuration;
import rtg.api.biome.lotsomobs.config.BiomeConfigLOM;
import rtg.config.BiomeConfigManager;
import rtg.util.Logger;

import java.io.File;

public class ConfigLOM {

    public static Configuration config;

    public static void init(File configFile) {

        config = new Configuration(configFile);

        try {
            config.load();

            BiomeConfigManager.setBiomeConfigsFromUserConfigs(BiomeConfigLOM.getBiomeConfigs(), config);

        } catch (Exception e) {
            Logger.error("RTG has had a problem loading LOM configuration.");
        } finally {
            if (config.hasChanged()) {
                config.save();
            }
        }
    }
}
