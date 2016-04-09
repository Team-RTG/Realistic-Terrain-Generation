package teamrtg.rtg.world.biome.surface.part;

import java.util.ArrayList;

/**
 * @author topisani
 */
public abstract class SurfacePartBase {
    public ArrayList<SurfacePartBase> subparts;

    public SurfacePartBase() {
        subparts = new ArrayList<>();
    }

    public final boolean paintWithSubparts(int x, int y, int z, int depth, float[] noise) {
        if (this.applies(x, y, z, depth, noise)) {
            for (SurfacePartBase part : subparts) {
                if (part.paintWithSubparts(x, y, z, depth, noise)) return true;
            }
            return this.paintSurface(x, y, z, depth, noise);
        }
        return false;
    }

    public boolean paintSurface(int x, int y, int z, int depth, float[] noise) {
        return false;
    }

    public boolean applies(int x, int y, int z, int depth, float[] noise) {
        return true;
    }
}
