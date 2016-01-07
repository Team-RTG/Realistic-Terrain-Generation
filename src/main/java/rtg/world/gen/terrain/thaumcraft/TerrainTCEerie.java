package rtg.world.gen.terrain.thaumcraft;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.terrain.TerrainBase;

public class TerrainTCEerie extends TerrainBase
{
	public TerrainTCEerie()
	{
	}
	
	@Override
	public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river)
	{
		float h = simplex.noise2(x / 100f, y / 100f) * 8;
		h += simplex.noise2(x / 30f, y / 30f) * 4;
		h += simplex.noise2(x / 15f, y / 15f) * 2;
		h += simplex.noise2(x / 7f, y / 7f);
		
    	return 70f + (20f * river) + h;
	}
}
