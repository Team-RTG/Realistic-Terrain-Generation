package rtg.api.biome.biomesyougo.config;

import rtg.api.biome.BiomeConfig;

public class BiomeConfigBYG {

    public static BiomeConfig biomeConfigBYGAutumnForest;
    public static BiomeConfig biomeConfigBYGBirchPlains;
    public static BiomeConfig biomeConfigBYGFrozenTundra;
    public static BiomeConfig biomeConfigBYGLushForest;
    public static BiomeConfig biomeConfigBYGRedDesert;
    public static BiomeConfig biomeConfigBYGRedRockMountains;
    public static BiomeConfig biomeConfigBYGWillowSwamps;

    public static BiomeConfig[] getBiomeConfigs() {

        BiomeConfig[] biomeConfigs = new BiomeConfig[]{
            biomeConfigBYGAutumnForest,
            biomeConfigBYGBirchPlains,
            biomeConfigBYGFrozenTundra,
            biomeConfigBYGLushForest,
            biomeConfigBYGRedDesert,
            biomeConfigBYGRedRockMountains,
            biomeConfigBYGWillowSwamps
        };

        return biomeConfigs;
    }
}
