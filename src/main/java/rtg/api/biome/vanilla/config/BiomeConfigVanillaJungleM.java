package rtg.api.biome.vanilla.config;

import rtg.api.biome.BiomeConfigProperty;
import rtg.api.biome.BiomeConfigProperty.Type;


public class BiomeConfigVanillaJungleM extends BiomeConfigVanillaBase
{
    public static final String decorationCactusId = "decorationCactus";
    public static final String decorationCactusName = "RTG Decoration: Cactus";
    
    public BiomeConfigVanillaJungleM()
    {
        super();
        
        this.biomeSlug = "junglem";
        
        this.addProperty(new BiomeConfigProperty(decorationCactusId, Type.BOOLEAN, decorationCactusName, "", true));
    }
}
