package teamrtg.rtg.api.tools.terrain;

import teamrtg.rtg.api.world.RTGWorld;
import teamrtg.rtg.api.world.biome.TerrainBase;


public class TerrainMarsh extends TerrainBase {

    public TerrainMarsh() {

    }

    @Override
    public float generateNoise(RTGWorld rtgWorld, int x, int y, float biomeWeight, float border, float river) {
        return terrainMarsh(x, y, rtgWorld.simplex, 62f);
    }
}
