package teamrtg.rtg.modules.bop;

import teamrtg.rtg.api.config.ModConfig;
import teamrtg.rtg.api.module.RTGModule;

/**
 * @author WhichOnesPink
 */
public class RTGModuleBOP extends RTGModule {

    public final ModConfig config;
    public final RealisticBOPBiomes biomes;

    public RTGModuleBOP() {

        super("BiomesOPlenty", true, true);
        config = new ModConfig("BiomesOPlenty");
        biomes = new RealisticBOPBiomes();
        super.biomes = biomes;
        super.config = config;
    }
}
