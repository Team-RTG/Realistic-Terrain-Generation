package rtg.world.gen.terrain.enhancedbiomes;

import rtg.util.CellNoise;
import rtg.util.PerlinNoise;
import rtg.world.gen.terrain.TerrainBase;

public class TerrainEBSahara extends TerrainBase
{
	public TerrainEBSahara()
	{
	}
	
	@Override
	public float generateNoise(PerlinNoise perlin, CellNoise cell, int x, int y, float ocean, float border, float river)
	{
		float st = (perlin.noise2(x / 160f, y / 160f) + 0.38f) * 35f * river;
		st = st < 0.2f ? 0.2f : st;
		
		float h = perlin.noise2(x / 60f, y / 60f) * st * 2f;
		h = h > 0f ? -h : h;
		h += st;
		h *= h / 50f;
		h += st;
		
    	return 70f + h;
	}
}
