package rtg.world.gen.terrain.biomesoplenty;

import rtg.util.noise.CellNoise;
import rtg.util.noise.OpenSimplexNoise;
import rtg.world.gen.terrain.HillockEffect;
import rtg.world.gen.terrain.TerrainBase;

public class TerrainBOPHeathland extends TerrainBase {
    private float baseHeight = 68f;
    private HillockEffect hills;

    public TerrainBOPHeathland() {
        hills = new HillockEffect();
        hills.height = 30;
        hills.minimumSimplex = 0.3f;
        hills.octave = 0;
        hills.wavelength = 50f;

    }

    @Override
    public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {
        float added = groundNoise(x, y, groundNoiseAmplitudeHills, simplex);
        added += hills.added(simplex, cell, x, y);
        return riverized(baseHeight + added, river);
    }
}
