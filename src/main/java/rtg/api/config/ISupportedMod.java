package rtg.api.config;

import rtg.config.ModConfig;

/**
 * @author topisani
 */
public interface ISupportedMod {
    boolean isPresent();
    String getModId();
    ModConfig getConfig();
}
