package rtg.world.gen.terrain.biomesoplenty;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.terrain.TerrainBase;

public class TerrainBOPAlps extends TerrainBase
{
    // the BoP version has steep slopes and a flat area on top. The RTG version will
    private float start = 0f;// this puts a minimum on "ruggedness" on the top. We want to allow flats
	private float height = 40f; // sets the variability range
	private float base = 100f; // the base height
	private float width = 80f; // width of irregularity noise on top. We want low, for a lot of features.

	public TerrainBOPAlps()
	{
	}

	@Override
	public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river)
	{

        return terrainHighland(x, y, simplex, cell, river, start, width, height, base);
        //return terrainMountainRiver(x, y, simplex, cell, river, 300f, 67f);
	}
}
