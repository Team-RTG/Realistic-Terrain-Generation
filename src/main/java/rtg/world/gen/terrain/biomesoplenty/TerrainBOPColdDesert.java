package rtg.world.gen.terrain.biomesoplenty;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.terrain.TerrainBase;

public class TerrainBOPColdDesert extends TerrainBase {

    private float ruggedness = 3f;
    private float ruggednessWavelength = 100f;
    private float heightPitch = 35f;// the ruggedness parameter will multiply this by 0.2
    private float heightDivisor = 1f;

    public TerrainBOPColdDesert() {

    }

    @Override
    public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {

        float result = terrainPlains(x, y, simplex, river, ruggednessWavelength, ruggedness, heightPitch, heightDivisor, base);
        // no indentations; cutoff is not noticeable with these low slopes
        return result > base ? result : base;
    }
}
