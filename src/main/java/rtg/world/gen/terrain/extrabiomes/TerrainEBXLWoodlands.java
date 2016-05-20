package rtg.world.gen.terrain.extrabiomes;

import rtg.world.gen.terrain.BlendedHillEffect;
import rtg.world.gen.terrain.FunctionalTerrainBase;
import rtg.world.gen.terrain.GroundEffect;


public class TerrainEBXLWoodlands extends FunctionalTerrainBase{

	public TerrainEBXLWoodlands()
	{
        base = 66;

        BlendedHillEffect bumps = new BlendedHillEffect();
        bumps.height = 8;
        bumps.wavelength = 70;
        bumps.hillBottomSimplexValue = 0.5f; // not too common

        height = bumps.plus(new GroundEffect(2));
	}


}
