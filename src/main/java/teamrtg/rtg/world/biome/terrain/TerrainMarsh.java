package teamrtg.rtg.world.biome.terrain;

import teamrtg.rtg.world.gen.ChunkProviderRTG;


public class TerrainMarsh extends TerrainBase {

    public TerrainMarsh() {

    }

    @Override
    public float generateNoise(ChunkProviderRTG provider, int x, int y, float border, float river) {
        return terrainMarsh(x, y, provider.simplex, 62f);
    }
}
