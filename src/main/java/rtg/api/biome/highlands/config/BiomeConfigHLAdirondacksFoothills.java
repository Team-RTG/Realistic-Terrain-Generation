package rtg.api.biome.highlands.config;

import rtg.api.biome.BiomeConfigProperty;

public class BiomeConfigHLAdirondacksFoothills extends BiomeConfigHLBase {

    public static final String surfaceMixBlockId = "surfaceMixBlock";
    public static final String surfaceMixBlockName = "RTG Surface: Mix Block";

    public static final String surfaceMixBlockMetaId = "surfaceMixBlockMeta";
    public static final String surfaceMixBlockMetaName = "RTG Surface: Mix Block Meta";

    public static final String surfaceMixFillerBlockId = "surfaceMixFillerBlock";
    public static final String surfaceMixFillerBlockName = "RTG Surface: Mix Filler Block";

    public static final String surfaceMixFillerBlockMetaId = "surfaceMixFillerBlockMeta";
    public static final String surfaceMixFillerBlockMetaName = "RTG Surface: Mix Filler Block Meta";

    public BiomeConfigHLAdirondacksFoothills() {

        super("adirondacksfoothills");

        this.addProperty(new BiomeConfigProperty(surfaceMixBlockId, BiomeConfigProperty.Type.STRING, surfaceMixBlockName, "", ""));
        this.addProperty(new BiomeConfigProperty(surfaceMixBlockMetaId, BiomeConfigProperty.Type.STRING, surfaceMixBlockMetaName, "", ""));
        this.addProperty(new BiomeConfigProperty(surfaceMixFillerBlockId, BiomeConfigProperty.Type.STRING, surfaceMixFillerBlockName, "", ""));
        this.addProperty(new BiomeConfigProperty(surfaceMixFillerBlockMetaId, BiomeConfigProperty.Type.STRING, surfaceMixFillerBlockMetaName, "", ""));
    }
}
