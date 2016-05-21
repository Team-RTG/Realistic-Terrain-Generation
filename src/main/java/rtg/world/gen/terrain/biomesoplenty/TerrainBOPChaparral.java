package rtg.world.gen.terrain.biomesoplenty;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.util.SimplexOctave;
import rtg.world.gen.terrain.TerrainBase;

public class TerrainBOPChaparral extends TerrainBase
{
    private float baseHeight = 76f;
    private float peakyHillWavelength = 40f;
    private float peakyHillStrength = 40f;
    private float smoothHillWavelength = 60f;
    private float smoothHillStrength = 30f;


    private SimplexOctave.Derivative jitter = new SimplexOctave.Derivative();
    private float wavelength = 10f;// of jitter
    private float amplitude = 2f;// of jitter

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

        simplex.riverJitter().evaluateNoise((float)x / wavelength, (float)y / wavelength, jitter);
        int pX = (int)Math.round(x + jitter.deltax() * amplitude);
        int pY = (int)Math.round(y + jitter.deltay() * amplitude);
        float h = this.terrainGrasslandHills(pX, pY, simplex, cell, river, peakyHillWavelength, peakyHillStrength, smoothHillWavelength, smoothHillStrength, baseHeight);

        return groundNoise+ h;
    }
}
