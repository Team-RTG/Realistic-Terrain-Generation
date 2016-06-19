package rtg.api.biome.idt.config;

import rtg.api.biome.BiomeConfig;


public class BiomeConfigIDT
{

	public static BiomeConfig biomeConfigIDTEbonyForest;
	public static BiomeConfig biomeConfigIDTSilkwoodForest;
	public static BiomeConfig biomeConfigIDTWillowForest;
    
    public static BiomeConfig[] getBiomeConfigs()
    {
        BiomeConfig[] biomeConfigs = new BiomeConfig[]{
    		biomeConfigIDTEbonyForest,
    		biomeConfigIDTSilkwoodForest,
    		biomeConfigIDTWillowForest
        };
        
        return biomeConfigs;
    }
}
