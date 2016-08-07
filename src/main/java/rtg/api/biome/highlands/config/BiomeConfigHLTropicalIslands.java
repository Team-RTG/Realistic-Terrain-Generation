package rtg.api.biome.highlands.config;

import rtg.api.biome.BiomeConfigProperty;

public class BiomeConfigHLTropicalIslands extends BiomeConfigHLBase {

    public static final String decorationLogsId = "decorationLogs";
    public static final String decorationLogsName = "RTG Decoration: Logs";

    public BiomeConfigHLTropicalIslands() {

        super("tropicalislands");

        this.addProperty(new BiomeConfigProperty(decorationLogsId, BiomeConfigProperty.Type.BOOLEAN, decorationLogsName, "", true));
    }
}
