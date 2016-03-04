package rtg.world.gen.terrain;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;

public class TerrainMountain extends TerrainBase
{
	public TerrainMountain()
	{
	}
	
	@Override
	public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river)
	{
	    return terrainMountain(x, y, simplex, cell, river);
	}
}
