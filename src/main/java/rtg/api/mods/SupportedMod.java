package rtg.api.mods;

import rtg.api.config.ModConfig;

/**
 * Holds all properties that RTG would need to access for a supported mod.
 *
 * @author topisani
 */
public class SupportedMod {

    private final String modID;
    private final boolean hasConfig;
    public ModConfig config;
    public ModBiomes biomes;
    protected boolean present;

    public SupportedMod(String id, boolean hasConfig) {
        this.hasConfig = hasConfig;
        modID = id;
    }

    public boolean isPresent() {
        return present;
    }

    public String getModID() {
        return modID;
    }
}
