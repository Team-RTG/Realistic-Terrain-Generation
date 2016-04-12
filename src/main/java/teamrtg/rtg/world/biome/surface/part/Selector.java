package teamrtg.rtg.world.biome.surface.part;

import teamrtg.rtg.util.noise.IBoolAt;

/**
 * @author topisani
 */
public class Selector extends SurfacePart {

    private IBoolAt boolAt;

    public Selector(IBoolAt boolAt) {
        this.boolAt = boolAt;
    }

    @Override
    public boolean applies(int x, int y, int z, int depth, float[] noise, float river) {
        return boolAt.getBoolAt(x, y, z);
    }
}
