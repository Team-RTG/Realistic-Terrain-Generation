package rtg.world.gen.terrain.extrabiomes;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.terrain.TerrainBase;

public class TerrainEBXLForestedHills extends TerrainBase
{
	private float width;
	private float strength;
	private float lakeDepth;
	private float lakeWidth;

	/*
	 * width = 230f
	 * strength = 120f
	 * lake = 50f;
	 *
	 * 230f, 120f, 50f
	 */

	public TerrainEBXLForestedHills(float mountainWidth, float mountainStrength, float depthLake)
	{
		this(mountainWidth, mountainStrength, depthLake, 260f, 68f);
	}

	public TerrainEBXLForestedHills(float mountainWidth, float mountainStrength, float depthLake, float widthLake, float height)
	{
		width = mountainWidth;
		strength = mountainStrength;
		lakeDepth = depthLake;
		lakeWidth = widthLake;
		base = height;
	}

	@Override
	public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river)
	{
        return terrainLonelyMountain(x, y, simplex, cell, river, strength, width, base);
	}
}
