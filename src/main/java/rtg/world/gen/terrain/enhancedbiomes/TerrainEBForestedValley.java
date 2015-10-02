package rtg.world.gen.terrain.enhancedbiomes;

import rtg.util.CellNoise;
import rtg.util.PerlinNoise;
import rtg.world.gen.terrain.TerrainBase;

public class TerrainEBForestedValley extends TerrainBase
{
	private float valley;
	
	public TerrainEBForestedValley(float valleySize)
	{
		valley = valleySize;
	}

	@Override
	public float generateNoise(PerlinNoise perlin, CellNoise cell, int x, int y, float ocean, float border, float river)
	{
		float h = (perlin.noise2(x / valley, y / valley) + 0.25f) * 65f * river;
		h = h < 1f ? 1f : h;
		
		float r = cell.noise(x / 50D, y / 50D, 1D) * h * 2;
		h += r;
	
		h += perlin.noise2(x / 40f, y / 40f) * 8;
		h += perlin.noise2(x / 14f, y / 14f) * 2;
		
		return 70f + h;
	}
}
