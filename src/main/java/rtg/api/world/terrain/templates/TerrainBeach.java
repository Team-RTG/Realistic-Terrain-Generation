package rtg.api.world.terrain.templates;

import rtg.api.world.RTGWorld;
import rtg.api.world.terrain.TerrainBase;

public class TerrainBeach extends TerrainBase {

    public TerrainBeach() {

    }

    @Override
    public float generateNoise(RTGWorld rtgWorld, int x, int y, float border, float river) {

        return terrainBeach(x, y, rtgWorld.simplex, river, 180f, 35f, 60f);
    }
}
