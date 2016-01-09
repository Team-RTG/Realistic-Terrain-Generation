package rtg.world.gen.terrain.highlands;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.terrain.TerrainBase;

public class TerrainHLFlyingMountains extends TerrainBase
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

	public TerrainHLFlyingMountains(float mountainWidth, float mountainStrength, float depthLake)
	{
		this(mountainWidth, mountainStrength, depthLake, 260f, 63f);
	}
	
	public TerrainHLFlyingMountains(float mountainWidth, float mountainStrength, float depthLake, float widthLake, float height)
	{
        super(height);
		width = mountainWidth;
		strength = mountainStrength*1.3f;
		lakeDepth = depthLake;
		lakeWidth = widthLake;
		terrainHeight = height;
	}

    //reworked for the soar/plunge effect
    public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river)
	{
		float h = simplex.noise2(x / 20f, y / 20f) * 2;
		h += simplex.noise2(x / 7f, y / 7f) * 0.8f;

        // strategy: get 3 separate "mountain" terrains, all mostly water
        // take the maximum of the 3
        float adjustment = -0.3f;
        float simplex1 = stretched(stretched(simplex.noise2(x / width, y / width) - adjustment));
		float m1 = (simplex1)* strength;
        m1+=(strength-m1)*simplex.noise2(x / 20f, y / 20f)/4;
        m1+=(strength-m1)*simplex.noise2(x / 7f, y / 7f)/10;

        float width2 = width*0.7f;
        float strength2 = strength*0.7f;
        float simplex2 = stretched(stretched(simplex.noise2(x / width2+0.3f, y / width2+0.3f)- adjustment));
		float m2 = (simplex2)* strength2;
        m2+=(strength2-m2)*simplex.noise2(x / 20f, y / 20f)/4;
        m2+=(strength2-m2)*simplex.noise2(x / 7f, y / 7f)/10;

        float width3 = width*0.5f;
        float strength3 = strength*0.5f;
        float simplex3 = stretched(stretched(simplex.noise2(x / width3-0.3f, y / width3-0.3f) - adjustment));
		float m3 = (simplex3)* strength3;
        m3+=(strength-m3)*simplex.noise2(x / 20f, y / 20f)/4;
        m3+=(strength-m3)*simplex.noise2(x / 7f, y / 7f)/10;

        float m = Math.max(m3, Math.max(m1,m2));
		/*m *= m / 35f;
		m = m > 70f ? 70f + (m - 70f) / 2.5f : m;

		float st = m * 0.7f;
		st = st > 20f ? 20f : st;
		float c = cell.noise(x / 30f, y / 30f, 1D) * (5f + st);

		float sm = simplex.noise2(x / 30f, y / 30f) * 8f + simplex.noise2(x / 8f, y / 8f);
		sm *= (m + 10f) / 20f > 2.5f ? 2.5f : (m + 10f) / 20f;
		m += sm;

		m += c;*/

        // no lakes for now
        float l = 0f;
		/*float l = simplex.noise2(x / lakeWidth, y / lakeWidth) * lakeDepth;
		l *= l / 25f;
		l = l < -8f ? -8f : l;*/
		return terrainHeight + h + m2 - l;
	}
	
	/*@Override
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
		
		float sm = simplex.noise2(x / 30f, y / 30f) * 8f + simplex.noise2(x / 8f, y / 8f);
		sm *= (m + 10f) / 20f > 2.5f ? 2.5f : (m + 10f) / 20f;
		m += sm;
		
		m += c;
		
		float l = simplex.noise2(x / lakeWidth, y / lakeWidth) * lakeDepth;
		l *= l / 25f;
		l = l < -8f ? -8f : l;
		
		return terrainHeight + h + m - l;
	}*/
}
