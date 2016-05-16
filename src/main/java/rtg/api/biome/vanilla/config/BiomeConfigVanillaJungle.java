package rtg.api.biome.vanilla.config;

import rtg.api.biome.BiomeConfigProperty;
import rtg.api.biome.BiomeConfigProperty.Type;
import rtg.config.rtg.ConfigRTG;


public class BiomeConfigVanillaJungle extends BiomeConfigVanillaBase
{
    public static final String decorationLogsId = "decorationLogs";
    public static final String decorationLogsName = "RTG Decoration: Logs";
    
    public static final String decorationCactusId = "decorationCactus";
    public static final String decorationCactusName = "RTG Decoration: Cactus";
    
    public static final String surfaceMixBlockId = "surfaceMixBlock";
    public static final String surfaceMixBlockName = "RTG Surface: Mix Block";
    
    public static final String surfaceMixBlockMetaId = "surfaceMixBlockMeta";
    public static final String surfaceMixBlockMetaName = "RTG Surface: Mix Block Meta";
    
    public BiomeConfigVanillaJungle()
    {
        super("jungle");
        
        this.addProperty(new BiomeConfigProperty(decorationLogsId, Type.BOOLEAN, decorationLogsName, "", true));
        this.addProperty(new BiomeConfigProperty(decorationCactusId, Type.BOOLEAN, decorationCactusName, "", true));
        
        this.addProperty(new BiomeConfigProperty(surfaceMixBlockId, Type.STRING, surfaceMixBlockName, "", ""));
        this.addProperty(new BiomeConfigProperty(surfaceMixBlockMetaId, Type.STRING, surfaceMixBlockMetaName, "", ""));
        
        this.setPropertyValueById(allowVolcanoesId, true);
        this.setPropertyValueById(volcanoChanceId, (ConfigRTG.volcanoChance * 2));
    }
}
