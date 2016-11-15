package rtg.config;

import java.util.ArrayList;

import net.minecraftforge.common.config.Configuration;

import rtg.config.BiomeConfigProperty.Type;
import rtg.world.biome.realistic.RealisticBiomeBase;


public class BiomeConfig {

    public RealisticBiomeBase realisticBiome;
    public ArrayList<BiomeConfigProperty> properties;

    /*
     * GLOBAL CONFIGS
     */

    public final BiomeConfigPropertyBoolean ALLOW_VILLAGES;
    public final BiomeConfigPropertyBoolean ALLOW_VOLCANOES;
    public final BiomeConfigPropertyInt VOLCANO_CHANCE;
    public final BiomeConfigPropertyBoolean USE_RTG_DECORATIONS;
    public final BiomeConfigPropertyBoolean USE_RTG_SURFACES;
    public final BiomeConfigPropertyString SURFACE_TOP_BLOCK;
    public final BiomeConfigPropertyInt SURFACE_TOP_BLOCK_META;
    public final BiomeConfigPropertyString SURFACE_FILLER_BLOCK;
    public final BiomeConfigPropertyInt SURFACE_FILLER_BLOCK_META;
    public final BiomeConfigPropertyInt CAVE_DENSITY;
    public final BiomeConfigPropertyInt CAVE_FREQUENCY;
    public final BiomeConfigPropertyInt RAVINE_FREQUENCY;
    public final BiomeConfigPropertyInt BEACH_BIOME;
    public final BiomeConfigPropertyFloat TREE_DENSITY_MULTIPLIER;

    /*
     * OPTIONAL CONFIGS
     */

    public final BiomeConfigPropertyBoolean ALLOW_LOGS;
    public final BiomeConfigPropertyString SURFACE_MIX_BLOCK;
    public final BiomeConfigPropertyInt SURFACE_MIX_BLOCK_META;
    public final BiomeConfigPropertyString SURFACE_MIX_FILLER_BLOCK;
    public final BiomeConfigPropertyInt SURFACE_MIX_FILLER_BLOCK_META;
    public final BiomeConfigPropertyBoolean ALLOW_PALM_TREES;
    public final BiomeConfigPropertyBoolean ALLOW_CACTUS;
    public final BiomeConfigPropertyBoolean ALLOW_COBWEBS;
    public final BiomeConfigPropertyBoolean ALLOW_WHEAT;
    public final BiomeConfigPropertyInt WHEAT_CHANCE;
    public final BiomeConfigPropertyInt WHEAT_MIN_Y;
    public final BiomeConfigPropertyInt WHEAT_MAX_Y;

