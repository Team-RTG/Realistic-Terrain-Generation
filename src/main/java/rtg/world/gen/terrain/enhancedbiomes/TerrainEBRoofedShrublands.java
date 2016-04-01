package rtg.world.gen.terrain.enhancedbiomes;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.terrain.TerrainBase;

public class TerrainEBRoofedShrublands extends TerrainBase
{
    private TerrainBase parent = new TerrainEBShrublands();
	public TerrainEBRoofedShrublands()
	{
	}

	@Override
	public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river)
	{
        return parent.generateNoise(simplex, cell, x, y, border, river);
	}
}
