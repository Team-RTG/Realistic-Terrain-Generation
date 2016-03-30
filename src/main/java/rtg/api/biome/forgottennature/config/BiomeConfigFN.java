package rtg.api.biome.forgottennature.config;

import rtg.api.biome.BiomeConfig;


public class BiomeConfigFN {

    public static BiomeConfig biomeConfigFNCherryBlossomWoodland;
    public static BiomeConfig biomeConfigFNCrystalForest;
    public static BiomeConfig biomeConfigFNEucalyptusForest;
    public static BiomeConfig biomeConfigFNGreatwoodForest;
    public static BiomeConfig biomeConfigFNMapleForest;
    public static BiomeConfig biomeConfigFNRedwoodForest;
    public static BiomeConfig biomeConfigFNRedwoodForestHills;
    public static BiomeConfig biomeConfigFNTropicalForest;
    public static BiomeConfig biomeConfigFNTropicalForestHills;

    public static BiomeConfig[] getBiomeConfigs() {
        BiomeConfig[] biomeConfigs = new BiomeConfig[] {
                biomeConfigFNCherryBlossomWoodland,
                biomeConfigFNCrystalForest,
                biomeConfigFNEucalyptusForest,
                biomeConfigFNGreatwoodForest,
                biomeConfigFNMapleForest,
                biomeConfigFNRedwoodForest,
                biomeConfigFNRedwoodForestHills,
                biomeConfigFNTropicalForest,
                biomeConfigFNTropicalForestHills
        };

        return biomeConfigs;
    }
}
