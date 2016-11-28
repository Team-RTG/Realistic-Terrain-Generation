package rtg.world.gen.terrain;

import rtg.api.util.noise.CellNoise;
import rtg.api.util.noise.OpenSimplexNoise;

public class TerrainGrasslandFlats extends TerrainBase {

    public TerrainGrasslandFlats() {

    }

    @Override
    public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {

        return terrainGrasslandFlats(x, y, simplex, river, 40f, 25f, 68f);
    }
}
