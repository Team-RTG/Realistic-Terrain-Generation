package rtg.world.gen.terrain.enhancedbiomes;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.terrain.TerrainBase;

public class TerrainEBMeadowM extends TerrainBase
{

    private float baseHeight = 70f;
    private float hillStrength = 25f;

    public TerrainEBMeadowM(float bh, float hs)
    {
        baseHeight = bh;
        hillStrength = hs;
    }

    @Override
    public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river)
    {
        return terrainHighland(x, y, simplex, cell, river, 10f, 68f, hillStrength, baseHeight-62f);

    }
}
