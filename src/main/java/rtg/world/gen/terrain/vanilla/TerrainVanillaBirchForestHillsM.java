package rtg.world.gen.terrain.vanilla;

import rtg.util.noise.CellNoise;
import rtg.util.noise.OpenSimplexNoise;
import rtg.world.gen.terrain.TerrainBase;

public class TerrainVanillaBirchForestHillsM extends TerrainBase {
    private float hillStrength = 65f;

    public TerrainVanillaBirchForestHillsM() {
    }

    @Override
    public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {
        return terrainHighland(x, y, simplex, cell, river, 10f, 68f, hillStrength, 10f);
    }
}
