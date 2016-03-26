package rtg.world.gen.terrain.vanilla;

import rtg.util.noise.CellNoise;
import rtg.util.noise.OpenSimplexNoise;
import rtg.world.gen.terrain.TerrainBase;

public class TerrainVanillaExtremeHillsEdge extends TerrainBase {
    private float start;
    private float height;
    private float base;
    private float width;

    public TerrainVanillaExtremeHillsEdge(float hillStart, float landHeight, float baseHeight, float hillWidth) {
        start = 10f;
        height = 120f;
        base = 68f;
        width = 200f;
    }

    @Override
    public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {
        return terrainHighland(x, y, simplex, cell, river, start, width, height, 10f);
    }
}
