package rtg.api.world.terrain;

import rtg.api.world.IRTGWorld;
import rtg.api.world.terrain.heighteffect.HeightEffect;

/**
 * @author Zeno410
 */
public class FunctionalTerrainBase extends TerrainBase {

    protected HeightEffect height;

    @Override
    public float generateNoise(IRTGWorld rtgWorld, int x, int y, float border, float river) {

        return riverized(height.added(rtgWorld, x, y) + base, river);
    }

}
