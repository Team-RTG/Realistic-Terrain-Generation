package rtg.world.gen.terrain;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;

public class TerrainBase 
{
    protected float base; // added as most terrains have this;
    protected final float minOceanFloor; // The lowest Y coord an ocean floor is allowed to be.
    protected final float groundNoiseAmplitudeHills;
    protected float groundNoise;
    protected final float groundVariation;
    
	public TerrainBase(){
        this(68f);// default to marginally above sea level;
	}

    public TerrainBase(float base) {
        this.base = base;
        this.minOceanFloor = 30f;
        this.groundVariation = 2f;
        this.groundNoise = this.base;
        this.groundNoiseAmplitudeHills = 6f;
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

    public static final float unsignedPower(float number, float power) {
        if (number > 0) {
            return (float)Math.pow(number,power);
        }
        //(else)
        return (-1f)*(float)Math.pow((-1f)*number,power);
    }
    
	public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river)
	{
		return 70f;
	}

    public static float hills(int x, int y, float hillStrength, OpenSimplexNoise simplex, float river) {

        float m = simplex.noise2(x / 200f, y / 200f) * hillStrength * river;
        m *= m / ((hillStrength * 0.1f) + hillStrength);

        float sm = simplex.noise2(x / hillStrength, y / hillStrength) * 8f;
        sm *= m / 20f > 3.75f ? 3.75f : m / 20f;
        m += sm;

        return m;
    }

    public static float groundNoise(int x, int y, float amplitude, OpenSimplexNoise simplex) {
        float h = simplex.noise2(x / 49f, y / 49f) * amplitude;
        h += simplex.noise2(x / 23f, y / 23f) * amplitude/2;
        return h;
    }
}
