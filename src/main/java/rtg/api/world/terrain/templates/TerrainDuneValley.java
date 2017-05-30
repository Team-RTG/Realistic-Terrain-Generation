package rtg.api.world.terrain.templates;

import rtg.api.world.RTGWorld;
import rtg.api.world.terrain.TerrainBase;

public class TerrainDuneValley extends TerrainBase {

    private float valley;

    public TerrainDuneValley(float valleySize) {

        valley = valleySize;
    }

    @Override
    public float generateNoise(RTGWorld rtgWorld, int x, int y, float border, float river) {

        return terrainDuneValley(x, y, rtgWorld.simplex, rtgWorld.cell, river, valley, 65f, 70f);
    }
}
