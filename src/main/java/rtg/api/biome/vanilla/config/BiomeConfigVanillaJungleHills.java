package rtg.api.biome.vanilla.config;

import rtg.api.biome.BiomeConfigProperty;
import rtg.api.biome.BiomeConfigProperty.Type;


public class BiomeConfigVanillaJungleHills extends BiomeConfigVanillaBase
{
    public static final String decorationCactusId = "decorationCactus";
    public static final String decorationCactusName = "RTG Decoration: Cactus";
    
    public BiomeConfigVanillaJungleHills()
    {
        super();
        
        this.biomeSlug = "junglehills";
        
        this.addProperty(new BiomeConfigProperty(decorationCactusId, Type.BOOLEAN, decorationCactusName, "", true));
    }
}
