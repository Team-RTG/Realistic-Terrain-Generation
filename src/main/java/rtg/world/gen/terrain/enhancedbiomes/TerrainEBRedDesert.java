package rtg.world.gen.terrain.enhancedbiomes;

import rtg.util.CellNoise;
import rtg.util.PerlinNoise;
import rtg.world.gen.terrain.TerrainBase;

public class TerrainEBRedDesert extends TerrainBase
{
	public TerrainEBRedDesert()
	{
	}

	public float generateNoise(PerlinNoise perlin, CellNoise cell, int x, int y, float ocean, float border, float river)
	{
		float st = (perlin.noise2(x / 160f, y / 160f) + 0.38f) * 35f;
		st = st < 0.2f ? 0.2f : st;
		
		float h = perlin.noise2(x / 60f, y / 60f) * st * 2f;
		h = h > 0f ? -h : h;
		h += st;
		h *= h / 50f;
		h += st;
		
		if(h < 10f)
		{
			float d = (h - 10f) / 2f;
			d = d > 4f ? 4f : d;
	    	h += cell.noise(x / 25D, y / 25D, 1D) * d;
			h += perlin.noise2(x / 30f, y / 30f) * d;
			h += perlin.noise2(x / 14f, y / 14f) * d * 0.5f;
		}
		
		return 70f + (h * river);
	}
}
