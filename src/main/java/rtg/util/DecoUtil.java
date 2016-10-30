package rtg.util;

import rtg.api.biome.BiomeConfig;
import rtg.config.rtg.ConfigRTG;
import rtg.world.biome.deco.DecoBase;
import rtg.world.biome.realistic.RealisticBiomeBase;

public class DecoUtil {

    private DecoBase deco;

    public DecoUtil(DecoBase deco) {

        this.deco = deco;
    }

    public int calculateLoopCountFromTreeDensity(int loopCount, RealisticBiomeBase biome) {

        float multiplier = ConfigRTG.treeDensityMultiplier;
        float biomeMultiplier = biome.config._float(BiomeConfig.treeDensityMultiplierId);

        if (biomeMultiplier >= 0f) {
            multiplier = (biomeMultiplier > ConfigRTG.MAX_TREE_DENSITY) ? ConfigRTG.MAX_TREE_DENSITY : biomeMultiplier;
        }

        loopCount = (int)((float)loopCount * multiplier);

        return loopCount;
    }
}