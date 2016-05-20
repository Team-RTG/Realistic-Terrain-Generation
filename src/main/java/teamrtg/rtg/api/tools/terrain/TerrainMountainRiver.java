package teamrtg.rtg.api.tools.terrain;

import teamrtg.rtg.api.world.RTGWorld;
import teamrtg.rtg.api.world.biome.TerrainBase;

public class TerrainMountainRiver extends TerrainBase {
    public TerrainMountainRiver() {

    }

    @Override
    public float generateNoise(RTGWorld rtgWorld, int x, int y, float biomeWeight, float border, float river) {
        return terrainMountainRiver(x, y, rtgWorld.simplex, rtgWorld.cell, river, 300f, 67f);
    }
}
