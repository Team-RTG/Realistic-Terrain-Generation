package rtg.world.gen.terrain.enhancedbiomes;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.terrain.AdjustableSpikeEffect;
import rtg.world.gen.terrain.BlendedHillEffect;
import rtg.world.gen.terrain.GroundEffect;
import rtg.world.gen.terrain.HeightEffect;
import rtg.world.gen.terrain.JitterEffect;
import rtg.world.gen.terrain.TerrainBase;

public class TerrainEBSilverPineForest extends TerrainBase
{

    private HeightEffect height;

	public TerrainEBSilverPineForest()
	{
		base = 68f;
        BlendedHillEffect rollingHills = new BlendedHillEffect();
        rollingHills.height = 20;
        rollingHills.wavelength = 100;
        rollingHills.hillBottomSimplexValue = 0;

        AdjustableSpikeEffect outcrops = new AdjustableSpikeEffect();
        outcrops.height = 25;
        outcrops.minimumSimplex = 0.7f; // pretty rare
        outcrops.wavelength = 200;
        outcrops.octave = 2;

        JitterEffect jitteredOutcrops = new JitterEffect(10,30,outcrops);

        height = rollingHills.plus(jitteredOutcrops).plus(new GroundEffect(3f));
	}


	@Override
	public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river)
	{
        return riverized(base + height.added(simplex, cell, x, y),river);
	}
}
