package rtg.config.vampirism;

import net.minecraftforge.common.config.Configuration;
import rtg.api.biome.vampirism.config.BiomeConfigVAMP;
import rtg.config.BiomeConfigManager;
import rtg.util.Logger;

import java.io.File;

public class ConfigVAMP {

    public static Configuration config;

    public static void init(File configFile) {

        config = new Configuration(configFile);

        try {
            config.load();

            BiomeConfigManager.setBiomeConfigsFromUserConfigs(BiomeConfigVAMP.getBiomeConfigs(), config);

        } catch (Exception e) {
            Logger.error("RTG has had a problem loading VAMP configuration.");
        } finally {
            if (config.hasChanged()) {
                config.save();
            }
        }
    }
}
