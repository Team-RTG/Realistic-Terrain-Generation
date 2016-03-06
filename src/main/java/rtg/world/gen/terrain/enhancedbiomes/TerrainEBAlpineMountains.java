package rtg.world.gen.terrain.enhancedbiomes;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.terrain.TerrainBase;

public class TerrainEBAlpineMountains extends TerrainBase
{
	public TerrainEBAlpineMountains()
	{
	}

	@Override
	public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river)
	{
        return terrainMountainRiver(x, y, simplex, cell, river, 300f, 67f);
	}
}
