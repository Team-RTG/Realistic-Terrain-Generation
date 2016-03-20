package rtg.config.growthcraft;

import net.minecraftforge.common.config.Configuration;
import rtg.api.biome.growthcraft.config.BiomeConfigGC;
import rtg.config.BiomeConfigManager;
import rtg.util.Logger;

import java.io.File;

public class ConfigGC {

    public static Configuration config;

    public static void init(File configFile) {

        config = new Configuration(configFile);

        try {
            config.load();

            BiomeConfigManager.setBiomeConfigsFromUserConfigs(BiomeConfigGC.getBiomeConfigs(), config);

        } catch (Exception e) {
            Logger.error("RTG has had a problem loading GC configuration.");
        } finally {
            if (config.hasChanged()) {
                config.save();
            }
        }
    }
}
