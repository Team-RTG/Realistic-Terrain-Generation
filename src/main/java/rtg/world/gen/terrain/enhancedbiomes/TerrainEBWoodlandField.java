package rtg.world.gen.terrain.enhancedbiomes;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.terrain.TerrainBase;

public class TerrainEBWoodlandField extends TerrainBase
{
	public TerrainEBWoodlandField()
	{
	}

	@Override  // note: Same as TerrainEBWoodlans
	public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river)
	{
        return terrainGrasslandFlats(x, y, simplex, river, 80f, 25f, 68f);
	}
}
