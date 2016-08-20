package rtg.api.biome.biomesoplenty.config;

import rtg.api.biome.BiomeConfigProperty;
import rtg.api.biome.BiomeConfigProperty.Type;


public class BiomeConfigBOPMapleWoods extends BiomeConfigBOPBase {

    public static final String decorationLogsId = "decorationLogs";
    public static final String decorationLogsName = "RTG Decoration: Logs";

    public BiomeConfigBOPMapleWoods() {

        super("maplewoods");

        this.addProperty(new BiomeConfigProperty(decorationLogsId, Type.BOOLEAN, decorationLogsName, "", true));
    }
}
