package teamrtg.rtg.world.biome.terrain;

import teamrtg.rtg.world.gen.ChunkProviderRTG;

public class TerrainPlains extends TerrainBase {

    public TerrainPlains() {

    }

    @Override
    public float generateNoise(ChunkProviderRTG provider, int x, int y, float border, float river) {
        return terrainPlains(x, y, provider.simplex, river, 160f, 10f, 60f, 200f, 66f);
    }
}
