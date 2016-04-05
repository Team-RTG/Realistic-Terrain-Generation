package rtg.world.gen.terrain.extrabiomes;

import rtg.world.gen.terrain.FunctionalTerrainBase;
import rtg.world.gen.terrain.GroundEffect;
import rtg.world.gen.terrain.JitterEffect;
import rtg.world.gen.terrain.LonelyMountainEffect;


public class TerrainEBXLGlacier extends FunctionalTerrainBase
{
	public TerrainEBXLGlacier()
	{
        LonelyMountainEffect mountain = new LonelyMountainEffect();
        mountain.mountainHeight = 40;
        mountain.mountainWavelength = 80;
        mountain.spikeHeight = 8;
        mountain.spikeWavelength = 20;

        JitterEffect jitteredMountain = new JitterEffect(6f,12f,mountain);
        height = jitteredMountain.plus(new GroundEffect(4f));
        base = 80;
	}
}
