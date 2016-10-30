package rtg.config.morechinesemc;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

import rtg.api.biome.morechinesemc.config.BiomeConfigMCM;
import rtg.config.BiomeConfigManager;
import rtg.util.Logger;

public class ConfigMCM {

    public static Configuration config;

    public static void init(File configFile) {

        config = new Configuration(configFile);

        try {
            config.load();

            BiomeConfigManager.setBiomeConfigsFromUserConfigs(BiomeConfigMCM.getBiomeConfigs(), config);
        }
        catch (Exception e) {
            Logger.error("RTG had a problem loading MCM configuration.");
        }
        finally {
            if (config.hasChanged()) {
                config.save();
            }
        }
    }
}