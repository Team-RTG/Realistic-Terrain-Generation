package rtg.world.gen.terrain.enhancedbiomes;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.terrain.BlendedHillEffect;
import rtg.world.gen.terrain.GroundEffect;
import rtg.world.gen.terrain.HeightEffect;
import rtg.world.gen.terrain.JitterEffect;
import rtg.world.gen.terrain.TerrainBase;

public class TerrainEBLowHills extends TerrainBase
{
    private HeightEffect height;
	public TerrainEBLowHills()
	{
        base = 66f;
        BlendedHillEffect hills = new BlendedHillEffect();
        hills.height = 20;
        hills.hillBottomSimplexValue = 0.2f;
        hills.wavelength = 40;
        hills.octave = 0;
        JitterEffect jitteredHills = new JitterEffect();
        jitteredHills.amplitude = 7;
        jitteredHills.wavelength = 20;
        jitteredHills.jittered = hills;
        height = jitteredHills.plus(new GroundEffect(3f));
	}

	@Override
	public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river)
	{
        return riverized(height.added(simplex, cell, x, y)+base,river);
	}
}
