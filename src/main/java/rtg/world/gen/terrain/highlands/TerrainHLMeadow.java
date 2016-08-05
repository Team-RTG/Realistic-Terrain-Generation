package rtg.world.gen.terrain.highlands;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.terrain.TerrainBase;

public class TerrainHLMeadow extends TerrainBase {

    public TerrainHLMeadow() {

    }

    @Override
    public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {

        return terrainPlains(x, y, simplex, river, 160f, 10f, 60f, 200f, 66f);
    }
}
