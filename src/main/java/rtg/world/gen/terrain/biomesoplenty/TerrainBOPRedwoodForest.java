package rtg.world.gen.terrain.biomesoplenty;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.terrain.TerrainBase;

public class TerrainBOPRedwoodForest extends TerrainBase
{
    private float minHeight = 63f;
    private float maxHeight = 80f;
    private float hillStrength = 30f;
    
    // 63f, 80f, 30f
    
    public TerrainBOPRedwoodForest()
    {
    
    }
    
    public TerrainBOPRedwoodForest(float minHeight, float maxHeight, float hillStrength)
    {
        this.minHeight = minHeight;
        this.maxHeight = (maxHeight > 80f) ? 80f : ((maxHeight < this.minHeight) ? 80f : maxHeight);
        this.hillStrength = hillStrength;
    }
    
    @Override
    public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river)
    {
    
        float h = simplex.noise2(x / 200f, y / 200f) * 4;
        h += simplex.noise2(x / 100f, y / 100f) * 2;
        
        float m = simplex.noise2(x / 200f, y / 200f) * hillStrength * river;
        m *= m / ((hillStrength * 0.1f) + hillStrength);
        
        float sm = simplex.noise2(x / hillStrength, y / hillStrength) * 8f;
        sm *= m / 20f > 3.75f ? 3.75f : m / 20f;
        m += sm;
        
        float l = simplex.noise2(x / 260f, y / 260f) * 38f;
        l *= l / 25f;
        l = l < -8f ? -8f : l;
        
        float floNoise = maxHeight + h + m - l;
        floNoise = (floNoise < minHeight) ? minHeight : floNoise;
        
        return floNoise;
    }
}
