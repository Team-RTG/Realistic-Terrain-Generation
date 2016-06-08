package teamrtg.rtg.api.tools.terrain;

import teamrtg.rtg.api.world.RTGWorld;
import teamrtg.rtg.api.world.biome.TerrainBase;

public class TerrainDuneValley extends TerrainBase {
    private float valley;

    public TerrainDuneValley(float valleySize) {
        valley = valleySize;
    }

    @Override
    public float generateNoise(RTGWorld rtgWorld, int x, int y, float biomeWeight, float border, float river) {
        return terrainDuneValley(x, y, rtgWorld.simplex, rtgWorld.cell, river, valley, 65f, 70f);
    }
}
