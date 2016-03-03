package rtg.world.gen.terrain.lotsomobs;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.terrain.TerrainBase;

public class TerrainLOMAntartica extends TerrainBase
{
	public TerrainLOMAntartica()
	{
	}
	
	@Override
	public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river)
	{
        return terrainPolar(x, y, simplex, river);
	}
}
