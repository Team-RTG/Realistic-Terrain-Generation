package rtg.world.gen.terrain;

import rtg.api.util.noise.CellNoise;
import rtg.api.util.noise.OpenSimplexNoise;

public class TerrainFlatLakes extends TerrainBase {

    public TerrainFlatLakes() {

    }

    public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {

        return terrainFlatLakes(x, y, simplex, river, 3f, 62f);
    }
}
