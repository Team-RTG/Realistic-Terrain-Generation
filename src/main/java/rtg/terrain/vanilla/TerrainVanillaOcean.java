package rtg.terrain.vanilla;

import rtg.terrain.TerrainBase;
import rtg.util.CellNoise;
import rtg.util.PerlinNoise;

public class TerrainVanillaOcean extends TerrainBase
{
	public TerrainVanillaOcean()
	{
		
	}

	public float generateNoise(PerlinNoise perlin, CellNoise cell, int x, int y, float ocean, float border, float river)
	{
		return 45f;
	}
}
