package rtg.util.mods;

import rtg.api.mods.SupportedMod;

/**
 * @author topisani
 */
public class Mods {
    public static final SupportedModRTG RTG = new SupportedModRTG();
    public static final SupportedModVanilla VANILLA = new SupportedModVanilla();
    public static final SupportedMod ABYSSALCRAFT = new SupportedMod("Abyssalcraft", true);

    public static void initAll(SupportedMod[] mods) {
        //TODO: do something here
    }
}
