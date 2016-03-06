package rtg.world.gen.terrain.chromaticraft;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.terrain.TerrainBase;

public class TerrainCCEnderForest extends TerrainBase
{

    public TerrainCCEnderForest()
    {

    }

    @Override
    public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river)
    {
        return terrainPlains(x, y, simplex, river, 160f, 10f, 60f, 80f, 65f);
    }
}
