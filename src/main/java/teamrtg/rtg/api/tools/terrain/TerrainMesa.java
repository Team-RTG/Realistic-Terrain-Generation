package teamrtg.rtg.api.tools.terrain;

import teamrtg.rtg.api.world.RTGWorld;
import teamrtg.rtg.api.world.biome.TerrainBase;

public class TerrainMesa extends TerrainBase {
    public TerrainMesa() {
    }

    @Override
    public float generateNoise(RTGWorld rtgWorld, int x, int y, float biomeWeight, float border, float river) {
        return terrainMesa(x, y, rtgWorld.simplex, river, biomeWeight);
    }
}
