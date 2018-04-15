package rtg.api.world.terrain;

import rtg.api.RTGAPI;
import rtg.api.config.RTGConfig;
import rtg.api.util.WorldUtil.Terrain;
import rtg.api.util.noise.CellNoise;
import rtg.api.util.noise.OpenSimplexNoise;
import rtg.api.world.IRTGWorld;
import rtg.api.world.terrain.heighteffect.VariableRuggednessEffect;

@SuppressWarnings("WeakerAccess")
public abstract class TerrainBase {

    private static final float minimumOceanFloor = 20.01f; // The lowest Y coord an ocean floor is allowed to be.
    private static final float minimumDuneHeight = 21f; // The strength factor to which the dune height config option is added.
    protected final float minDuneHeight; // The strength factor to which the dune height config option is added.
    protected final float groundNoiseAmplitudeHills;
    protected final float groundVariation;
    protected final float rollingHillsMaxHeight;
    protected float base; // added as most terrains have this;
    protected float groundNoise;
    protected RTGConfig rtgConfig = RTGAPI.config();

    public TerrainBase() {

        this(68f);// default to marginally above sea level;
    }

    public TerrainBase(float base) {

        this.base = base;
        this.minDuneHeight = minimumDuneHeight;
        this.groundVariation = 2f;
        this.groundNoise = this.base;
        this.groundNoiseAmplitudeHills = 6f;
        this.rollingHillsMaxHeight = 80f;
    }

    public static float blendedHillHeight(float simplex) {
        // this takes a simplex supposed to vary from -1 to 1
        // and produces an output which varies from 0 to 1 non-linearly
        // with the value of 0 mapped to about 0.15 and smooth transition
        // the purpose is to make hills above plains without significant deadvalleys
        float result = simplex + 1;
        result = result * result * result + 10;
        result = (float) Math.pow(result, .33333333333333);
        result = result / 0.46631f;// this is the different between the values for -1 and 1,
        //so normalizing to a distance of 1
        result = result - 4.62021f;// subtracting the result for input -1 so we actually get 0 to 1
        return result;
    }

    public static float blendedHillHeight(float simplex, float turnAt) {
        // like blendedHillHeight, but the effect of zero occurs at the turnAt parameter instead
        float adjusted = (1f - (1f - simplex) / (1f - turnAt));
        return blendedHillHeight(adjusted);
    }

    public static float above(float limited, float limit) {

        if (limited > limit) {
            return limited - limit;
        }
        return 0f;
    }

    public static float unsignedPower(float number, float power) {

        if (number > 0) {
            return (float) Math.pow(number, power);
        }
        //(else)
        return (-1f) * (float) Math.pow((-1f) * number, power);
    }

    public static float hills(float x, float y, float hillStrength, OpenSimplexNoise simplex) {

        float m = simplex.noise2(x / 150f, y / 150f);
        m = blendedHillHeight(m, 0.2f);

        float sm = simplex.octave(2).noise2(x / 55, y / 55);// there are artifacts if this is close to a multiple of 16
        sm = blendedHillHeight(sm, 0.2f);
        //sm = sm*0.8f;
        sm *= sm * m;
        m += sm / 3f;

        return m * hillStrength ;
    }

    public static float groundNoise(int x, int y, float amplitude, OpenSimplexNoise simplex) {

        float h = blendedHillHeight(simplex.noise2(x / 49f, y / 49f), 0.2f) * amplitude;
        h += blendedHillHeight(simplex.octave(1).noise2(x / 23f, y / 23f), 0.2f) * amplitude / 2f;
        h += blendedHillHeight(simplex.octave(2).noise2(x / 11f, y / 11f), 0.2f) * amplitude / 4f;
        return h;
    }

    public static float groundNoise(float x, float y, float amplitude, OpenSimplexNoise simplex) {

        float h = blendedHillHeight(simplex.noise2(x / 49f, y / 49f), 0.2f) * amplitude;
        h += blendedHillHeight(simplex.octave(1).noise2(x / 23f, y / 23f), 0.2f) * amplitude / 2f;
        h += blendedHillHeight(simplex.octave(2).noise2(x / 11f, y / 11f), 0.2f) * amplitude / 4f;
        return h;
    }

    public static float getTerrainBase() {

        return 68f;
    }

    public static float getTerrainBase(float river) {

        return 62f + 6f * river;
    }

    public static float mountainCap(float m) {
        // heights can "blow through the ceiling" so pull more extreme values down a bit

        if (m > 160) {
            m = 160 + (m - 160) * .75f;
            if (m > 180) {
                m = 180 + (m - 180f) * .75f;
            }
        }
        return m;
    }

