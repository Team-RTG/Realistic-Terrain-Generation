package rtg.world.gen.terrain.abyssalcraft;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.terrain.TerrainBase;

public class TerrainACCoraliumInfestedSwamp extends TerrainBase
{

    public TerrainACCoraliumInfestedSwamp()
    {

    }

    @Override
    public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river)
    {
        return terrainMarsh(x, y, simplex, 62f);
    }
}
