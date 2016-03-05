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
        return terrainGrasslandMountains(x, y, simplex, cell, river, 7f, 120f, 68f);
	}
}
