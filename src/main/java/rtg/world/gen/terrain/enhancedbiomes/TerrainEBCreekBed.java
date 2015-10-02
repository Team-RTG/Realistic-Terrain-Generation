package rtg.world.gen.terrain.enhancedbiomes;

import rtg.util.CellNoise;
import rtg.util.PerlinNoise;
import rtg.world.gen.terrain.TerrainBase;

public class TerrainEBCreekBed extends TerrainBase
{
	public TerrainEBCreekBed()
	{
	}
	
	@Override
	public float generateNoise(PerlinNoise perlin, CellNoise cell, int x, int y, float ocean, float border, float river)
	{
		float h = perlin.noise2(x / 130f, y / 130f) * 30f;
		
		h += perlin.noise2(x / 12f, y / 12f) * 2f;
		h += perlin.noise2(x / 18f, y / 18f) * 4f;
		
		h = h < 4f ? 0f : h - 4f;
		
		if(h == 0f)
		{
			h += perlin.noise2(x / 20f, y / 20f) + perlin.noise2(x / 5f, y / 5f);
		}
		
		return 62f + h;
	}
}
