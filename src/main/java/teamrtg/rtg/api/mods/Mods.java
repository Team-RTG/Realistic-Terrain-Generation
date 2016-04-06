package teamrtg.rtg.api.mods;

import teamrtg.rtg.mods.vanilla.RTGSupportVanilla;

import java.util.ArrayList;
import java.util.List;

/**
 * @author topisani
 */
public class Mods {
    public static final RTGSupportRTG RTG = new RTGSupportRTG();
    public static final RTGSupportVanilla VANILLA = new RTGSupportVanilla();
    public static final RTGSupport ABYSSALCRAFT = new RTGSupport("Abyssalcraft", false, false);

    private static final List<RTGSupport> mods = new ArrayList<>();

    static {
        registerMod(RTG);
        registerMod(VANILLA);
        registerMod(ABYSSALCRAFT);
    }

    public static void initAllBiomes() {
        mods.forEach(RTGSupport::initBiomes);
    }

    public static void syncAllConfigs() {
        mods.forEach(RTGSupport::syncConfig);
    }

    /**
     * Add your RTGSupport Object here, and RTG will take care of adding biomes and such
     */
    public static void registerMod(RTGSupport mod) {
        mods.add(mod);
    }
}
