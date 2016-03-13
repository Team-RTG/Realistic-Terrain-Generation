package rtg.world.gen.terrain.biomesoplenty;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.terrain.TerrainBase;

public class TerrainBOPChaparral extends TerrainBase
{
    private float baseHeight = 76f;
    private float peakyHillWavelength = 40f;
    private float peakyHillStrength = 10f;
    private float smoothHillWavelength = 20f;
    private float smoothHillStrength = 5f;

    public TerrainBOPChaparral()
    {

    }

    public TerrainBOPChaparral(float bh, float hs)
    {
        baseHeight = bh;
        peakyHillStrength = hs;
    }

    @Override
    public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river)
    {

        groundNoise = groundNoise(x, y, groundNoiseAmplitudeHills, simplex);

        //float m = hills(x, y, peakyHillStrength, simplex, river);

        float h = this.terrainGrasslandHills(x, y, simplex, cell, river, peakyHillWavelength, peakyHillStrength, smoothHillWavelength, smoothHillStrength, baseHeight);

        return groundNoise+ h;
    }
}
