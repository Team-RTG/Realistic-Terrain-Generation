package teamrtg.rtg.world.biome.surface.part;

import teamrtg.rtg.api.biome.RealisticBiomeBase;
import teamrtg.rtg.util.noise.RTGNoise;

/**
 * Applies if depth is inside the given range
 * Allows for noise to be added to the min and max values separately.
 * @author topisani
 */
public class DepthSelectorPart extends SurfacePartBase {

    private final int min;
    private final int max;
    private RTGNoise minNoise = RTGNoise.EMPTY;
    private RTGNoise maxNoise = RTGNoise.EMPTY;

    public DepthSelectorPart(RealisticBiomeBase biome, int min, int max) {
        super(biome);
        this.min = min;
        this.max = max;
    }

    public DepthSelectorPart setMinNoise(RTGNoise minNoise) {
        this.minNoise = minNoise;
        return this;
    }

    public DepthSelectorPart setMaxNoise(RTGNoise maxNoise) {
        this.maxNoise = maxNoise;
        return this;
    }

    @Override
    public boolean applies(int x, int y, int z, int depth, float[] noise, float river) {
        return depth >= (min + minNoise.getAt(x, y, z)) && depth <= (max + maxNoise.getAt(x, y, z));
    }
}
