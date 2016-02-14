package rtg.api.biome.vanilla.config;

import rtg.api.biome.BiomeConfigProperty;
import rtg.api.biome.BiomeConfigProperty.Type;


public class BiomeConfigVanillaBirchForest extends BiomeConfigVanillaBase
{
    public static final String decorationLogsId = "decorationLogs";
    public static final String decorationLogsName = "RTG Decoration: Logs";
    
    public static final String surfaceMixBlockId = "surfaceMixBlock";
    public static final String surfaceMixBlockName = "RTG Surface: Mix Block";
    
    public static final String surfaceMixBlockMetaId = "surfaceMixBlockMeta";
    public static final String surfaceMixBlockMetaName = "RTG Surface: Mix Block Meta";
    
    public BiomeConfigVanillaBirchForest()
    {
        super("birchforest");
        
        this.addProperty(new BiomeConfigProperty(decorationLogsId, Type.BOOLEAN, decorationLogsName, "", true));
        
        this.addProperty(new BiomeConfigProperty(surfaceMixBlockId, Type.STRING, surfaceMixBlockName, "", ""));
        this.addProperty(new BiomeConfigProperty(surfaceMixBlockMetaId, Type.STRING, surfaceMixBlockMetaName, "", ""));
    }
}
