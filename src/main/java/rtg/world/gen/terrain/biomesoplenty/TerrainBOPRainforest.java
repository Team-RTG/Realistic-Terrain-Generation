package rtg.world.gen.terrain.biomesoplenty;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.terrain.TerrainBase;

public class TerrainBOPRainforest extends TerrainBase
{
	private float heigth;
	private float width;

	public TerrainBOPRainforest(float mountainHeight, float mountainWidth)
	{
		heigth = mountainHeight;
		width = mountainWidth;
	}

	@Override
	public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river)
	{
        return terrainSwampMountain(x, y, simplex, cell, river, width, heigth, 140f, 39f, 65f);
	}
}
