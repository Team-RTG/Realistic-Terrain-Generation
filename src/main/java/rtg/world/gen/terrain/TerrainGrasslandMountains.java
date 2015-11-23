package rtg.world.gen.terrain;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;

public class TerrainGrasslandMountains extends TerrainBase
{
	public TerrainGrasslandMountains()
	{
	}
	
	@Override
	public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river)
	{
		float h = simplex.noise2(x / 100f, y / 100f) * 7;
		h += simplex.noise2(x / 20f, y / 20f) * 2;
		
		float m = simplex.noise2(x / 230f, y / 230f) * 120f * river;
		m *= m / 35f;
		m = m > 70f ? 70f + (m - 70f) / 2.5f : m;
		
		float c = cell.noise(x / 30f, y / 30f, 1D) * (m * 0.30f);
		
		float sm = simplex.noise2(x / 30f, y / 30f) * 8f + simplex.noise2(x / 8f, y / 8f);
		sm *= m / 20f > 2.5f ? 2.5f : m / 20f;
		m += sm;
		
		m += c;
		
		float l = simplex.noise2(x / 260f, y / 260f) * 38f;
		l *= l / 25f;
		l = l < -8f ? -8f : l;
		
		return 68f + h + m - l;
	}
}
