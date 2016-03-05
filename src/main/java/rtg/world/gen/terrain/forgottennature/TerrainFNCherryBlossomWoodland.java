package rtg.world.gen.terrain.forgottennature;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.terrain.TerrainBase;

public class TerrainFNCherryBlossomWoodland extends TerrainBase
{

    public TerrainFNCherryBlossomWoodland()
    {

    }

    @Override
    public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river)
    {
        return terrainPlains(x, y, simplex, river, 80f, 68f);
    }
}
