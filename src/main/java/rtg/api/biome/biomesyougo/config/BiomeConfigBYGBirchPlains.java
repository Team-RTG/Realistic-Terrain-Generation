package rtg.api.biome.biomesyougo.config;


import rtg.api.biome.BiomeConfigProperty;

public class BiomeConfigBYGBirchPlains extends BiomeConfigBYGBase {

    public static final String decorationLogsId = "decorationLogs";
    public static final String decorationLogsName = "RTG Decoration: Logs";

    public static final String surfaceMixBlockId = "surfaceMixBlock";
    public static final String surfaceMixBlockName = "RTG Surface: Mix Block";

    public static final String surfaceMixBlockMetaId = "surfaceMixBlockMeta";
    public static final String surfaceMixBlockMetaName = "RTG Surface: Mix Block Meta";

    public BiomeConfigBYGBirchPlains() {

        super("birchplains2");

        this.addProperty(new BiomeConfigProperty(decorationLogsId, BiomeConfigProperty.Type.BOOLEAN, decorationLogsName, "", true));

        this.addProperty(new BiomeConfigProperty(surfaceMixBlockId, BiomeConfigProperty.Type.STRING, surfaceMixBlockName, "", ""));
        this.addProperty(new BiomeConfigProperty(surfaceMixBlockMetaId, BiomeConfigProperty.Type.STRING, surfaceMixBlockMetaName, "", ""));
    }
}
