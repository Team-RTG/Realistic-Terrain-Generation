package rtg.api.biome.enhancedbiomes.config;

import rtg.api.biome.BiomeConfigProperty;
import rtg.api.biome.BiomeConfigProperty.Type;

public class BiomeConfigEBBlossomWoods extends BiomeConfigEBBase
{
    public static final String decorationLogsId = "decorationLogs";
    public static final String decorationLogsName = "RTG Decoration: Logs";
    
    public BiomeConfigEBBlossomWoods()
    {
        super("blossomwoods");
        
        this.addProperty(new BiomeConfigProperty(decorationLogsId, Type.BOOLEAN, decorationLogsName, "", true));
    }
}
