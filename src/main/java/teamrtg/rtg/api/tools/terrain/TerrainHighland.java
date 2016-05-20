package teamrtg.rtg.api.tools.terrain;

import teamrtg.rtg.api.world.RTGWorld;
import teamrtg.rtg.api.world.biome.TerrainBase;

public class TerrainHighland extends TerrainBase {
    private float start;
    private float height;
    private float base;
    private float width;

    public TerrainHighland(float hillStart, float landHeight, float baseHeight, float hillWidth) {
        start = hillStart;
        height = landHeight;
        base = baseHeight;
        width = hillWidth;
    }

    @Override
    public float generateNoise(RTGWorld rtgWorld, int x, int y, float biomeWeight, float border, float river) {
        return terrainHighland(x, y, rtgWorld.simplex, rtgWorld.cell, river, start, width, height, 0f);
    }
}
