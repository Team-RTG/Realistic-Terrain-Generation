package rtg.world.gen.terrain;

import rtg.util.CellNoise;
import rtg.util.PerlinNoise;

public class TerrainBase 
{
	public TerrainBase()
	{
	}
	
	public float generateNoise(PerlinNoise perlin, CellNoise cell, int x, int y, float ocean, float border, float river)
	{
		return 70f;
	}
}
