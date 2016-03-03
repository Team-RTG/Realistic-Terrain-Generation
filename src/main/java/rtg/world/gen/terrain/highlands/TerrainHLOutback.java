package rtg.world.gen.terrain.highlands;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.terrain.TerrainBase;

public class TerrainHLOutback extends TerrainBase
{
	private float valley;
	
	public TerrainHLOutback(float valleySize)
	{
		valley = valleySize;
	}

	@Override
	public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river)
	{
        return terrainDuneValley(x, y, simplex, cell, river, valley);
	}
}
