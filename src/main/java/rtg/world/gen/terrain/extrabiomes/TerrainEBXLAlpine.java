package rtg.world.gen.terrain.extrabiomes;


import rtg.world.gen.terrain.FunctionalTerrainBase;
import rtg.world.gen.terrain.GroundEffect;
import rtg.world.gen.terrain.JitterEffect;
import rtg.world.gen.terrain.LonelyMountainEffect;

public class TerrainEBXLAlpine extends FunctionalTerrainBase
{
	public TerrainEBXLAlpine()
	{
        LonelyMountainEffect mountain = new LonelyMountainEffect();
        mountain.mountainHeight = 50;
        mountain.mountainWavelength = 60;
        mountain.spikeHeight = 10;
        mountain.spikeWavelength = 20;

        JitterEffect jitteredMountain = new JitterEffect(6f,9f,mountain);
        height = jitteredMountain.plus(new GroundEffect(4f));
        base = 85;
	}
}
