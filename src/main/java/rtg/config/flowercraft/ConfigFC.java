package rtg.config.flowercraft;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

import rtg.api.biome.flowercraft.config.BiomeConfigFC;
import rtg.config.BiomeConfigManager;
import rtg.util.Logger;

public class ConfigFC {

    public static Configuration config;

    public static void init(File configFile) {

        config = new Configuration(configFile);

        try {
            config.load();

            BiomeConfigManager.setBiomeConfigsFromUserConfigs(BiomeConfigFC.getBiomeConfigs(), config);
        }
        catch (Exception e) {
            Logger.error("RTG had a problem loading FC configuration.");
        }
        finally {
            if (config.hasChanged()) {
                config.save();
            }
        }
    }
}