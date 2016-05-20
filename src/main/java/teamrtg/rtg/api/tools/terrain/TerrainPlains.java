package teamrtg.rtg.api.tools.terrain;

import teamrtg.rtg.api.world.RTGWorld;
import teamrtg.rtg.api.world.biome.TerrainBase;

public class TerrainPlains extends TerrainBase {

    public TerrainPlains() {

    }

    @Override
    public float generateNoise(RTGWorld rtgWorld, int x, int y, float biomeWeight, float border, float river) {
        return terrainPlains(x, y, rtgWorld.simplex, river, 160f, 10f, 60f, 200f, 66f);
    }
}
