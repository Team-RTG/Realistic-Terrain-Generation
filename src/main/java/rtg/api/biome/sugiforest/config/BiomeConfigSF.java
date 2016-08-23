package rtg.api.biome.sugiforest.config;

import rtg.api.biome.BiomeConfig;

public class BiomeConfigSF {

    public static BiomeConfig biomeConfigSFSugiForest;

    public static BiomeConfig[] getBiomeConfigs() {

        BiomeConfig[] biomeConfigs = new BiomeConfig[]{
            biomeConfigSFSugiForest
        };

        return biomeConfigs;
    }
}
