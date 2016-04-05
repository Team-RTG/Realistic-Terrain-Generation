
package rtg.world.gen.terrain;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;

/**
 *
 * @author Zeno410
 */
public class FunctionalTerrainBase extends TerrainBase {

    protected HeightEffect height;

    @Override
    public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river)
    {
        return riverized(height.added(simplex, cell, x, y) + base, river);
    }

}
