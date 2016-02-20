package rtg.world.gen.terrain.extrabiomes;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.terrain.TerrainBase;

public class TerrainEBXLWoodlands extends TerrainBase
{
	private float start;
	private float height;
	private float base;
	private float width;
	
	public TerrainEBXLWoodlands(float hillStart, float landHeight, float baseHeight, float hillWidth)
	{
		start = 0f;
		height = 80f;
		base = 68f;
		width = 200f;
	}
	
	@Override
	public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river)
	{
		float h = simplex.noise2(x / width, y / width) * height * river;
		h = h < start ? start + ((h - start) / 4.5f) : h;
		
		if(h > 0f)
		{
			float st = h * 1.5f > 15f ? 15f : h * 1.5f;
			h += cell.noise(x / 70D, y / 70D, 1D) * st;
		}
		
		h += simplex.noise2(x / 20f, y / 20f) * 5f;
		h += simplex.noise2(x / 12f, y / 12f) * 3f;
		h += simplex.noise2(x / 5f, y / 5f) * 1.5f;
		
    	return base + h;
	}
}