    public static float riverized(float height, float river) {

        if (height < 62.45f) {
            return height;
        }
        // experimental adjustment to make riverbanks more varied
        float adjustment = (height - 62.45f)/10f + .6f;
        river = Terrain.bayesianAdjustment(river, adjustment);
        return 62.45f + (height - 62.45f) * river;
        }

    public static float terrainBeach(int x, int y, OpenSimplexNoise simplex, float river, float baseHeight) {
        return riverized(baseHeight + TerrainBase.groundNoise(x, y, 4f, simplex),river);
    }

    public static float terrainBryce(int x, int y, OpenSimplexNoise simplex, float river, float height) {

        float b = 0;
        float n;
        float sn = simplex.noise2(x / 2f, y / 2f) * 0.5f + 0.5f;
        sn += simplex.noise2(x, y) * 0.2 + 0.2;
        sn += simplex.noise2(x / 4f, y / 4f) * 4f + 4f;
        sn += simplex.noise2(x / 8f, y / 8f) * 2f + 2f;
        n = height / sn * 2;
        n += simplex.noise2(x / 64f, y / 64f) * 4f;
        n = (sn < 6) ? n : 0f;
        b += n;
        return riverized(getTerrainBase() + b, river);
    }

    public static float terrainCanyon(int x, int y, OpenSimplexNoise simplex, float river, float[] height, float border, float strength, int heightLength, boolean booRiver) {
        //float b = simplex.noise2(x / cWidth, y / cWidth) * cHeigth * river;
        //b *= b / cStrength;
        //river *= 1.3f;
        //river = river > 1f ? 1f : river;
        float r = simplex.noise2(x / 100f, y / 100f) * 50f * river;
        r = r < -7.4f ? -7.4f : r > 7.4f ? 7.4f : r;
        float b = (17f + r) * river;

        float hn = simplex.noise2(x / 12f, y / 12f) * 0.5f;
        float sb = 0f;
        if (b > 0f) {
            sb = b;
            sb = sb > 7f ? 7f : sb;
            sb = hn * sb * river;
        }
        b += sb;

        float cTotal = 0f;
        float cTemp;

        for (int i = 0; i < heightLength; i += 2) {
            cTemp = 0;
            if (b > height[i] && border > 0.6f + (height[i] * 0.015f) + hn * 0.2f) {
                cTemp = b > height[i] + height[i + 1] ? height[i + 1] : b - height[i];
                cTemp *= strength;
            }
            cTotal += cTemp;
        }


        float bn = 0f;
        if (booRiver) {
            if (b < 5f) {
                bn = 5f - b;
                for (int i = 0; i < 3; i++) {
                    bn *= bn / 4.5f;
                }
            }
        }
        else if (b < 5f) {
            bn = (simplex.noise2(x / 7f, y / 7f) * 1.3f + simplex.noise2(x / 15f, y / 15f) * 2f) * (5f - b) * 0.2f;
        }

        b += cTotal - bn;

        return getTerrainBase(river) + b;
    }

    public static float terrainFlatLakes(int x, int y, OpenSimplexNoise simplex, float river, float baseHeight) {
        /*float h = simplex.noise2(x / 300f, y / 300f) * 40f * river;
        h = h > hMax ? hMax : h;
        h += simplex.noise2(x / 50f, y / 50f) * (12f - h) * 0.4f;
        h += simplex.noise2(x / 15f, y / 15f) * (12f - h) * 0.15f;*/

        float ruggedNoise = simplex.octave(1).noise2(x / VariableRuggednessEffect.STANDARD_RUGGEDNESS_WAVELENGTH
            , y / VariableRuggednessEffect.STANDARD_RUGGEDNESS_WAVELENGTH);

        ruggedNoise = blendedHillHeight(ruggedNoise);
        float h = groundNoise(x, y, 2f * (ruggedNoise + 1f), simplex);// ground noise
        return riverized(baseHeight + h, river);
    }

    public static float terrainGrasslandFlats(int x, int y, OpenSimplexNoise simplex, float river, float mPitch, float baseHeight) {

        float h = simplex.noise2(x / 100f, y / 100f) * 7;
        h += simplex.noise2(x / 20f, y / 20f) * 2;

        float m = simplex.noise2(x / 180f, y / 180f) * 35f * river;
        m *= m / mPitch;

        float sm = blendedHillHeight(simplex.noise2(x / 30f, y / 30f)) * 8f;
        sm *= m / 20f > 3.75f ? 3.75f : m / 20f;
        m += sm;

        return riverized(baseHeight + h + m, river);
    }

