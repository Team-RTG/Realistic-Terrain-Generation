package rtg.world.gen.terrain.highlands;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.util.SimplexOctave;
import rtg.world.gen.terrain.TerrainBase;

public class TerrainHLCliffs extends TerrainBase
{
	private float width;
	private float strength;
	private float lakeDepth;
	private float lakeWidth;
	private float terrainHeight;
    private static float startCliffsAt = 30f;

	/*
	 * width = 230f
	 * strength = 120f
	 * lake = 50f;
	 *
	 * 230f, 120f, 50f
	 */

	public TerrainHLCliffs(float mountainWidth, float mountainStrength, float depthLake)
	{
		this(mountainWidth, mountainStrength, depthLake, 260f, 78f);
	}

	public TerrainHLCliffs(float mountainWidth, float mountainStrength, float depthLake, float widthLake, float height)
	{
		width = mountainWidth;
		strength = mountainStrength;
		lakeDepth = depthLake;
		lakeWidth = widthLake;
		terrainHeight = height;
	}

	@Override
	public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river)
	{
		float h = simplex.noise2(x / 20f, y / 20f) * 2;
		h += simplex.noise2(x / 7f, y / 7f) * 0.8f;

		float m = simplex.noise2(x / width, y / width) * strength * river;
		m *= m / 35f;
		m = m > 70f ? 70f + (m - 70f) / 2.5f : m;


            SimplexOctave.Disk jitter = new SimplexOctave.Disk();
            simplex.riverJitter().evaluateNoise((float)x / 15.0, (float)y / 15.0, jitter);
            float pX = x + (float)jitter.deltax() * 5f;
            float pY = y + (float)jitter.deltay() * 5f;


		float st = m * 0.7f;
		st = st > 20f ? 20f : st;
		float c = cell.noise(pX / 30f, pY / 30f, 1D) * (5f + st);

        //c = this.above(c, startCliffsAt);

		float sm = simplex.noise2(pX / 30f, pY / 30f) * 8f + simplex.noise2(pX / 8f, pY / 8f);
        //craggier
        sm += simplex.noise2(pX/ 4.9f, pY / 4.9f);
		sm *= (m + 10f) / 20f > 2.5f ? 2.5f : (m + 10f) / 20f;
		m += sm;

		m += c;
        m = above(m, startCliffsAt);

        // we're going to have the rivers "pierce" the cliffs so river noise won't affect the cliff parts
        if (m> 0) return terrainHeight+h+m;

		float result = terrainHeight + h;
        if (river<1) return 62f+(result-62f)*river;
        return result;
	}
}
