package teamrtg.rtg.api.tools.terrain;

import teamrtg.rtg.api.world.RTGWorld;
import teamrtg.rtg.api.world.biome.TerrainBase;

public class TerrainBeach extends TerrainBase {
    public TerrainBeach() {

    }

    @Override
    public float generateNoise(RTGWorld rtgWorld, int x, int y, float biomeWeight, float border, float river) {
        return terrainBeach(x, y, rtgWorld.simplex, river, 180f, 35f, 60f);
    }
}
