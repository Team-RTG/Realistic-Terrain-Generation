package rtg.world.gen.terrain.vanilla;

import rtg.util.noise.CellNoise;
import rtg.util.noise.OpenSimplexNoise;
import rtg.world.gen.terrain.TerrainBase;

public class TerrainVanillaDeepOcean extends TerrainBase {

    public TerrainVanillaDeepOcean() {

    }

    @Override
    public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {
        return terrainOcean(x, y, simplex, river, 40f);
    }
}
