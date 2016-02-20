package rtg.world.gen.terrain.highlands;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.terrain.TerrainBase;

public class TerrainHLRainforest extends TerrainBase
{
	private float width;
	private float strength;
	private float lakeDepth;
	private float lakeWidth;
	private float terrainHeight;
    private static float startCliffsAt = 40f;
	
	public TerrainHLRainforest(float mountainHeight, float mountainWidth, float depthLake)
	{
		this(300f, 120f, 8f, 260f, 78f);
	}

    public TerrainHLRainforest(float mountainWidth, float mountainStrength, float depthLake, float widthLake, float height)
	{
		width = mountainWidth;
		strength = mountainStrength;
		lakeDepth = depthLake;
		lakeWidth = 260f;
		terrainHeight = 78f;
	}
		@Override
	public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river)
	{
		float h = simplex.noise2(x / 20f, y / 20f) * 2;
		h += simplex.noise2(x / 7f, y / 7f) * 0.8f;

		float m = simplex.noise2(x / width, y / width) * strength * river;
		m *= m / 35f;
		m = m > 70f ? 70f + (m - 70f) / 2.5f : m;

		float st = m * 0.7f;
		st = st > 20f ? 20f : st;
		float c = cell.noise(x / 30f, y / 30f, 1D) * (5f + st);

        //c = this.above(c, startCliffsAt);

		float sm = simplex.noise2(x / 30f, y / 30f) * 8f + simplex.noise2(x / 8f, y / 8f);
		sm *= (m + 10f) / 20f > 2.5f ? 2.5f : (m + 10f) / 20f;
		m += sm;

		m += c;
        m = above(m, startCliffsAt);

		float l = simplex.noise2(x / lakeWidth, y / lakeWidth) * lakeDepth;
		l *= l / 25f;
		l = l < -8f ? -8f : l;

		return terrainHeight + h + m - l;
	}

	/*@Override
	public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river)
	{
		float h = simplex.noise2(x / width, y / width) * heigth * river;
		h *= h / 32f;
		h = h > 150f ? 150f : h;

		if(h < 14f)
		{
			h += simplex.noise2(x / 25f, y / 25f) * (14f - h) * 0.8f;
		}
		
		if(h < 6)
		{
			h = 6f - ((6f - h) * 0.07f) + simplex.noise2(x / 20f, y / 20f) + simplex.noise2(x / 5f, y / 5f);
		}
		
		if(h > 10f)
		{
			float d = (h - 10f) / 2f > 8f ? 8f : (h - 10f) / 2f;
			h += simplex.noise2(x / 35f, y / 35f) * d;
			h += simplex.noise2(x / 60f, y / 60f) * d * 0.5f;

			if(h > 35f)
			{
				float d2 = (h - 35f) / 1.5f > 30f ? 30f : (h - 35f) / 1.5f;
				h += cell.noise(x / 25D, y / 25D, 1D) * d2;
			}
		}

		if(h > 2f)
		{
			float d = (h - 2f) / 2f > 4f ? 4f : (h - 2f) / 2f;
    		h += simplex.noise2(x / 28f, y / 28f) * d;
    		h += simplex.noise2(x / 18f, y / 18f) * (d / 2f);
    		h += simplex.noise2(x / 8f, y / 8f) * (d / 2f);
		}
		
		return h + 56f;
	}*/
}
