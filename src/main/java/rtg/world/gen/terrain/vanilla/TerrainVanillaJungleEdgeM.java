package rtg.world.gen.terrain.vanilla;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.terrain.TerrainBase;

public class TerrainVanillaJungleEdgeM extends TerrainBase
{
	public TerrainVanillaJungleEdgeM()
	{
	}
	
	@Override
	public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river)
	{
		float h = simplex.noise2(x / 100f, y / 100f) * 4;
		h += simplex.noise2(x / 20f, y / 20f) * 2;
		
		float m = simplex.noise2(x / 230f, y / 230f) * 80f * river;
		m *= m / 35f;
		m = m > 70f ? 70f + (m - 70f) / 2.5f : m;
				
		float sm = simplex.noise2(x / 30f, y / 30f) * 8f + simplex.noise2(x / 8f, y / 8f);
		sm *= m / 20f > 2.5f ? 2.5f : m / 20f;
		m += sm;
		
		float l = simplex.noise2(x / 260f, y / 260f) * 38f;
		l *= l / 25f;
		l = l < -8f ? -8f : l;
		
		return 63f + h + m - l;
	}
}
