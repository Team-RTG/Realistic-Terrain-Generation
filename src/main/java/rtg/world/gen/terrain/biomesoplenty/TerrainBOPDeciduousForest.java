package rtg.world.gen.terrain.biomesoplenty;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.terrain.TerrainBase;

public class TerrainBOPDeciduousForest extends TerrainBase
{
    private float baseHeight = 76f;
    private float hillStrength = 30f;
    
    public TerrainBOPDeciduousForest()
    {
    
    }
    
    public TerrainBOPDeciduousForest(float bh, float hs)
    {
        baseHeight = bh;
        hillStrength = hs;
    }
    
    @Override
    public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river)
    {
    
        float h = groundNoise(x, y, groundNoiseAmplitudeHills, simplex);
        
        float m = hills(x, y, hillStrength, simplex, river);
        
        return baseHeight + h + m;
    }
}
