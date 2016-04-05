package rtg.world.gen.terrain.extrabiomes;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.terrain.TerrainBase;

public class TerrainEBXLRainforest extends TerrainBase
{
	private float width;
	private float strength;

	public TerrainEBXLRainforest(float mountainWidth, float mountainStrength)
	{
        width = mountainWidth;
		strength = mountainStrength;
		base = 69;
	}


	@Override
	public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river)
	{
        return terrainLonelyMountain(x, y, simplex, cell, river, strength, width, base);
	}
}
