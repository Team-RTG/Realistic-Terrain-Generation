package rtg.world.gen.terrain.enhancedbiomes;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.terrain.TerrainBase;

public class TerrainEBGlacier extends TerrainBase
{


    public TerrainEBGlacier() {
        base = 90f;
    }
    @Override
    public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river)
    {
        return riverized(base + TerrainBase.groundNoise(x, y,groundNoiseAmplitudeHills, simplex),river);
    }
}
