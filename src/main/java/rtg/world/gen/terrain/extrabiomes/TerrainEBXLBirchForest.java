package rtg.world.gen.terrain.extrabiomes;

import rtg.world.gen.terrain.BlendedHillEffect;
import rtg.world.gen.terrain.FunctionalTerrainBase;
import rtg.world.gen.terrain.GroundEffect;
import rtg.world.gen.terrain.HeightVariation;
import rtg.world.gen.terrain.JitterEffect;
import rtg.world.gen.terrain.HillsEverywhereEffect;


public class TerrainEBXLBirchForest extends FunctionalTerrainBase
{


    public TerrainEBXLBirchForest()
    {
        base = 75;
        
        // first some occasional hills
        BlendedHillEffect hillsOnHills = new BlendedHillEffect();
        hillsOnHills.height= 10;
        hillsOnHills.hillBottomSimplexValue = 0.2f;
        hillsOnHills.wavelength = 40;

        // on top of a hills-everywhere base
        HillsEverywhereEffect baseHills = new HillsEverywhereEffect();
        baseHills.height = 30;
        baseHills.wavelength = 80;
        baseHills.hillBottomSimplexValue = 0.3f;// a little space for jittering
        baseHills.modified = hillsOnHills;
        baseHills.octave = 1;

        // some jitter to disguise the math
        JitterEffect jitteredHills = new JitterEffect();
        jitteredHills.amplitude = 8;
        jitteredHills.wavelength = 20;
        jitteredHills.jittered = baseHills;

        // a little roll to disguise the passes
        HeightVariation passes = new HeightVariation();
        passes.height = 10;
        passes.wavelength = 50;
        passes.octave = 2;

        height = passes.plus(jitteredHills).plus(new GroundEffect(4f));
    }
}


