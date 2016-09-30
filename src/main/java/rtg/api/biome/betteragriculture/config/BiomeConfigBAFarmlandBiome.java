package rtg.api.biome.betteragriculture.config;


import rtg.api.biome.BiomeConfigProperty;

public class BiomeConfigBAFarmlandBiome extends rtg.api.biome.betteragriculture.config.BiomeConfigBABase {

    public static final String decorationLogsId = "decorationLogs";
    public static final String decorationLogsName = "RTG Decoration: Logs";

    public BiomeConfigBAFarmlandBiome() {

        super("farmlandbiome");

        this.addProperty(new BiomeConfigProperty(decorationLogsId, BiomeConfigProperty.Type.BOOLEAN, decorationLogsName, "", true));
    }
}
