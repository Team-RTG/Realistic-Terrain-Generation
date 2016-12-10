package rtg.world.gen.terrain;

import rtg.api.world.RTGWorld;

public class TerrainMesa extends TerrainBase {

    public TerrainMesa() {

    }

    @Override
    public float generateNoise(RTGWorld rtgWorld, int x, int y, float border, float river) {

        return terrainMesa(x, y, rtgWorld.simplex, river, border);
    }
}
