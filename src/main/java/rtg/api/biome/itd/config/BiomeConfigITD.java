package rtg.api.biome.itd.config;

import rtg.api.biome.BiomeConfig;


public class BiomeConfigITD
{

    public static BiomeConfig biomeConfigITDDarkForest;
    
    public static BiomeConfig[] getBiomeConfigs()
    {
        BiomeConfig[] biomeConfigs = new BiomeConfig[]{
            biomeConfigITDDarkForest
        };
        
        return biomeConfigs;
    }
}
