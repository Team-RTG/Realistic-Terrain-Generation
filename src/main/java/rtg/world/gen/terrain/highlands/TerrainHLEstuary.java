package rtg.world.gen.terrain.highlands;

import rtg.world.gen.terrain.FunctionalTerrainBase;
import rtg.world.gen.terrain.HeightVariation;
import rtg.world.gen.terrain.JitterEffect;
import rtg.world.gen.terrain.VariableRuggednessEffect;


public class TerrainHLEstuary extends FunctionalTerrainBase
{

    public TerrainHLEstuary()
    {
        base = 60.5f;
        HeightVariation waterLand = new HeightVariation();
        waterLand.height = 3f;
        waterLand.wavelength = 40f;
        waterLand.octave =VariableRuggednessEffect.STANDARD_RUGGEDNESS_OCTAVE;

        height = new JitterEffect(20f,40f,waterLand);

    }

}
