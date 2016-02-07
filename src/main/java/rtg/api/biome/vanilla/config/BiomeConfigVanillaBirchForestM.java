package rtg.api.biome.vanilla.config;

import rtg.api.biome.BiomeConfigProperty;
import rtg.api.biome.BiomeConfigProperty.Type;


public class BiomeConfigVanillaBirchForestM extends BiomeConfigVanillaBase
{
    public static final String decorationLogsId = "decorationLogs";
    public static final String decorationLogsName = "RTG Decoration: Logs";
    
    public BiomeConfigVanillaBirchForestM()
    {
        super("birchforestm");
        
        this.addProperty(new BiomeConfigProperty(decorationLogsId, Type.BOOLEAN, decorationLogsName, "", true));
    }
}
