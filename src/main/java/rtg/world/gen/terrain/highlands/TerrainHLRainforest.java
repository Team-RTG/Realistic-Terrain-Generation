package rtg.world.gen.terrain.highlands;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.util.SimplexOctave;
import rtg.world.gen.terrain.TerrainBase;

public class TerrainHLRainforest extends TerrainBase
{
	private float width;
	private float strength;
	private float terrainHeight;
    private static float startCliffsAt = 40f;
    private int wavelength = 20;
    private SimplexOctave.Disk jitter = new SimplexOctave.Disk();
    private double amplitude = 5;

	public TerrainHLRainforest(float mountainHeight, float mountainWidth, float depthLake)
	{
		this(mountainWidth, mountainHeight, depthLake, 260f, 78f);
	}

    public TerrainHLRainforest(float mountainWidth, float mountainStrength, float depthLake, float widthLake, float height)
	{
		width = mountainWidth;
		strength = mountainStrength;
		terrainHeight = height;
	}
		@Override
	public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river)
	{

        simplex.riverJitter().evaluateNoise((float)x / wavelength, (float)y / wavelength, jitter);
        int pX = (int)Math.round(x + jitter.deltax() * amplitude);
        int pY = (int)Math.round(y + jitter.deltay() * amplitude);

        x = pX;
        y = pY;
        
		float h = simplex.noise2(x / 20f, y / 20f) * 2;
		h += simplex.noise2(x / 7f, y / 7f) * 0.8f;

		float m = simplex.noise2(x / width, y / width) * strength * river;
		m *= m / 35f;
		m = m > 70f ? 70f + (m - 70f) / 2.5f : m;

		float st = m * 0.7f;
		st = st > 20f ? 20f : st;
		float c = (float)simplex.noise(x / 30f, y / 30f, 1D) * (3f + st);

        //c = this.above(c, startCliffsAt);

		float sm = simplex.noise2(x / 15f, y / 15f) * 4f + simplex.noise2(x / 8f, y / 8f) * 2f;
		sm *= (m + 10f) / 20f > 2.5f ? 2.5f : (m + 10f) / 20f;
		m += sm;

		m += c;
        m = above(m, startCliffsAt);

		float result = terrainHeight + h + m;
        if (river<1) return 62f+(result-62f)*river;
        return result;
	}

	/*@Override
	public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river)
	{
        return terrainSwampMountain(x, y, simplex, cell, river, width, heigth);
	}*/
}
