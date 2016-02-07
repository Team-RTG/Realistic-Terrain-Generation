package rtg.api.biome.highlands.config;

import rtg.api.biome.BiomeConfigProperty;
import rtg.api.biome.BiomeConfigProperty.Type;


public class BiomeConfigHLRockMountains extends BiomeConfigHLBase
{
    public static final String decorationLogsId = "decorationLogs";
    public static final String decorationLogsName = "RTG Decoration: Logs";
    
    public BiomeConfigHLRockMountains()
    {
        super("rockmountains");
        
        this.addProperty(new BiomeConfigProperty(decorationLogsId, Type.BOOLEAN, decorationLogsName, "", true));
    }
}
