package rtg.world.gen.terrain;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;

public class TerrainSwampRiver extends TerrainBase
{
	public TerrainSwampRiver()
	{
	}
	
	@Override
	public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river)
	{
	    return terrainSwampRiver(x, y, simplex, river);
	}
}
