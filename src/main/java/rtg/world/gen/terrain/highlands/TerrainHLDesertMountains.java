package rtg.world.gen.terrain.highlands;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.terrain.TerrainBase;

public class TerrainHLDesertMountains extends TerrainBase
{
	private float width;
	private float strength;
	private float lakeDepth;
	private float lakeWidth;
	private float terrainHeight;

	/*
	 * width = 230f
	 * strength = 120f
	 * lake = 50f;
	 *
	 * 230f, 120f, 50f
	 */

	public TerrainHLDesertMountains(float mountainWidth, float mountainStrength, float depthLake)
	{
		this(mountainWidth, mountainStrength, depthLake, 260f, 68f);
	}

	public TerrainHLDesertMountains(float mountainWidth, float mountainStrength, float depthLake, float widthLake, float height)
	{
		width = mountainWidth;
		strength = mountainStrength;
		lakeDepth = depthLake;
		lakeWidth = widthLake;
		terrainHeight = height;
                // experimentation
        terrainHeight = 45;
        width = 120;
	}

    // based on rock mountains but there's one noise abs'd instead of two, to stop lakes
    // and everything is a little lower and rounder
	@Override
	public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river)
	{
        float h = simplex.noise2(x / 20f, y / 20f);
        h = h*h*1.5f;
        float h2 = simplex.noise2(x / 14f, y / 14f);
        h2 = h2 * h2 * 1f;
        h = Math.max(h, h2);
		h += h2;
        float h3 = simplex.noise2(x/50f,y/50f);
        h3= h3*h3*4f;
        h+= h3;

        float m = unsignedPower(Math.abs(simplex.noise2(x / width, y / width)),1.3f) * strength * river;
        // invert y and x for complexity
        //float m2 = unsignedPower(simplex.noise2(y / (width*1.5f), x / (width*1.5f)),1.4f) * strength * river;

        //m = Math.max(m, m2);

        // intensify ruggedness at height
        h = m>10? h * m/10: h;

        m = above(m,-50f);

        float longNoise = simplex.noise2(x /100f, y / 100f)*10f;


        return terrainHeight + h + m + longNoise;
	}
}
