package teamrtg.rtg.world.biome.terrain;

import teamrtg.rtg.world.gen.ChunkProviderRTG;

public class TerrainRollingHills extends TerrainBase {
    private float minHeight = 63f;
    private float maxHeight = 80f;
    private float hillStrength = 30f;

    // 63f, 80f, 30f

    public TerrainRollingHills(float minHeight, float maxHeight, float hillStrength) {
        this.minHeight = minHeight;
        this.maxHeight = (maxHeight > rollingHillsMaxHeight) ? rollingHillsMaxHeight : ((maxHeight < this.minHeight) ? rollingHillsMaxHeight : maxHeight);
        this.hillStrength = hillStrength;
    }

    @Override
    public float generateNoise(ChunkProviderRTG provider, int x, int y, float border, float river) {
        groundNoise = groundNoise(x, y, groundNoiseAmplitudeHills, provider.simplex);

        float m = hills(x, y, hillStrength, provider.simplex, river);

        float floNoise = riverized(minHeight + groundNoise, river) + m;

        return floNoise + 0f;
    }
}
