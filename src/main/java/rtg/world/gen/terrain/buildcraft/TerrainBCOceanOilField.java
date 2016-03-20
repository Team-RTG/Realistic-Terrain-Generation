package rtg.world.gen.terrain.buildcraft;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.terrain.TerrainBase;

public class TerrainBCOceanOilField extends TerrainBase {

    public TerrainBCOceanOilField() {

    }

    @Override
    public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {
        return terrainOcean(x, y, simplex, river, 50f);
    }
}
