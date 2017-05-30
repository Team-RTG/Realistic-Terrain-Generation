package rtg.api.world.terrain.templates;

import rtg.api.world.RTGWorld;
import rtg.api.world.terrain.TerrainBase;

public class TerrainFlatLakes extends TerrainBase {

    public TerrainFlatLakes() {

    }

    @Override
    public float generateNoise(RTGWorld rtgWorld, int x, int y, float border, float river) {

        return terrainFlatLakes(x, y, rtgWorld.simplex, river, 3f, 62f);
    }
}
