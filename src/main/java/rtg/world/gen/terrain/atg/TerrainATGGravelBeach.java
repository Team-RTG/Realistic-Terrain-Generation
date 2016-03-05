package rtg.world.gen.terrain.atg;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.terrain.TerrainBase;

public class TerrainATGGravelBeach extends TerrainBase
{
	public TerrainATGGravelBeach()
	{
	}

	@Override
	public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river)
	{
        return terrainBeach(x, y, simplex, river, 180f, 35f, 60f);
	}
}
