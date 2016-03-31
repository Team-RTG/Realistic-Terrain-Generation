package rtg.api.config;

import rtg.config.ModConfig;

/**
 * @author topisani
 */
public interface ISupportedMod {
    void init();
    boolean isPresent();
    String getModId();
    ModConfig getConfig();
}
