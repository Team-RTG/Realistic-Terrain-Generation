package rtg.world.gen.terrain;

import rtg.util.noise.CellNoise;
import rtg.util.noise.OpenSimplexNoise;

public class TerrainForest extends TerrainBase {

    public TerrainForest() {

    }

    @Override
    public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {
        return terrainForest(x, y, simplex, river, 70f);
    }
}
