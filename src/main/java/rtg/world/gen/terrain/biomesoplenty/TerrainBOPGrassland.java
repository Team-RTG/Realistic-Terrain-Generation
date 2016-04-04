package rtg.world.gen.terrain.biomesoplenty;

import rtg.util.noise.CellNoise;
import rtg.util.noise.OpenSimplexNoise;
import rtg.world.gen.terrain.TerrainBase;

public class TerrainBOPGrassland extends TerrainBase {

    public TerrainBOPGrassland() {

    }

    @Override
    public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {
        return terrainPlains(x, y, simplex, river, 160f, 10f, 60f, 200f, 66f);
    }
}
