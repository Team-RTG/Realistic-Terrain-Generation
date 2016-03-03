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
    
    public static float terrainDuneValley(int x, int y, OpenSimplexNoise simplex, CellNoise cell, float river, float valley)
    {
        float h = (simplex.noise2(x / valley, y / valley) + 0.25f) * 65f * river;
        h = h < 1f ? 1f : h;
        
        float r = cell.noise(x / 50D, y / 50D, 1D) * h * 2;
        h += r;
    
        h += simplex.noise2(x / 40f, y / 40f) * 8;
        h += simplex.noise2(x / 14f, y / 14f) * 2;
        
        return 70f + h;
    }
    
    public static float terrainFlatLakes(int x, int y, OpenSimplexNoise simplex, float river)
    {
        float h = simplex.noise2(x / 300f, y / 300f) * 40f * river;
        h = h > 3f ? 3f : h; 
        h += simplex.noise2(x / 50f, y / 50f) * (12f - h) * 0.4f;
        h += simplex.noise2(x / 15f, y / 15f) * (12f - h) * 0.15f;
        
        return 62f + h;
    }
    
    public static float terrainGrasslandFlats(int x, int y, OpenSimplexNoise simplex, float river)
    {
        float h = simplex.noise2(x / 100f, y / 100f) * 7;
        h += simplex.noise2(x / 20f, y / 20f) * 2;
        
        float m = simplex.noise2(x / 180f, y / 180f) * 70f * river;
        m *= m / 40f;
        
        float sm = simplex.noise2(x / 30f, y / 30f) * 8f;
        sm *= m / 20f > 3.75f ? 3.75f : m / 20f;
        m += sm;
        
        float l = simplex.noise2(x / 260f, y / 260f) * 38f;
        l *= l / 25f;
        l = l < -8f ? -8f : l;
        
        return 68f + h + m - l;
    }
    
    public static float terrainGrasslandHills(int x, int y, OpenSimplexNoise simplex, CellNoise cell, float river, float vWidth, float vHeight, float hWidth, float hHeight, float lWidth, float lHeight, float bHeight)
    {
        float h = simplex.noise2(x / vWidth, y / vWidth) * vHeight * river;
        h += simplex.noise2(x / 20f, y / 20f) * 2;
        
        float m = simplex.noise2(x / hWidth, y / hWidth) * hHeight * river;
        m *= m / 40f;
        
        float sm = simplex.noise2(x / 30f, y / 30f) * 8f;
        sm *= m / 20f > 3.75f ? 3.75f : m / 20f;
        m += sm;
        
        float cm = cell.noise(x / 25D, y / 25D, 1D) * 12f;
        cm *= m / 20f > 3.75f ? 3.75f : m / 20f;
        m += cm;
        
        float l = simplex.noise2(x / lWidth, y / lWidth) * lHeight;
        l *= l / 25f;
        l = l < 8f ? 8f : l;
        
        h += simplex.noise2(x / 12f, y / 12f) * 3f;
        h += simplex.noise2(x / 5f, y / 5f) * 1.5f;
        
        return bHeight + h + m - l;
    }
    
    public static float terrainGrasslandMountains(int x, int y, OpenSimplexNoise simplex, CellNoise cell, float river)
    {
        float h = simplex.noise2(x / 100f, y / 100f) * 7;
        h += simplex.noise2(x / 20f, y / 20f) * 2;
        
        float m = simplex.noise2(x / 230f, y / 230f) * 120f * river;
        m *= m / 35f;
        m = m > 70f ? 70f + (m - 70f) / 2.5f : m;
        
        float c = cell.noise(x / 30f, y / 30f, 1D) * (m * 0.30f);
        
        float sm = simplex.noise2(x / 30f, y / 30f) * 8f + simplex.noise2(x / 8f, y / 8f);
        sm *= m / 20f > 2.5f ? 2.5f : m / 20f;
        m += sm;
        
        m += c;
        
        float l = simplex.noise2(x / 260f, y / 260f) * 38f;
        l *= l / 25f;
        l = l < -8f ? -8f : l;
        
        return 68f + h + m - l;
    }
    
    public static float terrainHighland(int x, int y, OpenSimplexNoise simplex, CellNoise cell, float river, float start, float width, float height)
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
        
        return getTerrainBase() + h;
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
