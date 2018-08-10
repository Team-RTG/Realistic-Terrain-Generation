package teamrtg.rtg.modules.vanilla;

import teamrtg.rtg.api.config.ModConfig;
import teamrtg.rtg.api.module.RTGModule;

/**
 * @author topisani
 */
public class RTGModuleVanilla extends RTGModule {

    public final ModConfig config;
    public final RealisticVanillaBiomes biomes;

    public RTGModuleVanilla() {

        super("vanilla", true, true);
        config = new ModConfig("Vanilla");
        biomes = new RealisticVanillaBiomes();
        super.biomes = biomes;
        super.config = config;
    }
}
