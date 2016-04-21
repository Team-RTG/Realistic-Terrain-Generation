package rtg.world.gen.terrain.highlands;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.terrain.TerrainBase;

public class TerrainHLGlacier extends TerrainBase
{
	private float hHeight;
	private float hWidth;
	private float vHeight;
	private float vWidth;
	private float bHeight;


	public TerrainHLGlacier(float hillHeight, float hillWidth, float varHeight, float varWidth,  float baseHeight)
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
		h += simplex.noise2(x / 20f, y / 20f) * 2;

		float m = simplex.noise2(x / hWidth, y / hWidth) * hHeight * river;
		m *= m / 40f;

		float sm = simplex.noise2(x / 30f, y / 30f) * 8f;
		sm *= m / 20f > 3.75f ? 3.75f : m / 20f;
		m += sm;

		float cm = cell.noise(x / 25D, y / 25D, 1D) * 12f;
		cm *= m / 20f > 3.75f ? 3.75f : m / 20f;
		m += cm;
        m = m/2f;
        m= above(m,15);

        h += this.groundNoise(x, y, 1.5f, simplex);
		//h += simplex.noise2(x / 12f, y / 12f) * 2f;
		//h += simplex.noise2(x / 5f, y / 5f) * 1f;

		return bHeight + h + m ;
	}
}
