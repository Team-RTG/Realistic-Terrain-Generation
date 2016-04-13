package rtg.world.gen.terrain.highlands;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.terrain.TerrainBase;

public class TerrainHLSteppe extends TerrainBase
{
	private float hHeight;
	private float hWidth;
	private float vHeight;
	private float vWidth;
	private float bHeight;
	public static float mountainStart = 30;

	public TerrainHLSteppe(float hillHeight, float hillWidth, float varHeight, float varWidth, float baseHeight)
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

        // this was originally in h but I moved it to m to get smoother plains
		m += simplex.noise2(x / 12f, y / 12f) * 3f;
		m += simplex.noise2(x / 5f, y / 5f) * 1.5f;

        // make mountains less common
        m = above(m, mountainStart);

		/*float l = simplex.noise2(x / lWidth, y / lWidth) * lHeight;
		l *= l / 25f;
		l = l < 8f ? 8f : l;*/
        //put in a little roll
		h += simplex.noise2(x / 100f, y / 100f) * 2f;

        // no lakes
		return riverized(bHeight + h + m,river);
	}
}
