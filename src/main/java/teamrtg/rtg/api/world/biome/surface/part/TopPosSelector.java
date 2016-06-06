package teamrtg.rtg.api.world.biome.surface.part;

import teamrtg.rtg.api.world.RTGWorld;

/**
 * @author topisani
 *         Select based on the height of the top block at these coords
 */
public class TopPosSelector extends SurfacePart {

    private final int min;
    private final int max;

    public TopPosSelector(int min, int max) {
        this.min = min;
        this.max = max;
    }

    @Override
    public boolean applies(int x, int y, int z, int depth, float[] noise, float river, RTGWorld rtgWorld) {
        return (y + depth) >= min && (y + depth) <= max;
    }

}
