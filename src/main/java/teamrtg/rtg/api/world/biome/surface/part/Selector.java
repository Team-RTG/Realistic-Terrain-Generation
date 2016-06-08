package teamrtg.rtg.api.world.biome.surface.part;

import teamrtg.rtg.api.util.noise.IBoolAt;
import teamrtg.rtg.api.world.RTGWorld;

/**
 * @author topisani
 */
public class Selector extends SurfacePart {

    private IBoolAt boolAt;

    public Selector(IBoolAt boolAt) {
        this.boolAt = boolAt;
    }

    @Override
    public boolean applies(int x, int y, int z, int depth, float[] noise, float river, RTGWorld rtgWorld) {
        return boolAt.getAt(x, y, z, rtgWorld);
    }
}
