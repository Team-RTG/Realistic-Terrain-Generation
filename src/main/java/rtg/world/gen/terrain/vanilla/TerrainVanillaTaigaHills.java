package rtg.world.gen.terrain.vanilla;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.terrain.TerrainBase;

public class TerrainVanillaTaigaHills extends TerrainBase
{
    private float hillStrength = 40f;

    public TerrainVanillaTaigaHills()
    {
        this(76f,45f);
    }

    public TerrainVanillaTaigaHills(float bh, float hs)
    {
        base= bh;
        hillStrength = hs;
    }

    @Override
    public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river)
    {
        return terrainHighland(x, y, simplex, cell, river, 10f, 68f, hillStrength, 10f);
    }
}
