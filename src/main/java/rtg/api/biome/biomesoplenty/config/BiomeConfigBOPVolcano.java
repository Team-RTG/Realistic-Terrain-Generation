package rtg.api.biome.biomesoplenty.config;

import rtg.config.rtg.ConfigRTG;



public class BiomeConfigBOPVolcano extends BiomeConfigBOPBase
{
    public BiomeConfigBOPVolcano()
    {
        super("volcano");
        
        this.setPropertyValueById(allowVolcanoesId, true);
        this.setPropertyValueById(volcanoChanceId, -1);
    }
}
