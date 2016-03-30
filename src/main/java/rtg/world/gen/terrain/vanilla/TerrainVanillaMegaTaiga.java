package rtg.world.gen.terrain.vanilla;

import rtg.util.noise.CellNoise;
import rtg.util.noise.OpenSimplexNoise;
import rtg.world.gen.terrain.TerrainBase;

public class TerrainVanillaMegaTaiga extends TerrainBase {

    public TerrainVanillaMegaTaiga() {

    }

    public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {
        return terrainFlatLakes(x, y, simplex, river, 13f, 66f);
    }
}
