package teamrtg.rtg.modules.vanilla;

import teamrtg.rtg.api.config.ModConfig;
import teamrtg.rtg.api.module.RTGModule;
import teamrtg.rtg.api.util.ModPresenceTester;

/**
 * @author topisani
 */
public class RTGModuleVanilla extends RTGModule {

    public final ModConfig config;
    public final RealisticVanillaBiomes biomes;

    public RTGModuleVanilla() {
        super("Vanilla", true, true);
        config = new ModConfig("Vanilla");
        biomes = new RealisticVanillaBiomes();
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
