package rtg.api.biome.buildcraft.config;

import rtg.api.biome.BiomeConfig;

public class BiomeConfigBC
{
    public static BiomeConfig biomeConfigBCDesertOilField;
    public static BiomeConfig biomeConfigBCOceanOilField;
    
    public static BiomeConfig[] getBiomeConfigs()
    {
        BiomeConfig[] biomeConfigs = new BiomeConfig[]{
            biomeConfigBCDesertOilField,
            biomeConfigBCOceanOilField
        };
        
        return biomeConfigs;
    }
}
