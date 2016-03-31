package rtg.config;

import net.minecraftforge.common.config.Configuration;
import rtg.api.config.BiomeConfig;
import rtg.api.config.ConfigProperty;
import rtg.api.config.ISupportedMod;
import rtg.util.Logger;
import rtg.util.SupportedMod;
import rtg.world.biome.realistic.RealisticBiomeBase;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class ModConfig {
    public final Configuration config;
    public final ISupportedMod mod;
    private Map<Class<? extends RealisticBiomeBase>, BiomeConfig> biomeConfigMap = new HashMap<>();

    public ModConfig(SupportedMod mod) {
        this.config = new Configuration(new File("biomes/" + mod.getModId() + ".cfg"));
        this.mod = mod;

        try {
            config.load();

            this.setDefaults();

        } catch (Exception e) {
            Logger.error("RTG has had a problem loading " + mod.getModId() + " configuration.");
        } finally {
            if (config.hasChanged()) {
                config.save();
            }
        }
    }

    public BiomeConfig setBiomeConfig(Class<? extends RealisticBiomeBase> biome, BiomeConfig config) {
        return this.biomeConfigMap.put(biome, config);
    }

    public BiomeConfig setBiomeConfig(Class<? extends RealisticBiomeBase> biome, ConfigProperty[] props) {
        String s = biome.getSimpleName();
        s = s.substring(s.indexOf("RealisticBiome") + 1);
        return this.biomeConfigMap.put(biome, new BiomeConfig(mod.getModId(), s, props));
    }

    public BiomeConfig getBiomeConfig(Class<? extends RealisticBiomeBase> biome) {
        return this.biomeConfigMap.get(biome);
    }

    public void setDefaults() {}
}
