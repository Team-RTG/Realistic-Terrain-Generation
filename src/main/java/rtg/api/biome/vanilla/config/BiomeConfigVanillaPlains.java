package rtg.api.biome.vanilla.config;


import net.minecraftforge.common.config.Configuration;

import rtg.api.biome.BiomeConfigProperty;

public class BiomeConfigVanillaPlains extends BiomeConfigVanillaBase {

    public static final String decorationWheatId = "decorationWheat";
    public static final String decorationWheatName = "RTG Decoration: Wheat";
    public static final String decorationWheatChanceId = "decorationWheatChance";
    public static final String decorationWheatChanceName = "RTG Decoration: Wheat (Chance)";
    public static final String decorationWheatMinYId = "decorationWheatMinY";
    public static final String decorationWheatMinYName = "RTG Decoration: Wheat (Min Y)";
    public static final String decorationWheatMaxYId = "decorationWheatMaxY";
    public static final String decorationWheatMaxYName = "RTG Decoration: Wheat (Max Y)";

    public BiomeConfigVanillaPlains() {

        super("plains");

        this.addProperty(new BiomeConfigProperty(decorationWheatId, BiomeConfigProperty.Type.BOOLEAN, decorationWheatName, "", true));

        this.addProperty(new BiomeConfigProperty(
            decorationWheatChanceId,
            BiomeConfigProperty.Type.INTEGER,
            decorationWheatChanceName,
            "1/x chance that wheat will generate."
                + Configuration.NEW_LINE +
                "0 = Never generate; 1 = Always generate if possible; 2 = 50% chance; 4 = 25% chance"
                + Configuration.NEW_LINE,
            50, 0, Integer.MAX_VALUE
        ));

        this.addProperty(new BiomeConfigProperty(
            decorationWheatMinYId,
            BiomeConfigProperty.Type.INTEGER,
            decorationWheatMinYName,
            "The lowest Y value at which wheat is allowed to generate in this biome.",
            63, 63, 255
        ));

        this.addProperty(new BiomeConfigProperty(
            decorationWheatMaxYId,
            BiomeConfigProperty.Type.INTEGER,
            decorationWheatMaxYName,
            "The highest Y value at which wheat is allowed to generate in this biome.",
            255, 63, 255
        ));
    }
}
