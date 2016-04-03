package rtg.util.mods;

import rtg.api.util.ModPresenceTester;
import rtg.api.mods.SupportedMod;
import rtg.config.ConfigRTG;

/**
 * @author topisani
 */
public class SupportedModRTG extends SupportedMod {

    public final ConfigRTG config;

    public SupportedModRTG() {
        super("RTG", true);
        config = new ConfigRTG();
    }

    /**
     * Instantiates all realistic versions of biomes for this mod.
     */
    @Override
    public void addBiomes() {

    }

    /**
     * Is the mod present?
     *
     * @return true if mod is currently installed
     * @see ModPresenceTester
     */
    @Override
    public boolean isPresent() {
        return false;
    }

    @Override
    public String getModID() {
        return null;
    }
}