    public static float terrainGrasslandHills(int x, int y, OpenSimplexNoise simplex, float river, float vWidth, float vHeight, float hWidth, float hHeight, float bHeight) {

        float h = simplex.noise2(x / vWidth, y / vWidth);
        h = blendedHillHeight(h, 0.3f);

        float m = simplex.octave(1).noise2(x / hWidth, y / hWidth);
        m = blendedHillHeight(m, 0.3f) * h;
        m *= m;

        h *= vHeight * river;
        m *= hHeight * river;

        h += TerrainBase.groundNoise(x, y, 4f, simplex);

        return riverized(bHeight + h, river)  + m;
    }

    public static float terrainGrasslandMountains(int x, int y, OpenSimplexNoise simplex, float river, float hFactor, float mFactor, float baseHeight) {

        float h = simplex.noise2(x / 100f, y / 100f) * hFactor;
        h += simplex.noise2(x / 20f, y / 20f) * 2;

        float m = simplex.noise2(x / 230f, y / 230f) * mFactor * river;
        m *= m / 35f;
        m = m > 70f ? 70f + (m - 70f) / 2.5f : m;

        float c = (float) simplex.octave(4).noise(x / 30f, y / 30f, 1D) * (m * 0.30f);

        float sm = simplex.noise2(x / 30f, y / 30f) * 8f + simplex.noise2(x / 8f, y / 8f);
        sm *= m / 20f > 2.5f ? 2.5f : m / 20f;
        m += sm;

        m += c;

        return riverized(baseHeight + h + m, river);
    }

    public static float terrainHighland(float x, float y, OpenSimplexNoise simplex, float river, float start, float width, float height, float baseAdjust) {

        float h = simplex.noise2(x / width, y / width) * height * river; //-140 to 140
        h = h < start ? start + ((h - start) / 4.5f) : h;

        if (h < 0f) {
            h = 0;//0 to 140
        }
        if (h > 0f) {
            float st = h * 1.5f > 15f ? 15f : h * 1.5f;// 0 to 15
            h += simplex.octave(4).noise(x / 70D, y / 70D, 1D) * st;// 0 to 155
            h = h * river;
        }

        h += blendedHillHeight(simplex.noise2(x / 20f, y / 20f), 0f) * 4f;
        h += blendedHillHeight(simplex.noise2(x / 12f, y / 12f), 0f) * 2f;
        h += blendedHillHeight(simplex.noise2(x / 5f, y / 5f), 0f) * 1f;

        if (h < 0) {
            h = h / 2f;
        }

        if (h < -3) {
            h = (h + 3f) / 2f - 3f;
        }

        return getTerrainBase(river) + (h + baseAdjust) * river;
    }

    public static float terrainLonelyMountain(int x, int y, OpenSimplexNoise simplex, float river, float strength, float width, float terrainHeight) {

        float h = blendedHillHeight(simplex.noise2(x / 20f, y / 20f), 0) * 3;
        h += blendedHillHeight(simplex.noise2(x / 7f, y / 7f), 0) * 1.3f;

        float m = simplex.noise2(x / width, y / width) * strength * river;
        m *= m / 35f;
        m = m > 70f ? 70f + (m - 70f) / 2.5f : m;

        float st = m * 0.7f;
        st = st > 20f ? 20f : st;
        float c = (float) simplex.octave(4).noise(x / 30f, y / 30f, 1D) * (5f + st);

        float sm = simplex.noise2(x / 30f, y / 30f) * 8f + simplex.noise2(x / 8f, y / 8f);
        sm *= (m + 10f) / 20f > 2.5f ? 2.5f : (m + 10f) / 20f;
        m += sm;

        m += c;

        // the parameters can "blow through the ceiling" so pull more extreme values down a bit
        // this should allow a height parameter up to about 120
        if (m > 90) {
            m = 90f + (m - 90f) * .75f;
            if (m > 110) {
                m = 110f + (m - 110f) * .75f;
            }
        }
        return riverized(terrainHeight + h + m, river);
    }

    public static float terrainMarsh(int x, int y, OpenSimplexNoise simplex, float baseHeight, float river) {

        float h = simplex.noise2(x / 130f, y / 130f) * 20f;

        h += simplex.noise2(x / 12f, y / 12f) * 2f;
        h += simplex.noise2(x / 18f, y / 18f) * 4f;

        h = h < 8f ? 0f : h - 8f;

        if (h == 0f) {
            h += simplex.noise2(x / 20f, y / 20f) + simplex.noise2(x / 5f, y / 5f);
            h *= 2f;
        }

        return riverized(baseHeight + h,river);
    }

