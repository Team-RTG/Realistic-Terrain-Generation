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
	private float bHeight;

	public TerrainHLShrubland(float hillHeight, float hillWidth, float varHeight, float varWidth, float lakeHeight, float lakeWidth, float baseHeight)
	{
		hHeight = hillHeight;
		hWidth = hillWidth;

		vHeight = varHeight;
		vWidth = varWidth;

		bHeight = baseHeight;
	}

	@Override
	public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river)
	{
		float h = simplex.noise2(x / vWidth, y / vWidth) * vHeight * river;

		float m = simplex.noise2(x / hWidth, y / hWidth) * hHeight * river;
		//m *= m / 40f;

		float sm = simplex.noise2(x / 30f, y / 30f) * 2f;
		sm *= m / 5f > 3.75f ? 3.75f : m / 20f;
		m += sm;

		float cm = cell.noise(x / 25D, y / 25D, 1D) * 4f;
		cm *= m / 5f > 3.75f ? 3.75f : m / 20f;
		m += cm;
        m = above(m,hHeight/2);

		h += groundNoise(x, y, 5f, simplex);

		return riverized(bHeight + h + m,river);
	}
}
