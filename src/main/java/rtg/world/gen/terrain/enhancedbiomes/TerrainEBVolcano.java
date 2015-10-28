package rtg.world.gen.terrain.enhancedbiomes;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.terrain.TerrainBase;

public class TerrainEBVolcano extends TerrainBase
{
	public TerrainEBVolcano()
	{
	}
	
	@Override
	public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float ocean, float border, float river)
	{
        float st = 15f - ((cell.noise(x / 500D, y / 500D, 1D) * 42f) + (simplex.noise2(x / 30f, y / 30f) * 2f));
        
        st = st < 0f ? 0f : st;

        float h = st;
        h = h < 0f ? 0f : h;
        h += (h * 0.4f) * ((h * 0.4f) * 2f);
        
        if(h > 10f)
        {
            float d2 = (h - 10f) / 1.5f > 30f ? 30f : (h - 10f) / 1.5f;
            h += cell.noise(x / 25D, y / 25D, 1D) * d2;
        }
        
        h += simplex.noise2(x / 18f, y / 18f) * 3;
        h += simplex.noise2(x / 8f, y / 8f) * 2;
        
        return 65f + h * border;
	}
}
