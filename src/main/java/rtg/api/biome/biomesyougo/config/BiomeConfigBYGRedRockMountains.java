package rtg.api.biome.biomesyougo.config;


import rtg.api.biome.BiomeConfigProperty;

public class BiomeConfigBYGRedRockMountains extends BiomeConfigBYGBase {

    public static final String decorationLogsId = "decorationLogs";
    public static final String decorationLogsName = "RTG Decoration: Logs";

    public BiomeConfigBYGRedRockMountains() {

        super("redrockmoutains");

        this.addProperty(new BiomeConfigProperty(decorationLogsId, BiomeConfigProperty.Type.BOOLEAN, decorationLogsName, "", true));
    }
}
