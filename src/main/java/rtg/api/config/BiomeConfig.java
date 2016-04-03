package rtg.api.config;

public class BiomeConfig extends Config {

    public final ConfigProperty.PropertyBool ALLOW_VILLAGES = new ConfigProperty.PropertyBool("Allow Villages", "");
    public final ConfigProperty.PropertyBool USE_RTG_SURFACES = new ConfigProperty.PropertyBool("Use RTG surfaces", "");
    public final ConfigProperty.PropertyBool USE_RTG_DECORATIONS = new ConfigProperty.PropertyBool("Use RTG decorations", "");
    public final ConfigProperty.PropertyBlock TOP_BLOCK = new ConfigProperty.PropertyBlock("Top block", "");
    public final ConfigProperty.PropertyBlock FILL_BLOCK = new ConfigProperty.PropertyBlock("Fill block", "");

    public final ConfigProperty.PropertyBool DECORATION_LOG = new ConfigProperty.PropertyBool("Decoration: Log", "").setDefault(true);
    public final ConfigProperty.PropertyBool DECORATION_TREE_PALM = new ConfigProperty.PropertyBool("Decoration: Palm tree", "").setDefault(true);

    public final ConfigProperty.PropertyBlock CLIFF_BLOCK_1 = new ConfigProperty.PropertyBlock("Cliff block 1", "");
    public final ConfigProperty.PropertyBlock CLIFF_BLOCK_2 = new ConfigProperty.PropertyBlock("Cliff block 2", "");
    public final ConfigProperty.PropertyBlock MIX_BLOCK = new ConfigProperty.PropertyBlock("Mix block", "");
    public final ConfigProperty.PropertyBlock MIX_BLOCK_TOP = new ConfigProperty.PropertyBlock("Mix top block", "");
    public final ConfigProperty.PropertyBlock MIX_BLOCK_FILL = new ConfigProperty.PropertyBlock("Mix fill block", "");
    public final ConfigProperty.PropertyBlock BEACH_BLOCK = new ConfigProperty.PropertyBlock("Beach block", "");
    public final ConfigProperty.PropertyBlock BOTTOM_BLOCK = new ConfigProperty.PropertyBlock("Bottom block", "");

    private void setDefaults() {
        ALLOW_VILLAGES.setComment("").setDefault(true);
        USE_RTG_SURFACES.setComment("Set to false to do something that i, topisani, dont understand.").setDefault(true);
        USE_RTG_DECORATIONS.setComment("If false RTG will not try to decorate this biome, but instead let it handle that itself.").setDefault(true);
        TOP_BLOCK.setComment("The top surface block used in this biome (Grass in plains).");
        FILL_BLOCK.setComment("The block that fills between the surface block and the stone underneath (Dirt in plains).");
    }

    public String modSlug;
    public String biomeSlug;

    public BiomeConfig(String modSlug, String biomeSlug) {
        super();
        this.modSlug = modSlug;
        this.biomeSlug = biomeSlug;
        setDefaults();
    }

    public ConfigProperty.PropertyBool addBool(String id) {
        ConfigProperty.PropertyBool property = new ConfigProperty.PropertyBool(id, "");
        this.properties.add(property);
        return property;
    }

    public ConfigProperty.PropertyInt addInt(String id) {
        ConfigProperty.PropertyInt property = new ConfigProperty.PropertyInt(id, "");
        this.properties.add(property);
        return property;
    }

    public ConfigProperty.PropertyString addString(String id) {
        ConfigProperty.PropertyString property = new ConfigProperty.PropertyString(id, "");
        this.properties.add(property);
        return property;
    }

    public ConfigProperty.PropertyBlock addBlock(String id) {
        ConfigProperty.PropertyBlock property = new ConfigProperty.PropertyBlock(id, "");
        this.properties.add(property);
        return property;
    }

}