    public BiomeConfig(RealisticBiomeBase realisticBiome) {

        this.realisticBiome = realisticBiome;
        this.properties = new ArrayList<BiomeConfigProperty>();

        /*
         * GLOBAL CONFIGS
         */

        ALLOW_VILLAGES = new BiomeConfigPropertyBoolean(Type.BOOLEAN, "Allow Villages", "", true);
        this.addProperty(ALLOW_VILLAGES);

        ALLOW_VOLCANOES = new BiomeConfigPropertyBoolean(Type.BOOLEAN, "Allow Volcanoes", "", false);
        this.addProperty(ALLOW_VOLCANOES);

        VOLCANO_CHANCE = new BiomeConfigPropertyInt(Type.INTEGER, "Volcano Chance", "1/x chance that a volcano will generate if this biome has volcanoes enabled." + Configuration.NEW_LINE + "1 = Always generate if possible; 2 = 50% chance; 4 = 25% chance" + Configuration.NEW_LINE + "Set to -1 to use global setting. Set to 0 to disable volcanoes for this biome." + Configuration.NEW_LINE, -1, -1, Integer.MAX_VALUE);
        this.addProperty(VOLCANO_CHANCE);

        USE_RTG_DECORATIONS = new BiomeConfigPropertyBoolean(Type.BOOLEAN, "Use RTG Decorations", "", true);
        this.addProperty(USE_RTG_DECORATIONS);

        USE_RTG_SURFACES = new BiomeConfigPropertyBoolean(Type.BOOLEAN, "Use RTG Surfaces", "", true);
        this.addProperty(USE_RTG_SURFACES);

        SURFACE_TOP_BLOCK = new BiomeConfigPropertyString(Type.STRING, "RTG Surface: Top Block", "", "");
        this.addProperty(SURFACE_TOP_BLOCK);

        SURFACE_TOP_BLOCK_META = new BiomeConfigPropertyInt(Type.INTEGER, "RTG Surface: Top Block Meta", "", 0, 0, 15);
        this.addProperty(SURFACE_TOP_BLOCK_META);

        SURFACE_FILLER_BLOCK = new BiomeConfigPropertyString(Type.STRING, "RTG Surface: Filler Block", "", "");
        this.addProperty(SURFACE_FILLER_BLOCK);

        SURFACE_FILLER_BLOCK_META = new BiomeConfigPropertyInt(Type.INTEGER, "RTG Surface: Filler Block Meta", "", 0, 0, 15);
        this.addProperty(SURFACE_FILLER_BLOCK_META);

        CAVE_DENSITY = new BiomeConfigPropertyInt(Type.INTEGER, "Cave Density", "This setting controls the size of caves." + Configuration.NEW_LINE + "HIGHER values = BIGGER caves & MORE lag. (14 = vanilla cave density)" + Configuration.NEW_LINE + "Set to -1 to use global setting. Set to 0 to disable caves for this biome." + Configuration.NEW_LINE, -1, -1, 40);
        this.addProperty(CAVE_DENSITY);

        CAVE_FREQUENCY = new BiomeConfigPropertyInt(Type.INTEGER, "Cave Frequency", "This setting controls the number of caves that generate." + Configuration.NEW_LINE + "LOWER values = MORE caves & MORE lag. (6 = vanilla cave frequency)" + Configuration.NEW_LINE + "Set to -1 to use global setting. Set to 0 to disable caves for this biome." + Configuration.NEW_LINE, -1, -1, 40);
        this.addProperty(CAVE_FREQUENCY);

        RAVINE_FREQUENCY = new BiomeConfigPropertyInt(Type.INTEGER, "Ravine Frequency", "This setting controls the number of ravines that generate." + Configuration.NEW_LINE + "LOWER values = MORE ravines & MORE lag. (50 = vanilla ravine frequency)" + Configuration.NEW_LINE + "Set to -1 to use global setting. Set to 0 to disable ravines for this biome." + Configuration.NEW_LINE, -1, -1, 100);
        this.addProperty(RAVINE_FREQUENCY);

        BEACH_BIOME = new BiomeConfigPropertyInt(
            Type.INTEGER, "Beach Biome",
            "Biome ID to use for this biome's beach."
                + Configuration.NEW_LINE
                + "The only 'officially supported' values for this setting are:"
                + Configuration.NEW_LINE
                + "-1 = Automatic beach generation (RECOMMENDED)"
                + Configuration.NEW_LINE
                + "16 = Vanilla Beach"
                + Configuration.NEW_LINE
                + "26 = Vanilla Cold Beach"
                + Configuration.NEW_LINE
                + "25 = Vanilla Stone Beach"
                + Configuration.NEW_LINE
                + "The ID of this biome = No beach"
                + Configuration.NEW_LINE
                + "Other biome IDs are allowed, but they have not been tested, may yield undesirable results, and will not be supported."
                + Configuration.NEW_LINE
                + "Note: If this biome has been hardcoded by RTG to use a specific beach, this setting will have no effect."
                + Configuration.NEW_LINE,
            -1, -1, 255
        );
        this.addProperty(BEACH_BIOME);

        TREE_DENSITY_MULTIPLIER = new BiomeConfigPropertyFloat(
            Type.FLOAT,
            "RTG Tree Density Multiplier",
            "This setting allows you to increase/decrease the number of RTG trees that generate in this biome."
                + Configuration.NEW_LINE +
                "This setting overrides the global setting (see /.minecraft/config/RTG/rtg.cfg) and only affects trees generated by RTG."
                + Configuration.NEW_LINE +
                "Trees generated by this biome's decorator will adhere to their own density rules."
                + Configuration.NEW_LINE +
                "Set to -1.0 to use the global setting."
                + Configuration.NEW_LINE +
                "1.0 = Default tree generation; 2.0 = Twice as many trees; 0.5 = half as many trees; 0 = No trees"
                + Configuration.NEW_LINE,
            -1.0f, -1.0f, 5.0f
        );
        this.addProperty(TREE_DENSITY_MULTIPLIER);

        /*
         * OPTIONAL CONFIGS
         */

        ALLOW_LOGS = new BiomeConfigPropertyBoolean(BiomeConfigProperty.Type.BOOLEAN, "RTG Decoration: Logs", "", true);
        SURFACE_MIX_BLOCK = new BiomeConfigPropertyString(Type.STRING, "RTG Surface: Mix Block", "", "");
        SURFACE_MIX_BLOCK_META = new BiomeConfigPropertyInt(Type.INTEGER, "RTG Surface: Mix Block Meta", "", 0, 0, 15);
        SURFACE_MIX_FILLER_BLOCK = new BiomeConfigPropertyString(Type.STRING, "RTG Surface: Mix Filler Block", "", "");
        SURFACE_MIX_FILLER_BLOCK_META = new BiomeConfigPropertyInt(Type.INTEGER, "RTG Surface: Mix Filler Block Meta", "", 0, 0, 15);
        ALLOW_PALM_TREES = new BiomeConfigPropertyBoolean(Type.BOOLEAN, "RTG Decoration: Palm Trees", "", true);
        ALLOW_CACTUS = new BiomeConfigPropertyBoolean(Type.BOOLEAN, "RTG Decoration: Cactus", "", true);
        ALLOW_COBWEBS = new BiomeConfigPropertyBoolean(Type.BOOLEAN, "RTG Decoration: Cobwebs", "", true);
        ALLOW_WHEAT = new BiomeConfigPropertyBoolean(Type.BOOLEAN, "RTG Decoration: Wheat", "", true);
        WHEAT_CHANCE = new BiomeConfigPropertyInt(Type.INTEGER, "RTG Decoration: Wheat (Chance)", "", 0, 0, Integer.MAX_VALUE);
        WHEAT_MIN_Y = new BiomeConfigPropertyInt(Type.INTEGER, "RTG Decoration: Wheat (Min Y)", "", 0, 0, Integer.MAX_VALUE);
        WHEAT_MAX_Y = new BiomeConfigPropertyInt(Type.INTEGER, "RTG Decoration: Wheat (Max Y)", "", 0, 0, Integer.MAX_VALUE);
    }

