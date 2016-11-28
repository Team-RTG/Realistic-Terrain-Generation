package rtg.world.gen.terrain;

import rtg.api.world.RTGWorld;

/**
 * @author Zeno410
 */
public class FunctionalTerrainBase extends TerrainBase {

    protected HeightEffect height;

    @Override
    public float generateNoise(RTGWorld rtgWorld, int x, int y, float border, float river) {

        return riverized(height.added(rtgWorld, x, y) + base, river);
    }

}
