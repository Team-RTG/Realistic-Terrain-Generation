package teamrtg.rtg.world.biome.surface.part;

import teamrtg.rtg.util.noise.IFloatAt;
import teamrtg.rtg.world.gen.ChunkProviderRTG;

/**
 * @author topisani
 */
public class RiverSelector extends SurfacePart {

    private IFloatAt min;

    public RiverSelector(IFloatAt min) {
        this.min = min;
    }

    public RiverSelector(float min) {
        this.min = (x, y, z, provider) -> min;
    }

    @Override
    public boolean applies(int x, int y, int z, int depth, float[] noise, float river, ChunkProviderRTG provider) {
        return river >= min.getAt(x, y, z, provider);
    }
}
