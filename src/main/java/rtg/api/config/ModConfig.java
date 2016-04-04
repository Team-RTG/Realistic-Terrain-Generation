package rtg.api.config;

import net.minecraftforge.common.config.Configuration;
import rtg.RTG;
import rtg.api.util.debug.Logger;

import java.io.File;

/**
 * A configuration file for a mod
 * Holds a main Config object and all biome configs
 *
 * @author topisani
 */
public class ModConfig extends Config {
    public final String modID;
    protected final Configuration config;

    public ModConfig(String modID) {
        super();
        this.modID = modID;
        this.config = new Configuration(
                new File(RTG.configPath + "/biomes/" +
                        modID + ".cfg"));

        try {
            config.load();

        } catch (Exception e) {
            Logger.error("RTG has had a problem loading " + modID + " configuration.");
        } finally {
            if (config.hasChanged()) {
                config.save();
            }
        }
    }

    public void initDefaults() {}
}
