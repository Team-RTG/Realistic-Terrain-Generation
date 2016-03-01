package rtg.world.gen.terrain.biomesoplenty;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.terrain.TerrainBase;

public class TerrainBOPSeasonalForestClearing extends TerrainBase
{
    private float minHeight;
    private float maxHeight;
    private float hillStrength;
    
    // 63f, 80f, 30f

    public TerrainBOPSeasonalForestClearing(float minHeight, float maxHeight, float hillStrength)
    {
        this.minHeight = minHeight;
        this.maxHeight = (maxHeight > 80f) ? 80f : ((maxHeight < this.minHeight) ? 80f : maxHeight);
        this.hillStrength = hillStrength;
    }
    
    @Override
    public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river)
    {
    
        float h = groundNoise(x, y, groundNoiseAmplitudeHills, simplex);
        
        float m = hills(x, y, hillStrength, simplex, river);
        
        float floNoise = maxHeight + h + m;
        floNoise = (floNoise < minHeight) ? minHeight : floNoise;
        
        return floNoise+4;
    }
}
