package rtg.world.gen.terrain.biomesoplenty;

import rtg.util.noise.CellNoise;
import rtg.util.noise.OpenSimplexNoise;
import rtg.world.gen.terrain.TerrainBase;

public class TerrainBOPBayou extends TerrainBase {
    public TerrainBOPBayou() {
    }

    @Override
    public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {
        return terrainMarsh(x, y, simplex, 61f);

        //return terrainBeach(x, y, simplex, river, 180f, 35f, 60f);
    }
}
