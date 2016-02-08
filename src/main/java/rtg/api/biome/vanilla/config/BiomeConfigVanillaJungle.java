package rtg.api.biome.vanilla.config;

import rtg.api.biome.BiomeConfigProperty;
import rtg.api.biome.BiomeConfigProperty.Type;


public class BiomeConfigVanillaJungle extends BiomeConfigVanillaBase
{
    public static final String decorationLogsId = "decorationLogs";
    public static final String decorationLogsName = "RTG Decoration: Logs";
    
    public static final String decorationCactusId = "decorationCactus";
    public static final String decorationCactusName = "RTG Decoration: Cactus";
    
    public BiomeConfigVanillaJungle()
    {
        super("jungle");
        
        this.addProperty(new BiomeConfigProperty(decorationLogsId, Type.BOOLEAN, decorationLogsName, "", true));
        this.addProperty(new BiomeConfigProperty(decorationCactusId, Type.BOOLEAN, decorationCactusName, "", true));
    }
}
