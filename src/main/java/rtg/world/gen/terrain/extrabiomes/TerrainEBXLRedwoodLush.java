package rtg.world.gen.terrain.extrabiomes;

import rtg.world.gen.terrain.BlendedHillEffect;
import rtg.world.gen.terrain.FunctionalTerrainBase;
import rtg.world.gen.terrain.GroundEffect;
import rtg.world.gen.terrain.HillsEverywhereEffect;
import rtg.world.gen.terrain.JitterEffect;

public class TerrainEBXLRedwoodLush extends FunctionalTerrainBase
{

    public TerrainEBXLRedwoodLush()
    {
        base = 72;

        BlendedHillEffect bumps = new BlendedHillEffect();
        bumps.height = 10;
        bumps.wavelength = 15;
        bumps.hillBottomSimplexValue = 0.2f;

        HillsEverywhereEffect hills = new HillsEverywhereEffect();
        hills.height = 30;
        hills.wavelength = 60;
        hills.modified = bumps;
        hills.octave = 2;
        hills.hillBottomSimplexValue = 0.3f;

        HillsEverywhereEffect multiplier = new HillsEverywhereEffect();
        multiplier.height = 0;// just using it for a multiplier
        multiplier.wavelength = 120;
        multiplier.modified = hills;
        multiplier.octave = 3;

        JitterEffect disguise = new JitterEffect();
        disguise.amplitude = 6;
        disguise.wavelength = 10;
        disguise.jittered = multiplier;

        height = disguise.plus(new GroundEffect(4));
    }
}