    protected void addProp(BiomeConfigProperty property) {

        for (int i = 0; i < this.properties.size(); i++) {

            if (this.properties.get(i).name.contentEquals(property.name)) {
                removeProp(property.name);
                break;
            }
        }

        this.properties.add(property);
    }

    protected void removeProp(String name) {

        for (int i = 0; i < this.properties.size(); i++) {

            if (this.properties.get(i).name.contentEquals(name)) {
                this.properties.remove(i);
                return;
            }
        }
    }

    public BiomeConfigPropertyBoolean addProperty(BiomeConfigPropertyBoolean property) {
        this.addProp(property);
        return property;
    }

    public BiomeConfigPropertyFloat addProperty(BiomeConfigPropertyFloat property) {
        this.addProp(property);
        return property;
    }

    public BiomeConfigPropertyInt addProperty(BiomeConfigPropertyInt property) {
        this.addProp(property);
        return property;
    }

    public BiomeConfigPropertyString addProperty(BiomeConfigPropertyString property) {
        this.addProp(property);
        return property;
    }

    public ArrayList<BiomeConfigProperty> getProperties() {

        return this.properties;
    }

    public static String formatSlug(String s) {

        s = s.toLowerCase();
        s = s.replaceAll("\\+", "plus");
        s = s.replaceAll("\\W", "");

        return s;
    }
}
