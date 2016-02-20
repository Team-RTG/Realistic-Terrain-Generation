package rtg.world.gen.terrain.highlands;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.terrain.TerrainBase;

public class TerrainHLEstuary extends TerrainBase
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
	public TerrainHLEstuary(float hillHeight, float hillWidth, float varHeight, float varWidth, float lakeHeight, float lakeWidth, float baseHeight)
	{
		hHeight = 90f;
		hWidth = 180f;
		
		vHeight = 13f;
		vWidth = 100f;
		
		lHeight = 38f;
		lWidth = 260f;
		
		bHeight = 71f;
	}
	
	@Override
	public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river)
	{
		float h = simplex.noise2(x / 130f, y / 130f) * 4f;

		h += simplex.noise2(x / 12f, y / 12f) * 2f;
		h += simplex.noise2(x / 18f, y / 18f) * 4f;

		h = h < 4f ? 0f : h - 4f;

		if(h == 0f)
		{
			h += simplex.noise2(x / 20f, y / 20f) + simplex.noise2(x / 5f, y / 5f);
		}

		return 62f + h;
	}
}
