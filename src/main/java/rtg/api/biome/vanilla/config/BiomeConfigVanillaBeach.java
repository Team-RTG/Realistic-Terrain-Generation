package rtg.api.biome.vanilla.config;

import rtg.api.biome.BiomeConfigProperty;
import rtg.api.biome.BiomeConfigProperty.Type;


public class BiomeConfigVanillaBeach extends BiomeConfigVanillaBase
{
    public static final String decorationPalmTreesId = "decorationPalmTrees";
    public static final String decorationPalmTreesName = "RTG Decoration: Palm Trees";
    
    public BiomeConfigVanillaBeach()
    {
        super("beach");
        
        this.addProperty(new BiomeConfigProperty(decorationPalmTreesId, Type.BOOLEAN, decorationPalmTreesName, "", true));
    }
}
