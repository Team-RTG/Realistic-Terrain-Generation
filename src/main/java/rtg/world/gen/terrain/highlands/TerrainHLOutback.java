package rtg.world.gen.terrain.highlands;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.util.SimplexOctave;
import rtg.world.gen.terrain.TerrainBase;

public class TerrainHLOutback extends TerrainBase
{
	private float valley;

    private int wavelength = 20;
    private SimplexOctave.Disk jitter = new SimplexOctave.Disk();
    private double amplitude = 5;
	public TerrainHLOutback(float valleySize)
	{
		valley = valleySize;
	}

	@Override
	public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river)
	{
        simplex.riverJitter().evaluateNoise((float)x / wavelength, (float)y / wavelength, jitter);
        float pX = (int)Math.round(x + jitter.deltax() * amplitude);
        float pY = (int)Math.round(y + jitter.deltay() * amplitude);

        return terrainDuneValley(pX, pY, simplex, cell, river, valley, 65f, 70f);
	}
}
