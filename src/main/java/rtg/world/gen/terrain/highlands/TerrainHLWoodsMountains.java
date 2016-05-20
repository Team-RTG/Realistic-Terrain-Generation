package rtg.world.gen.terrain.highlands;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.util.SimplexOctave;
import rtg.world.gen.terrain.TerrainBase;

public class TerrainHLWoodsMountains extends TerrainBase
{
    private float width;
    private float strength;
    private float terrainHeight;
    private int wavelength = 40;
    private SimplexOctave.Disk jitter = new SimplexOctave.Disk();
    private double amplitude = 10;

    public TerrainHLWoodsMountains(float mountainWidth, float mountainStrength, float height)
    {
        width = mountainWidth;
        strength = mountainStrength;
        terrainHeight = height;
    }

    @Override
    public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river)
    {
        simplex.riverJitter().evaluateNoise((float)x / wavelength, (float)y / wavelength, jitter);
        int pX = (int)Math.round(x + jitter.deltax() * amplitude);
        int pY = (int)Math.round(y + jitter.deltay() * amplitude);
        x = pX;
        y = pY;

        return terrainLonelyMountain(x, y, simplex, cell, river, strength, width, terrainHeight);
    }
}
