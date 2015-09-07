package rtg.terrain;

import rtg.util.CellNoise;
import rtg.util.PerlinNoise;

public class TerrainOcean extends TerrainBase
{
	public TerrainOcean()
	{
		
	}

	public float generateNoise(PerlinNoise perlin, CellNoise cell, int x, int y, float ocean, float border, float river)
	{
		return 45f;
	}
}
