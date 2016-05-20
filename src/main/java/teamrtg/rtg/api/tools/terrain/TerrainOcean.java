package teamrtg.rtg.api.tools.terrain;

import teamrtg.rtg.api.world.RTGWorld;
import teamrtg.rtg.api.world.biome.TerrainBase;

public class TerrainOcean extends TerrainBase {

    public TerrainOcean() {

    }

    @Override
    public float generateNoise(RTGWorld rtgWorld, int x, int y, float biomeWeight, float border, float river) {
        float h = rtgWorld.simplex.noise2(x / 300f, y / 300f) * 40f * river;
        h = h > 3f ? 3f : h;
        h += rtgWorld.simplex.noise2(x / 50f, y / 50f) * (12f - h) * 0.4f;
        h += rtgWorld.simplex.noise2(x / 15f, y / 15f) * (12f - h) * 0.15f;

        float floNoise = 50f + h;
        floNoise = floNoise < minimumOceanFloor ? minimumOceanFloor : floNoise;

        return floNoise;
    }
}
