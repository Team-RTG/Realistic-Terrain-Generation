package rtg.config.tofucraft;

import net.minecraftforge.common.config.Configuration;
import rtg.api.biome.tofucraft.config.BiomeConfigTOFU;
import rtg.config.BiomeConfigManager;
import rtg.util.Logger;

import java.io.File;

public class ConfigTOFU {
    public static Configuration config;

    public static void init(File configFile) {
        config = new Configuration(configFile);

        try {
            config.load();

            BiomeConfigManager.setBiomeConfigsFromUserConfigs(BiomeConfigTOFU.getBiomeConfigs(), config);
        } catch (Exception e) {
            Logger.error("RTG has had a problem loading TOFU configuration.");
        } finally {
            if (config.hasChanged()) {
                config.save();
            }
        }
    }
}
