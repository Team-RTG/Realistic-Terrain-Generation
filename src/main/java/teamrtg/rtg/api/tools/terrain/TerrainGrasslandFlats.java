package teamrtg.rtg.api.tools.terrain;

import teamrtg.rtg.api.world.RTGWorld;
import teamrtg.rtg.api.world.biome.TerrainBase;

public class TerrainGrasslandFlats extends TerrainBase {

    public TerrainGrasslandFlats() {

    }

    @Override
    public float generateNoise(RTGWorld rtgWorld, int x, int y, float biomeWeight, float border, float river) {
        return terrainGrasslandFlats(x, y, rtgWorld.simplex, river, 40f, 25f, 68f);
    }
}
