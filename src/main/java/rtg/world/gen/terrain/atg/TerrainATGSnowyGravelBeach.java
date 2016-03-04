package rtg.world.gen.terrain.atg;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.terrain.TerrainBase;

public class TerrainATGSnowyGravelBeach extends TerrainBase
{
	public TerrainATGSnowyGravelBeach()
	{
	}

	@Override
	public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river)
	{
        return terrainSwampRiver(x, y, simplex, river);
	}
}
