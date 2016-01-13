package rtg.api.biome.arsmagica.config;

import rtg.api.biome.BiomeConfig;


public class BiomeConfigAM
{

    public static BiomeConfigAMWitchwoodForest biomeConfigAMWitchwoodForest;
    
    public static BiomeConfig[] getBiomeConfigs()
    {
        BiomeConfig[] biomeConfigs = new BiomeConfig[]{
            biomeConfigAMWitchwoodForest
        };
        
        return biomeConfigs;
    }
}
