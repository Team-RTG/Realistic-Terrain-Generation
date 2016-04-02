package rtg.api.config;

/**
 * @author topisani
 */
public class BiomeConfigHelper {
    public static final ConfigProperty.PropertyBool ALLOW_VILLAGES = new ConfigProperty.PropertyBool("Allow Villages", "");
    public static final ConfigProperty.PropertyBool USE_RTG_SURFACES = new ConfigProperty.PropertyBool("Use RTG surfaces", "");
    public static final ConfigProperty.PropertyBool USE_RTG_DECORATIONS = new ConfigProperty.PropertyBool("Use RTG decorations", "");
    public static final ConfigProperty.PropertyBlock TOP_BLOCK = new ConfigProperty.PropertyBlock("Top block", "");
    public static final ConfigProperty.PropertyBlock FILL_BLOCK = new ConfigProperty.PropertyBlock("Fill block", "");

    static {
        ALLOW_VILLAGES.setComment("").setDefault(true);
        USE_RTG_SURFACES.setComment("Set to false to do something that i, topisani, dont understand.").setDefault(true);
        USE_RTG_DECORATIONS.setComment("If false RTG will not try to decorate this biome, but instead let it handle that itself.").setDefault(true);
        TOP_BLOCK.setComment("The top surface block used in this biome (Grass in plains).");
        FILL_BLOCK.setComment("The block that fills between the surface block and the stone underneath (Dirt in plains).");
    }
}
