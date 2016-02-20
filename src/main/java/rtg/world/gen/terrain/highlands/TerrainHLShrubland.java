package rtg.world.gen.terrain.highlands;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.terrain.TerrainBase;

public class TerrainHLShrubland extends TerrainBase
{
	private float hHeight;
	private float hWidth;
	private float vHeight;
	private float vWidth;
	private float lHeight;
	private float lWidth;
	private float bHeight;
	
	/*
	 * hillHeight = 70f
	 * hillWidth = 180f
	 * 
	 * varHeight = 7f
	 * varWidth = 100f
	 * 
	 * lakeHeigth = 38f
	 * lakeWidth = 260f
	 * 
	 * baseHeight = 68f
	 * 
	 * 70f, 180f, 7f, 100f, 38f, 260f, 68f
	 */
	public TerrainHLShrubland(float hillHeight, float hillWidth, float varHeight, float varWidth, float lakeHeight, float lakeWidth, float baseHeight)
	{
		hHeight = 10f;
		hWidth = 20f;
		
		vHeight = 7f;
		vWidth = 90f;
		
		lHeight = 10f;
		lWidth = 200f;
		
		bHeight = 68f;
	}
	
	@Override
	public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river)
	{
		float h = simplex.noise2(x / vWidth, y / vWidth) * vHeight * river;
		h += simplex.noise2(x / 20f, y / 20f) * 2;
		
		float m = simplex.noise2(x / hWidth, y / hWidth) * hHeight * river;
		//m *= m / 40f;
		
		float sm = simplex.noise2(x / 30f, y / 30f) * 2f;
		sm *= m / 5f > 3.75f ? 3.75f : m / 20f;
		m += sm;
		
		float cm = cell.noise(x / 25D, y / 25D, 1D) * 4f;
		cm *= m / 5f > 3.75f ? 3.75f : m / 20f;
		m += cm;
        m = above(m,hHeight/2);
		
		float l = simplex.noise2(x / lWidth, y / lWidth) * lHeight;
		l *= l / 25f;
		l = l < 8f ? 8f : l;
		
		h += simplex.noise2(x / 12f, y / 12f) * 3f;
		h += simplex.noise2(x / 5f, y / 5f) * 1.5f;
		
		return bHeight + h + m;
	}
}
