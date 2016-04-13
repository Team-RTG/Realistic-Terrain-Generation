package rtg.world.gen.terrain.highlands;

import rtg.config.rtg.ConfigRTG;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.terrain.TerrainBase;

public class TerrainHLDunes extends TerrainBase
{
	public TerrainHLDunes()
	{
	}

	@Override
	public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river)
	{
        return terrainPolar(x, y, simplex, river, 140f, 23+ConfigRTG.duneHeight, 40, 50, base) +
                groundNoise(base, base, 2f, simplex)*river;
	}

}
