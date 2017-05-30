package rtg.api.world.terrain.templates;

import rtg.api.world.RTGWorld;
import rtg.api.world.terrain.TerrainBase;


public class TerrainMarsh extends TerrainBase {

    public TerrainMarsh() {

    }

    @Override
    public float generateNoise(RTGWorld rtgWorld, int x, int y, float border, float river) {

        return terrainMarsh(x, y, rtgWorld.simplex, 62f,river);
    }
}
