package rtg.world.gen.terrain.highlands;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.terrain.TerrainBase;

public class TerrainHLLowlands extends TerrainBase
{
	public TerrainHLLowlands()
	{
	}

	@Override
	public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river)
	{
        // there is also code in the realistic biome which adds small lakes
        return terrainFlatLakes(x, y, simplex, river, 50f, 62f);
	}
}
