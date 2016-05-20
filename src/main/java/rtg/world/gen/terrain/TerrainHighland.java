package rtg.world.gen.terrain;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;

public class TerrainHighland extends TerrainBase
{
	private float start;
	private float height;
	private float width;

	public TerrainHighland(float hillStart, float landHeight, float baseHeight, float hillWidth)
	{
		start = hillStart;
		height = landHeight;
		base = baseHeight;
		width = hillWidth;
	}

	@Override
	public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river)
	{
        return terrainHighland(x, y, simplex, cell, river, start, width, height, base - 62f);
	}
}
