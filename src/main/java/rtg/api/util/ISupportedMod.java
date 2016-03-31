package rtg.api.util;

import rtg.api.config.Config;

/**
 * The way RTG interacts with a supported mod, be it added by itself or RTG
 * @author topisani
 */
public interface ISupportedMod {
    /**
     * Needs to set up configs, the constructor is too early to access certain needed parts
     */
    void init();

    /**
     * Instantiates all realistic versions of biomes for this mod.
     */
    void addBiomes();

    /**
     * Is the mod present?
     * @see ModPresenceTester
     * @return true if mod is currently installed
     */
    boolean isPresent();

    String getModId();

    /**
     * Can return null if this mod doesn't need an RTG config.
     * That is probably mostly useful for first party implementations
     */
    Config.ModConfig getConfig();
}
