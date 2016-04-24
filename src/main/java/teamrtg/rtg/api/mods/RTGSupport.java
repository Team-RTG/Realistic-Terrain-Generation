package teamrtg.rtg.api.mods;

import teamrtg.rtg.api.config.ModConfig;
import teamrtg.rtg.api.biome.RealisticBiomeBase;
import teamrtg.rtg.world.gen.ChunkProviderRTG;

/**
 * Holds all properties that RTG would need to access for a supported mod.
 * @author topisani
 */
public class RTGSupport {

    private final String modID;
    private final boolean hasConfig;
    private final boolean hasBiomes;
    public ModConfig config;
    public ModBiomes biomes;
    protected boolean present;

    public RTGSupport(String id, boolean hasConfig, boolean hasBiomes) {
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
                for (RealisticBiomeBase biome : biomes.getBiomes()) {
                    biome.config.syncConfiguration(config.forgeConfig);
                }
            }
        }
    }

    public void initBiomes(ChunkProviderRTG chunkProvider) {
        if (hasBiomes) {
            biomes.initBiomes(chunkProvider);
        }
    }
}
