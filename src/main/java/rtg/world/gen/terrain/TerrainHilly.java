package rtg.world.gen.terrain;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;

public class TerrainHilly extends TerrainBase
{
	private float width;
	private float strength;
	private float lakeDepth;
	private float lakeWidth;
	private float terrainHeight;
	
	/*
	 * width = 230f
	 * strength = 120f
	 * lake = 50f;
	 * 
	 * 230f, 120f, 50f
	 */

	public TerrainHilly(float mountainWidth, float mountainStrength, float depthLake)
	{
		this(mountainWidth, mountainStrength, depthLake, 260f, 68f);
	}
	
	public TerrainHilly(float mountainWidth, float mountainStrength, float depthLake, float widthLake, float height)
	{
		width = mountainWidth;
		strength = mountainStrength;
		lakeDepth = depthLake;
		lakeWidth = 260f;
		terrainHeight = 68f;
	}
	
	@Override
	public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river)
	{
		float h = simplex.noise2(x / 20f, y / 20f) * 2;
		h += simplex.noise2(x / 7f, y / 7f) * 0.8f;
		
		float m = simplex.noise2(x / width, y / width) * strength * river;
		m *= m / 35f;
		m = m > 70f ? 70f + (m - 70f) / 2.5f : m;
		
		float st = m * 0.7f;
		st = st > 20f ? 20f : st;
		float c = cell.noise(x / 30f, y / 30f, 1D) * (5f + st);
		
		float sm = simplex.noise2(x / 30f, y / 30f) * 8f + simplex.noise2(x / 8f, y / 8f);
		sm *= (m + 10f) / 20f > 2.5f ? 2.5f : (m + 10f) / 20f;
		m += sm;
		
		m += c;
		
		float l = simplex.noise2(x / lakeWidth, y / lakeWidth) * lakeDepth;
		l *= l / 25f;
		l = l < -8f ? -8f : l;
		
		return terrainHeight + h + m - l;
	}
}
