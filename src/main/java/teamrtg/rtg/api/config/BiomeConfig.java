package teamrtg.rtg.api.config;


import teamrtg.rtg.api.util.EnumUtils;

public class BiomeConfig extends Config {

    public final ConfigProperty.PropertyBool ALLOW_VILLAGES;
    public final ConfigProperty.PropertyBool USE_RTG_SURFACES;
    public final ConfigProperty.PropertyBool USE_RTG_DECORATIONS;
    public final ConfigProperty.PropertyBlock TOP_BLOCK;
    public final ConfigProperty.PropertyBlock FILL_BLOCK;
    public final ConfigProperty.PropertyInt FILL_LAYERS;
    public final ConfigProperty.PropertyString SCATTERED_FEATURE;
    public final ConfigProperty.PropertyStrings DECORATIONS;
    public final ConfigProperty.PropertyInt WATER_POND_CHANCE;
    public final ConfigProperty.PropertyInt LAVA_POND_CHANCE;
    public final ConfigProperty.PropertyBool GENERATE_EMERALDS;
    public final ConfigProperty.PropertyInt RAVINE_FREQUENCY;
    public final ConfigProperty.PropertyInt CAVE_FREQUENCY;
    public final ConfigProperty.PropertyInt CAVE_DENSITY;
    public final ConfigProperty.PropertyBool SURFACE_BLEED_IN;
    public final ConfigProperty.PropertyBool SURFACE_BLEED_OUT;
    public final ConfigProperty.PropertyBlock CLIFF_BLOCK_1 = new ConfigProperty.PropertyBlock("Cliff block 1", "");
    public final ConfigProperty.PropertyBlock CLIFF_BLOCK_2 = new ConfigProperty.PropertyBlock("Cliff block 2", "");
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

        ALLOW_VILLAGES = this.addBool("Allow Villages", "");
        USE_RTG_SURFACES = this.addBool("Use RTG surfaces", "");
        USE_RTG_DECORATIONS = this.addBool("Use RTG decorations", "");
        TOP_BLOCK = this.addBlock("Top block", "");
        FILL_BLOCK = this.addBlock("Fill block", "");
        FILL_LAYERS = this.addInt("Fill layers", "");
        SCATTERED_FEATURE = this.addString("Scattered feature", "");
        DECORATIONS = this.addStrings("Decorations", "");
        WATER_POND_CHANCE = this.addInt("Surface water lake chance", "");
        LAVA_POND_CHANCE = this.addInt("Surface lava lake chance", "");
        GENERATE_EMERALDS = this.addBool("Generate Emeralds", "");
        RAVINE_FREQUENCY = this.addInt("Ravine frequency", "");
        CAVE_FREQUENCY = this.addInt("Cave frequency", "");
        CAVE_DENSITY = this.addInt("Cave density", "");
        SURFACE_BLEED_IN = this.addBool("Surface bleed into", "");
        SURFACE_BLEED_OUT = this.addBool("Surface bleed out of", "");
        setDefaults();
    }

    private void setDefaults() {
        ALLOW_VILLAGES.setDefault(true).setComment("");
        USE_RTG_SURFACES.setDefault(true).setComment("Set to false to do something that i, topisani, dont understand.").setDefault(true);
        USE_RTG_DECORATIONS.setComment("If false RTG will not try to decorate this biome, but instead let it handle that itself.").setDefault(true);
        TOP_BLOCK.setComment("The top surface block used in this biome (Grass in plains).");
        FILL_BLOCK.setComment("The block that fills between the surface block and the stone underneath (Dirt in plains).");
        FILL_LAYERS.setDefault(4);
        SCATTERED_FEATURE.setOptions(EnumUtils.names(FeatureType.class)).setDefault(FeatureType.NONE.name())
            .setComment("What scattered feature does this biome allow?");
        DECORATIONS.setDefault(new String[0])
            .setComment("What scattered feature does this biome allow?");
        LAVA_POND_CHANCE.setDefault(0);
        WATER_POND_CHANCE.setDefault(10);
        GENERATE_EMERALDS.setDefault(false);
        RAVINE_FREQUENCY.setDefault(-1);
        CAVE_FREQUENCY.setDefault(-1);
        CAVE_DENSITY.setDefault(-1);
        SURFACE_BLEED_IN.setDefault(true);
        SURFACE_BLEED_OUT.setDefault(true);
    }

    @Override
    public void addProperty(ConfigProperty property) {
        super.addProperty(property.setSection(biomeSlug));
    }

    public enum FeatureType {
        DESERT_TEMPLE,
        JUNGLE_TEMPLE,
        WITCH_HUT,
        IGLOO,
        NONE
    }

}
