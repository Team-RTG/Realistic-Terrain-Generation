package rtg.world.gen.terrain.betteragriculture;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.terrain.TerrainBase;

public class TerrainBAFarmlandBiome extends TerrainBase {

    private float baseHeight = 67f;
    private float peakyHillWavelength = 15f;
    private float peakyHillStrength = 5f;
    private float smoothHillWavelength = 20f;
    private float smoothHillStrength = 20f;

    public TerrainBAFarmlandBiome() {

    }

    @Override
    public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {

        groundNoise = groundNoise(x, y, groundNoiseAmplitudeHills, simplex);

        float h = terrainGrasslandHills(x, y, simplex, cell, river, peakyHillWavelength, peakyHillStrength, smoothHillWavelength, smoothHillStrength, baseHeight);

        return riverized(groundNoise + h, river);
    }
}
