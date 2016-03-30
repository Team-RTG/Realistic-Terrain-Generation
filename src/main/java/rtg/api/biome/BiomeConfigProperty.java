package rtg.api.biome;


import org.apache.commons.lang3.StringUtils;

import static rtg.api.biome.ConfigProperty.IPropertyEnum.Type.BLOCK;
import static rtg.api.biome.ConfigProperty.IPropertyEnum.Type.BOOLEAN;

public enum BiomeConfigProperty implements ConfigProperty.IPropertyEnum {

    ALLOW_VILLAGES(BOOLEAN),
    USE_RTG_DECORATIONS(BOOLEAN),
    USE_RTG_SURFACES(BOOLEAN),
    SURFACE_TOP_BLOCK(BLOCK),
    SURFACE_FILLER_BLOCK(BLOCK),
    // End defaults
    SURFACE_TOP_MIX_BLOCK(BLOCK),
    SURFACE_FILLER_MIX_BLOCK(BLOCK),
    DECORATION_LOG(BOOLEAN),
    DECORATION_CACTI(BOOLEAN);

    public final String localized;
    public final String comment;
    public final Type type;
    public final ConfigProperty prop;

    BiomeConfigProperty(Type type) {
        this(type, "");
    }

    BiomeConfigProperty(Type type, String comment) {
        this.type = type;
        this.localized = StringUtils.capitalize(this.name().replaceAll("_", " "));
        this.comment = comment;
        this.prop = new ConfigProperty(this, localized, comment);
    }

    public Type getType() {
        return this.type;
    }

    public ConfigProperty getProperty() {
        return prop;
    }
}
