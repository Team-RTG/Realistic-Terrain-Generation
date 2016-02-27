package rtg.api.biome.thaumcraft.config;

import rtg.api.biome.VillageConfig;

public class VillageConfigTC
{
    public static VillageConfig villageConfigTCEerie;
    public static VillageConfig villageConfigTCMagicalForest;
    public static VillageConfig villageConfigTCTaintedLand;
    
    public static VillageConfig[] getVillageConfigs()
    {
        VillageConfig[] villageConfigs = new VillageConfig[]{
            villageConfigTCEerie,
            villageConfigTCMagicalForest,
            villageConfigTCTaintedLand
        };
        
        return villageConfigs;
    }
}
