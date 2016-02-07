package rtg.api.biome.atg.config;

import rtg.api.biome.BiomeConfig;



public class BiomeConfigATG
{

    public static BiomeConfig biomeConfigATGGravelBeach;
    public static BiomeConfig biomeConfigATGRockySteppe;
    public static BiomeConfig biomeConfigATGShrubland;
    public static BiomeConfig biomeConfigATGSnowyGravelBeach;
    public static BiomeConfig biomeConfigATGTropicalShrubland;
    public static BiomeConfig biomeConfigATGTundra;
    public static BiomeConfig biomeConfigATGVolcano;
    public static BiomeConfig biomeConfigATGWoodland;
    
    public static BiomeConfig[] getBiomeConfigs()
    {
        BiomeConfig[] biomeConfigs = new BiomeConfig[]{
            biomeConfigATGGravelBeach,
            biomeConfigATGRockySteppe,
            biomeConfigATGShrubland,
            biomeConfigATGSnowyGravelBeach,
            biomeConfigATGTropicalShrubland,
            biomeConfigATGTundra,
            biomeConfigATGVolcano,
            biomeConfigATGWoodland
        };
        
        return biomeConfigs;
    }
}
