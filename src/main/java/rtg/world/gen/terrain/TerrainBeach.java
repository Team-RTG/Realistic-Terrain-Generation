package rtg.world.gen.terrain;

import rtg.api.world.RTGWorld;

public class TerrainBeach extends TerrainBase {

    public TerrainBeach() {

    }

    @Override
    public float generateNoise(RTGWorld rtgWorld, int x, int y, float border, float river) {

        return terrainBeach(x, y, rtgWorld.simplex, river, 180f, 35f, 60f);
    }
}
