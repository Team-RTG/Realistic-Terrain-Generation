package teamrtg.rtg.api.tools.terrain;

import teamrtg.rtg.api.world.RTGWorld;
import teamrtg.rtg.api.world.biome.TerrainBase;

public class TerrainGrasslandMountains extends TerrainBase {
    public TerrainGrasslandMountains() {
    }

    @Override
    public float generateNoise(RTGWorld rtgWorld, int x, int y, float biomeWeight, float border, float river) {
        return terrainGrasslandMountains(x, y, rtgWorld.simplex, rtgWorld.cell, river, 7f, 120f, 68f);
    }
}
