package rtg.util;

import rtg.RTG;
import rtg.config.RTGConfig;
import rtg.world.biome.deco.DecoBase;
import rtg.world.biome.realistic.RealisticBiomeBase;

public class DecoUtil {

    private DecoBase deco;
    private RTGConfig rtgConfig = RTG.instance.getConfig();

    public DecoUtil(DecoBase deco) {

        this.deco = deco;
    }

    public int calculateLoopCountFromTreeDensity(int loopCount, RealisticBiomeBase biome) {

        float multiplier = rtgConfig.TREE_DENSITY_MULTIPLIER.get();
        float biomeMultiplier = biome.getConfig().TREE_DENSITY_MULTIPLIER.get();

        if (biomeMultiplier >= 0f) {
            multiplier = (biomeMultiplier > rtgConfig.MAX_TREE_DENSITY) ? RTG.instance.getConfig().MAX_TREE_DENSITY : biomeMultiplier;
        }

        loopCount = (int)((float)loopCount * multiplier);

        return loopCount;
    }
}