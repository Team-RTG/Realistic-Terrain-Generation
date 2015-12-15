package rtg.world.gen.terrain.vanilla;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.terrain.TerrainBase;

public class TerrainVanillaTaigaM extends TerrainBase
{
    private float hHeight;
    private float hWidth;
    private float vHeight;
    private float vWidth;
    private float lHeight;
    private float lWidth;
    private float bHeight;
    
    /*
     * hillHeight = 70f
     * hillWidth = 180f
     * 
     * varHeight = 7f
     * varWidth = 100f
     * 
     * lakeHeigth = 38f
     * lakeWidth = 260f
     * 
     * baseHeight = 68f
     * 
     * 70f, 180f, 7f, 100f, 38f, 260f, 68f
     */
    public TerrainVanillaTaigaM(float hillHeight, float hillWidth, float varHeight, float varWidth, float lakeHeight, float lakeWidth, float baseHeight)
    {
        hHeight = hillHeight;
        hWidth = hillWidth;
        
        vHeight = varHeight;
        vWidth = varWidth;
        
        lHeight = lakeHeight;
        lWidth = lakeWidth;
        
        bHeight = baseHeight;
    }
    
    @Override
    public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river)
    {
        float h = simplex.noise2(x / vWidth, y / vWidth) * vHeight * river;
        h += simplex.noise2(x / 20f, y / 20f) * 2;
        
        float m = simplex.noise2(x / hWidth, y / hWidth) * hHeight * river;
        m *= m / 40f;
        
        float sm = simplex.noise2(x / 30f, y / 30f) * 8f;
        sm *= m / 20f > 3.75f ? 3.75f : m / 20f;
        m += sm;
        
        h += simplex.noise2(x / 12f, y / 12f) * 3f;
        h += simplex.noise2(x / 5f, y / 5f) * 1.5f;
        
        return bHeight + h + m;
    }
}