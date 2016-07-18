package teamrtg.rtg.modules.bop;

import teamrtg.rtg.api.config.ModConfig;
import teamrtg.rtg.api.module.RTGModule;
import teamrtg.rtg.api.util.ModPresenceTester;

/**
 * @author WhichOnesPink
 */
public class RTGModuleBOP extends RTGModule {

    public final ModConfig config;
    public final RealisticBOPBiomes biomes;

    public RTGModuleBOP() {
        super("BiomesOPlenty", true, true);
        config = new ModConfig("BOP");
        biomes = new RealisticBOPBiomes();
        super.biomes = biomes;
        super.config = config;
    }

    /**
     * Is the mod present?
     * @return true if mod is currently installed
     * @see ModPresenceTester
     */
    @Override
    public boolean isPresent() {
        return false;
    }

    @Override
    public String getID() {
        return null;
    }
}
