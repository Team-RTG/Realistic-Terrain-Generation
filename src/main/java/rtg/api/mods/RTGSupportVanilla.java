package rtg.api.mods;

import rtg.api.config.ModConfig;
import rtg.api.util.ModPresenceTester;
import rtg.world.biome.realistic.vanilla.RealisticVanillaBiomes;

/**
 * @author topisani
 */
public class RTGSupportVanilla extends RTGSupport {

    public final ModConfig config;
    public final RealisticVanillaBiomes biomes;

    public RTGSupportVanilla() {
        super("Vanilla", true, true);
        config = new ModConfig("Vanilla");
        biomes = new RealisticVanillaBiomes();
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
