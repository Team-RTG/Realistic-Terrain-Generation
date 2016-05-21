package rtg.world.gen.terrain.icmod;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.terrain.TerrainBase;

/**
 * Created by VelocityRa on 16/4/2016.
 */
public class TerrainICIceCream extends TerrainBase{

    public TerrainICIceCream() {}

    @Override
    public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river)
    {
        float baseHeight = 67f;
        float peakyHillWavelength = 30f;
        float peakyHillStrength = 10f;
        float smoothHillWavelength = 80f;
        float smoothHillStrength = 25f;

        return terrainGrasslandHills(x, y, simplex, cell, river, peakyHillWavelength, peakyHillStrength, smoothHillWavelength, smoothHillStrength, baseHeight) +
                groundNoise(x, y, 5f, simplex)*river;
    }
}
