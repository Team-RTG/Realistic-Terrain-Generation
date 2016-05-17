package teamrtg.rtg.world.biome.terrain;

import teamrtg.rtg.world.gen.ChunkProviderRTG;

public class TerrainMesa extends TerrainBase {
    public TerrainMesa() {
    }

    @Override
    public float generateNoise(ChunkProviderRTG provider, int x, int y, float border, float river) {
        return terrainMesa(x, y, provider.simplex, river, border);
    }
}