    public static float terrainOcean(int x, int y, OpenSimplexNoise simplex, float river, float averageFloor) {

        float h = simplex.noise2(x / 300f, y / 300f) * 8f * river;
        //h = h > 3f ? 3f : h;
        h += simplex.noise2(x / 50f, y / 50f) * 2f;
        h += simplex.noise2(x / 15f, y / 15f) * 1f;

        float floNoise = averageFloor + h;
        floNoise = floNoise < minimumOceanFloor ? minimumOceanFloor : floNoise;

        return floNoise;
    }

    public static float terrainOceanCanyon(int x, int y, OpenSimplexNoise simplex, float river, float[] height, float border, float strength, int heightLength, boolean booRiver) {
        //float b = simplex.noise2(x / cWidth, y / cWidth) * cHeigth * river;
        //b *= b / cStrength;
        river *= 1.3f;
        river = river > 1f ? 1f : river;
        float r = simplex.noise2(x / 100f, y / 100f) * 50f;
        r = r < -7.4f ? -7.4f : r > 7.4f ? 7.4f : r;
        float b = (17f + r) * river;

        float hn = simplex.noise2(x / 12f, y / 12f) * 0.5f;
        float sb = 0f;
        if (b > 0f) {
            sb = b;
            sb = sb > 7f ? 7f : sb;
            sb = hn * sb;
        }
        b += sb;

        float cTotal = 0f;
        float cTemp;

        for (int i = 0; i < heightLength; i += 2) {
            cTemp = 0;
            if (b > height[i] && border > 0.6f + (height[i] * 0.015f) + hn * 0.2f) {
                cTemp = b > height[i] + height[i + 1] ? height[i + 1] : b - height[i];
                cTemp *= strength;
            }
            cTotal += cTemp;
        }


        float bn = 0f;
        if (booRiver) {
            if (b < 5f) {
                bn = 5f - b;
                for (int i = 0; i < 3; i++) {
                    bn *= bn / 4.5f;
                }
            }
        }
        else if (b < 5f) {
            bn = (simplex.noise2(x / 7f, y / 7f) * 1.3f + simplex.noise2(x / 15f, y / 15f) * 2f) * (5f - b) * 0.2f;
        }

        b += cTotal - bn;

        float floNoise = 30f + b;
        floNoise = floNoise < minimumOceanFloor ? minimumOceanFloor : floNoise;

        return floNoise;
    }

    public static float terrainPlains(int x, int y, OpenSimplexNoise simplex, float river, float stPitch, float stFactor, float hPitch, float hDivisor, float baseHeight) {

        float floNoise;
        float st = (simplex.noise2(x / stPitch, y / stPitch) + 0.38f) * stFactor * river;
        st = st < 0.2f ? 0.2f : st;

        float h = simplex.noise2(x / hPitch, y / hPitch) * st * 2f;
        h = h > 0f ? -h : h;
        h += st;
        h *= h / hDivisor;
        h += st;

        floNoise = riverized(baseHeight + h, river);
        return floNoise;
    }

    public static float terrainPlateau(float x, float y, OpenSimplexNoise simplex, float river, float[] height, float border, float strength, int heightLength, float selectorWaveLength, boolean isM) {

        river = river > 1f ? 1f : river;
        float border2 = border * 4 - 2.5f;
        border2 = border2 > 1f ? 1f : (border2 < 0f) ? 0f : border2;
        float b = simplex.noise2(x / 40f, y / 40f) * 1.5f;

        float sn = simplex.noise2(x / selectorWaveLength, y / selectorWaveLength) * 0.5f + 0.5f;
        sn *= border2;
        sn *= river;
        sn += simplex.noise2(x / 4f, y / 4f) * 0.01f + 0.01f;
        sn += simplex.noise2(x / 2f, y / 2f) * 0.01f + 0.01f;
        float n, hn, stepUp;
        for (int i = 0; i < heightLength; i += 2) {
            n = (sn - height[i + 1]) / (1 - height[i + 1]);
            n = n * strength;
            n = (n < 0f) ? 0f : (n > 1f) ? 1f : n;
            hn = height[i] * 0.5f * ((sn * 2f) - 0.4f);
            hn = (hn < 0) ? 0f : hn;
            stepUp = 0f;
            if (sn > height[i + 1]) {
                stepUp += (height[i] * n);
                if (isM) {
                    stepUp += simplex.noise2(x / 20f, y / 20f) * 3f * n;
                    stepUp += simplex.noise2(x / 12f, y / 12f) * 2f * n;
                    stepUp += simplex.noise2(x / 5f, y / 5f) * 1f * n;
                }
            }
            if (i == 0 && stepUp < hn) {
                b += hn;
            }
            stepUp = (stepUp < 0) ? 0f : stepUp;
            b += stepUp;
        }
        if (isM) {
            b += simplex.noise2(x / 12, y / 12) * sn;
        }
        //Counteracts smoothing
        b /= border;

        return riverized(getTerrainBase(), river) + b;
    }

