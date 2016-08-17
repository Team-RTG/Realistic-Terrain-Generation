package rtg.config.vanilla;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

import rtg.api.biome.vanilla.config.BiomeConfigVanilla;
import rtg.config.BiomeConfigManager;
import rtg.util.Logger;

public class ConfigVanilla {

    public static Configuration config;

    public static void init(File configFile) {

        config = new Configuration(configFile);

        try {
            config.load();

            BiomeConfigManager.setBiomeConfigsFromUserConfigs(BiomeConfigVanilla.getBiomeConfigs(), config);
        }
        catch (Exception e) {
            Logger.error("RTG has had a problem loading Vanilla configuration.");
        }
        finally {
            if (config.hasChanged()) {
                config.save();
            }
        }
    }
}
