package rtg.world.gen.terrain.enhancedbiomes;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.terrain.TerrainBase;

public class TerrainEBForestedMountains extends TerrainBase
{

    private float width;
    private float strength;
    private float terrainHeight;

    /*
     * width = 230f strength = 120f lake = 50f;
     *
     * 230f, 120f, 50f
     */

    public TerrainEBForestedMountains(float mountainWidth, float mountainStrength)
    {

        this(mountainWidth, mountainStrength,68f);
    }

    public TerrainEBForestedMountains(float mountainWidth, float mountainStrength, float height)
    {

        width = mountainWidth;
        strength = mountainStrength;
        terrainHeight = height;
    }

    @Override
    public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river)
    {
        return terrainLonelyMountain(x, y, simplex, cell, river, strength, width, terrainHeight);
    }
}
