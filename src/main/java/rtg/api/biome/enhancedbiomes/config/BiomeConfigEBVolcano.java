package rtg.api.biome.enhancedbiomes.config;

import rtg.config.rtg.ConfigRTG;

public class BiomeConfigEBVolcano extends BiomeConfigEBBase
{
    public BiomeConfigEBVolcano()
    {
        super("volcano");
        
        this.setPropertyValueById(allowVolcanoesId, true);
        this.setPropertyValueById(volcanoChanceId, -1);
    }
}
