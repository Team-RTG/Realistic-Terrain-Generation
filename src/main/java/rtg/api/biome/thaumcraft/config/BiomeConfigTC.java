package rtg.api.biome.thaumcraft.config;

import rtg.api.biome.BiomeConfig;

public class BiomeConfigTC
{
    public static BiomeConfigTCEerie biomeConfigTCEerie;
    public static BiomeConfigTCMagicalForest biomeConfigTCMagicalForest;
    public static BiomeConfigTCTaintedLand biomeConfigTCTaintedLand;
    
    public static BiomeConfig[] getBiomeConfigs()
    {
        BiomeConfig[] biomeConfigs = new BiomeConfig[]{
            biomeConfigTCEerie,
            biomeConfigTCMagicalForest,
            biomeConfigTCTaintedLand
        };
        
        return biomeConfigs;
    }
}
