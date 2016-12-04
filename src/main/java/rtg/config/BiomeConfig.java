package rtg.config;

import net.minecraftforge.common.config.Configuration;

import rtg.api.config.Config;
import rtg.api.config.property.*;
import rtg.api.config.property.ConfigProperty.Type;


public class BiomeConfig extends Config {

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

    public BiomeConfig() {

        /*
         * GLOBAL CONFIGS
         */

        ALLOW_VILLAGES = new ConfigPropertyBoolean(
            ConfigProperty.Type.BOOLEAN,
            "Allow Villages",
            "Villages",
            "Set this to FALSE to prevent villages from generating in this biome.",
            true
        );
        this.addProperty(ALLOW_VILLAGES);

        ALLOW_VOLCANOES = new ConfigPropertyBoolean(
            ConfigProperty.Type.BOOLEAN,
            "Allow volcanoes",
            "Volcanoes",
            "Set this to TRUE to allow volcanoes to generate in this biome.",
            false
        );
        this.addProperty(ALLOW_VOLCANOES);

        VOLCANO_CHANCE = new ConfigPropertyInt(
            Type.INTEGER,
            "Volcano Chance",
            "Volcanoes",
            "1/x chance that a volcano will generate if this biome has volcanoes enabled."
                + Configuration.NEW_LINE + "1 = Always generate if possible; 2 = 50% chance; 4 = 25% chance"
                + Configuration.NEW_LINE + "Set to -1 to use global setting. Set to 0 to disable volcanoes for this biome.",
            -1, -1, Integer.MAX_VALUE
        );
        this.addProperty(VOLCANO_CHANCE);

        USE_RTG_DECORATIONS = new ConfigPropertyBoolean(
            ConfigProperty.Type.BOOLEAN,
            "Use RTG Decorations",
            "Decorations",
            "If FALSE, no RTG decorations will generate in this biome. Instead, only vanilla decorations will generate."
                + Configuration.NEW_LINE + "RTG decorations include custom trees, shrubs, boulders, etc.",
            true
        );
        this.addProperty(USE_RTG_DECORATIONS);

        USE_RTG_SURFACES = new ConfigPropertyBoolean(
            ConfigProperty.Type.BOOLEAN,
            "Use RTG Surfaces",
            "Surfaces",
            "If FALSE, no RTG surfaces will be used in this biome. Instead, only vanilla surfaces will be used."
                + Configuration.NEW_LINE + "RTG surfaces include custom top & filler blocks, and 'mix' blocks like podzol in Forests.",
            true
        );
        this.addProperty(USE_RTG_SURFACES);

        SURFACE_TOP_BLOCK = new ConfigPropertyString(
            Type.STRING,
            "Top Block ID",
            "Surfaces.Top Block",
            "If you want to change this biome's top block, enter a valid block ID here (e.g. minecraft:grass)."
                + Configuration.NEW_LINE +
                "For more info, visit http://minecraft.gamepedia.com/Data_values#Block_IDs",
            ""
        );
        this.addProperty(SURFACE_TOP_BLOCK);

        SURFACE_TOP_BLOCK_META = new ConfigPropertyInt(
            Type.INTEGER,
            "Top Block Meta (Data Value)",
            "Surfaces.Top Block",
            "If you're using a custom top block, enter its numeric data value here."
                + Configuration.NEW_LINE +
                "For example, if you want to use red wool for this biome's top block, you would enter minecraft:wool for the Top Block ID,"
                + Configuration.NEW_LINE +
                "and you would enter 6 here, because red rool has a data value of 6. (For most blocks, this value will be 0.)"
                + Configuration.NEW_LINE +
                "For more info, visit http://minecraft.gamepedia.com/Data_values",
            0, 0, 15
        );
        this.addProperty(SURFACE_TOP_BLOCK_META);

        SURFACE_FILLER_BLOCK = new ConfigPropertyString(
            Type.STRING,
            "Filler Block ID",
            "Surfaces.Filler Block",
            "If you want to change this biome's filler block (the block underneath the top block), enter a valid block ID here (e.g. minecraft:dirt)."
                + Configuration.NEW_LINE +
                "For more info, visit http://minecraft.gamepedia.com/Data_values#Block_IDs",
            ""
        );
        this.addProperty(SURFACE_FILLER_BLOCK);

        SURFACE_FILLER_BLOCK_META = new ConfigPropertyInt(
            Type.INTEGER,
            "Filler Block Meta (Data Value)",
            "Surfaces.Filler Block",
            "If you're using a custom filler block, enter its numeric data value here."
                + Configuration.NEW_LINE +
                "For example, if you want to use red wool for this biome's filler block, you would enter minecraft:wool for the Filler Block ID,"
                + Configuration.NEW_LINE +
                "and you would enter 6 here, because red rool has a data value of 6. (For most blocks, this value will be 0.)"
                + Configuration.NEW_LINE +
                "For more info, visit http://minecraft.gamepedia.com/Data_values",
            0, 0, 15
        );
        this.addProperty(SURFACE_FILLER_BLOCK_META);

        CAVE_DENSITY = new ConfigPropertyInt(
            Type.INTEGER,
            "Cave Density",
            "Caves",
            "This setting controls the size of caves."
                + Configuration.NEW_LINE + "HIGHER values = BIGGER caves & MORE lag. (14 = vanilla cave density)"
                + Configuration.NEW_LINE + "Set to -1 to use global setting. Set to 0 to disable caves for this biome.",
            -1, -1, 40
        );
        this.addProperty(CAVE_DENSITY);

        CAVE_FREQUENCY = new ConfigPropertyInt(
            Type.INTEGER,
            "Cave Frequency",
            "Caves",
            "This setting controls the number of caves that generate."
                + Configuration.NEW_LINE + "LOWER values = MORE caves & MORE lag. (6 = vanilla cave frequency)"
                + Configuration.NEW_LINE + "Set to -1 to use global setting. Set to 0 to disable caves for this biome.",
            -1, -1, 40
        );
        this.addProperty(CAVE_FREQUENCY);

        RAVINE_FREQUENCY = new ConfigPropertyInt(
            Type.INTEGER,
            "Ravine Frequency",
            "Ravines",
            "This setting controls the number of ravines that generate."
                + Configuration.NEW_LINE + "LOWER values = MORE ravines & MORE lag. (50 = vanilla ravine frequency)"
                + Configuration.NEW_LINE + "Set to -1 to use global setting. Set to 0 to disable ravines for this biome.",
            -1, -1, 100
        );
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
                + "Note: If this biome has been hardcoded by RTG to use a specific beach, this setting will have no effect.",
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
                "1.0 = Default tree generation; 2.0 = Twice as many trees; 0.5 = half as many trees; 0 = No trees",
            -1.0f, -1.0f, 5.0f
        );
        this.addProperty(TREE_DENSITY_MULTIPLIER);

