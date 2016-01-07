package rtg.world.gen.terrain.vanilla;

import org.apache.logging.log4j.Level;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.terrain.TerrainBase;
import cpw.mods.fml.common.FMLLog;

public class TerrainVanillaJungleEdge extends TerrainBase
{
    
    public TerrainVanillaJungleEdge()
    {
    
    }
    
    @Override
    public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river)
    {
    
        float floNoise;
        float st = (simplex.noise2(x / 160f, y / 160f) + 0.38f) * 10f * river;
        st = st < 0.2f ? 0.2f : st;
        
        float h = simplex.noise2(x / 60f, y / 60f) * st * 2f;
        h = h > 0f ? -h : h;
        h += st;
        h *= h / 200f;
        h += st;
        
        floNoise = 63f + h;
        
        //FMLLog.log(Level.INFO, "floNoise = %f", floNoise);
        
        return floNoise;
    }
}
