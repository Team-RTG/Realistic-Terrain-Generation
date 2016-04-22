package rtg.api.biome.sushicraft.config;

import rtg.api.biome.BiomeConfig;


public class BiomeConfigSC
{

    public static BiomeConfig biomeConfigSCSakuraForest;
    
    public static BiomeConfig[] getBiomeConfigs()
    {
        BiomeConfig[] biomeConfigs = new BiomeConfig[]{
            biomeConfigSCSakuraForest
        };
        
        return biomeConfigs;
    }
}
