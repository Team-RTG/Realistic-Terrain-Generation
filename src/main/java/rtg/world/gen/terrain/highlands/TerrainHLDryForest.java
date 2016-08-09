package rtg.world.gen.terrain.highlands;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.util.SimplexOctave;
import rtg.world.gen.terrain.TerrainBase;

public class TerrainHLDryForest extends TerrainBase {

    private float start;
    private float height;
    private float width;
    private float wavelength = 15;
    private float amplitude = 2;
    private SimplexOctave.Disk jitter = new SimplexOctave.Disk();

    public TerrainHLDryForest(float hillStart, float landHeight, float baseHeight, float hillWidth) {

        start = hillStart;
        height = landHeight;
        base = baseHeight;
        width = hillWidth;
    }

    @Override
    public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border,
                               float river) {

        simplex.riverJitter().evaluateNoise((float)x / wavelength, (float)y / wavelength, jitter);
        float pX = (float)((float)x + jitter.deltax() * amplitude);
        float pY = (float)((float)y + jitter.deltay() * amplitude);

        // base hills everywhere but with some spacing.
        float h = blendedHillHeight(Math.abs(simplex.noise2(pX / width, pY / width)),0.3f) * height * river;
        h = h < start ? start + ((h - start) / 4.5f) : h;

        if (h > 0f) {
            float st = h * 1.5f > 15f ? 15f : h * 1.5f;
            h += blendedHillHeight((float)simplex.noise(pX / 70D, pY / 70D, 1D)) * st;
        }

        h = above(h,10f);

        h += this.groundNoise(pX, pY, 4f, simplex);

        return riverized(base + h,river);
    }
}
