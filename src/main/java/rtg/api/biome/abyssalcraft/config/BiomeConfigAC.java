package rtg.api.biome.abyssalcraft.config;

import rtg.api.biome.BiomeConfig;

public class BiomeConfigAC {

    public static BiomeConfig biomeConfigACCoraliumInfestedSwamp;
    public static BiomeConfig biomeConfigACDarklands;
    public static BiomeConfig biomeConfigACDarklandsForest;
    public static BiomeConfig biomeConfigACDarklandsHighland;
    public static BiomeConfig biomeConfigACDarklandsMountains;
    public static BiomeConfig biomeConfigACDarklandsPlains;

    public static BiomeConfig[] getBiomeConfigs() {

        BiomeConfig[] biomeConfigs = new BiomeConfig[]{
            biomeConfigACCoraliumInfestedSwamp,
            biomeConfigACDarklands,
            biomeConfigACDarklandsForest,
            biomeConfigACDarklandsHighland,
            biomeConfigACDarklandsMountains,
            biomeConfigACDarklandsPlains
        };

        return biomeConfigs;
    }
}
