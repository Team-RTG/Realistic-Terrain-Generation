package rtg.api.biome.minestrappolation.config;

import rtg.api.biome.BiomeConfig;

public class BiomeConfigMS {

    public static BiomeConfig biomeConfigMSRedwoodForest;
    public static BiomeConfig biomeConfigMSTheFrost;

    public static BiomeConfig[] getBiomeConfigs() {

        BiomeConfig[] biomeConfigs = new BiomeConfig[]{
            biomeConfigMSRedwoodForest,
            biomeConfigMSTheFrost
        };

        return biomeConfigs;
    }
}
