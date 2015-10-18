package rtg.world.gen.terrain.vanilla;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.terrain.TerrainBase;

public class TerrainVanillaSavannaPlateau extends TerrainBase
{
    private float start;
    private float height;
    private float base;
    private float width;
    
    /**
     * 
     * @param hillStart The Y coord to start calculations?
     * @param landHeight The max Y coord to generate terrain (fuzzy)?
     * @param baseHeight The Y cood to start to incline of the hill?
     * @param hillWidth The horizontal distance of the hill in blocks?
     */
    public TerrainVanillaSavannaPlateau(float hillStart, float landHeight, float baseHeight, float hillWidth)
    {
        start = hillStart;
        height = landHeight;
        base = baseHeight;
        width = hillWidth;
    }
    
    @Override
    public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float ocean, float border, float river)
    {
        float h = simplex.noise2(x / width, y / width) * height * river;
        h = h < start ? start + ((h - start) / 4.5f) : h;
        
        if(h > 0f)
        {
            float st = h * 1.5f > 15f ? 15f : h * 1.5f;
            h += cell.noise(x / 70D, y / 70D, 1D) * st;
        }
        
        h += simplex.noise2(x / 20f, y / 20f) * 5f;
        h += simplex.noise2(x / 12f, y / 12f) * 3f;
        h += simplex.noise2(x / 5f, y / 5f) * 1.5f;
        
        return base + h;
    }
}
