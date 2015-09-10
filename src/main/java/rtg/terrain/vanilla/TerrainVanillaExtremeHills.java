package rtg.terrain.vanilla;

import rtg.terrain.TerrainBase;
import rtg.util.CellNoise;
import rtg.util.PerlinNoise;

public class TerrainVanillaExtremeHills extends TerrainBase
{
	private float start;
	private float height;
	private float base;
	private float width;
	
	public TerrainVanillaExtremeHills(float hillStart, float landHeight, float baseHeight, float hillWidth)
	{
		start = hillStart;
		height = landHeight;
		base = baseHeight;
		width = hillWidth;
	}
	
	@Override
	public float generateNoise(PerlinNoise perlin, CellNoise cell, int x, int y, float ocean, float border, float river)
	{
		float h = perlin.noise2(x / width, y / width) * height * river;
		h = h < start ? start + ((h - start) / 4.5f) : h;
		
		if(h > 0f)
		{
			float st = h * 1.5f > 15f ? 15f : h * 1.5f;
			h += cell.noise(x / 70D, y / 70D, 1D) * st;
		}
		
		h += perlin.noise2(x / 20f, y / 20f) * 5f;
		h += perlin.noise2(x / 12f, y / 12f) * 3f;
		h += perlin.noise2(x / 5f, y / 5f) * 1.5f;
		
    	return base + h;
	}
}
