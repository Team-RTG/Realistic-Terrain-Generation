package teamrtg.rtg.api.world.biome.surface.part;

import teamrtg.rtg.api.util.math.CliffCalculator;
import teamrtg.rtg.api.util.noise.IFloatAt;
import teamrtg.rtg.api.world.RTGWorld;

import static teamrtg.rtg.api.util.math.MathUtils.globalToLocal;

/**
 * @author topisani
 */
public class CliffSelector extends SurfacePart {

    public final IFloatAt minCliff;

    public CliffSelector(float minCliff) {
        this.minCliff = (x, y, z, rtgWorld) -> minCliff;
    }

    public CliffSelector(IFloatAt minCliff) {
        this.minCliff = minCliff;
    }

    @Override
    public boolean applies(int x, int y, int z, int depth, float[] noise, float river, RTGWorld rtgWorld) {
        return CliffCalculator.calc(globalToLocal(x), globalToLocal(z), noise) > this.minCliff.getAt(x, y, z, rtgWorld);
    }
}
