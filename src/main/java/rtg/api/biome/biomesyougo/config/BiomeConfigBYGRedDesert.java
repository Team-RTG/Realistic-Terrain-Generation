package rtg.api.biome.biomesyougo.config;


import rtg.api.biome.BiomeConfigProperty;

public class BiomeConfigBYGRedDesert extends BiomeConfigBYGBase {

    public static final String decorationLogsId = "decorationLogs";
    public static final String decorationLogsName = "RTG Decoration: Logs";

    public BiomeConfigBYGRedDesert() {

        super("reddesert");

        this.addProperty(new BiomeConfigProperty(decorationLogsId, BiomeConfigProperty.Type.BOOLEAN, decorationLogsName, "", true));
    }
}
