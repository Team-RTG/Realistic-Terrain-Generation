package teamrtg.rtg.world.biome.surface.part;

import teamrtg.rtg.world.gen.ChunkProviderRTG;

import java.util.ArrayList;
import java.util.List;

/**
 * Takes a few parts as arguments and applies if any of them apply
 * @author topisani
 */
public class OrSelector extends SurfacePart {

    public List<SurfacePart> parts = new ArrayList<>();

    public OrSelector or(SurfacePart part) {
        parts.add(part);
        return this;
    }

    @Override
    public boolean applies(int x, int y, int z, int depth, float[] noise, float river, ChunkProviderRTG provider) {
        for (SurfacePart part : parts) {
            if (part.applies(x, y, z, depth, noise, river, provider)) return true;
        }
        return false;
    }
}
