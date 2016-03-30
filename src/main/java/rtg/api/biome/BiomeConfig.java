package rtg.api.biome;

import rtg.api.biome.ConfigProperty.IPropertyEnum;

import java.util.ArrayList;

import static rtg.api.biome.BiomeConfigProperty.*;

public class BiomeConfig extends Config{

    public String modSlug;
    public String biomeSlug;

    public BiomeConfig(String modSlug, String biomeSlug) {
        this.modSlug = modSlug;
        this.biomeSlug = biomeSlug;

        this.properties = new ArrayList<>();

        this.addProperty(ALLOW_VILLAGES.prop);
        this.addProperty(USE_RTG_DECORATIONS.prop);
        this.addProperty(USE_RTG_SURFACES.prop);
        this.addProperty(SURFACE_TOP_BLOCK.prop);
        this.addProperty(SURFACE_FILLER_BLOCK.prop);
    }

    public BiomeConfig(String modSlug, String biomeSlug, IPropertyEnum[] props) {
        this(modSlug, biomeSlug);
        for (IPropertyEnum prop : props) {
            this.addProperty(prop.getProperty());
        }
    }
}
