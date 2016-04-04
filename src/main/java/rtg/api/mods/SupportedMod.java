package rtg.api.mods;

import rtg.api.config.ModConfig;

/**
 * Holds all properties that RTG would need to access for a supported mod.
 * @author topisani
 */
public class SupportedMod {

    private final String modID;
    protected boolean present;
    private final boolean hasConfig;
    public ModConfig config;
    public ModBiomes biomes;

    public SupportedMod(String id, boolean hasConfig) {
        this.hasConfig = hasConfig;
        modID = id;
    }

    public void addBiomes() {}

    public boolean isPresent() {
        return present;
    }

    public String getModID() {
        return modID;
    }
}
