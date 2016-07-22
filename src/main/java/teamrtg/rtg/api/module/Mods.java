package teamrtg.rtg.api.module;

import teamrtg.rtg.modules.abyssalcraft.RTGModuleAC;
import teamrtg.rtg.modules.vanilla.RTGModuleVanilla;

import java.util.ArrayList;
import java.util.List;

/**
 * @author topisani
 */
public class Mods {
    public static final RTGSupportRTG RTG = new RTGSupportRTG();
    public static final RTGModuleVanilla VANILLA = new RTGModuleVanilla();
    public static final RTGModuleAC ABYSSALCRAFT = new RTGModuleAC();

    private static final List<RTGModule> mods = new ArrayList<>();

    static {
        registerMod(RTG);
        registerMod(VANILLA);
        registerMod(ABYSSALCRAFT);
    }

    public static void initAllBiomes() {
        for (RTGModule mod : mods) {
            mod.initBiomes();
        }
    }

    public static void syncAllConfigs() {
        mods.forEach(RTGModule::syncConfig);
    }

    /**
     * Add your RTGSupport Object here, and RTG will take care of adding biomes and such
     */
    public static void registerMod(RTGModule mod) {
        mods.add(mod);
    }
}
