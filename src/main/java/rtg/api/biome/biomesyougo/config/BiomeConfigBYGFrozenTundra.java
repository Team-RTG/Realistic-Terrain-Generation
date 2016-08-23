package rtg.api.biome.biomesyougo.config;


import rtg.api.biome.BiomeConfigProperty;

public class BiomeConfigBYGFrozenTundra extends BiomeConfigBYGBase {

    public static final String decorationLogsId = "decorationLogs";
    public static final String decorationLogsName = "RTG Decoration: Logs";

    public BiomeConfigBYGFrozenTundra() {

        super("frozentundra");

        this.addProperty(new BiomeConfigProperty(decorationLogsId, BiomeConfigProperty.Type.BOOLEAN, decorationLogsName, "", true));
    }
}
