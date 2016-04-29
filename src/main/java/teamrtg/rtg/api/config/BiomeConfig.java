package teamrtg.rtg.api.config;

import teamrtg.rtg.api.config.ConfigProperty.*;
import teamrtg.rtg.api.util.debug.Logger;
import teamrtg.rtg.util.EnumUtils;
import teamrtg.rtg.world.gen.structure.MapGenScatteredFeatureRTG.FeatureType;

public class BiomeConfig extends Config {

    public final PropertyBool ALLOW_VILLAGES;
    public final PropertyBool USE_RTG_SURFACES;
    public final PropertyBool USE_RTG_DECORATIONS;
    public final PropertyBlock TOP_BLOCK;
    public final PropertyBlock FILL_BLOCK;
    public final PropertyString SCATTERED_FEATURE;
    public final PropertyStrings DECORATIONS;
    public final PropertyInt WATER_POND_CHANCE;
    public final PropertyInt LAVA_POND_CHANCE;
    public final PropertyBool GENERATE_EMERALDS;
    public final PropertyInt RAVINE_FREQUENCY;
    public final PropertyInt CAVE_FREQUENCY;
    public final PropertyInt CAVE_DENSITY;
    public final PropertyBool SURFACE_BLEED_IN;
    public final PropertyBool SURFACE_BLEED_OUT;

    public final PropertyBlock CLIFF_BLOCK_1 = new PropertyBlock("Cliff block 1", "");
    public final PropertyBlock CLIFF_BLOCK_2 = new PropertyBlock("Cliff block 2", "");
    @Deprecated //Should allways use mix top or mix fill
    public final PropertyBlock MIX_BLOCK = new PropertyBlock("Mix block", "");
    public final PropertyBlock MIX_BLOCK_TOP = new PropertyBlock("Mix top block", "");
    public final PropertyBlock MIX_BLOCK_FILL = new PropertyBlock("Mix fill block", "");
    public final PropertyBlock BEACH_BLOCK = new PropertyBlock("Beach block", "");
    public final PropertyBlock BOTTOM_BLOCK = new PropertyBlock("Bottom block", "");

    public String modSlug;
    public String biomeSlug;

    public BiomeConfig(String modSlug, String biomeSlug) {
        super();
        this.modSlug = modSlug;
        this.biomeSlug = biomeSlug;
        Logger.info("%s:%s", modSlug, biomeSlug);

        ALLOW_VILLAGES = this.addBool("Allow Villages", "");
        USE_RTG_SURFACES = this.addBool("Use RTG surfaces", "");
        USE_RTG_DECORATIONS = this.addBool("Use RTG decorations", "");
        TOP_BLOCK = this.addBlock("Top block", "");
        FILL_BLOCK = this.addBlock("Fill block", "");
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

    public PropertyBool addBool(String id) {
        PropertyBool property = new PropertyBool(id, biomeSlug);
        addProperty(property);
        return property;
    }

    public PropertyInt addInt(String id) {
        PropertyInt property = new PropertyInt(id, biomeSlug);
        addProperty(property);
        return property;
    }

    public PropertyString addString(String id) {
        PropertyString property = new PropertyString(id, biomeSlug);
        addProperty(property);
        return property;
    }

    public PropertyBlock addBlock(String id) {
        PropertyBlock property = new PropertyBlock(id, biomeSlug);
        addProperty(property);
        return property;
    }

}
