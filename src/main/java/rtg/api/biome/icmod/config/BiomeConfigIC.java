package rtg.api.biome.icmod.config;

import rtg.api.biome.BiomeConfig;

/**
 * Created by VelocityRa on 16/4/2016.
 */
public class BiomeConfigIC {
    public static BiomeConfig biomeConfigICIceCream;

    public static BiomeConfig[] getBiomeConfigs()
    {
        BiomeConfig[] biomeConfigs = new BiomeConfig[]{
                biomeConfigICIceCream
        };

        return biomeConfigs;
    }
}
