package rtg.api.world.terrain.templates;

import rtg.api.world.IRTGWorld;
import rtg.api.world.terrain.TerrainBase;

public class TerrainForest extends TerrainBase {

    public TerrainForest() {

    }

    @Override
    public float generateNoise(IRTGWorld rtgWorld, int x, int y, float border, float river) {

        return terrainForest(x, y, rtgWorld.simplex(), river, 70f);
    }
}
