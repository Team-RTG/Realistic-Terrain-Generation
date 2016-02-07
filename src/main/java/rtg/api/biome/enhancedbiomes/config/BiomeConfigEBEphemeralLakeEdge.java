package rtg.api.biome.enhancedbiomes.config;

import rtg.api.biome.BiomeConfigProperty;
import rtg.api.biome.BiomeConfigProperty.Type;

public class BiomeConfigEBEphemeralLakeEdge extends BiomeConfigEBBase
{
    public static final String decorationLogsId = "decorationLogs";
    public static final String decorationLogsName = "RTG Decoration: Logs";
    
    public BiomeConfigEBEphemeralLakeEdge()
    {
        super("ephemerallakeedge");
        
        this.addProperty(new BiomeConfigProperty(decorationLogsId, Type.BOOLEAN, decorationLogsName, "", true));
    }
}
