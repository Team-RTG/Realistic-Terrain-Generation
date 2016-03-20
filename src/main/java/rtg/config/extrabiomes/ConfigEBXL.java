package rtg.config.extrabiomes;

import net.minecraftforge.common.config.Configuration;
import rtg.api.biome.extrabiomes.config.BiomeConfigEBXL;
import rtg.config.BiomeConfigManager;
import rtg.util.Logger;

import java.io.File;

public class ConfigEBXL {
    public static Configuration config;

    public static void init(File configFile) {
        config = new Configuration(configFile);

        try {
            config.load();

            BiomeConfigManager.setBiomeConfigsFromUserConfigs(BiomeConfigEBXL.getBiomeConfigs(), config);
        } catch (Exception e) {
            Logger.error("RTG has had a problem loading EBXL configuration.");
        } finally {
            if (config.hasChanged()) {
                config.save();
            }
        }
    }
}
