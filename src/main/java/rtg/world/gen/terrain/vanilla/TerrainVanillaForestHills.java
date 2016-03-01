package rtg.world.gen.terrain.vanilla;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.terrain.TerrainBase;

public class TerrainVanillaForestHills extends TerrainBase
{
    private float baseHeight = 76f;
    private float hillStrength = 30f;
    
    public TerrainVanillaForestHills()
    {
    
    }
    
    public TerrainVanillaForestHills(float bh, float hs)
    {
        baseHeight = bh;
        hillStrength = hs;
    }
    
    @Override
    public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river)
    {

        float h = groundNoise(x, y, 6f, simplex);

        float m = hills(x, y, hillStrength, simplex, river);
        
        float l = simplex.noise2(x / 260f, y / 260f) * 38f;
        l *= l / 25f;
        l = l < -8f ? -8f : l;
        
        return baseHeight + h + m;
    }
}
