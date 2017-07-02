package rtg.api.biome.hotwatermod.config;

import rtg.api.biome.BiomeConfig;

public class BiomeConfigHWM
{
    public static BiomeConfig biomeConfigHWMHotSprings;
    
    public static BiomeConfig[] getBiomeConfigs()
    {
        BiomeConfig[] biomeConfigs = new BiomeConfig[]{
            biomeConfigHWMHotSprings
        };
        
        return biomeConfigs;
    }
}
