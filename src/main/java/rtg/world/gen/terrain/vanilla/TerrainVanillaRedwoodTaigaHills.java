package rtg.world.gen.terrain.vanilla;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.terrain.TerrainBase;

public class TerrainVanillaRedwoodTaigaHills extends TerrainBase
{

    public TerrainVanillaRedwoodTaigaHills()
    {

    }

    @Override
    public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river)
    {
        return terrainMountainRiver(x, y, simplex, cell, river, 300f, 68f);
    }
}
