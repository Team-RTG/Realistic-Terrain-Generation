package rtg.world.gen.terrain.thaumcraft;

import rtg.util.CellNoise;
import rtg.util.PerlinNoise;
import rtg.world.gen.terrain.TerrainBase;

public class TerrainTCMagicalForest extends TerrainBase
{
	public TerrainTCMagicalForest()
	{
	}
	
	@Override
	public float generateNoise(PerlinNoise perlin, CellNoise cell, int x, int y, float ocean, float border, float river)
	{
		float h = perlin.noise2(x / 100f, y / 100f) * 8;
		h += perlin.noise2(x / 30f, y / 30f) * 4;
		h += perlin.noise2(x / 15f, y / 15f) * 2;
		h += perlin.noise2(x / 7f, y / 7f);
		
    	return 70f + (20f * river) + h;
	}
}
