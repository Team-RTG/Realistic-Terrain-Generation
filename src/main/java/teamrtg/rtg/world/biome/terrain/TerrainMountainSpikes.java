package teamrtg.rtg.world.biome.terrain;

import teamrtg.rtg.world.gen.ChunkProviderRTG;

public class TerrainMountainSpikes extends TerrainBase {
    public TerrainMountainSpikes() {
    }

    @Override
    public float generateNoise(ChunkProviderRTG provider, int x, int y, float border, float river) {
        float b = (12f + (provider.simplex.noise2(x / 300f, y / 300f) * 6f));
        float h = provider.cell.noise(x / 200D, y / 200D, 1D) * b * river;
        h *= h * 1.5f;
        h = h > 155f ? 155f : h;

        if (h > 2f) {
            float d = (h - 2f) / 2f > 8f ? 8f : (h - 2f) / 2f;
            h += provider.simplex.noise2(x / 30f, y / 30f) * d;
            h += provider.simplex.noise2(x / 50f, y / 50f) * d * 0.5f;

            if (h > 35f) {
                float d2 = (h - 35f) / 1.5f > 30f ? 30f : (h - 35f) / 1.5f;
                h += provider.cell.noise(x / 25D, y / 25D, 1D) * d2;
            }
        }

        h += provider.simplex.noise2(x / 18f, y / 18f) * 3;
        h += provider.simplex.noise2(x / 8f, y / 8f) * 2;

        return 45f + h + (b * 2);
    }
}
