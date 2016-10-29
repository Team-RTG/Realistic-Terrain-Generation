package rtg.api.biome.biomesyougo.config;

import rtg.api.biome.BiomeConfig;

public class BiomeConfigBYG {

    public static BiomeConfig biomeConfigBYGAthuraForest;
    public static BiomeConfig biomeConfigBYGAutumnForest;
    public static BiomeConfig biomeConfigBYGBirchPlains;
    public static BiomeConfig biomeConfigBYGFrozenTundra;
    public static BiomeConfig biomeConfigBYGLushForest;
    public static BiomeConfig biomeConfigBYGMushroomMountains;
    public static BiomeConfig biomeConfigBYGRedDesert;
    public static BiomeConfig biomeConfigBYGRedRockMountains;
    public static BiomeConfig biomeConfigBYGShrubs;
    public static BiomeConfig biomeConfigBYGWillowSwamps;

    public static BiomeConfig[] getBiomeConfigs() {

        BiomeConfig[] biomeConfigs = new BiomeConfig[]{
            biomeConfigBYGAthuraForest,
            biomeConfigBYGAutumnForest,
            biomeConfigBYGBirchPlains,
            biomeConfigBYGFrozenTundra,
            biomeConfigBYGLushForest,
            biomeConfigBYGMushroomMountains,
            biomeConfigBYGRedDesert,
            biomeConfigBYGRedRockMountains,
            biomeConfigBYGShrubs,
            biomeConfigBYGWillowSwamps
        };

        return biomeConfigs;
    }
}
