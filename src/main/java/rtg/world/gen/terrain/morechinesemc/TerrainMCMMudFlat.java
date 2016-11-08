package rtg.world.gen.terrain.morechinesemc;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.terrain.HeightVariation;
import rtg.world.gen.terrain.HillockEffect;
import rtg.world.gen.terrain.TerrainBase;

public class TerrainMCMMudFlat extends TerrainBase {

    private final float bottom = 58f;
    private final HeightVariation bottomVariation;
    private final HillockEffect smallHills;
    private final HillockEffect mediumHills;

    // surprisingly the BoP version is mostly above water and kind of hilly
    public TerrainMCMMudFlat() {

        bottomVariation = new HeightVariation();
        bottomVariation.height = 2;
        bottomVariation.octave = 0;
        bottomVariation.wavelength = 40;

        smallHills = new HillockEffect();
        smallHills.height = 6;
        smallHills.wavelength = 15;
        smallHills.minimumSimplex = 0.2f;
        smallHills.octave = 1;

        mediumHills = new HillockEffect();
        mediumHills.height = 12;
        mediumHills.wavelength = 25;
        mediumHills.minimumSimplex = 0.2f;
        mediumHills.octave = 2;

    }

    @Override
    public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {

        float increment = bottomVariation.added(simplex, cell, x, y) + smallHills.added(simplex, cell, x, y);
        increment += mediumHills.added(simplex, cell, x, y);
        return riverized(bottom + increment, river);
    }
}
