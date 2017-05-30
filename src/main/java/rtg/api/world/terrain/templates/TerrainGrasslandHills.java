package rtg.api.world.terrain.templates;

import rtg.api.world.RTGWorld;
import rtg.api.world.terrain.TerrainBase;


public class TerrainGrasslandHills extends TerrainBase {

    private float hHeight;
    private float hWidth;
    private float vHeight;
    private float vWidth;
    private float lHeight;
    private float lWidth;
    private float bHeight;

    public TerrainGrasslandHills(float hillHeight, float hillWidth, float varHeight, float varWidth, float lakeHeight, float lakeWidth, float baseHeight) {

        hHeight = hillHeight;
        hWidth = hillWidth;

        vHeight = varHeight;
        vWidth = varWidth;

        lHeight = lakeHeight;
        lWidth = lakeWidth;

        bHeight = baseHeight;
    }

    @Override
    public float generateNoise(RTGWorld rtgWorld, int x, int y, float border, float river) {

        return terrainGrasslandHills(x, y, rtgWorld.simplex, rtgWorld.cell, river, vWidth, vHeight, hWidth, hHeight, bHeight);
    }
}
