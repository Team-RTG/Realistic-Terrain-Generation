package teamrtg.rtg.world.biome.surface.part;

import teamrtg.rtg.util.noise.RTGNoise;

/**
 * @author topisani
 */
public class HeightSelector extends SurfacePart {
    private final int min;
    private final int max;
    private RTGNoise minNoise = RTGNoise.EMPTY;
    private RTGNoise maxNoise = RTGNoise.EMPTY;

    public HeightSelector(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public HeightSelector setMinNoise(RTGNoise minNoise) {
        this.minNoise = minNoise;
        return this;
    }

    public HeightSelector setMaxNoise(RTGNoise maxNoise) {
        this.maxNoise = maxNoise;
        return this;
    }

    @Override
    public boolean applies(int x, int y, int z, int depth, float[] noise, float river) {
        return y >= (min + minNoise.getAt(x, y, z)) && y <= (max + maxNoise.getAt(x, y, z));
    }
}
