package teamrtg.rtg.api.config;

import net.minecraftforge.common.config.Configuration;
import teamrtg.rtg.api.util.debug.Logger;

import java.io.File;

import static teamrtg.rtg.core.RTG.configPath;

/**
 * A configuration file for a mod
 * Holds a main Config object and all biome configs
 * @author topisani
 */
public class ModConfig extends Config {
    public final String modID;
    public final Configuration forgeConfig;
    public final Configuration biomeConfig;

    public ModConfig(String modID) {
        super();
        this.modID = modID;
        this.forgeConfig = new Configuration(
            new File(configPath + modID + ".cfg"));
        this.biomeConfig = new Configuration(
            new File(configPath + "biomes/" + modID + ".cfg"));

        try {
            forgeConfig.load();
            biomeConfig.load();

        } catch (Exception e) {
            Logger.error("RTG has had a problem loading " + modID + " configuration.");
        } finally {
            if (forgeConfig.hasChanged()) {
                forgeConfig.save();
            }
            if (biomeConfig.hasChanged()) {
                biomeConfig.save();
            }
        }
    }

    public void initDefaults() {}
}
