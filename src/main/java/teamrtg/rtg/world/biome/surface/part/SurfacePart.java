package teamrtg.rtg.world.biome.surface.part;

import net.minecraft.world.chunk.ChunkPrimer;
import teamrtg.rtg.api.biome.RealisticBiomeBase;

import java.util.ArrayList;

/**
 * @author topisani
 */
public abstract class SurfacePart {
    protected ArrayList<SurfacePart> subparts;
    protected final RealisticBiomeBase biome;

    public SurfacePart(RealisticBiomeBase biome) {
        subparts = new ArrayList<>();
        this.biome = biome;
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
    public final boolean paintWithSubparts(ChunkPrimer primer, int x, int y, int z, int depth, float[] noise, float river) {
        if (this.applies(x, y, z, depth, noise, river)) {
            for (SurfacePart part : subparts) {
                if (part.paintWithSubparts(primer, x, y, z, depth, noise, river)) return true;
            }
            this.paintSurface(primer, x, y, z, depth, noise, river);
            return true;
        }
        return false;
    }

    /**
     * Places the actual blocks at the coordinates.
     * will only be called if none of the subparts returned true for this function.
     */
    protected void paintSurface(ChunkPrimer primer, int x, int y, int z, int depth, float[] noise, float river) {}

    /**
     * Does this surface part and its subparts even apply to these coordinates?
     * Defaults to true
     */
    public boolean applies(int x, int y, int z, int depth, float[] noise, float river) {
        return true;
    }

    public SurfacePart addSubPart(SurfacePart part) {
        this.subparts.add(part);
        return this;
    }
}
