package rtg.config.afraidofthedark;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

import rtg.api.biome.afraidofthedark.config.BiomeConfigAOTD;
import rtg.config.BiomeConfigManager;
import rtg.util.Logger;

public class ConfigAOTD {

    public static Configuration config;

    public static void init(File configFile) {

        config = new Configuration(configFile);

        try {
            config.load();

            BiomeConfigManager.setBiomeConfigsFromUserConfigs(BiomeConfigAOTD.getBiomeConfigs(), config);
        }
        catch (Exception e) {
            Logger.error("RTG has had a problem loading AOTD configuration.");
        }
        finally {
            if (config.hasChanged()) {
                config.save();
            }
        }
    }
}