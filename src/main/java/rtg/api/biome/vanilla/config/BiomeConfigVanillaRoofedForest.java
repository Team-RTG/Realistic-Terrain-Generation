package rtg.api.biome.vanilla.config;

import rtg.api.biome.BiomeConfigProperty;
import rtg.api.biome.BiomeConfigProperty.Type;


public class BiomeConfigVanillaRoofedForest extends BiomeConfigVanillaBase
{
    public static final String decorationLogsId = "decorationLogs";
    public static final String decorationLogsName = "RTG Decoration: Logs";
    
    public static final String decorationCobwebsId = "decorationCobwebs";
    public static final String decorationCobwebsName = "RTG Decoration: Cobwebs";
    
    public BiomeConfigVanillaRoofedForest()
    {
        super("roofedforest");
        
        this.addProperty(new BiomeConfigProperty(decorationLogsId, Type.BOOLEAN, decorationLogsName, "", true));
        this.addProperty(new BiomeConfigProperty(decorationCobwebsId, Type.BOOLEAN, decorationCobwebsName, "", true));
    }
}
