package rtg.config.betteragriculture;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

import rtg.api.biome.betteragriculture.config.BiomeConfigBA;
import rtg.config.BiomeConfigManager;
import rtg.util.Logger;

public class ConfigBA {

    public static Configuration config;

    public static void init(File configFile) {

        config = new Configuration(configFile);

        try {
            config.load();

            BiomeConfigManager.setBiomeConfigsFromUserConfigs(BiomeConfigBA.getBiomeConfigs(), config);
        }
        catch (Exception e) {
            Logger.error("RTG had a problem loading BA configuration.");
        }
        finally {
            if (config.hasChanged()) {
                config.save();
            }
        }
    }
}