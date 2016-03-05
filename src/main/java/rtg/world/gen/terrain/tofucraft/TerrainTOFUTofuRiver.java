package rtg.world.gen.terrain.tofucraft;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.terrain.TerrainBase;

public class TerrainTOFUTofuRiver extends TerrainBase
{
	public TerrainTOFUTofuRiver()
	{

	}

	@Override
	public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river)
	{
        return terrainFlatLakes(x, y, simplex, river, 3f, 60f);
	}
}
