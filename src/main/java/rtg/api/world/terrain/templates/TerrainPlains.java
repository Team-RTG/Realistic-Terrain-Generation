package rtg.api.world.terrain.templates;

import rtg.api.world.RTGWorld;
import rtg.api.world.terrain.TerrainBase;

public class TerrainPlains extends TerrainBase {

    public TerrainPlains() {

    }

    @Override
    public float generateNoise(RTGWorld rtgWorld, int x, int y, float border, float river) {

        return terrainPlains(x, y, rtgWorld.simplex, river, 160f, 10f, 60f, 200f, 66f);
    }
}
