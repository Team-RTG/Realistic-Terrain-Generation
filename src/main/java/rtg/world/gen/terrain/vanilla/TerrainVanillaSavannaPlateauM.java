package rtg.world.gen.terrain.vanilla;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.terrain.TerrainBase;

public class TerrainVanillaSavannaPlateauM extends TerrainBase
{
	private boolean booRiver;
	private float[] height;
	private int heightLength;
	private float strength;
	private float smooth;
	private float cWidth;
	private float cHeigth;
	private float cStrength;
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
	public TerrainVanillaSavannaPlateauM(boolean riverGen, float heightStrength, float canyonWidth, float canyonHeight, float canyonStrength, float baseHeight)
	{
		booRiver = true;
		/**
		 * Values come in pairs per layer. First is how high to step up.
		 * 	Second is a value between 0 and 1, signifying when to step up.
		 */
		height = new float[]{16.5f, 0.4f, 8f, 0.6f, 5f, 0.8f};
		strength = 20f;
		smooth = 0.1f;
		heightLength = height.length;
		cWidth = 160f;
		cHeigth = 60f;
		cStrength = 40f;
		base = 69f;
	}

	@Override
	public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river)
	{
		river *= 0.5f;
		river = river > 1f ? 1f : river;
		float b = simplex.noise2(x / 40f, y / 40f) * 1.5f;
		b *= river;

		float sn = simplex.noise2(x / 50f, y / 50f) * 0.5f + 0.5f;
		float n;
		for (int i = 0; i < heightLength; i += 2) {
			n = (sn - height[i + 1]) / smooth;
			n = (n < 0) ? 0 : (n > 1) ? 1 : n;
			if (n > height[i + 1]) {
				b += (height[i] * (n - 0.5f) / 0.5f);
				b += simplex.noise2(x / 20f, y / 20f) * 5f * n;
				b += simplex.noise2(x / 12f, y / 12f) * 3f * n;
				b += simplex.noise2(x / 5f, y / 5f) * 1.5f * n;
			}
		}
		b += simplex.noise2(x / 12, y / 12) * sn;

		return base + b;
	}
}