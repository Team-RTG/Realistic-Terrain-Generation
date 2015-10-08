package rtg.world.gen.terrain.highlands;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.terrain.TerrainBase;

public class TerrainHLWindyIsland extends TerrainBase
{
	public TerrainHLWindyIsland()
	{
	}
	
	@Override
	public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float ocean, float border, float river)
	{
		float st = (simplex.noise2(x / 160f, y / 160f) + 0.38f) * 35f * river;
		st = st < 0.2f ? 0.2f : st;
		
		float h = simplex.noise2(x / 60f, y / 60f) * st * 2f;
		h = h > 0f ? -h : h;
		h += st;
		h *= h / 50f;
		h += st;
		
    	return 70f + h;
	}
}
