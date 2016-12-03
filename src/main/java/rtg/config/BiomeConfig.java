package rtg.config;

import net.minecraftforge.common.config.Configuration;

import rtg.config.property.*;
import rtg.config.property.ConfigProperty.Type;
import rtg.world.biome.realistic.RealisticBiomeBase;


public class BiomeConfig extends Config {

    public RealisticBiomeBase realisticBiome;

    /*
     * GLOBAL CONFIGS
     */

    public final ConfigPropertyBoolean ALLOW_VILLAGES;
    public final ConfigPropertyBoolean ALLOW_VOLCANOES;
    public final ConfigPropertyInt VOLCANO_CHANCE;
    public final ConfigPropertyBoolean USE_RTG_DECORATIONS;
    public final ConfigPropertyBoolean USE_RTG_SURFACES;
    public final ConfigPropertyString SURFACE_TOP_BLOCK;
    public final ConfigPropertyInt SURFACE_TOP_BLOCK_META;
    public final ConfigPropertyString SURFACE_FILLER_BLOCK;
    public final ConfigPropertyInt SURFACE_FILLER_BLOCK_META;
    public final ConfigPropertyInt CAVE_DENSITY;
    public final ConfigPropertyInt CAVE_FREQUENCY;
    public final ConfigPropertyInt RAVINE_FREQUENCY;
    public final ConfigPropertyInt BEACH_BIOME;
    public final ConfigPropertyFloat TREE_DENSITY_MULTIPLIER;

    /*
     * OPTIONAL CONFIGS
     */

    public final ConfigPropertyBoolean ALLOW_LOGS;
    public final ConfigPropertyString SURFACE_MIX_BLOCK;
    public final ConfigPropertyInt SURFACE_MIX_BLOCK_META;
    public final ConfigPropertyString SURFACE_MIX_FILLER_BLOCK;
    public final ConfigPropertyInt SURFACE_MIX_FILLER_BLOCK_META;
    public final ConfigPropertyBoolean ALLOW_PALM_TREES;
    public final ConfigPropertyBoolean ALLOW_CACTUS;
    public final ConfigPropertyBoolean ALLOW_COBWEBS;
    public final ConfigPropertyBoolean ALLOW_WHEAT;
    public final ConfigPropertyInt WHEAT_CHANCE;
    public final ConfigPropertyInt WHEAT_MIN_Y;
    public final ConfigPropertyInt WHEAT_MAX_Y;

