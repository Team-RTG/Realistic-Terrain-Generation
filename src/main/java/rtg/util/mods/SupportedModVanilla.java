package rtg.util.mods;

import rtg.api.config.ModConfig;
import rtg.api.mods.SupportedMod;
import rtg.api.util.ModPresenceTester;
import rtg.world.biome.realistic.vanilla.RealisticVanillaBiomes;

/**
 * @author tobia
 */
public class SupportedModVanilla extends SupportedMod {

    public final ModConfig config;
    public final RealisticVanillaBiomes biomes;

    public SupportedModVanilla() {
        super("RTG", true);
        config = new ModConfig(this);
        biomes = new RealisticVanillaBiomes();
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
