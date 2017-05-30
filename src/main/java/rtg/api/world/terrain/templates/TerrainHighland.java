package rtg.api.world.terrain.templates;

import rtg.api.world.RTGWorld;
import rtg.api.world.terrain.TerrainBase;

public class TerrainHighland extends TerrainBase {

    private float start;
    private float height;
    private float width;

    public TerrainHighland(float hillStart, float landHeight, float baseHeight, float hillWidth) {

        start = hillStart;
        height = landHeight;
        base = baseHeight;
        width = hillWidth;
    }

    @Override
    public float generateNoise(RTGWorld rtgWorld, int x, int y, float border, float river) {

        return terrainHighland(x, y, rtgWorld.simplex, rtgWorld.cell, river, start, width, height, base - 62f);
    }
}
