package rtg.world.gen.terrain;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;

public class TerrainPolar extends TerrainBase
{
	public TerrainPolar()
	{
	}
	
	@Override
	public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river)
	{
	    return terrainPolar(x, y, simplex, river);
	}
}
