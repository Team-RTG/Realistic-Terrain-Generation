package teamrtg.rtg.world.biome.terrain;

import teamrtg.rtg.world.gen.ChunkProviderRTG;

public class TerrainBeach extends TerrainBase {
    public TerrainBeach() {

    }

    @Override
    public float generateNoise(ChunkProviderRTG provider, int x, int y, float border, float river) {
        return terrainBeach(x, y, provider.simplex, river, 180f, 35f, 60f);
    }
}