    public BiomeConfig(RealisticBiomeBase realisticBiome) {

        this.realisticBiome = realisticBiome;

        /*
         * GLOBAL CONFIGS
         */

        ALLOW_VILLAGES = new ConfigPropertyBoolean(Type.BOOLEAN, "Allow Villages", "Villages", "", true);
        this.addProperty(ALLOW_VILLAGES);

        ALLOW_VOLCANOES = new ConfigPropertyBoolean(Type.BOOLEAN, "Allow Volcanoes", "Volcanoes", "", false);
        this.addProperty(ALLOW_VOLCANOES);

        VOLCANO_CHANCE = new ConfigPropertyInt(Type.INTEGER, "Volcano Chance", "Volcanoes", "1/x chance that a volcano will generate if this biome has volcanoes enabled." + Configuration.NEW_LINE + "1 = Always generate if possible; 2 = 50% chance; 4 = 25% chance" + Configuration.NEW_LINE + "Set to -1 to use global setting. Set to 0 to disable volcanoes for this biome." + Configuration.NEW_LINE, -1, -1, Integer.MAX_VALUE);
        this.addProperty(VOLCANO_CHANCE);

        USE_RTG_DECORATIONS = new ConfigPropertyBoolean(Type.BOOLEAN, "Use RTG Decorations", "Decorations", "", true);
        this.addProperty(USE_RTG_DECORATIONS);

        USE_RTG_SURFACES = new ConfigPropertyBoolean(Type.BOOLEAN, "Use RTG Surfaces", "Surfaces", "", true);
        this.addProperty(USE_RTG_SURFACES);

        SURFACE_TOP_BLOCK = new ConfigPropertyString(Type.STRING, "RTG Surface: Top Block", "Surfaces", "", "");
        this.addProperty(SURFACE_TOP_BLOCK);

        SURFACE_TOP_BLOCK_META = new ConfigPropertyInt(Type.INTEGER, "RTG Surface: Top Block Meta", "Surfaces", "", 0, 0, 15);
        this.addProperty(SURFACE_TOP_BLOCK_META);

        SURFACE_FILLER_BLOCK = new ConfigPropertyString(Type.STRING, "RTG Surface: Filler Block", "Surfaces", "", "");
        this.addProperty(SURFACE_FILLER_BLOCK);

        SURFACE_FILLER_BLOCK_META = new ConfigPropertyInt(Type.INTEGER, "RTG Surface: Filler Block Meta", "Surfaces", "", 0, 0, 15);
        this.addProperty(SURFACE_FILLER_BLOCK_META);

        CAVE_DENSITY = new ConfigPropertyInt(Type.INTEGER, "Cave Density", "Caves", "This setting controls the size of caves." + Configuration.NEW_LINE + "HIGHER values = BIGGER caves & MORE lag. (14 = vanilla cave density)" + Configuration.NEW_LINE + "Set to -1 to use global setting. Set to 0 to disable caves for this biome." + Configuration.NEW_LINE, -1, -1, 40);
        this.addProperty(CAVE_DENSITY);

        CAVE_FREQUENCY = new ConfigPropertyInt(Type.INTEGER, "Cave Frequency", "Caves", "This setting controls the number of caves that generate." + Configuration.NEW_LINE + "LOWER values = MORE caves & MORE lag. (6 = vanilla cave frequency)" + Configuration.NEW_LINE + "Set to -1 to use global setting. Set to 0 to disable caves for this biome." + Configuration.NEW_LINE, -1, -1, 40);
        this.addProperty(CAVE_FREQUENCY);

        RAVINE_FREQUENCY = new ConfigPropertyInt(Type.INTEGER, "Ravine Frequency", "Ravines", "This setting controls the number of ravines that generate." + Configuration.NEW_LINE + "LOWER values = MORE ravines & MORE lag. (50 = vanilla ravine frequency)" + Configuration.NEW_LINE + "Set to -1 to use global setting. Set to 0 to disable ravines for this biome." + Configuration.NEW_LINE, -1, -1, 100);
        this.addProperty(RAVINE_FREQUENCY);

        BEACH_BIOME = new ConfigPropertyInt(
            Type.INTEGER, "Beach Biome", "Beaches",
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

        TREE_DENSITY_MULTIPLIER = new ConfigPropertyFloat(
            Type.FLOAT,
            "RTG Tree Density Multiplier",
            "Trees",
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

        ALLOW_LOGS = new ConfigPropertyBoolean(ConfigProperty.Type.BOOLEAN, "RTG Decoration: Logs", "Decorations", "", true);
        SURFACE_MIX_BLOCK = new ConfigPropertyString(Type.STRING, "RTG Surface: Mix Block", "Surfaces", "", "");
        SURFACE_MIX_BLOCK_META = new ConfigPropertyInt(Type.INTEGER, "RTG Surface: Mix Block Meta", "Surfaces", "", 0, 0, 15);
        SURFACE_MIX_FILLER_BLOCK = new ConfigPropertyString(Type.STRING, "RTG Surface: Mix Filler Block", "Surfaces", "", "");
        SURFACE_MIX_FILLER_BLOCK_META = new ConfigPropertyInt(Type.INTEGER, "RTG Surface: Mix Filler Block Meta", "Surfaces", "", 0, 0, 15);
        ALLOW_PALM_TREES = new ConfigPropertyBoolean(Type.BOOLEAN, "RTG Decoration: Palm Trees", "Decorations", "", true);
        ALLOW_CACTUS = new ConfigPropertyBoolean(Type.BOOLEAN, "RTG Decoration: Cactus", "Decorations", "", true);
        ALLOW_COBWEBS = new ConfigPropertyBoolean(Type.BOOLEAN, "RTG Decoration: Cobwebs", "Decorations", "", true);
        ALLOW_WHEAT = new ConfigPropertyBoolean(Type.BOOLEAN, "RTG Decoration: Wheat", "Decorations", "", true);
        WHEAT_CHANCE = new ConfigPropertyInt(Type.INTEGER, "RTG Decoration: Wheat (Chance)", "Decorations", "", 0, 0, Integer.MAX_VALUE);
        WHEAT_MIN_Y = new ConfigPropertyInt(Type.INTEGER, "RTG Decoration: Wheat (Min Y)", "Decorations", "", 0, 0, Integer.MAX_VALUE);
        WHEAT_MAX_Y = new ConfigPropertyInt(Type.INTEGER, "RTG Decoration: Wheat (Max Y)", "Decorations", "", 0, 0, Integer.MAX_VALUE);
    }

    public static String formatSlug(String s) {

        s = s.toLowerCase();
        s = s.replaceAll("\\+", "plus");
        s = s.replaceAll("\\W", "");

        return s;
    }
}
