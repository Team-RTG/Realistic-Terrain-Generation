package rtg.api.biome.vanilla.config;

import rtg.api.biome.BiomeConfigProperty;
import rtg.api.biome.BiomeConfigProperty.Type;
import rtg.config.rtg.ConfigRTG;


public class BiomeConfigVanillaJungleHills extends BiomeConfigVanillaBase
{
    public static final String decorationLogsId = "decorationLogs";
    public static final String decorationLogsName = "RTG Decoration: Logs";
    
    public static final String decorationCactusId = "decorationCactus";
    public static final String decorationCactusName = "RTG Decoration: Cactus";
    
    public BiomeConfigVanillaJungleHills()
    {
        super("junglehills");
        
        this.addProperty(new BiomeConfigProperty(decorationLogsId, Type.BOOLEAN, decorationLogsName, "", true));
        this.addProperty(new BiomeConfigProperty(decorationCactusId, Type.BOOLEAN, decorationCactusName, "", true));
        
        this.setPropertyValueById(allowVolcanoesId, true);
        this.setPropertyValueById(volcanoChanceId, (ConfigRTG.volcanoChance * 2));
    }
}
