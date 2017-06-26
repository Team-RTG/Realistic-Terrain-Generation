package rtg.api.world.terrain.templates;

import rtg.api.world.IRTGWorld;
import rtg.api.world.terrain.TerrainBase;

public class TerrainGrasslandFlats extends TerrainBase {

    public TerrainGrasslandFlats() {

    }

    @Override
    public float generateNoise(IRTGWorld rtgWorld, int x, int y, float border, float river) {

        return terrainGrasslandFlats(x, y, rtgWorld.simplex(), river, 40f, 25f, 68f);
    }
}
