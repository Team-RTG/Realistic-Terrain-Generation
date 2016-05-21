package rtg.world.gen.terrain.extrabiomes;

import rtg.world.gen.terrain.BlendedHillEffect;
import rtg.world.gen.terrain.FunctionalTerrainBase;
import rtg.world.gen.terrain.GroundEffect;
import rtg.world.gen.terrain.HillsEverywhereEffect;
import rtg.world.gen.terrain.JitterEffect;
import rtg.world.gen.terrain.PlateauEffect;


public class TerrainEBXLGreenHills extends FunctionalTerrainBase
{

	public TerrainEBXLGreenHills()
	{
        base = 72;

        BlendedHillEffect bumps = new BlendedHillEffect();
        bumps.height = 10;
        bumps.wavelength = 15;
        bumps.hillBottomSimplexValue = 0.2f;

        HillsEverywhereEffect hills = new HillsEverywhereEffect();
        hills.height = 60;
        hills.wavelength = 50;
        hills.modified = bumps;
        hills.octave = 2;
        hills.hillBottomSimplexValue = 0.4f;// this makes most area flat

        PlateauEffect multiplier = new PlateauEffect();
        multiplier.bottomSimplexValue = -0.3f;
        multiplier.topSimplexValue = 0.3f;
        multiplier.height = 0;
        multiplier.octave = 3;
        multiplier.subordinate = hills;
        multiplier.wavelength= 80;

        /*HillsEverywhereEffect multiplier = new HillsEverywhereEffect();
        multiplier.height = 0;// just using it for a multiplier
        multiplier.wavelength = 80;
        multiplier.modified = hills;
        multiplier.octave = 3;
        hills.hillBottomSimplexValue = 0f;*/

        JitterEffect disguise = new JitterEffect();
        disguise.amplitude = 6;
        disguise.wavelength = 10;
        disguise.jittered = multiplier;

        height = disguise.plus(new GroundEffect(2));

	}

}
