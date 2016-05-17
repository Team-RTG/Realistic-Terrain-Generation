package teamrtg.rtg.world.biome.terrain;

import teamrtg.rtg.world.gen.ChunkProviderRTG;

public class TerrainHighland extends TerrainBase {
    private float start;
    private float height;
    private float base;
    private float width;

    public TerrainHighland(float hillStart, float landHeight, float baseHeight, float hillWidth) {
        start = hillStart;
        height = landHeight;
        base = baseHeight;
        width = hillWidth;
    }

    @Override
    public float generateNoise(ChunkProviderRTG provider, int x, int y, float border, float river) {
        return terrainHighland(x, y, provider.simplex, provider.cell, river, start, width, height, 0f);
    }
}
