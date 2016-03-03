package rtg.world.gen.terrain;

import rtg.config.rtg.ConfigRTG;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;

public class TerrainBase 
{
    protected float base; // added as most terrains have this;
    protected final float minOceanFloor; // The lowest Y coord an ocean floor is allowed to be.
    protected final float groundNoiseAmplitudeHills;
    protected float groundNoise;
    protected final float groundVariation;
    protected final float rollingHillsMaxHeight;
    
	public TerrainBase(){
        this(68f);// default to marginally above sea level;
	}

    public TerrainBase(float base) {
        this.base = base;
        this.minOceanFloor = 30f;
        this.groundVariation = 2f;
        this.groundNoise = this.base;
        this.groundNoiseAmplitudeHills = 6f;
        this.rollingHillsMaxHeight = 80f;
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
    
    public static float getTerrainBase()
    {
        return 68f;
    }
    
    public static float terrainCanyon(int x, int y, OpenSimplexNoise simplex, float river, float[] height, float border, float strength, int heightLength, boolean booRiver)
    {
        //float b = simplex.noise2(x / cWidth, y / cWidth) * cHeigth * river;
        //b *= b / cStrength;
        river *= 1.3f;
        river = river > 1f ? 1f : river;
        float r = simplex.noise2(x / 100f, y / 100f) * 50f;
        r = r < -7.4f ? -7.4f : r > 7.4f ? 7.4f : r;
        float b = (17f + r) * river;
        
        float hn = simplex.noise2(x / 12f, y / 12f) * 0.5f;
        float sb = 0f;
        if(b > 0f)
        {
            sb = b;
            sb = sb < 0f ? 0f : sb > 7f ? 7f : sb;
            sb = hn * sb;
        }
        b += sb;

        float cTotal = 0f;
        float cTemp = 0f;
        
        for(int i = 0; i < heightLength; i += 2)
        {
            cTemp = 0;
            if(b > height[i] && border > 0.6f + (height[i] * 0.015f) + hn * 0.2f)
            {
                cTemp = b > height[i] + height[i + 1] ? height[i + 1] : b - height[i];
                cTemp *= strength;
            }
            cTotal += cTemp;
        }
        
        
        float bn = 0f;
        if(booRiver)
        {
            if(b < 5f)
            {
                bn = 5f - b;
                for(int i = 0; i < 3; i++)
                {
                    bn *= bn / 4.5f;
                }
            }
        }
        else if(b < 5f)
        {
            bn = (simplex.noise2(x / 7f, y / 7f) * 1.3f + simplex.noise2(x / 15f, y / 15f) * 2f) * (5f - b) * 0.2f;
        }
        
        b += cTotal - bn;
        
        return getTerrainBase() + b;
    }
    
    public static float terrainDunes(int x, int y, OpenSimplexNoise simplex, CellNoise cell, float river)
    {
        float st = (simplex.noise2(x / 160f, y / 160f) + 0.38f) * (ConfigRTG.duneHeight + 23f);
        st = st < 0.2f ? 0.2f : st;
        
        float h = simplex.noise2(x / 60f, y / 60f) * st * 2f;
        h = h > 0f ? -h : h;
        h += st;
        h *= h / 50f;
        h += st;
        
        if(h < 10f)
        {
            float d = (h - 10f) / 2f;
            d = d > 4f ? 4f : d;
            h += cell.noise(x / 25D, y / 25D, 1D) * d;
            h += simplex.noise2(x / 30f, y / 30f) * d;
            h += simplex.noise2(x / 14f, y / 14f) * d * 0.5f;
        }
        
        return 70f + (h * river);
    }
    
    public static float terrainFlatLakes(int x, int y, OpenSimplexNoise simplex, float river)
    {
        float h = simplex.noise2(x / 300f, y / 300f) * 40f * river;
        h = h > 3f ? 3f : h; 
        h += simplex.noise2(x / 50f, y / 50f) * (12f - h) * 0.4f;
        h += simplex.noise2(x / 15f, y / 15f) * (12f - h) * 0.15f;
        
        return 62f + h;
    }
    
    public static float terrainPolar(int x, int y, OpenSimplexNoise simplex, float river)
    {
        float st = (simplex.noise2(x / 160f, y / 160f) + 0.38f) * (ConfigRTG.duneHeight + 23f) * river;
        st = st < 0.2f ? 0.2f : st;
        
        float h = simplex.noise2(x / 60f, y / 60f) * st * 2f;
        h = h > 0f ? -h : h;
        h += st;
        h *= h / 50f;
        h += st;
        
        return 70f + h;
    }
}
