package rtg.world.gen.terrain.biomesoplenty;

import rtg.util.noise.CellNoise;
import rtg.util.noise.OpenSimplexNoise;
import rtg.world.gen.terrain.TerrainBase;

public class TerrainBOPEucalyptusForest extends TerrainBase {
    private float baseHeight = 76f;
    private float peakyHillWavelength = 40f;
    private float peakyHillStrength = 20f;
    private float smoothHillWavelength = 20f;
    private float smoothHillStrength = 10f;

    public TerrainBOPEucalyptusForest() {

    }

    @Override
    public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {
        groundNoise = groundNoise(x, y, groundNoiseAmplitudeHills, simplex);

        float h = terrainGrasslandHills(x, y, simplex, cell, river, peakyHillWavelength, peakyHillStrength, smoothHillWavelength, smoothHillStrength, baseHeight);

        return riverized(groundNoise, river) + h;
    }
}
