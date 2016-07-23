package teamrtg.rtg.modules.abyssalcraft;

import teamrtg.rtg.api.config.ModConfig;
import teamrtg.rtg.api.module.RTGModule;
import teamrtg.rtg.api.util.ModPresenceTester;

/**
 * @author WhichOnesPink
 */
public class RTGModuleAC extends RTGModule {

    public final ModConfig config;
    public final RealisticACBiomes biomes;

    public RTGModuleAC() {

        super("abyssalcraft", true, true);
        config = new ModConfig("abyssalcraft");
        biomes = new RealisticACBiomes();
        super.biomes = biomes;
        super.config = config;
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
    public String getID() {

        return null;
    }
}
