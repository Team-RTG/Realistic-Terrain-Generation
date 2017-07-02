package rtg.world.gen.terrain.eccentricbiomes;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.terrain.TerrainBase;

public class TerrainECCGlassland extends TerrainBase
{

    public TerrainECCGlassland()
    {

    }

    @Override
    public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river)
    {
        return terrainMarsh(x, y, simplex, 61.5f);
    }
}
