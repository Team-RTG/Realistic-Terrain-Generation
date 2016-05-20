package rtg.world.gen.terrain.enhancedbiomes;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.terrain.TerrainBase;

public class TerrainEBAlpineMountainsEdge extends TerrainBase
{
	private TerrainBase alpineMountains = new TerrainEBAlpineMountains();


	@Override
	public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river)
	{
        return alpineMountains.generateNoise(simplex, cell, x, y, border, river);
	}
}
