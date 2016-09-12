package rtg.world.gen.terrain.mithwoodforest;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.terrain.TerrainBase;

public class TerrainMFMithwoodForest extends TerrainBase {

    public TerrainMFMithwoodForest() {

    }

    @Override
    public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {

        groundNoise = groundNoise(x, y, groundNoiseAmplitudeHills, simplex);

        float h = terrainPlains(x, y, simplex, river, 160f, 10f, 60f, 80f, 65f);

        return riverized(groundNoise + h, river);
    }
}
