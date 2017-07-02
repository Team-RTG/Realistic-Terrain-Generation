package rtg.world.gen.terrain.hotwatermod;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.terrain.TerrainBase;

public class TerrainHWMHotSprings extends TerrainBase
{

    public TerrainHWMHotSprings()
    {

    }

    @Override
    public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river)
    {
        return terrainMarsh(x, y, simplex, 61.5f);
    }
}
