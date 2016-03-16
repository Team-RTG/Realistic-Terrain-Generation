package rtg.world.gen.terrain;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;


public class TerrainGrasslandHills extends TerrainBase
{
	private float hHeight;
	private float hWidth;
	private float vHeight;
	private float vWidth;
	private float lHeight;
	private float lWidth;
	private float bHeight;

	public TerrainGrasslandHills(float hillHeight, float hillWidth, float varHeight, float varWidth, float lakeHeight, float lakeWidth, float baseHeight)
	{
		hHeight = hillHeight;
		hWidth = hillWidth;

		vHeight = varHeight;
		vWidth = varWidth;

		lHeight = lakeHeight;
		lWidth = lakeWidth;

		bHeight = baseHeight;
	}

	@Override
	public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river)
	{
        return terrainGrasslandHills(x, y, simplex, cell, river, vWidth, vHeight, hWidth, hHeight, bHeight);
	}
}
