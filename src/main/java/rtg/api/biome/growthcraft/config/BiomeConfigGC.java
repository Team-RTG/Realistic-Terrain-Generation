package rtg.api.biome.growthcraft.config;

import rtg.api.biome.BiomeConfig;


public class BiomeConfigGC {

    public static BiomeConfig biomeConfigGCBambooForest;

    public static BiomeConfig[] getBiomeConfigs() {
        BiomeConfig[] biomeConfigs = new BiomeConfig[]{
                biomeConfigGCBambooForest
        };

        return biomeConfigs;
    }
}
