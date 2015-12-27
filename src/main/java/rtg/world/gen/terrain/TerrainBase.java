package rtg.world.gen.terrain;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;

public class TerrainBase 
{
    protected float base; // added as most terrains have this;
	public TerrainBase(){
        this(68f);// default to marginally above sea level;
	}

    public TerrainBase(float base) {
        this.base = base;
    }

    public static final float above(float limited, float limit) {
        if (limited>limit) {
            return limited-limit;
        }
        return 0f;
    }

    public static final float stretched(float toStretch) {
        if (toStretch > 0) {
            return (float)Math.sqrt(toStretch);
        }
        //(else)
        return (-1f)*(float)Math.sqrt((-1f)*toStretch);
    }
    
	public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river)
	{
		return 70f;
	}
}
