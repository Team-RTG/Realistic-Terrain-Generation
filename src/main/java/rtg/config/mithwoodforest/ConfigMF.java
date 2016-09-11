package rtg.config.mithwoodforest;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

import rtg.api.biome.mithwoodforest.config.BiomeConfigMF;
import rtg.config.BiomeConfigManager;
import rtg.util.Logger;

public class ConfigMF {

    public static Configuration config;

    public static void init(File configFile) {

        config = new Configuration(configFile);

        try {
            config.load();

            BiomeConfigManager.setBiomeConfigsFromUserConfigs(BiomeConfigMF.getBiomeConfigs(), config);
        }
        catch (Exception e) {
            Logger.error("RTG had a problem loading MF configuration.");
        }
        finally {
            if (config.hasChanged()) {
                config.save();
            }
        }
    }
}
