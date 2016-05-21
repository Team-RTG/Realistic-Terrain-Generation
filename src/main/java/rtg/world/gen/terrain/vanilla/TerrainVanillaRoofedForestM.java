package rtg.world.gen.terrain.vanilla;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.terrain.TerrainBase;

public class TerrainVanillaRoofedForestM extends TerrainBase
{
    public TerrainVanillaRoofedForestM()
    {
    }

    @Override
    public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river)
    {
        return terrainGrasslandMountains(x, y, simplex, cell, river, 4f, 50f, 68f);
    }
}
