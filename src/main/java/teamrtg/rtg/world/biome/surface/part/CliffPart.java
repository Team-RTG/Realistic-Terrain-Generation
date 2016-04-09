package teamrtg.rtg.world.biome.surface.part;

import teamrtg.rtg.api.biome.RealisticBiomeBase;
import teamrtg.rtg.util.math.CliffCalculator;

import static teamrtg.rtg.util.math.MathUtils.globalToLocal;

/**
 * @author topisani
 */
public class CliffPart extends SurfacePartBase {

    public final float minCliff;

    public CliffPart(float minCliff) {
        this.minCliff = minCliff;
    }

    @Override
    public boolean applies(RealisticBiomeBase biome, int x, int y, int z, int depth, float[] noise) {
        return CliffCalculator.calc(globalToLocal(x), globalToLocal(z), noise) > this.minCliff;
    }
}
