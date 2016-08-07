package rtg.world.gen.terrain.highlands;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.util.SimplexOctave;
import rtg.world.gen.terrain.TerrainBase;

public class TerrainHLPinelands extends TerrainBase {
    private float baseHeight = 76f;
    private float peakyHillWavelength = 60f;
    private float peakyHillStrength = 50f;
    private float smoothHillWavelength = 85f;
    private float smoothHillStrength = 20f;


    private SimplexOctave.Derivative jitter = new SimplexOctave.Derivative();
    private float wavelength = 12f;// of jitter
    private float amplitude = 4f;// of jitter

    public TerrainHLPinelands()
    {

    }

    public TerrainHLPinelands(float bh, float hs)
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

        return groundNoise*river+ h;
    }
}
