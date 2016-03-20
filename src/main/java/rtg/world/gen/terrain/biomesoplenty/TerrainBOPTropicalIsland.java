package rtg.world.gen.terrain.biomesoplenty;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.terrain.TerrainBase;

public class TerrainBOPTropicalIsland extends TerrainBase {
    public TerrainBOPTropicalIsland() {

    }

    @Override
    public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {
        return terrainBeach(x, y, simplex, river, 160f, 30f, 65f);
    }
}
