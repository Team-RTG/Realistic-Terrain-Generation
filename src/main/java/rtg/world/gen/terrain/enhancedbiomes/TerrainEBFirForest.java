package rtg.world.gen.terrain.enhancedbiomes;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.terrain.AdjustableSpikeEffect;
import rtg.world.gen.terrain.GroundEffect;
import rtg.world.gen.terrain.HeightEffect;
import rtg.world.gen.terrain.JitterEffect;
import rtg.world.gen.terrain.TerrainBase;

public class TerrainEBFirForest extends TerrainBase
{
	private float groundNoiseAmplitude = 6f;
    private float baseHeight = 66f;
    private HeightEffect height;

	public TerrainEBFirForest()
	{
		height  = new GroundEffect(groundNoiseAmplitude);

                AdjustableSpikeEffect outcrops = new AdjustableSpikeEffect();
        outcrops.height = 40;
        outcrops.minimumSimplex = 0.6f; // rarish
        outcrops.wavelength = 200;
        outcrops.octave = 2;
        outcrops.power = 1.6f;

        JitterEffect jitteredOutcrops = new JitterEffect(15,30,outcrops);

        height = height.plus(jitteredOutcrops);
	}


	@Override
	public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river)
	{
        return riverized(height.added(simplex, cell, x, y)+baseHeight,river);
	}
}
