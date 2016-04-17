package rtg.world.gen.terrain.highlands;

import rtg.world.gen.terrain.FunctionalTerrainBase;
import rtg.world.gen.terrain.GroundEffect;
import rtg.world.gen.terrain.HillockEffect;
import rtg.world.gen.terrain.HillsEverywhereEffect;
import rtg.world.gen.terrain.JitterEffect;



public class TerrainHLBog extends FunctionalTerrainBase
{

    public TerrainHLBog()
    {
        this.base = 59f;
        HillsEverywhereEffect small = new HillsEverywhereEffect();
        small.height = 7;
        small.hillBottomSimplexValue = 0.25f;
        small.wavelength = 40;
        small.octave = 2;

        HillockEffect large = new HillockEffect();
        large.height = 11;
        large.minimumSimplex = .4f;
        large.wavelength = 80;
        large.octave = 3;

        height = small.plus(large);

        height = new JitterEffect(30,40,height);
        height = new JitterEffect(10,15,height);
        height = height.plus(new GroundEffect(2));

    }

}
