package teamrtg.rtg.world.biome.surface.part;

import teamrtg.rtg.util.noise.RTGNoise;

/**
 * Applies if depth is inside the given range
 * Allows for noise to be added to the min and max values separately.
 * @author topisani
 */
public class DepthSelector extends SurfacePart {

    private final int min;
    private final int max;
    private RTGNoise minNoise = RTGNoise.EMPTY;
    private RTGNoise maxNoise = RTGNoise.EMPTY;

    public DepthSelector(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public DepthSelector setMinNoise(RTGNoise minNoise) {
        this.minNoise = minNoise;
        return this;
    }

    public DepthSelector setMaxNoise(RTGNoise maxNoise) {
        this.maxNoise = maxNoise;
        return this;
    }

    @Override
    public boolean applies(int x, int y, int z, int depth, float[] noise, float river) {
        return depth >= (min + minNoise.getFloatAt(x, y, z)) && depth <= (max + maxNoise.getFloatAt(x, y, z));
    }
}
