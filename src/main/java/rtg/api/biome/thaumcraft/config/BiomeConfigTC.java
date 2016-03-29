package rtg.api.biome.thaumcraft.config;

import rtg.api.biome.BiomeConfig;

public class BiomeConfigTC {
    public static BiomeConfig biomeConfigTCEerie;
    public static BiomeConfig biomeConfigTCMagicalForest;
    public static BiomeConfig biomeConfigTCTaintedLand;

    public static BiomeConfig[] getBiomeConfigs() {
        BiomeConfig[] biomeConfigs = new BiomeConfig[] {
                biomeConfigTCEerie,
                biomeConfigTCMagicalForest,
                biomeConfigTCTaintedLand
        };

        return biomeConfigs;
    }
}
