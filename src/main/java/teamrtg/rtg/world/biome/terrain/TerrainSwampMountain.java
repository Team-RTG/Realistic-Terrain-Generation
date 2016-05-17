package teamrtg.rtg.world.biome.terrain;

import teamrtg.rtg.world.gen.ChunkProviderRTG;

public class TerrainSwampMountain extends TerrainBase {
    private float heigth;
    private float width;

    public TerrainSwampMountain(float mountainHeight, float mountainWidth) {
        heigth = mountainHeight;
        width = mountainWidth;
    }

    @Override
    public float generateNoise(ChunkProviderRTG provider, int x, int y, float border, float river) {
        return terrainSwampMountain(x, y, provider.simplex, provider.cell, river, width, heigth, 150f, 32f, 56f);
    }
}
