package rtg.world.gen.terrain;

import rtg.api.world.RTGWorld;

public class TerrainVolcano extends TerrainBase {

    public TerrainVolcano() {

    }

    @Override
    public float generateNoise(RTGWorld rtgWorld, int x, int y, float border, float river) {

        return terrainVolcano(x, y, rtgWorld.simplex, rtgWorld.cell, border, 70f);
    }
}
