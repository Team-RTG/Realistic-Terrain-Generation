package rtg.world.gen.terrain.enhancedbiomes;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.terrain.GroundEffect;
import rtg.world.gen.terrain.HeightEffect;
import rtg.world.gen.terrain.HillockEffect;
import rtg.world.gen.terrain.JitterEffect;
import rtg.world.gen.terrain.TerrainBase;

public class TerrainEBShrublands extends TerrainBase
{
    private HeightEffect height;
    private float hillHeight = 9f;
    private float hillWavelength = 30;
	public TerrainEBShrublands()
	{
        base = 68f;
        HillockEffect smallHills = new HillockEffect();
        smallHills.height = hillHeight;
        smallHills.minimumSimplex = 0.3f;
        smallHills.wavelength =hillWavelength;

        JitterEffect jittered = new JitterEffect();
        jittered.amplitude = 2f;
        jittered.wavelength = 15f;
        jittered.jittered = smallHills;;

        height  = jittered.plus(new GroundEffect(3f));
	}

	@Override
	public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river)
	{
        return riverized(height.added(simplex, cell, x, y) + base, river);
	}
}
