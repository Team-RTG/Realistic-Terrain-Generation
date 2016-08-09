package rtg.world.gen.terrain.biomesoplenty;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.terrain.TerrainBase;

public class TerrainBOPGrove extends TerrainBase {

    private float baseHeight = 64f;
    private float peakyHillWavelength = 40f;
    private float peakyHillStrength = 5f;
    private float smoothHillWavelength = 20f;
    private float smoothHillStrength = 10f;

    public TerrainBOPGrove() {

    }

    @Override
    public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {
        // no ground noise

        float h = this.terrainGrasslandHills(x, y, simplex, cell, river, smoothHillWavelength, smoothHillStrength, peakyHillWavelength, peakyHillStrength, baseHeight);

        return h;
    }
}
