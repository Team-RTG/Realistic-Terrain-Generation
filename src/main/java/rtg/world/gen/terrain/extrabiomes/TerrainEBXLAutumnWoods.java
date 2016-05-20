package rtg.world.gen.terrain.extrabiomes;

import rtg.world.gen.terrain.BumpyHillsEffect;
import rtg.world.gen.terrain.FunctionalTerrainBase;
import rtg.world.gen.terrain.JitterEffect;

public class TerrainEBXLAutumnWoods extends FunctionalTerrainBase
{

    public TerrainEBXLAutumnWoods()
    {
        BumpyHillsEffect hills = new BumpyHillsEffect();
        hills.hillHeight = 60;
        hills.hillWavelength = 150;
        hills.spikeHeight = 40;
        hills.spikeWavelength = 50;

        JitterEffect largeScale = new JitterEffect();
        largeScale.amplitude = 20;
        largeScale.wavelength = 40;
        largeScale.jittered = hills;

        JitterEffect smallScale = new JitterEffect();
        smallScale.amplitude = 3;
        smallScale.wavelength = 10;
        smallScale.jittered = largeScale;

        height = smallScale;
        base = 73;

    }


}
