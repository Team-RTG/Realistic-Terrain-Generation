package rtg.world.gen.terrain.extrabiomes;

import rtg.world.gen.terrain.FunctionalTerrainBase;
import rtg.world.gen.terrain.GroundEffect;
import rtg.world.gen.terrain.HeightVariation;
import rtg.world.gen.terrain.JitterEffect;
import rtg.world.gen.terrain.PlateauEffect;

public class TerrainEBXLShrubland extends FunctionalTerrainBase
{

    public TerrainEBXLShrubland()
    {
        base = 66;
        HeightVariation tops = new HeightVariation();
        tops.height = 3;
        tops.wavelength = 40;
        tops.octave = 0;

        PlateauEffect flatHills = new PlateauEffect();
        flatHills.height = 5f;
        flatHills.bottomSimplexValue = 0.1f;
        flatHills.topSimplexValue = 0.5f;
        flatHills.octave = 1;
        flatHills.wavelength = 40;
        flatHills.subordinate = tops;

        JitterEffect jitteredHills = new JitterEffect();
        jitteredHills.amplitude = 3f;
        jitteredHills.wavelength = 15f;
        jitteredHills.jittered = flatHills;

        height = jitteredHills.plus(new GroundEffect(4));

    }
}
