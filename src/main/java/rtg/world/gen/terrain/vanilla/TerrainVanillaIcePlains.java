package rtg.world.gen.terrain.vanilla;

import rtg.util.noise.CellNoise;
import rtg.util.noise.OpenSimplexNoise;
import rtg.world.gen.terrain.TerrainBase;

public class TerrainVanillaIcePlains extends TerrainBase {
    public TerrainVanillaIcePlains() {
    }

    @Override
    public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {
        float base = 62;
        float b = simplex.noise2(x / 24f, y / 24f) * 0.25f;
        b *= river;
        float n = simplex.noise2(x / 16f, y / 16f) * 10f - 9f;
        n = (n < 0) ? 0f : n;
        b += n;
        return base + b;
    }
}
