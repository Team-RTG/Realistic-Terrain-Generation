package rtg.world.gen.terrain.biomesyougo;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.terrain.TerrainBase;

public class TerrainBYGAthuraForest extends TerrainBase {

    private float baseHeight = 72f;
    private float peakyHillWavelength = 40f;
    private float peakyHillStrength = 10f;
    private float smoothHillWavelength = 20f;
    private float smoothHillStrength = 20f;

    public TerrainBYGAthuraForest() {

    }

    @Override
    public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {

        groundNoise = groundNoise(x, y, groundNoiseAmplitudeHills, simplex);

        float h = terrainGrasslandHills(x, y, simplex, cell, river, peakyHillWavelength, peakyHillStrength, smoothHillWavelength, smoothHillStrength, baseHeight);

        return riverized(groundNoise + h, river);
    }
}
