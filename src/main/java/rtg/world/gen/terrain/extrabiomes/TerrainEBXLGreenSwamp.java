package rtg.world.gen.terrain.extrabiomes;

import rtg.world.gen.terrain.BlendedHillEffect;
import rtg.world.gen.terrain.FunctionalTerrainBase;
import rtg.world.gen.terrain.HeightVariation;
import rtg.world.gen.terrain.JitterEffect;
import rtg.world.gen.terrain.VariableRuggednessEffect;


public class TerrainEBXLGreenSwamp extends FunctionalTerrainBase
{
	public TerrainEBXLGreenSwamp()
	{
        base = 61.5f;
        HeightVariation waterLand = new HeightVariation();
        waterLand.height = 3f;
        waterLand.wavelength = 25;
        waterLand.octave =VariableRuggednessEffect.STANDARD_RUGGEDNESS_OCTAVE;

        height = new JitterEffect(15f,30f,waterLand);
        height = new JitterEffect(5f,10f,height);

        // add in some occasional hills
        BlendedHillEffect intermittentHills = new BlendedHillEffect();
        intermittentHills.height = 10f;
        intermittentHills.hillBottomSimplexValue = 0.5f;// rarish
        intermittentHills.wavelength = 80f;

        JitterEffect jitteredHills = new JitterEffect(5f,15f,intermittentHills);

        height = height.plus(jitteredHills);
    }
}
