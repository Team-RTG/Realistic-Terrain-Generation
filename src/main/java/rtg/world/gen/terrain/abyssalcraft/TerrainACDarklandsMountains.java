package rtg.world.gen.terrain.abyssalcraft;

import rtg.util.noise.CellNoise;
import rtg.util.noise.OpenSimplexNoise;
import rtg.world.gen.terrain.TerrainBase;

public class TerrainACDarklandsMountains extends TerrainBase {

    public TerrainACDarklandsMountains() {

    }

    @Override
    public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {
        return terrainMountainRiver(x, y, simplex, cell, river, 300f, 67f);
    }
}
