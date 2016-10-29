package rtg.api.biome.morechinesemc.config;

import rtg.api.biome.BiomeConfigProperty;
import rtg.api.biome.BiomeConfigProperty.Type;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPBase;


public class BiomeConfigMCMLoessPlateau extends BiomeConfigBOPBase {

    public static final String decorationLogsId = "decorationLogs";
    public static final String decorationLogsName = "RTG Decoration: Logs";

    public BiomeConfigMCMLoessPlateau() {

        super("loessplateau");

        this.addProperty(new BiomeConfigProperty(decorationLogsId, Type.BOOLEAN, decorationLogsName, "", true));
    }
}
