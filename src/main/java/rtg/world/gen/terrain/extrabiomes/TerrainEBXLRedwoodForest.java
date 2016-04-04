package rtg.world.gen.terrain.extrabiomes;

import rtg.world.gen.terrain.BlendedHillEffect;
import rtg.world.gen.terrain.FunctionalTerrainBase;
import rtg.world.gen.terrain.GroundEffect;
import rtg.world.gen.terrain.HillsEverywhereEffect;
import rtg.world.gen.terrain.JitterEffect;

public class TerrainEBXLRedwoodForest extends FunctionalTerrainBase
{

    public TerrainEBXLRedwoodForest()
    {
        base = 78;

        BlendedHillEffect bumps = new BlendedHillEffect();
        bumps.height = 10;
        bumps.wavelength = 15;
        bumps.hillBottomSimplexValue = 0.2f;

        HillsEverywhereEffect hills = new HillsEverywhereEffect();
        hills.height = 20;
        hills.wavelength = 60;
        hills.modified = bumps;
        hills.octave = 2;
        hills.hillBottomSimplexValue = 0.2f;

        JitterEffect disguise = new JitterEffect();
        disguise.amplitude = 6;
        disguise.wavelength = 20;
        disguise.jittered = hills;

        height = disguise.plus(new GroundEffect(4));
    }
}
