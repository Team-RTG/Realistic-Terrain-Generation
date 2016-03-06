package rtg.world.gen.terrain;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;

public class TerrainMesa extends TerrainBase
{
	public TerrainMesa()
	{
	}

	@Override
	public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river)
	{
	    return terrainMesa(x, y, simplex, river, border);
	}
}
