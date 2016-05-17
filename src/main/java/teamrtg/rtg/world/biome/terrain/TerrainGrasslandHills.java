package teamrtg.rtg.world.biome.terrain;

import teamrtg.rtg.world.gen.ChunkProviderRTG;


public class TerrainGrasslandHills extends TerrainBase {
    private float hHeight;
    private float hWidth;
    private float vHeight;
    private float vWidth;
    private float lHeight;
    private float lWidth;
    private float bHeight;

    public TerrainGrasslandHills(float hillHeight, float hillWidth, float varHeight, float varWidth, float lakeHeight, float lakeWidth, float baseHeight) {
        hHeight = hillHeight;
        hWidth = hillWidth;

        vHeight = varHeight;
        vWidth = varWidth;

        lHeight = lakeHeight;
        lWidth = lakeWidth;

        bHeight = baseHeight;
    }

    @Override
    public float generateNoise(ChunkProviderRTG provider, int x, int y, float border, float river) {
        return terrainGrasslandHills(x, y, provider.simplex, provider.cell, river, vWidth, vHeight, hWidth, hHeight, bHeight);
    }
}
