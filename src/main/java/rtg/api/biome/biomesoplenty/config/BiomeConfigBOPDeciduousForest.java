package rtg.api.biome.biomesoplenty.config;

import rtg.api.biome.BiomeConfigProperty;
import rtg.api.biome.BiomeConfigProperty.Type;



public class BiomeConfigBOPDeciduousForest extends BiomeConfigBOPBase
{
    public static final String decorationLogsId = "decorationLogs";
    public static final String decorationLogsName = "RTG Decoration: Logs";
    
    public BiomeConfigBOPDeciduousForest()
    {
        super("deciduousforest");
        
        this.addProperty(new BiomeConfigProperty(decorationLogsId, Type.BOOLEAN, decorationLogsName, "", true));
    }
}
