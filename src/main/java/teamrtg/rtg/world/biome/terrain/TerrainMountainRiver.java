package teamrtg.rtg.world.biome.terrain;

import teamrtg.rtg.world.gen.ChunkProviderRTG;

public class TerrainMountainRiver extends TerrainBase {
    public TerrainMountainRiver() {

    }

    @Override
    public float generateNoise(ChunkProviderRTG provider, int x, int y, float border, float river) {
        return terrainMountainRiver(x, y, provider.simplex, provider.cell, river, 300f, 67f);
    }
}
