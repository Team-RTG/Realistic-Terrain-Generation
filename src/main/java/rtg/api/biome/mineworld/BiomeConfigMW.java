package rtg.api.biome.mineworld;

import rtg.api.biome.BiomeConfig;

public class BiomeConfigMW {

    public static BiomeConfig biomeConfigMWAppleForest;
    public static BiomeConfig biomeConfigMWArctic;
    public static BiomeConfig biomeConfigMWDeadForest;
    public static BiomeConfig biomeConfigMWIceHills;
    public static BiomeConfig biomeConfigMWPalms;
    public static BiomeConfig biomeConfigMWVolcano;

    public static BiomeConfig[] getBiomeConfigs() {

        BiomeConfig[] biomeConfigs = new BiomeConfig[]{
            biomeConfigMWAppleForest,
            biomeConfigMWArctic,
            biomeConfigMWDeadForest,
            biomeConfigMWIceHills,
            biomeConfigMWPalms,
            biomeConfigMWVolcano
        };

        return biomeConfigs;
    }
}
