package rtg.world.gen.terrain;

import rtg.api.world.RTGWorld;

public class TerrainMountainRiver extends TerrainBase {

    public TerrainMountainRiver() {

    }

    @Override
    public float generateNoise(RTGWorld rtgWorld, int x, int y, float border, float river) {

        return terrainMountainRiver(x, y, rtgWorld.simplex, rtgWorld.cell, river, 300f, 67f);
    }
}
