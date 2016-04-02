package rtg.api.biome.lotsomobs.config;

import rtg.api.biome.BiomeConfig;


public class BiomeConfigLOM {

    public static BiomeConfig biomeConfigLOMAntartica;
    public static BiomeConfig biomeConfigLOMTropicalBeach;

    public static BiomeConfig[] getBiomeConfigs() {
        BiomeConfig[] biomeConfigs = new BiomeConfig[] {
                biomeConfigLOMAntartica,
                biomeConfigLOMTropicalBeach
        };

        return biomeConfigs;
    }
}
