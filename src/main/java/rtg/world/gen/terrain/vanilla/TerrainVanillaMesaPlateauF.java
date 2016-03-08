package rtg.world.gen.terrain.vanilla;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.terrain.TerrainBase;

public class TerrainVanillaMesaPlateauF extends TerrainBase
{
	private boolean booRiver;
	private float[] height;
	private int heightLength;
	private float strength;
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
	public TerrainVanillaMesaPlateauF(boolean riverGen, float heightStrength, float canyonWidth, float canyonHeight, float canyonStrength, float baseHeight)
	{
		booRiver = true;
		height = new float[]{5.0f, 0.5f, 12.5f, 0.5f, 18.0f, 0.5f};
		strength = 35f;
		heightLength = height.length;
		cWidth = 160f;
		cHeigth = 60f;
		cStrength = 40f;
		base = 69f;
	}

	@Override
	public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river)
	{
        return terrainCanyon(x, y, simplex, river, height, border, strength, heightLength, booRiver);
	}
}
