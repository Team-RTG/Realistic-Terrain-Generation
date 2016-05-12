package rtg.world.gen.terrain.vanilla;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.terrain.TerrainBase;

public class TerrainVanillaRedwoodTaigaHills extends TerrainBase
{

    private float hillStrength = 40f;
    public TerrainVanillaRedwoodTaigaHills()
    {
        base = 72f;
    }

    @Override
    public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river)
    {

        return terrainHighland(x, y, simplex, cell, river, 10f, 68f, hillStrength, base - 62f);
    }
}
