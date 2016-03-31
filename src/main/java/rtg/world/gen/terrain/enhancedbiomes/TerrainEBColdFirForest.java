package rtg.world.gen.terrain.enhancedbiomes;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.terrain.GroundEffect;
import rtg.world.gen.terrain.HeightEffect;
import rtg.world.gen.terrain.TerrainBase;

public class TerrainEBColdFirForest extends TerrainBase
{
	private float groundNoiseAmplitude = 6f;
    private float baseHeight = 66f;
    private HeightEffect height;

	/*
	 * width = 230f
	 * strength = 120f
	 * lake = 50f;
	 *
	 * 230f, 120f, 50f
	 */

	public TerrainEBColdFirForest()
	{
		height  = new GroundEffect(groundNoiseAmplitude);
	}


	@Override
	public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river)
	{
        return riverized(height.added(simplex, cell, x, y)+baseHeight,river);
	}
}
