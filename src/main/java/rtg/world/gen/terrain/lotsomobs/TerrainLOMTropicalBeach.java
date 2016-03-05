package rtg.world.gen.terrain.lotsomobs;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.terrain.TerrainBase;

public class TerrainLOMTropicalBeach extends TerrainBase
{
	public TerrainLOMTropicalBeach()
	{
	    
	}

	@Override
	public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river)
	{
        return terrainBeach(x, y, simplex, river, 160f, 30f, 65f);
	}
}
