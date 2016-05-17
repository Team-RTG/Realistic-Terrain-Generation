package teamrtg.rtg.world.biome.surface.part;

import net.minecraft.world.chunk.ChunkPrimer;
import teamrtg.rtg.world.gen.ChunkProviderRTG;

import java.util.ArrayList;

/**
 * @author topisani
 */
public class SurfacePart {
    protected ArrayList<SurfacePart> subparts;

    public SurfacePart() {
        subparts = new ArrayList<>();
    }

    /**
     * Will first try to call itself on all subparts, and if none of them returned true (or there were none),
     * it will call {@code this.paintSurface()}
     * @param primer
     * @param x
     * @param y
     * @param z
     * @param depth
     * @param noise
     * @return
     */
    public final boolean paintWithSubparts(ChunkPrimer primer, int x, int y, int z, int depth, float[] noise, float river, ChunkProviderRTG provider) {
        if (this.applies(x, y, z, depth, noise, river, provider)) {
            for (SurfacePart part : subparts) {
                if (part.paintWithSubparts(primer, x, y, z, depth, noise, river, provider)) return true;
            }
            return this.paintSurface(primer, x, y, z, depth, noise, river, provider);
        }
        return false;
    }

    /**
     * Places the actual blocks at the coordinates.
     * will only be called if none of the subparts returned true for this function.
     */
    protected boolean paintSurface(ChunkPrimer primer, int x, int y, int z, int depth, float[] noise, float river, ChunkProviderRTG provider) {
        return false;
    }

    /**
     * Does this surface part and its subparts even apply to these coordinates?
     * Defaults to true
     */
    public boolean applies(int x, int y, int z, int depth, float[] noise, float river, ChunkProviderRTG provider) {
        return true;
    }

    public SurfacePart add(SurfacePart part) {
        this.subparts.add(part);
        return this;
    }
}
