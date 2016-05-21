package rtg.world.gen.terrain.enhancedbiomes;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.terrain.BumpyHillsEffect;
import rtg.world.gen.terrain.GroundEffect;
import rtg.world.gen.terrain.HeightEffect;
import rtg.world.gen.terrain.TerrainBase;
import rtg.world.gen.terrain.VariableRuggednessEffect;

public class TerrainEBShield extends TerrainBase
{
    private float rollHeight = 10;
    private float hillWidth = 40;
    private float hillHeight = 40;
    private float baseHeight = 66;
    private HeightEffect height;
	public TerrainEBShield()
	{
        BumpyHillsEffect intermittentHills  = new BumpyHillsEffect();
        intermittentHills.hillHeight = rollHeight;
        intermittentHills.hillWavelength = VariableRuggednessEffect.STANDARD_RUGGEDNESS_WAVELENGTH;
        intermittentHills.hillOctave =VariableRuggednessEffect.STANDARD_RUGGEDNESS_OCTAVE;
        intermittentHills.spikeHeight = hillHeight;
        intermittentHills.spikeWavelength = hillWidth;

        height = intermittentHills.plus(new GroundEffect(4f));
	}

	@Override
	public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river)
	{
        return riverized(height.added(simplex, cell, x, y)+baseHeight,river);
        //return terrainGrasslandFlats(x, y, simplex, river, 40f, 25f, 68f);
	}
}
