package rtg.world.gen.terrain;

import rtg.api.world.RTGWorld;

public class TerrainGrasslandMountains extends TerrainBase {

    public TerrainGrasslandMountains() {

    }

    @Override
    public float generateNoise(RTGWorld rtgWorld, int x, int y, float border, float river) {

        return terrainGrasslandMountains(x, y, rtgWorld.simplex, rtgWorld.cell, river, 7f, 120f, 68f);
    }
}
