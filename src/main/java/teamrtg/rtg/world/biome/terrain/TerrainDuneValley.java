package teamrtg.rtg.world.biome.terrain;

import teamrtg.rtg.world.gen.ChunkProviderRTG;

public class TerrainDuneValley extends TerrainBase {
    private float valley;

    public TerrainDuneValley(float valleySize) {
        valley = valleySize;
    }

    @Override
    public float generateNoise(ChunkProviderRTG provider, int x, int y, float border, float river) {
        return terrainDuneValley(x, y, provider.simplex, provider.cell, river, valley, 65f, 70f);
    }
}
