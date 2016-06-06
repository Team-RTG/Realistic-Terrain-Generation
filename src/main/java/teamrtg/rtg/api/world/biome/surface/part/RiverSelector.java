package teamrtg.rtg.api.world.biome.surface.part;

import teamrtg.rtg.api.util.noise.IFloatAt;
import teamrtg.rtg.api.world.RTGWorld;

/**
 * @author topisani
 */
public class RiverSelector extends SurfacePart {

    private IFloatAt min;

    public RiverSelector(IFloatAt min) {
        this.min = min;
    }

    public RiverSelector(float min) {
        this.min = (x, y, z, rtgWorld) -> min;
    }

    @Override
    public boolean applies(int x, int y, int z, int depth, float[] noise, float river, RTGWorld rtgWorld) {
        return river >= min.getAt(x, y, z, rtgWorld);
    }
}
