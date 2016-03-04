package rtg.world.gen.terrain.biomesoplenty;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.terrain.TerrainBase;

public class TerrainBOPScrubland extends TerrainBase
{
    private float minHeight;
    private float maxHeight;
    private float hillStrength;
    
    // 63f, 80f, 30f
    
    public TerrainBOPScrubland(float minHeight, float maxHeight, float hillStrength)
    {
        this.minHeight = minHeight;
        this.maxHeight = (maxHeight > rollingHillsMaxHeight) ? rollingHillsMaxHeight : ((maxHeight < this.minHeight) ? rollingHillsMaxHeight : maxHeight);
        this.hillStrength = hillStrength;
    }
    
    @Override
    public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river)
    {
        return terrainRollingHills(x, y, simplex, river, hillStrength, maxHeight, groundNoise, groundNoiseAmplitudeHills, 3f);
    }
}
