package rtg.world.gen.terrain.agriculturalrevolution;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.terrain.TerrainBase;

public class TerrainARDeepReef extends TerrainBase {

    public TerrainARDeepReef() {

    }

    @Override
    public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {

        return terrainOcean(x, y, simplex, river, 40f);
    }
}
