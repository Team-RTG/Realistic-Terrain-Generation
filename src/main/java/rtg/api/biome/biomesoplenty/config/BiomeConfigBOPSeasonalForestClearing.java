package rtg.api.biome.biomesoplenty.config;

import rtg.api.biome.BiomeConfigProperty;
import rtg.api.biome.BiomeConfigProperty.Type;



public class BiomeConfigBOPSeasonalForestClearing extends BiomeConfigBOPBase
{
    public static final String decorationLogsId = "decorationLogs";
    public static final String decorationLogsName = "RTG Decoration: Logs";
    
    public BiomeConfigBOPSeasonalForestClearing()
    {
        super("seasonalforestclearing");
        
        this.addProperty(new BiomeConfigProperty(decorationLogsId, Type.BOOLEAN, decorationLogsName, "", true));
    }
}
