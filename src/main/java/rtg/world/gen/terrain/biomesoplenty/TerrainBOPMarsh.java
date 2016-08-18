package rtg.world.gen.terrain.biomesoplenty;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.terrain.HeightVariation;
import rtg.world.gen.terrain.TerrainBase;

public class TerrainBOPMarsh extends TerrainBase {

    private float baseHeight = 62f;
    private HeightVariation variation;
    private HeightVariation smallVariation;

    public TerrainBOPMarsh() {

        variation = new HeightVariation();
        variation.height = 1.5f;
        variation.wavelength = 20;
        variation.octave = 0;

        smallVariation = new HeightVariation();
        smallVariation.height = 1.5f;
        smallVariation.wavelength = 10;
        smallVariation.octave = 0;
    }

    @Override
    public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {

        return baseHeight + variation.added(simplex, cell, x, y) + smallVariation.added(simplex, cell, x, y);
    }
}
