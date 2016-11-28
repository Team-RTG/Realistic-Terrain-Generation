package rtg.world.gen.terrain;

import rtg.api.world.RTGWorld;

public class TerrainPlains extends TerrainBase {

    public TerrainPlains() {

    }

    @Override
    public float generateNoise(RTGWorld rtgWorld, int x, int y, float border, float river) {

        return terrainPlains(x, y, rtgWorld.simplex, river, 160f, 10f, 60f, 200f, 66f);
    }
}
