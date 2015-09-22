package rtg.world.gen.terrain;

import rtg.util.CellNoise;
import rtg.util.PerlinNoise;

public class TerrainMountain extends TerrainBase
{
	public TerrainMountain()
	{
	}
	
	@Override
	public float generateNoise(PerlinNoise perlin, CellNoise cell, int x, int y, float ocean, float border, float river)
	{
		float h = perlin.noise2(x / 300f, y / 300f) * 135f * river;
		h *= h / 32f;
		h = h > 150f ? 150f : h;

		if(h > 10f)
		{
			float d = (h - 10f) / 2f > 8f ? 8f : (h - 10f) / 2f;
			h += perlin.noise2(x / 35f, y / 35f) * d;
			h += perlin.noise2(x / 60f, y / 60f) * d * 0.5f;

			if(h > 35f)
			{
				float d2 = (h - 35f) / 1.5f > 30f ? 30f : (h - 35f) / 1.5f;
				h += cell.noise(x / 25D, y / 25D, 1D) * d2;
			}
		}
		
		h += perlin.noise2(x / 28f, y / 28f) * 4;
		h += perlin.noise2(x / 18f, y / 18f) * 2;
		h += perlin.noise2(x / 8f, y / 8f) * 2;
		
		return h + 67f;
	}
}
