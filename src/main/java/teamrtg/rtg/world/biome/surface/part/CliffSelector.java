package teamrtg.rtg.world.biome.surface.part;

import teamrtg.rtg.util.math.CliffCalculator;
import teamrtg.rtg.util.noise.IFloatAt;

import static teamrtg.rtg.util.math.MathUtils.globalToLocal;

/**
 * @author topisani
 */
public class CliffSelector extends SurfacePart {

    public final IFloatAt minCliff;

    public CliffSelector(float minCliff) {
        this.minCliff = (x, y, z) -> minCliff;
    }

    public CliffSelector(IFloatAt minCliff) {
        this.minCliff = minCliff;
    }

    @Override
    public boolean applies(int x, int y, int z, int depth, float[] noise, float river) {
        return CliffCalculator.calc(globalToLocal(x), globalToLocal(z), noise) > this.minCliff.getFloatAt(x, y, z);
    }
}
