package teamrtg.rtg.api.tools.terrain;

import teamrtg.rtg.api.world.RTGWorld;
import teamrtg.rtg.api.world.biome.TerrainBase;

public class TerrainFlatLakes extends TerrainBase {
    public TerrainFlatLakes() {

    }

    public float generateNoise(RTGWorld rtgWorld, int x, int y, float biomeWeight, float border, float river) {
        return terrainFlatLakes(x, y, rtgWorld.simplex, river, 3f, 62f);
    }
}
