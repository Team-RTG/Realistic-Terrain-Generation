package rtg.world.gen.terrain.biomesoplenty;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.terrain.BumpyHillsEffect;
import rtg.world.gen.terrain.TerrainBase;

public class TerrainBOPHighland extends TerrainBase {
    private float baseHeight = 90;
    private BumpyHillsEffect onTop = new BumpyHillsEffect();

    public TerrainBOPHighland() {
        onTop.hillHeight = 30;
        onTop.hillWavelength = 60;
        onTop.spikeHeight = 10;
        onTop.spikeWavelength = 10;
    }

    @Override
    public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {
        return riverized(baseHeight + onTop.added(simplex, cell, x, y) + groundNoise(x, y, 6, simplex), river);
        //return terrainGrasslandMountains(x, y, simplex, cell, river, 4f, 80f, 68f);
    }
}