    public static float terrainPolar(float x, float y, OpenSimplexNoise simplex, float river, float stPitch, float stFactor, float hPitch, float hDivisor, float baseHeight) {

        float floNoise;
        float st = (simplex.noise2(x / stPitch, y / stPitch) + 0.38f) * stFactor * river;
        st = st < 0.1f ? 0.1f : st;

        float h = simplex.noise2(x / hPitch, y / hPitch) * st * 2f;
        h = h > 0f ? -h : h;
        h += st;
        h *= h / hDivisor;
        h += st;

        floNoise = riverized(baseHeight + h, river);
        return floNoise;
    }

    public static float terrainRollingHills(int x, int y, OpenSimplexNoise simplex, float river, float hillStrength, float addedHeight, float groundNoiseAmplitudeHills, float lift) {

        float groundNoise = groundNoise(x, y, groundNoiseAmplitudeHills, simplex);

        float m = hills(x, y, hillStrength, simplex);

        float floNoise = addedHeight + groundNoise + m;

        return riverized(floNoise + lift, river);
    }

    public static float terrainRollingHills(int x, int y, OpenSimplexNoise simplex, float river, float hillStrength, float groundNoiseAmplitudeHills, float baseHeight) {

        float groundNoise = groundNoise(x, y, groundNoiseAmplitudeHills, simplex);

        float m = hills(x, y, hillStrength, simplex);

        float floNoise = groundNoise + m;

        return riverized(floNoise + baseHeight, river);
    }

    public static float terrainSwampMountain(int x, int y, OpenSimplexNoise simplex, float river, float width, float heigth, float hMax, float hDivisor, float baseHeight) {

        float h = simplex.noise2(x / width, y / width) * heigth * river;
        h *= h / hDivisor;
        h = h > hMax ? hMax : h;

        if (h < 14f) {
            h += simplex.noise2(x / 25f, y / 25f) * (14f - h) * 0.8f;
        }

        if (h < 6) {
            h = 6f - ((6f - h) * 0.07f) + simplex.noise2(x / 20f, y / 20f) + simplex.noise2(x / 5f, y / 5f);
        }

        if (h > 10f) {
            float d = (h - 10f) / 2f > 8f ? 8f : (h - 10f) / 2f;
            h += simplex.noise2(x / 35f, y / 35f) * d;
            h += simplex.noise2(x / 60f, y / 60f) * d * 0.5f;

            if (h > 35f) {
                float d2 = (h - 35f) / 1.5f > 30f ? 30f : (h - 35f) / 1.5f;
                h += simplex.octave(4).noise(x / 25D, y / 25D, 1D) * d2;
            }
        }

        if (h > 2f) {
            float d = (h - 2f) / 2f > 4f ? 4f : (h - 2f) / 2f;
            h += simplex.noise2(x / 28f, y / 28f) * d;
            h += simplex.noise2(x / 18f, y / 18f) * (d / 2f);
            h += simplex.noise2(x / 8f, y / 8f) * (d / 2f);
        }

        return riverized(h + baseHeight, river);
    }

    public static float terrainVolcano(int x, int y, OpenSimplexNoise simplex, CellNoise cell, float border, float baseHeight) {

        float st = 15f - ((cell.noise(x / 500D, y / 500D, 1D) * 42f) + (simplex.noise2(x / 30f, y / 30f) * 2f));

        st = st < 0f ? 0f : st;

        float h = st;
        h = h < 0f ? 0f : h;
        h += (h * 0.4f) * ((h * 0.4f) * 2f);

        if (h > 10f) {
            float d2 = (h - 10f) / 1.5f > 30f ? 30f : (h - 10f) / 1.5f;
            h += cell.noise(x / 25D, y / 25D, 1D) * d2;
        }

        h += simplex.noise2(x / 18f, y / 18f) * 3;
        h += simplex.noise2(x / 8f, y / 8f) * 2;

        return baseHeight + h * border;
    }

    public abstract float generateNoise(IRTGWorld rtgWorld, int x, int y, float border, float river);
}
