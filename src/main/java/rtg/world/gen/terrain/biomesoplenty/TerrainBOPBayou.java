package rtg.world.gen.terrain.biomesoplenty;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.terrain.TerrainBase;

public class TerrainBOPBayou extends TerrainBase
{
	public TerrainBOPBayou()
	{
	}

	@Override
	public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river)
	{
        return terrainPlains(x, y, simplex, river, 80f, 1f, 40f, 20f, 62f);
	}
}
