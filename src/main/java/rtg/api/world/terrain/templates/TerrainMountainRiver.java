package rtg.api.world.terrain.templates;

import rtg.api.world.IRTGWorld;
import rtg.api.world.terrain.TerrainBase;

public class TerrainMountainRiver extends TerrainBase {

    public TerrainMountainRiver() {

    }

    @Override
    public float generateNoise(IRTGWorld rtgWorld, int x, int y, float border, float river) {

        return terrainMountainRiver(x, y, rtgWorld.simplex(), rtgWorld.cell(), river, 300f, 67f);
    }
}
