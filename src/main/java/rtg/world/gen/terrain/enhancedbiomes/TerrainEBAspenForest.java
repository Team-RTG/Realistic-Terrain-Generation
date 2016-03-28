package rtg.world.gen.terrain.enhancedbiomes;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.terrain.TerrainBase;

public class TerrainEBAspenForest extends TerrainBase
{
    private float hillStrength = 10f;

	/*
	 * width = 230f
	 * strength = 120f
	 * lake = 50f;
	 *
	 * 230f, 120f, 50f
	 */

	public TerrainEBAspenForest(float hillStrength)
	{
		this.hillStrength = hillStrength;
	}

	@Override
	public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river)
	{
        groundNoise = groundNoise(x, y, groundVariation, simplex);

        float m = hills(x, y, hillStrength, simplex, river);

        float floNoise = 65f + groundNoise + m;

        return riverized(floNoise,river);
    }
}
