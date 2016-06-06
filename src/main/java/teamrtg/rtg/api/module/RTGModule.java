package teamrtg.rtg.api.module;

import teamrtg.rtg.api.config.ModConfig;
import teamrtg.rtg.api.world.biome.RTGBiome;

/**
 * Holds all properties that RTG would need to access for a supported mod.
 * @author topisani
 */
public class RTGModule {

    private final String modID;
    private final boolean hasConfig;
    private final boolean hasBiomes;
    public ModConfig config;
    public ModBiomes biomes;
    protected boolean present;

    public RTGModule(String id, boolean hasConfig, boolean hasBiomes) {
        this.hasConfig = hasConfig || hasBiomes;
        this.hasBiomes = hasBiomes;
        if (this.hasConfig) this.config = new ModConfig(id);
        if (this.hasBiomes) this.biomes = new ModBiomes();
        modID = id;
    }

    public boolean isPresent() {
        return present;
    }

    public String getID() {
        return modID;
    }

    public void syncConfig() {
        if (hasConfig) {
            config.syncConfiguration(config.forgeConfig);
            if (hasBiomes) {
                for (RTGBiome biome : biomes.getBiomes()) {
                    biome.getConfig().syncConfiguration(config.biomeConfig);
                }
            }
        }
    }

    public void initBiomes() {
        if (hasBiomes) {
            biomes.initBiomes();
        }
    }
}
