package rtg.api.biome.highlands.config;

import rtg.config.rtg.ConfigRTG;


public class BiomeConfigHLVolcanoIsland extends BiomeConfigHLBase
{
    public BiomeConfigHLVolcanoIsland()
    {
        super("volcanoisland");
        
        this.setPropertyValueById(allowVolcanoesId, true);
        this.setPropertyValueById(volcanoChanceId, -1);
    }
}
