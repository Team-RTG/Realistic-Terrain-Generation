package rtg.world.gen.terrain;

import rtg.util.noise.CellNoise;
import rtg.util.noise.OpenSimplexNoise;

public class TerrainVolcano extends TerrainBase {

    public TerrainVolcano() {

    }

    @Override
    public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {
        return terrainVolcano(x, y, simplex, cell, border, 70f);
    }
}
