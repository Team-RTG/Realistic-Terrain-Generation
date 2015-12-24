package rtg.world.gen.terrain;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;

public class TerrainBase 
{
	public TerrainBase()
	{
	}

    public static final float above(float limited, float limit) {
        if (limited>limit) {
            return limited-limit;
        }
        return 0f;
    }
    
	public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river)
	{
		return 70f;
	}
}
