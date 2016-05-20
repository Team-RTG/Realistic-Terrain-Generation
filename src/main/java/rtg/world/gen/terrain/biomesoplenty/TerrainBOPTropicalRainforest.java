package rtg.world.gen.terrain.biomesoplenty;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.terrain.TerrainBase;

public class TerrainBOPTropicalRainforest extends TerrainBase
{
	private float start;
	private float height;
	private float width;

	public TerrainBOPTropicalRainforest(float hillStart, float landHeight, float baseHeight, float hillWidth)
	{
		start = hillStart;
		height = landHeight;
		base = baseHeight;
		width = hillWidth;
	}

	@Override
	public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river)
	{
        return terrainHighland(x, y, simplex, cell, river, start, width, height,base -62f);
	}
}
