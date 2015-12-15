package rtg.world.gen.terrain.vanilla;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.terrain.TerrainBase;

public class TerrainVanillaStoneBeach extends TerrainBase
{
	private float start;
	private float height;
	private float base;
	private float width;
	
	public TerrainVanillaStoneBeach(float hillStart, float landHeight, float baseHeight, float hillWidth)
	{
		start = hillStart;
		height = landHeight;
		base = baseHeight;
		width = hillWidth;
	}
	
	@Override
	public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river)
	{
		float h = simplex.noise2(x / width, y / width) * height * river;
		h = h < start ? start + ((h - start) / 4.5f) : h;
		
		h += simplex.noise2(x / 20f, y / 20f) * 5f;
		h += simplex.noise2(x / 12f, y / 12f) * 3f;
		h += simplex.noise2(x / 5f, y / 5f) * 1.5f;
		
    	return base + h;
	}
}
