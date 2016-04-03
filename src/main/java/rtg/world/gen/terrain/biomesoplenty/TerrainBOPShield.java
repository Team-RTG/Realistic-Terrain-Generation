package rtg.world.gen.terrain.biomesoplenty;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.terrain.TerrainBase;
// this biome also changes the lake generation in RealisticBiomeBase
public class TerrainBOPShield extends TerrainBase
{
	private float start;
	private float height;
	private float base;
	private float width;

	public TerrainBOPShield(float hillStart, float landHeight, float baseHeight, float hillWidth)
	{
		start = hillStart;
		height = landHeight;
		base = baseHeight;
		width = hillWidth;
	}

	@Override
	public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river)
	{
        return terrainPlains(x, y, simplex, river, 160f, 10f, 60f, 200f, 64f);
        //return terrainHighland(x, y, simplex, cell, river, start, width, height, 0f);
	}
}
