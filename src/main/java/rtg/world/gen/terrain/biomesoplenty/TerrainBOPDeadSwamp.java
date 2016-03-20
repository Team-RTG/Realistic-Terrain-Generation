package rtg.world.gen.terrain.biomesoplenty;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.terrain.HeightEffect;
import rtg.world.gen.terrain.HeightVariation;
import rtg.world.gen.terrain.JitterEffect;
import rtg.world.gen.terrain.TerrainBase;

public class TerrainBOPDeadSwamp extends TerrainBase {

    private HeightEffect height;

    public TerrainBOPDeadSwamp() {
        HeightVariation waterLand = new HeightVariation();
        waterLand.height = 2f;
        waterLand.wavelength = 40f;
        waterLand.octave = 0;

        height = new JitterEffect(5f, 10f, waterLand);

    }

    @Override
    public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {
        return 62f + height.added(simplex, cell, x, y);
    }
}
