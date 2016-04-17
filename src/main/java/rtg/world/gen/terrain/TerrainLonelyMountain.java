package rtg.world.gen.terrain;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;

public class TerrainLonelyMountain extends TerrainBase
{
	private float width;
	private float strength;

	public TerrainLonelyMountain(float mountainWidth, float mountainStrength,float height)
	{
		width = mountainWidth;
		strength = mountainStrength;
		base = height;
	}

	@Override
	public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river)
	{
        return terrainLonelyMountain(x, y, simplex, cell, river, strength, width, base);
	}
}
