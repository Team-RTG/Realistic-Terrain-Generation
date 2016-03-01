package rtg.world.gen.terrain;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;

public class TerrainRollingHills extends TerrainBase
{
    private float minHeight = 63f;
    private float maxHeight = 80f;
    private float hillStrength = 30f;
    
    // 63f, 80f, 30f
    
    public TerrainRollingHills(float minHeight, float maxHeight, float hillStrength)
    {
        this.minHeight = minHeight;
        this.maxHeight = (maxHeight > 80f) ? 80f : ((maxHeight < this.minHeight) ? 80f : maxHeight);
        this.hillStrength = hillStrength;
    }
    
    @Override
    public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river)
    {
    
        groundNoise = groundNoise(x, y, groundNoiseAmplitudeHills, simplex);
        
        float m = hills(x, y, hillStrength, simplex, river);
        
        float floNoise = maxHeight + groundNoise + m;
        floNoise = (floNoise < minHeight) ? minHeight : floNoise;
        
        return floNoise;
    }
}
