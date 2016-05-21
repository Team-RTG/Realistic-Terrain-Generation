package rtg.api.biome.enhancedbiomes.config;

import rtg.config.rtg.ConfigRTG;

public class BiomeConfigEBVolcanoM extends BiomeConfigEBBase
{
    public BiomeConfigEBVolcanoM()
    {
        super("volcanom");
        
        this.setPropertyValueById(allowVolcanoesId, true);
        this.setPropertyValueById(volcanoChanceId, -1);
    }
}
