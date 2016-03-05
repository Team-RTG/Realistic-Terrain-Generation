package rtg.world.gen.terrain.vanilla;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.terrain.TerrainBase;

public class TerrainVanillaMushroomIsland extends TerrainBase
{
	private float heigth;
	private float width;

	public TerrainVanillaMushroomIsland()
	{

	}

	@Override
	public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river)
	{
        return terrainGrasslandFlats(x, y, simplex, river, 40f, 25f, 68f);
	}
}
