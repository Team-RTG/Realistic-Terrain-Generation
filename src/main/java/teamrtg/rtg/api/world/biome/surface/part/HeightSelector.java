package teamrtg.rtg.api.world.biome.surface.part;

import teamrtg.rtg.api.util.noise.IFloatAt;
import teamrtg.rtg.api.world.RTGWorld;

/**
 * @author topisani
 */
public class HeightSelector extends SurfacePart {
    private final int min;
    private final int max;
    private IFloatAt minNoise = IFloatAt.ZERO;
    private IFloatAt maxNoise = IFloatAt.ZERO;

    public HeightSelector(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public HeightSelector setMinNoise(IFloatAt minNoise) {
        this.minNoise = minNoise;
        return this;
    }

    public HeightSelector setMaxNoise(IFloatAt maxNoise) {
        this.maxNoise = maxNoise;
        return this;
    }

    @Override
    public boolean applies(int x, int y, int z, int depth, float[] noise, float river, RTGWorld rtgWorld) {
        return y >= (min + minNoise.getAt(x, y, z, rtgWorld)) && y <= (max + maxNoise.getAt(x, y, z, rtgWorld));
    }
}
