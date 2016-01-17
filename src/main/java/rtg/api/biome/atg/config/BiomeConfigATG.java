package rtg.api.biome.atg.config;

import rtg.api.biome.BiomeConfig;



public class BiomeConfigATG
{

    public static BiomeConfigATGGravelBeach biomeConfigATGGravelBeach;
    public static BiomeConfigATGRockySteppe biomeConfigATGRockySteppe;
    public static BiomeConfigATGShrubland biomeConfigATGShrubland;
    public static BiomeConfigATGSnowyGravelBeach biomeConfigATGSnowyGravelBeach;
    public static BiomeConfigATGTropicalShrubland biomeConfigATGTropicalShrubland;
    public static BiomeConfigATGTundra biomeConfigATGTundra;
    public static BiomeConfigATGVolcano biomeConfigATGVolcano;
    public static BiomeConfigATGWoodland biomeConfigATGWoodland;
    
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
