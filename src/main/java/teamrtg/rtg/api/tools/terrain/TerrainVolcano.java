package teamrtg.rtg.api.tools.terrain;

import teamrtg.rtg.api.world.RTGWorld;
import teamrtg.rtg.api.world.biome.TerrainBase;

public class TerrainVolcano extends TerrainBase {

    public TerrainVolcano() {

    }

    @Override
    public float generateNoise(RTGWorld rtgWorld, int x, int y, float biomeWeight, float border, float river) {
        return terrainVolcano(x, y, rtgWorld.simplex, rtgWorld.cell, biomeWeight, 70f);
    }
}
