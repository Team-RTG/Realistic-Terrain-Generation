package rtg.world.gen.terrain.vanilla;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.terrain.TerrainBase;

public class TerrainVanillaColdTaigaM extends TerrainBase
{
    public TerrainVanillaColdTaigaM()
    {
    }

    @Override
    public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river)
    {
        return terrainGrasslandMountains(x, y, simplex, cell, river, 4f, 80f, 68f);
    }
}
