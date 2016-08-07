package rtg.world.gen.terrain.highlands;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.terrain.TerrainBase;

public class TerrainHLPoplarHills extends TerrainBase
{
    private float width;
    private float strength;

    public TerrainHLPoplarHills(float mountainWidth, float mountainStrength, float height)
    {

        width = mountainWidth;
        strength = mountainStrength;
        base = height;
    }

    @Override
    public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river)
    {
        float h = simplex.noise2(x / 20f, y / 20f) * 2;
        h += simplex.noise2(x / 7f, y / 7f) * 0.8f;

        float m = (simplex.noise2(x / width, y / width)+1) * strength/2 * river;
        //m *= m / 35f;
        //m = m > 70f ? 70f + (m - 70f) / 2.5f : m;

        //float st = m * 0.7f;
        //st = st > 20f ? 20f : st;
        //float c = cell.noise(x / 30f, y / 30f, 1D) * (5f + st);

        float sm = simplex.noise2(x / 30f, y / 30f) * 8f + simplex.noise2(x / 8f, y / 8f);
        //sm *= (m + 10f) / 20f > 2.5f ? 2.5f : (m + 10f) / 20f;
        m += sm;

        //m += c;

        return riverized(base + h + m,river) ;
    }
}
