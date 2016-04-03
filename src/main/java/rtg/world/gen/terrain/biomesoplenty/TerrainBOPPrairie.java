package rtg.world.gen.terrain.biomesoplenty;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.terrain.TerrainBase;

public class TerrainBOPPrairie extends TerrainBase
{
    private float minHeight;
    private float maxHeight;
    private float hillStrength;

    // 63f, 80f, 30f

    public TerrainBOPPrairie(float minHeight, float maxHeight, float hillStrength)
    {
        this.minHeight = minHeight;
        this.maxHeight = (maxHeight > rollingHillsMaxHeight) ? rollingHillsMaxHeight : ((maxHeight < this.minHeight) ? rollingHillsMaxHeight : maxHeight);
        this.hillStrength = hillStrength;
    }

    @Override
    public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river)
    {
        return this.terrainPlains(x, y, simplex, river, 200f, 1f, 30f, 1f,  maxHeight);
    }
}
