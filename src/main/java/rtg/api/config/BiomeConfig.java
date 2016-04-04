package rtg.api.config;

import rtg.util.EnumUtils;
import rtg.world.gen.structure.MapGenScatteredFeatureRTG.Type;

public class BiomeConfig extends Config {

    public final ConfigProperty.PropertyBool ALLOW_VILLAGES = this.addBool("Allow Villages", "");
    public final ConfigProperty.PropertyBool USE_RTG_SURFACES = this.addBool("Use RTG surfaces", "");
    public final ConfigProperty.PropertyBool USE_RTG_DECORATIONS = this.addBool("Use RTG decorations", "");
    public final ConfigProperty.PropertyBlock TOP_BLOCK = this.addBlock("Top block", "");
    public final ConfigProperty.PropertyBlock FILL_BLOCK = this.addBlock("Fill block", "");
    public final ConfigProperty.PropertyString SCATTERED_FEATURE = this.addString("Scattered feature", "");

    public final ConfigProperty.PropertyBlock CLIFF_BLOCK_1 = new ConfigProperty.PropertyBlock("Cliff block 1", "");
    public final ConfigProperty.PropertyBlock CLIFF_BLOCK_2 = new ConfigProperty.PropertyBlock("Cliff block 2", "");
    public final ConfigProperty.PropertyBlock MIX_BLOCK = new ConfigProperty.PropertyBlock("Mix block", "");
    public final ConfigProperty.PropertyBlock MIX_BLOCK_TOP = new ConfigProperty.PropertyBlock("Mix top block", "");
    public final ConfigProperty.PropertyBlock MIX_BLOCK_FILL = new ConfigProperty.PropertyBlock("Mix fill block", "");
    public final ConfigProperty.PropertyBlock BEACH_BLOCK = new ConfigProperty.PropertyBlock("Beach block", "");
    public final ConfigProperty.PropertyBlock BOTTOM_BLOCK = new ConfigProperty.PropertyBlock("Bottom block", "");

    public String modSlug;
    public String biomeSlug;
    public BiomeConfig(String modSlug, String biomeSlug) {
        super();
        this.modSlug = modSlug;
        this.biomeSlug = biomeSlug;
        setDefaults();
    }

    private void setDefaults() {
        ALLOW_VILLAGES.setDefault(true).setComment("").setSection(biomeSlug);
        USE_RTG_SURFACES.setDefault(true).setComment("Set to false to do something that i, topisani, dont understand.").setDefault(true).setSection(biomeSlug);
        USE_RTG_DECORATIONS.setComment("If false RTG will not try to decorate this biome, but instead let it handle that itself.").setDefault(true).setSection(biomeSlug);
        TOP_BLOCK.setComment("The top surface block used in this biome (Grass in plains).").setSection(biomeSlug);
        FILL_BLOCK.setComment("The block that fills between the surface block and the stone underneath (Dirt in plains).").setSection(biomeSlug);
        SCATTERED_FEATURE.setOptions(EnumUtils.names(Type.class)).setDefault(Type.NONE.name())
                .setComment("What scattered feature does this biome allow?").setSection(biomeSlug);

        CLIFF_BLOCK_1.setSection(biomeSlug);
        CLIFF_BLOCK_2.setSection(biomeSlug);
        MIX_BLOCK.setSection(biomeSlug);
        MIX_BLOCK_TOP.setSection(biomeSlug);
        MIX_BLOCK_FILL.setSection(biomeSlug);
        BEACH_BLOCK.setSection(biomeSlug);
        BOTTOM_BLOCK.setSection(biomeSlug);
    }

    @Override
    public void addProperty(ConfigProperty property) {
        super.addProperty(property.setSection(biomeSlug));
    }

    public ConfigProperty.PropertyBool addBool(String id) {
        ConfigProperty.PropertyBool property = new ConfigProperty.PropertyBool(id, biomeSlug);
        addProperty(property);
        return property;
    }

    public ConfigProperty.PropertyInt addInt(String id) {
        ConfigProperty.PropertyInt property = new ConfigProperty.PropertyInt(id, biomeSlug);
        addProperty(property);
        return property;
    }

    public ConfigProperty.PropertyString addString(String id) {
        ConfigProperty.PropertyString property = new ConfigProperty.PropertyString(id, biomeSlug);
        addProperty(property);
        return property;
    }

    public ConfigProperty.PropertyBlock addBlock(String id) {
        ConfigProperty.PropertyBlock property = new ConfigProperty.PropertyBlock(id, biomeSlug);
        addProperty(property);
        return property;
    }

}
