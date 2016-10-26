package rtg.util;

import rtg.world.biome.deco.DecoBase;
import rtg.world.biome.realistic.RealisticBiomeBase;

public class DecoUtil {

    private DecoBase deco;

    public DecoUtil(DecoBase deco) {

        this.deco = deco;
    }

    public int calculateLoopCount(int loopCount, float globalConfigValue, RealisticBiomeBase biome, String biomeConfigId, float maxBiomeMultiplier) {

        float multiplier = globalConfigValue;
        String biomeMultiplier = biome.config._string(biomeConfigId);

        if (!biomeMultiplier.isEmpty() && biomeMultiplier != "") {

            multiplier = Float.valueOf(biomeMultiplier);
            multiplier = (multiplier > maxBiomeMultiplier) ? maxBiomeMultiplier : (multiplier < 0f) ? 0f : multiplier;

        }
        loopCount = (int)((float)loopCount * multiplier);

        return loopCount;
    }
}