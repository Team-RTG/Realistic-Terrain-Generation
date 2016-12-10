package rtg.world.gen.terrain;

import rtg.api.world.RTGWorld;

public class TerrainFlatLakes extends TerrainBase {

    public TerrainFlatLakes() {

    }

    @Override
    public float generateNoise(RTGWorld rtgWorld, int x, int y, float border, float river) {

        return terrainFlatLakes(x, y, rtgWorld.simplex, river, 3f, 62f);
    }
}
