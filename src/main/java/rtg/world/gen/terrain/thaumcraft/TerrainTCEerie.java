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
        return terrainForest(x, y, simplex, river, 70f);
	}
}
