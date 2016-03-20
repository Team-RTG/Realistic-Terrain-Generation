package rtg.api.biome.chromaticraft.config;

import rtg.api.biome.BiomeConfig;


public class BiomeConfigCC {
    public static BiomeConfig biomeConfigCCEnderForest;
    public static BiomeConfig biomeConfigCCRainbowForest;

    public static BiomeConfig[] getBiomeConfigs() {
        BiomeConfig[] biomeConfigs = new BiomeConfig[]{
                biomeConfigCCEnderForest,
                biomeConfigCCRainbowForest
        };

        return biomeConfigs;
    }
}
