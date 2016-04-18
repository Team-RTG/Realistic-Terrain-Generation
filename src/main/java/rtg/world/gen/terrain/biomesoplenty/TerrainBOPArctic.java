package rtg.world.gen.terrain.biomesoplenty;

import rtg.config.rtg.ConfigRTG;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.terrain.TerrainBase;

public class TerrainBOPArctic extends TerrainBase
{

	public TerrainBOPArctic()
	{
	}

	@Override
	public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river)
	{
		return terrainPolar(x, y, simplex, river, 140f, 23+ConfigRTG.duneHeight, 60, 70, base) +
                groundNoise(base, base, 2f, simplex)*river;
	}
}
