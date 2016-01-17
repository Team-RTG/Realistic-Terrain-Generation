package rtg.api.biome.chromaticraft.config;

import rtg.api.biome.BiomeConfig;


public class BiomeConfigCC
{
    public static BiomeConfigCCEnderForest biomeConfigCCEnderForest;
    public static BiomeConfigCCRainbowForest biomeConfigCCRainbowForest;
    
    public static BiomeConfig[] getBiomeConfigs()
    {
        BiomeConfig[] biomeConfigs = new BiomeConfig[]{
            biomeConfigCCEnderForest,
            biomeConfigCCRainbowForest
        };
        
        return biomeConfigs;
    }
}
