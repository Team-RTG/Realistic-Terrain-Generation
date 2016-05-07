package teamrtg.rtg.world.biome.surface.part;

import teamrtg.rtg.util.noise.IFloatAt;

/**
 * Applies if depth is inside the given range
 * Allows for noise to be added to the min and max values separately.
 * @author topisani
 */
public class DepthSelector extends SurfacePart {

    private final int min;
    private final int max;
    private IFloatAt minNoise = IFloatAt.ZERO;
    private IFloatAt maxNoise = IFloatAt.ZERO;

    public DepthSelector(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public DepthSelector setMinNoise(IFloatAt minNoise) {
        this.minNoise = minNoise;
        return this;
    }

    public DepthSelector setMaxNoise(IFloatAt maxNoise) {
        this.maxNoise = maxNoise;
        return this;
    }

    @Override
    public boolean applies(int x, int y, int z, int depth, float[] noise, float river) {
        return depth >= (min + minNoise.getAt(x, y, z)) && depth <= (max + maxNoise.getAt(x, y, z));
    }
}
