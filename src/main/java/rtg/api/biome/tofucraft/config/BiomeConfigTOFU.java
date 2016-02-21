package rtg.api.biome.tofucraft.config;

import rtg.api.biome.BiomeConfig;


public class BiomeConfigTOFU
{

    public static BiomeConfig biomeConfigTOFULeekPlains;
    public static BiomeConfig biomeConfigTOFUTofuBuildings;
    public static BiomeConfig biomeConfigTOFUTofuExtremeHills;
    public static BiomeConfig biomeConfigTOFUTofuExtremeHillsEdge;
    public static BiomeConfig biomeConfigTOFUTofuForest;
    public static BiomeConfig biomeConfigTOFUTofuForestHills;
    public static BiomeConfig biomeConfigTOFUTofuPlainHills;
    public static BiomeConfig biomeConfigTOFUTofuPlains;
    public static BiomeConfig biomeConfigTOFUTofuRiver;
    
    public static BiomeConfig[] getBiomeConfigs()
    {
        BiomeConfig[] biomeConfigs = new BiomeConfig[]{
            biomeConfigTOFULeekPlains,
            biomeConfigTOFUTofuBuildings,
            biomeConfigTOFUTofuExtremeHills,
            biomeConfigTOFUTofuExtremeHillsEdge,
            biomeConfigTOFUTofuForest,
            biomeConfigTOFUTofuForestHills,
            biomeConfigTOFUTofuPlainHills,
            biomeConfigTOFUTofuPlains,
            biomeConfigTOFUTofuRiver
        };
        
        return biomeConfigs;
    }
}
