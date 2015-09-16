package rtg.terrain.vanilla;

import rtg.terrain.TerrainBase;
import rtg.util.CellNoise;
import rtg.util.PerlinNoise;

public class TerrainVanillaDesert extends TerrainBase
{

public TerrainVanillaDesert(){
}

@Override
public float generateNoise(PerlinNoise perlin, CellNoise cell, int x, int y, float ocean, float border, float river)
{
	float st = (perlin.noise2(x / 80f, y / 80f) + 0.38f) * 35f * river;
	st = st < 0.2f ? 0.2f : st;
	
	float h = perlin.noise2(x / 60f, y / 60f) * st * 2f;
	h = h > 0f ? -h : h;
	h += st;
	h *= h / 90f; //50
	h += st;
	
	return 70f + h;
}
}
