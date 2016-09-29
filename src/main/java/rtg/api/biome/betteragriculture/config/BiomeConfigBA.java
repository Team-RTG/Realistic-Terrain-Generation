package rtg.api.biome.betteragriculture.config;

import rtg.api.biome.BiomeConfig;

public class BiomeConfigBA {

    public static BiomeConfig biomeConfigBAFarmlandBiome;

    public static BiomeConfig[] getBiomeConfigs() {

        BiomeConfig[] biomeConfigs = new BiomeConfig[]{
            biomeConfigBAFarmlandBiome
        };

        return biomeConfigs;
    }
}
