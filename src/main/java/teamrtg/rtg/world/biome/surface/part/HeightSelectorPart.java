package teamrtg.rtg.world.biome.surface.part;

import teamrtg.rtg.api.biome.RealisticBiomeBase;
import teamrtg.rtg.util.noise.RTGNoise;

/**
 * @author topisani
 */
public class HeightSelectorPart extends SurfacePartBase {
    private final int min;
    private final int max;
    private RTGNoise minNoise = RTGNoise.EMPTY;
    private RTGNoise maxNoise = RTGNoise.EMPTY;

    public HeightSelectorPart(RealisticBiomeBase biome, int min, int max) {
        super(biome);
        this.min = min;
        this.max = max;
    }

    public HeightSelectorPart setMinNoise(RTGNoise minNoise) {
        this.minNoise = minNoise;
        return this;
    }

    public HeightSelectorPart setMaxNoise(RTGNoise maxNoise) {
        this.maxNoise = maxNoise;
        return this;
    }

    @Override
    public boolean applies(int x, int y, int z, int depth, float[] noise, float river) {
        return y >= (min + minNoise.getAt(x, y, z)) && y <= (max + maxNoise.getAt(x, y, z));
    }
}
