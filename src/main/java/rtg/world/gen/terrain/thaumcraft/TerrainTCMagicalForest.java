package rtg.world.gen.terrain.thaumcraft;

import rtg.util.noise.CellNoise;
import rtg.util.noise.OpenSimplexNoise;
import rtg.world.gen.terrain.TerrainBase;

public class TerrainTCMagicalForest extends TerrainBase {
    public TerrainTCMagicalForest() {
    }

    @Override
    public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {
        return terrainForest(x, y, simplex, river, 70f);
    }
}
