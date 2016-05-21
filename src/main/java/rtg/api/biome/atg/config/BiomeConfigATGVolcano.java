package rtg.api.biome.atg.config;

import rtg.config.rtg.ConfigRTG;


public class BiomeConfigATGVolcano extends BiomeConfigATGBase
{
    public BiomeConfigATGVolcano()
    {
        super("volcano");
        
        this.setPropertyValueById(allowVolcanoesId, true);
        this.setPropertyValueById(volcanoChanceId, -1);
    }
}
