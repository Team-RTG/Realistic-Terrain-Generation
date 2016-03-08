package rtg.world.gen.terrain.vanilla;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.terrain.TerrainBase;

public class TerrainVanillaMesaBryce extends TerrainBase
{
	private float height;
	private float density;
	private float base;

	/*
	 * Example parameters:
	 *
	 * allowed to generate rivers?
	 * riverGen = true
	 *
	 * canyon jump heights
	 * heightArray = new float[]{2.0f, 0.5f, 6.5f, 0.5f, 14.0f, 0.5f, 19.0f, 0.5f}
	 *
	 * strength of canyon jump heights
	 * heightStrength = 35f
	 *
	 * canyon width (cliff to cliff)
	 * canyonWidth = 160f
	 *
	 * canyon heigth (total heigth)
	 * canyonHeight = 60f
	 *
	 * canyon strength
	 * canyonStrength = 40f
	 *
	 */
	public TerrainVanillaMesaBryce(boolean riverGen, float heightStrength, float canyonWidth, float canyonHeight, float canyonStrength, float baseHeight)
	{
		/**
		 * Values come in pairs per layer. First is how high to step up.
		 * 	Second is a value between 0 and 1, signifying when to step up.
		 */
		height = 20f;
		density = 0.7f;
		base = 69f;
	}

	@Override
	public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river)
	{
		river *= 0.5f;
		river = river > 1f ? 1f : river;
		float b = 0;
		float n = 0;
		float sn = simplex.noise2(x / 2f, y / 2f) * 0.5f + 0.5f;
		sn += simplex.noise2(x, y) * 0.2 + 0.2;
		sn += simplex.noise2(x / 4f, y / 4f) * 4f + 4f;
		sn += simplex.noise2(x / 8f, y / 8f) * 2f + 2f;
		n = (sn > 6) ? 20f + simplex.noise2(x / 64f, y / 64f) * 4f : 0f;
		b += n;
		return base + b;
	}
}