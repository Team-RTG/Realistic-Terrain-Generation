package teamrtg.rtg.modules.abyssalcraft;

import teamrtg.rtg.api.config.ModConfig;
import teamrtg.rtg.api.module.RTGModule;

/**
 * @author WhichOnesPink
 */
public class RTGModuleAC extends RTGModule {

    public final ModConfig config;
    public final RealisticACBiomes biomes;

    public RTGModuleAC() {

        super("abyssalcraft", true, true);
        config = new ModConfig("AbyssalCraft");
        biomes = new RealisticACBiomes();
        super.biomes = biomes;
        super.config = config;
    }
}
