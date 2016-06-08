package teamrtg.rtg.api.world.biome.surface.part;

import teamrtg.rtg.api.world.RTGWorld;

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
    public boolean applies(int x, int y, int z, int depth, float[] noise, float river, RTGWorld rtgWorld) {
        for (SurfacePart part : parts) {
            if (part.applies(x, y, z, depth, noise, river, rtgWorld)) return true;
        }
        return false;
    }
}