        /*
         * OPTIONAL CONFIGS
         *
         * These properties get 'added' by the individual biomes when relevant, so don't 'add' them here.
         */

        SURFACE_MIX_BLOCK = new ConfigPropertyString(
            Type.STRING,
            "Mix Block ID",
            "Surfaces.Mix Top Block",
            "If you want to change this biome's mix block, enter a valid block ID here (e.g. minecraft:grass)."
                + Configuration.NEW_LINE +
                "For more info, visit http://minecraft.gamepedia.com/Data_values#Block_IDs",
            ""
        );

        SURFACE_MIX_BLOCK_META = new ConfigPropertyInt(
            Type.INTEGER,
            "Mix Block Meta (Data Value)",
            "Surfaces.Mix Top Block",
            "If you're using a custom mix block, enter its numeric data value here."
                + Configuration.NEW_LINE +
                "For example, if you want to use podzol for this biome's mix block, you would enter minecraft:dirt for the Mix Block ID,"
                + Configuration.NEW_LINE +
                "and you would enter 2 here, because podzol has a data value of 2. (For most blocks, this value will be 0.)"
                + Configuration.NEW_LINE +
                "For more info, visit http://minecraft.gamepedia.com/Data_values",
            0, 0, 15
        );

        SURFACE_MIX_FILLER_BLOCK = new ConfigPropertyString(
            Type.STRING,
            "Mix Filler Block ID",
            "Surfaces.Mix Filler Block",
            "If you want to change this biome's mix filler block (the block underneath the mix block), enter a valid block ID here (e.g. minecraft:dirt)."
                + Configuration.NEW_LINE +
                "For more info, visit http://minecraft.gamepedia.com/Data_values#Block_IDs",
            ""
        );

        SURFACE_MIX_FILLER_BLOCK_META = new ConfigPropertyInt(
            Type.INTEGER,
            "Mix Filler Block Meta (Data Value)",
            "Surfaces.Mix Filler Block",
            "If you're using a custom mix filler block, enter its numeric data value here."
                + Configuration.NEW_LINE +
                "For example, if you want to use podzol for this biome's mix filler block, you would enter minecraft:dirt for the Mix Filler Block ID,"
                + Configuration.NEW_LINE +
                "and you would enter 2 here, because podzol has a data value of 2. (For most blocks, this value will be 0.)"
                + Configuration.NEW_LINE +
                "For more info, visit http://minecraft.gamepedia.com/Data_values",
            0, 0, 15
        );

        ALLOW_LOGS = new ConfigPropertyBoolean(ConfigProperty.Type.BOOLEAN, "Allow Logs", "Decorations.Logs", "", true);
        ALLOW_PALM_TREES = new ConfigPropertyBoolean(Type.BOOLEAN, "Allow Palm Trees", "Decorations.Palm Trees", "", true);
        ALLOW_CACTUS = new ConfigPropertyBoolean(Type.BOOLEAN, "Allow Cactus", "Decorations.Cactus", "", true);
        ALLOW_COBWEBS = new ConfigPropertyBoolean(Type.BOOLEAN, "Allow Cobwebs", "Decorations.Cobwebs", "", true);
        ALLOW_WHEAT = new ConfigPropertyBoolean(Type.BOOLEAN, "Allow Wheat", "Decorations.Wheat", "", true);
        WHEAT_CHANCE = new ConfigPropertyInt(Type.INTEGER, "Wheat (Chance)", "Decorations.Wheat", "", 0, 0, Integer.MAX_VALUE);
        WHEAT_MIN_Y = new ConfigPropertyInt(Type.INTEGER, "Wheat (Min Y)", "Decorations.Wheat", "", 0, 0, Integer.MAX_VALUE);
        WHEAT_MAX_Y = new ConfigPropertyInt(Type.INTEGER, "Wheat (Max Y)", "Decorations.Wheat", "", 0, 0, Integer.MAX_VALUE);
    }

    public static String formatSlug(String s) {

        s = s.toLowerCase();
        s = s.replaceAll("\\+", "plus");
        s = s.replaceAll("\\W", "");

        return s;
    }
}
