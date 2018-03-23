package rtg.api.util;

import rtg.api.RTGAPI;
import rtg.api.config.RTGConfig;
import rtg.api.world.biome.IRealisticBiome;

@UtilityClass
public final class DecoUtil {

    private DecoUtil() {

    }

    public static int calculateLoopCountFromTreeDensity(int loopCount, IRealisticBiome biome) {

        float multiplier = RTGAPI.config().TREE_DENSITY_MULTIPLIER.get();
        float biomeMultiplier = biome.getConfig().TREE_DENSITY_MULTIPLIER.get();

        if (biomeMultiplier >= 0f) {
            multiplier = (biomeMultiplier > RTGConfig.MAX_TREE_DENSITY) ? RTGConfig.MAX_TREE_DENSITY : biomeMultiplier;
        }

        loopCount = (int)(loopCount * multiplier);

        return loopCount;
    }

    public static int adjustChanceFromMultiplier(int chanceIn, float multiplier) {

        int chanceOut = (multiplier != 0f) ? ((int) Math.floor(chanceIn / multiplier)) : chanceIn;

        return (chanceOut == 0) ? 1 : chanceOut;
    }
}