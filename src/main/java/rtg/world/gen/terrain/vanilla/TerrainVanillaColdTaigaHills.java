package rtg.world.gen.terrain.vanilla;

import rtg.util.noise.CellNoise;
import rtg.util.noise.OpenSimplexNoise;
import rtg.world.gen.terrain.TerrainBase;

public class TerrainVanillaColdTaigaHills extends TerrainBase {

    public TerrainVanillaColdTaigaHills() {

    }

    @Override
    public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {
        return terrainHighland(x, y, simplex, cell, river, 10f, 68f, 60f, 10f);
        //return terrainMountainRiver(x, y, simplex, cell, river, 300f, 67f);
    }
}
