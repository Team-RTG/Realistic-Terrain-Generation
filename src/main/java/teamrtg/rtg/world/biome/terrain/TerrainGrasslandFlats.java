package teamrtg.rtg.world.biome.terrain;

import teamrtg.rtg.world.gen.ChunkProviderRTG;

public class TerrainGrasslandFlats extends TerrainBase {

    public TerrainGrasslandFlats() {

    }

    @Override
    public float generateNoise(ChunkProviderRTG provider, int x, int y, float border, float river) {
        return terrainGrasslandFlats(x, y, provider.simplex, river, 40f, 25f, 68f);
    }
}
