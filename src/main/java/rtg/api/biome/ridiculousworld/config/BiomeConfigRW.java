package rtg.api.biome.ridiculousworld.config;

import rtg.api.biome.BiomeConfig;


public class BiomeConfigRW
{

    public static BiomeConfig biomeConfigRWBotanicalGarden;
    public static BiomeConfig biomeConfigRWMountainOfMadness;
    public static BiomeConfig biomeConfigRWOssuary;
    public static BiomeConfig biomeConfigRWRockCandyMountain;
    public static BiomeConfig biomeConfigRWShadowFen;
    public static BiomeConfig biomeConfigRWSpookyForest;
    
    public static BiomeConfig[] getBiomeConfigs()
    {
        BiomeConfig[] biomeConfigs = new BiomeConfig[]{
            biomeConfigRWBotanicalGarden,
            biomeConfigRWMountainOfMadness,
            biomeConfigRWOssuary,
            biomeConfigRWRockCandyMountain,
            biomeConfigRWShadowFen,
            biomeConfigRWSpookyForest
        };
        
        return biomeConfigs;
    }
}
