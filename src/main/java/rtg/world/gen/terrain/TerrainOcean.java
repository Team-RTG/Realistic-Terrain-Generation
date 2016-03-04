package rtg.world.gen.terrain;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;

public class TerrainOcean extends TerrainBase
{

    public TerrainOcean()
    {

    }

    @Override
    public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river)
    {
        float h = simplex.noise2(x / 300f, y / 300f) * 40f * river;
        h = h > 3f ? 3f : h;
        h += simplex.noise2(x / 50f, y / 50f) * (12f - h) * 0.4f;
        h += simplex.noise2(x / 15f, y / 15f) * (12f - h) * 0.15f;

        float floNoise = 50f + h;
        floNoise = floNoise < minimumOceanFloor ? minimumOceanFloor : floNoise;

        return floNoise;
    }
}
