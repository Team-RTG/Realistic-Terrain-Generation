package rtg.api.biome.mineworld;


import rtg.api.biome.BiomeConfigProperty;

public class BiomeConfigMWVolcano extends BiomeConfigMWBase {

    public static final String surfaceMixBlockId = "surfaceMixBlock";
    public static final String surfaceMixBlockName = "RTG Surface: Mix Block";

    public static final String surfaceMixBlockMetaId = "surfaceMixBlockMeta";
    public static final String surfaceMixBlockMetaName = "RTG Surface: Mix Block Meta";

    public BiomeConfigMWVolcano() {

        super("volcano");

        this.addProperty(new BiomeConfigProperty(surfaceMixBlockId, BiomeConfigProperty.Type.STRING, surfaceMixBlockName, "", ""));
        this.addProperty(new BiomeConfigProperty(surfaceMixBlockMetaId, BiomeConfigProperty.Type.STRING, surfaceMixBlockMetaName, "", ""));

        this.setPropertyValueById(allowVolcanoesId, true);
        this.setPropertyValueById(volcanoChanceId, -1);
    }
}
