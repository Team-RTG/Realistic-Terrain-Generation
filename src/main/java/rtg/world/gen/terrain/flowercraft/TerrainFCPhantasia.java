package rtg.world.gen.terrain.flowercraft;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.terrain.GroundEffect;
import rtg.world.gen.terrain.TerrainBase;

public class TerrainFCPhantasia extends TerrainBase {

    public TerrainFCPhantasia() {

    }

    @Override
    public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {
        //return terrainPlains(x, y, simplex, river, 160f, 10f, 60f, 200f, 66f);
        return riverized(65f + groundEffect.added(simplex, cell, x, y), river);
    }

    private GroundEffect groundEffect = new GroundEffect(4f);
}
