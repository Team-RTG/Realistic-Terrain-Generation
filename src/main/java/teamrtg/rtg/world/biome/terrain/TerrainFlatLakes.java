package teamrtg.rtg.world.biome.terrain;

import teamrtg.rtg.world.gen.ChunkProviderRTG;

public class TerrainFlatLakes extends TerrainBase {
    public TerrainFlatLakes() {

    }

    public float generateNoise(ChunkProviderRTG provider, int x, int y, float border, float river) {
        return terrainFlatLakes(x, y, provider.simplex, river, 3f, 62f);
    }
}
