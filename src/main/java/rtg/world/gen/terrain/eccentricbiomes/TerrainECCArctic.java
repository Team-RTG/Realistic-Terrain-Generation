package rtg.world.gen.terrain.eccentricbiomes;

import rtg.config.rtg.ConfigRTG;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.terrain.TerrainBase;

public class TerrainECCArctic extends TerrainBase
{

    public TerrainECCArctic()
    {
    }

    @Override
    public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river)
    {
        return terrainPolar(x, y, simplex, river, 140f, (minDuneHeight + (float)ConfigRTG.duneHeight), 60, 70, base) +
            groundNoise(x, y, 2f, simplex)*river;
    }
}
