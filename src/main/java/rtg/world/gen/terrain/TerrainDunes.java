package rtg.world.gen.terrain;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;

public class TerrainDunes extends TerrainBase
{
	public TerrainDunes()
	{
	}

	public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river)
	{
	    return terrainDunes(x, y, simplex, cell, river);
	}
}
