package rtg.api.mods;

import rtg.api.config.ModConfig;

/**
 * Holds all the mods that RTG implements explicit support for
 * Provides access to modId, presence and configuration
 *
 * @author topisani
 */
public class SupportedMod {

    private final String modID;
    protected boolean present;
    private final boolean hasConfig;
    public ModConfig config;

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
