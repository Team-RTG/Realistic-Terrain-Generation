package teamrtg.rtg.world.biome.terrain;

import teamrtg.rtg.world.gen.ChunkProviderRTG;

public class TerrainGrasslandMountains extends TerrainBase {
    public TerrainGrasslandMountains() {
    }

    @Override
    public float generateNoise(ChunkProviderRTG provider, int x, int y, float border, float river) {
        return terrainGrasslandMountains(x, y, provider.simplex, provider.cell, river, 7f, 120f, 68f);
    }
}
