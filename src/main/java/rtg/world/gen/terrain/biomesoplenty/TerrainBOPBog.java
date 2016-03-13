package rtg.world.gen.terrain.biomesoplenty;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.terrain.HeightEffect;
import rtg.world.gen.terrain.HeightVariation;
import rtg.world.gen.terrain.HillockParameters;
import rtg.world.gen.terrain.TerrainBase;

public class TerrainBOPBog extends TerrainBase
{
    private final float bottom = 60f;
    private final HeightVariation bottomVariation;
    private final HillockParameters smallHills;
    private final HillockParameters mediumHills;
    // surprisingly the BoP version is mostly above water and kind of hilly
	public TerrainBOPBog()
	{
        bottomVariation = new HeightVariation();
        bottomVariation.height = 2;
        bottomVariation.octave = 0;
        bottomVariation.wavelength = 40;

        smallHills = new HillockParameters();
        smallHills.height = 8;
        smallHills.wavelength = 15;
        smallHills.minimumSimplex = 0.2f;
        smallHills.octave = 1;

        mediumHills = new HillockParameters();
        mediumHills.height = 15;
        mediumHills.wavelength = 25;
        mediumHills.minimumSimplex = 0.2f;
        mediumHills.octave = 2;

	}

	@Override
	public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river)
	{
        float increment = bottomVariation.added(simplex, x, y) + smallHills.added(simplex, x, y) ;
        increment += mediumHills.added(simplex, x, y);
        return bottom + increment;
	}
}
