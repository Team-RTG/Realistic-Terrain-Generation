package rtg.api.util;

import rtg.api.RTGAPI;
import rtg.api.config.RTGConfig;
import rtg.api.world.biome.IRealisticBiome;
import rtg.api.world.deco.DecoBase;

// TODO: [Clean-up] Class should be final to prevent extension, Class should have a private no-params contructor to prevent instantiation.
public class DecoUtil {

    private DecoBase deco;
    private RTGConfig rtgConfig = RTGAPI.config();

    public DecoUtil(DecoBase deco) {

        this.deco = deco;
    }

    public int calculateLoopCountFromTreeDensity(int loopCount, IRealisticBiome biome) {

        float multiplier = rtgConfig.TREE_DENSITY_MULTIPLIER.get();
        float biomeMultiplier = biome.getConfig().TREE_DENSITY_MULTIPLIER.get();

        if (biomeMultiplier >= 0f) {
// TODO: [Clean-up] Instance based call to static member.
            multiplier = (biomeMultiplier > rtgConfig.MAX_TREE_DENSITY) ? rtgConfig.MAX_TREE_DENSITY : biomeMultiplier;
        }

        loopCount = (int)((float)loopCount * multiplier);

        return loopCount;
    }

    public int adjustChanceFromMultiplier(int chanceIn, float multiplier) {

        int chanceOut = (multiplier != 0f) ? ((int) Math.floor((float)chanceIn / multiplier)) : chanceIn;

        return (chanceOut == 0) ? 1 : chanceOut;
    }
}