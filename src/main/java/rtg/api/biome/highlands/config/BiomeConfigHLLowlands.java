package rtg.api.biome.highlands.config;

import rtg.api.biome.BiomeConfigProperty;

public class BiomeConfigHLLowlands extends BiomeConfigHLBase {

    public static final String surfaceMixBlockId = "surfaceMixBlock";
    public static final String surfaceMixBlockName = "RTG Surface: Mix Block";

    public static final String surfaceMixBlockMetaId = "surfaceMixBlockMeta";
    public static final String surfaceMixBlockMetaName = "RTG Surface: Mix Block Meta";

    public BiomeConfigHLLowlands() {

        super("lowlands");

        this.addProperty(new BiomeConfigProperty(surfaceMixBlockId, BiomeConfigProperty.Type.STRING, surfaceMixBlockName, "", ""));
        this.addProperty(new BiomeConfigProperty(surfaceMixBlockMetaId, BiomeConfigProperty.Type.STRING, surfaceMixBlockMetaName, "", ""));
    }
}
