package rtg.world.gen.terrain.vanilla;

import org.apache.logging.log4j.Level;

import cpw.mods.fml.common.FMLLog;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.terrain.TerrainBase;

public class TerrainVanillaOcean extends TerrainBase
{
    
    public TerrainVanillaOcean()
    {
        
    }

    @Override
    public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float ocean, float border, float river)
    {
        float h = simplex.noise2(x / 300f, y / 300f) * 40f * river;
        h = h > 3f ? 3f : h; 
        h += simplex.noise2(x / 50f, y / 50f) * (12f - h) * 0.4f;
        h += simplex.noise2(x / 15f, y / 15f) * (12f - h) * 0.15f;
        
        float floNoise = 30f + h;
        floNoise = floNoise < 6 ? 6 : floNoise;
        
        return floNoise;
    }
}
