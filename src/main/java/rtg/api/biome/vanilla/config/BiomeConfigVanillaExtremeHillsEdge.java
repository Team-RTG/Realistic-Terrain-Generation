package rtg.api.biome.vanilla.config;

import rtg.api.biome.BiomeConfigProperty;
import rtg.api.biome.BiomeConfigProperty.Type;


public class BiomeConfigVanillaExtremeHillsEdge extends BiomeConfigVanillaBase
{
    public static final String decorationLogsId = "decorationLogs";
    public static final String decorationLogsName = "RTG Decoration: Logs";
    
    public static final String surfaceMixBlockId = "surfaceMixBlock";
    public static final String surfaceMixBlockName = "RTG Surface: Mix Block";
    
    public static final String surfaceMixBlockMetaId = "surfaceMixBlockMeta";
    public static final String surfaceMixBlockMetaName = "RTG Surface: Mix Block Meta";
    
    public static final String surfaceMixFillerBlockId = "surfaceMixFillerBlock";
    public static final String surfaceMixFillerBlockName = "RTG Surface: Mix Filler Block";
    
    public static final String surfaceMixFillerBlockMetaId = "surfaceMixFillerBlockMeta";
    public static final String surfaceMixFillerBlockMetaName = "RTG Surface: Mix Filler Block Meta";
    
    public BiomeConfigVanillaExtremeHillsEdge()
    {
        super("extremehillsedge");
        
        this.addProperty(new BiomeConfigProperty(decorationLogsId, Type.BOOLEAN, decorationLogsName, "", true));
        
        this.addProperty(new BiomeConfigProperty(surfaceMixBlockId, Type.STRING, surfaceMixBlockName, "", ""));
        this.addProperty(new BiomeConfigProperty(surfaceMixBlockMetaId, Type.STRING, surfaceMixBlockMetaName, "", ""));
        this.addProperty(new BiomeConfigProperty(surfaceMixFillerBlockId, Type.STRING, surfaceMixFillerBlockName, "", ""));
        this.addProperty(new BiomeConfigProperty(surfaceMixFillerBlockMetaId, Type.STRING, surfaceMixFillerBlockMetaName, "", ""));
    }
}
