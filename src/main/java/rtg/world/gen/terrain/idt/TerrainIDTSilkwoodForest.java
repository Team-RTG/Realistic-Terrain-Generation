package rtg.world.gen.terrain.idt;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.terrain.TerrainBase;

public class TerrainIDTSilkwoodForest extends TerrainBase
{
    private float hillStrength = 10f;// this needs to be linked to the
    public TerrainIDTSilkwoodForest()
    {

    }

    @Override
    public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river)
    {
        groundNoise = groundNoise(x, y, groundVariation, simplex);

        float m = hills(x, y, hillStrength, simplex, river);

        float floNoise = 65f + groundNoise + m;

        return riverized(floNoise,river);
    }
}
