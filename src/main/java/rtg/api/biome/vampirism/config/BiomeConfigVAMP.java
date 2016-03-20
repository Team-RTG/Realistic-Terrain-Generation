package rtg.api.biome.vampirism.config;

import rtg.api.biome.BiomeConfig;


public class BiomeConfigVAMP {

    public static BiomeConfig biomeConfigVAMPVampireForest;

    public static BiomeConfig[] getBiomeConfigs() {
        BiomeConfig[] biomeConfigs = new BiomeConfig[]{
                biomeConfigVAMPVampireForest
        };

        return biomeConfigs;
    }
}
