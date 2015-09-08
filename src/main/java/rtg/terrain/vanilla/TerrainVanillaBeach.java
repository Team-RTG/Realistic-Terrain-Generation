package rtg.terrain.vanilla;

import rtg.terrain.TerrainBase;
import rtg.util.CellNoise;
import rtg.util.PerlinNoise;

public class TerrainVanillaBeach extends TerrainBase
{
	public TerrainVanillaBeach()
	{
	}
	
	@Override
	public float generateNoise(PerlinNoise perlin, CellNoise cell, int x, int y, float ocean, float border, float river)
	{
		float h = perlin.noise2(x / 180f, y / 180f) * 40f * river;
		h *= h / 35f;
		
		if(h < 1f)
		{
			h = 1f;
		}
		
		if(h < 4f)
		{
			h += (perlin.noise2(x / 50f, y / 50f) + perlin.noise2(x / 15f, y / 15f)) * (4f - h);
		}
		
		return 60f + h;
	}
}
