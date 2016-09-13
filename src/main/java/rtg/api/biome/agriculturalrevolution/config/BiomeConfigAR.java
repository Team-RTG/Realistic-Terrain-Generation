package rtg.api.biome.agriculturalrevolution.config;

import rtg.api.biome.BiomeConfig;

public class BiomeConfigAR {

    public static BiomeConfig biomeConfigARBambooGrove;
    public static BiomeConfig biomeConfigARCoralReef;
    public static BiomeConfig biomeConfigARDeepReef;
    public static BiomeConfig biomeConfigARKelpForest;
    public static BiomeConfig biomeConfigAROrchard;
    public static BiomeConfig biomeConfigARTropicalHills;

    public static BiomeConfig[] getBiomeConfigs() {

        BiomeConfig[] biomeConfigs = new BiomeConfig[]{
            biomeConfigARBambooGrove,
            biomeConfigARCoralReef,
            biomeConfigARDeepReef,
            biomeConfigARKelpForest,
            biomeConfigAROrchard,
            biomeConfigARTropicalHills
        };

        return biomeConfigs;
    }
}
