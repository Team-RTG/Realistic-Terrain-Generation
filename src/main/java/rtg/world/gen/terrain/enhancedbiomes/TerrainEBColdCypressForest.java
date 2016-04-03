package rtg.world.gen.terrain.enhancedbiomes;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.terrain.TerrainBase;

public class TerrainEBColdCypressForest extends TerrainBase
{
	private TerrainBase parent = new TerrainEBBorealForest();

	@Override
	public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river)
	{
        return parent.generateNoise(simplex, cell, x, y, border, river);
	}

}
