package rtg.config.thaumcraft;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

import rtg.api.biome.thaumcraft.config.BiomeConfigTC;
import rtg.config.BiomeConfigManager;
import rtg.util.Logger;

public class ConfigTC {

    public static Configuration config;
    public static Configuration villageConfig;

    public static void init(File configFile) {

        config = new Configuration(configFile);

        try {
            config.load();

            BiomeConfigManager.setBiomeConfigsFromUserConfigs(BiomeConfigTC.getBiomeConfigs(), config);
        }
        catch (Exception e) {
            Logger.error("RTG has had a problem loading Thaumcraft configuration.");
        }
        finally {
            if (config.hasChanged()) {
                config.save();
            }
        }
    }
}