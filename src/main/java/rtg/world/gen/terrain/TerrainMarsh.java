package rtg.world.gen.terrain;

import rtg.util.noise.CellNoise;
import rtg.util.noise.OpenSimplexNoise;


public class TerrainMarsh extends TerrainBase {

    public TerrainMarsh() {

    }

    @Override
    public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {
        return terrainMarsh(x, y, simplex, 62f);
    }
}
