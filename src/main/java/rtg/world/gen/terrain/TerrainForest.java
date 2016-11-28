package rtg.world.gen.terrain;

import rtg.api.world.RTGWorld;

public class TerrainForest extends TerrainBase {

    public TerrainForest() {

    }

    @Override
    public float generateNoise(RTGWorld rtgWorld, int x, int y, float border, float river) {

        return terrainForest(x, y, rtgWorld.simplex, river, 70f);
    }
}
