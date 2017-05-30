package rtg.api.world.terrain.templates;

import rtg.api.world.RTGWorld;
import rtg.api.world.terrain.TerrainBase;

public class TerrainMesa extends TerrainBase {

    public TerrainMesa() {

    }

    @Override
    public float generateNoise(RTGWorld rtgWorld, int x, int y, float border, float river) {

        return terrainMesa(x, y, rtgWorld.simplex, river, border);
    }
}
