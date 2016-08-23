package rtg.api.biome.sugiforest.config;


import rtg.api.biome.BiomeConfigProperty;

public class BiomeConfigSFSugiForest extends BiomeConfigSFBase {

    public static final String decorationLogsId = "decorationLogs";
    public static final String decorationLogsName = "RTG Decoration: Logs";

    public BiomeConfigSFSugiForest() {

        super("sugiforest");

        this.addProperty(new BiomeConfigProperty(decorationLogsId, BiomeConfigProperty.Type.BOOLEAN, decorationLogsName, "", true));
    }
}
