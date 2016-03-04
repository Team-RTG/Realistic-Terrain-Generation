package rtg.world.gen.terrain.biomesoplenty;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.terrain.TerrainBase;

public class TerrainBOPTropics extends TerrainBase
{
	public TerrainBOPTropics()
	{
	}

	@Override
	public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river)
	{
		float h = simplex.noise2(x / 160f, y / 160f) * 40f * river;
		h *= h / 30f;

		if(h < 1f)
		{
			h = 1f;
		}

		if(h < 4f)
		{
			h += (simplex.noise2(x / 50f, y / 50f) + simplex.noise2(x / 15f, y / 15f)) * (4f - h);
		}

		return 65f + h;
	}
}
