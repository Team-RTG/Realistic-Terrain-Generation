package rtg.world.gen.terrain.vanilla;

import rtg.util.CellNoise;
import rtg.util.PerlinNoise;
import rtg.world.gen.terrain.TerrainBase;

public class TerrainVanillaDeepOcean extends TerrainBase
{
	public TerrainVanillaDeepOcean()
	{
		
	}

	public float generateNoise(PerlinNoise perlin, CellNoise cell, int x, int y, float ocean, float border, float river)
	{
		return 45f;
	}
}
