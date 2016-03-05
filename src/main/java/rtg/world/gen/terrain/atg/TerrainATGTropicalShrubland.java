package rtg.world.gen.terrain.atg;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.terrain.TerrainBase;

public class TerrainATGTropicalShrubland extends TerrainBase
{

    public TerrainATGTropicalShrubland()
    {

    }

    @Override
    public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river)
    {
        return terrainPlains(x, y, simplex, river, 200f, 65f);
    }
}
