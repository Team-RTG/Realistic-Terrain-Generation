package rtg.world.gen.terrain.enhancedbiomes;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.terrain.BlendedHillEffect;
import rtg.world.gen.terrain.GroundEffect;
import rtg.world.gen.terrain.HeightEffect;
import rtg.world.gen.terrain.TerrainBase;

public class TerrainEBBorealForest extends TerrainBase
{

    float hillStrength = 15;

    HeightEffect height;
	public TerrainEBBorealForest()
	{
        BlendedHillEffect hillEffect = new BlendedHillEffect();
        hillEffect.height = hillStrength;
        hillEffect.hillBottomSimplexValue = 0.3f;
        hillEffect.octave = 0;
        hillEffect.wavelength = 50;

        height = hillEffect.plus(new GroundEffect(3f));
	}

	@Override
	public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river)
	{
        return riverized(height.added(simplex, cell, x, y),river);
	}
}
