package rtg.world.gen.terrain;

import rtg.util.noise.CellNoise;
import rtg.util.noise.OpenSimplexNoise;

public class TerrainMountainRiver extends TerrainBase {
    public TerrainMountainRiver() {

    }

    @Override
    public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {
        return terrainMountainRiver(x, y, simplex, cell, river, 300f, 67f);
    }
}
