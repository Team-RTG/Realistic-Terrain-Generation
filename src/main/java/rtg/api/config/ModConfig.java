package rtg.api.config;

import net.minecraftforge.common.config.Configuration;
import rtg.RTG;
import rtg.api.mods.SupportedMod;
import rtg.api.util.debug.Logger;
import rtg.world.biome.realistic.RealisticBiomeBase;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * A configuration file for a mod
 * Holds a main Config object and all biome configs
 *
 * @author topisani
 */
public class ModConfig extends Config {
    public final SupportedMod mod;
    protected final Configuration config;
    private Map<Class<? extends RealisticBiomeBase>, BiomeConfig> biomeConfigMap = new HashMap<>();

    public ModConfig(SupportedMod mod) {
        super();
        this.config = new Configuration(new File(RTG.configPath + "/biomes/" + mod.getModID() + ".cfg"));
        this.mod = mod;

        try {
            config.load();

            this.initDefaults();

        } catch (Exception e) {
            Logger.error("RTG has had a problem loading " + mod.getModID() + " configuration.");
        } finally {
            if (config.hasChanged()) {
                config.save();
            }
        }
    }

    public void initDefaults() {}

    public void loadConfigs() {

    }
}
