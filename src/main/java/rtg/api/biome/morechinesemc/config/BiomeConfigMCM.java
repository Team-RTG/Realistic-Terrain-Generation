package rtg.api.biome.morechinesemc.config;

import rtg.api.biome.BiomeConfig;

public class BiomeConfigMCM {

    public static BiomeConfig biomeConfigMCMBlackPlain;
    public static BiomeConfig biomeConfigMCMBog;
    public static BiomeConfig biomeConfigMCMLoessPlateau;
    public static BiomeConfig biomeConfigMCMMudFlat;
    public static BiomeConfig biomeConfigMCMWarmTaiga;

    public static BiomeConfig[] getBiomeConfigs() {

        BiomeConfig[] biomeConfigs = new BiomeConfig[]{
            biomeConfigMCMBlackPlain,
            biomeConfigMCMBog,
            biomeConfigMCMLoessPlateau,
            biomeConfigMCMMudFlat,
            biomeConfigMCMWarmTaiga
        };

        return biomeConfigs;
    }
}
