package rtg.api.biome.mineworld;


import rtg.api.biome.BiomeConfigProperty;

public class BiomeConfigMWIceHills extends BiomeConfigMWBase {

    public static final String surfaceMixBlockId = "surfaceMixBlock";
    public static final String surfaceMixBlockName = "RTG Surface: Mix Block";

    public static final String surfaceMixBlockMetaId = "surfaceMixBlockMeta";
    public static final String surfaceMixBlockMetaName = "RTG Surface: Mix Block Meta";

    public static final String surfaceMixFillerBlockId = "surfaceMixFillerBlock";
    public static final String surfaceMixFillerBlockName = "RTG Surface: Mix Filler Block";

    public static final String surfaceMixFillerBlockMetaId = "surfaceMixFillerBlockMeta";
    public static final String surfaceMixFillerBlockMetaName = "RTG Surface: Mix Filler Block Meta";

    public BiomeConfigMWIceHills() {

        super("icehills");

        this.addProperty(new BiomeConfigProperty(surfaceMixBlockId, BiomeConfigProperty.Type.STRING, surfaceMixBlockName, "", ""));
        this.addProperty(new BiomeConfigProperty(surfaceMixBlockMetaId, BiomeConfigProperty.Type.STRING, surfaceMixBlockMetaName, "", ""));
        this.addProperty(new BiomeConfigProperty(surfaceMixFillerBlockId, BiomeConfigProperty.Type.STRING, surfaceMixFillerBlockName, "", ""));
        this.addProperty(new BiomeConfigProperty(surfaceMixFillerBlockMetaId, BiomeConfigProperty.Type.STRING, surfaceMixFillerBlockMetaName, "", ""));
    }
}
