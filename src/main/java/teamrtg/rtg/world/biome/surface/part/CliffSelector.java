package teamrtg.rtg.world.biome.surface.part;

import teamrtg.rtg.util.math.CliffCalculator;

import static teamrtg.rtg.util.math.MathUtils.globalToLocal;

/**
 * @author topisani
 */
public class CliffSelector extends SurfacePart {

    public final float minCliff;

    public CliffSelector(float minCliff) {
        this.minCliff = minCliff;
    }

    @Override
    public boolean applies(int x, int y, int z, int depth, float[] noise, float river) {
        return CliffCalculator.calc(globalToLocal(x), globalToLocal(z), noise) > this.minCliff;
    }
}
