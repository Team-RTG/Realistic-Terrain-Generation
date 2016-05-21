package rtg.world.gen.terrain.extrabiomes;

import rtg.world.gen.terrain.FunctionalTerrainBase;
import rtg.world.gen.terrain.GroundEffect;
import rtg.world.gen.terrain.HillsEverywhereEffect;
import rtg.world.gen.terrain.JitterEffect;


public class TerrainEBXLForestedIsland extends FunctionalTerrainBase
{

	public TerrainEBXLForestedIsland()
	{
        base = 66;

        HillsEverywhereEffect smallHills = new HillsEverywhereEffect();
        smallHills.height = 4;
        smallHills.hillBottomSimplexValue = .2f;
        smallHills.wavelength = 8;

        HillsEverywhereEffect largerHills = new HillsEverywhereEffect();
        largerHills.height = 8;
        largerHills.octave =1;
        largerHills.wavelength = 20;
        largerHills.modified = smallHills;

        JitterEffect disguising = new JitterEffect();
        disguising.amplitude = 3;
        disguising.wavelength = 10;
        disguising.jittered = largerHills;

        height = disguising.plus(new GroundEffect(4));

	}

}
