package rtg.api.world.terrain.templates;

import rtg.api.world.RTGWorld;
import rtg.api.world.terrain.TerrainBase;

public class TerrainGrasslandMountains extends TerrainBase {

    public TerrainGrasslandMountains() {

    }

    @Override
    public float generateNoise(RTGWorld rtgWorld, int x, int y, float border, float river) {

        return terrainGrasslandMountains(x, y, rtgWorld.simplex, rtgWorld.cell, river, 7f, 120f, 68f);
    }
}
