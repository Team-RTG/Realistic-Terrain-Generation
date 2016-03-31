package rtg.api.config;

import net.minecraftforge.common.config.Configuration;
import rtg.RTG;
import rtg.api.util.ISupportedMod;
import rtg.api.util.debug.Logger;
import rtg.util.SupportedMod;
import rtg.world.biome.realistic.RealisticBiomeBase;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * A configuration file for a mod
 * Holds a main Config object and all biome configs
 * @author topisani
 */
public class ModConfig extends Config{
    private final Configuration forgeConfig;
    public final ISupportedMod mod;
    private Map<Class<? extends RealisticBiomeBase>, BiomeConfig> biomeConfigMap = new HashMap<>();

    public ModConfig(SupportedMod mod) {
        super();
        this.forgeConfig = new Configuration(new File(RTG.configPath + "/biomes/" + mod.getModId() + ".cfg"));
        this.mod = mod;

        try {
            forgeConfig.load();

            this.setDefaults();

        } catch (Exception e) {
            Logger.error("RTG has had a problem loading " + mod.getModId() + " configuration.");
        } finally {
            if (forgeConfig.hasChanged()) {
                forgeConfig.save();
            }
        }
    }

    public BiomeConfig setBiomeConfig(Class<? extends RealisticBiomeBase> biome, BiomeConfig config) {
        return this.biomeConfigMap.put(biome, config);
    }

    public BiomeConfig setBiomeConfig(Class<? extends RealisticBiomeBase> biome, ConfigProperty[] props) {
        String s = biome.getSimpleName();
        s = s.substring("RealisticBiome".length());
        BiomeConfig config = new BiomeConfig(mod.getModId(), s, props);
        this.biomeConfigMap.put(biome, config );
        return config;
    }

    public BiomeConfig getBiomeConfig(Class<? extends RealisticBiomeBase> biome) {
        return this.biomeConfigMap.get(biome);
    }

    public void setDefaults() {}
}
