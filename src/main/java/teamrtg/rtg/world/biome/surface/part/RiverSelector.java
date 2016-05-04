package teamrtg.rtg.world.biome.surface.part;

import teamrtg.rtg.util.noise.IFloatAt;

/**
 * @author topisani
 */
public class RiverSelector extends SurfacePart {

    private IFloatAt min;

    public RiverSelector(IFloatAt min) {
        this.min = min;
    }

    public RiverSelector(float min) {
        this.min = (x, y, z) -> min;
    }

    @Override
    public boolean applies(int x, int y, int z, int depth, float[] noise, float river) {
        return river >= min.getAt(x, y, z);
    }
}
