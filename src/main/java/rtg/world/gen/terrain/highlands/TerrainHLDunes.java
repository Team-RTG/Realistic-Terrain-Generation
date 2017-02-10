package rtg.world.gen.terrain.highlands;

import rtg.config.rtg.ConfigRTG;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.util.SimplexOctave;
import rtg.world.gen.terrain.TerrainBase;

public class TerrainHLDunes extends TerrainBase
{
    private SimplexOctave.Derivative jitter = new SimplexOctave.Derivative();
    private float wavelength = 20f;// of jitter
    private float amplitude = 4f;// of jitter

    public TerrainHLDunes()
    {
    }

    @Override
    public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river)
    {
        simplex.riverJitter().evaluateNoise((float)x / wavelength, (float)y / wavelength, jitter);
        float pX = (float)(x + jitter.deltax() * amplitude);
        float pY = (float)(y + jitter.deltay() * amplitude);
        return terrainPolar(pX, pY, simplex, river, 180f, (minDuneHeight + (float)ConfigRTG.duneHeight) + 10f, 80, 30, base) +
            groundNoise(x, y, 2f, simplex)*river;
    }

}
