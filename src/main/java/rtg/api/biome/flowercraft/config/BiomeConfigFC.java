package rtg.api.biome.flowercraft.config;

import rtg.api.biome.BiomeConfig;

public class BiomeConfigFC {

    public static BiomeConfig biomeConfigFCPhantasia;

    public static BiomeConfig[] getBiomeConfigs() {

        BiomeConfig[] biomeConfigs = new BiomeConfig[]{
                biomeConfigFCPhantasia
        };

        return biomeConfigs;
    }
}
