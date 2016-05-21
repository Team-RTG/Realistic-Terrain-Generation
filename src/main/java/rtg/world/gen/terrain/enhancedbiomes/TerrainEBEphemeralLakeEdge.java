package rtg.world.gen.terrain.enhancedbiomes;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.terrain.TerrainBase;

public class TerrainEBEphemeralLakeEdge extends TerrainBase
{
    private TerrainEBEphemeralLake parentBiome = new TerrainEBEphemeralLake();

    public TerrainEBEphemeralLakeEdge()
    {

    }

    public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river)
    {
        return parentBiome.generateNoise(simplex, cell, x, y, border, river);
    }
}
