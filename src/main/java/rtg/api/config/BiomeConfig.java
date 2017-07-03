package rtg.api.config;

import net.minecraftforge.common.config.Configuration;

import rtg.api.config.property.*;
import rtg.api.config.property.ConfigProperty.Type;


public class BiomeConfig extends Config {

    public static final String MESA_PLATEAU_GRADIENT_METAS = "" +
        "4,4,4,-1,-1,1,14,14,-1,8,0,-1,-1,-1,1,-1,-1,-1,1,8,0,12,12,12,-1,1,-1,1,-1,1,-1,-1,-1";

    public static final String SAVANNA_PLATEAU_GRADIENT_METAS = "" +
        "0,0,0,0,8,8,12,12,8,0,8,12,12,8,12,8,0,0,8,12,12";

    /*
     * GLOBAL CONFIGS
     */

    public final ConfigPropertyBoolean ALLOW_RIVERS;
    public final ConfigPropertyBoolean ALLOW_SCENIC_LAKES;
    public final ConfigPropertyBoolean ALLOW_VILLAGES;
    public final ConfigPropertyBoolean ALLOW_VOLCANOES;
    public final ConfigPropertyInt VOLCANO_CHANCE;
    public final ConfigPropertyBoolean USE_RTG_DECORATIONS;
    public final ConfigPropertyBoolean USE_RTG_SURFACES;
    public final ConfigPropertyBoolean USE_RTG_TERRAIN;
    public final ConfigPropertyString SURFACE_TOP_BLOCK;
    public final ConfigPropertyInt SURFACE_TOP_BLOCK_META;
    public final ConfigPropertyString SURFACE_FILLER_BLOCK;
    public final ConfigPropertyInt SURFACE_FILLER_BLOCK_META;
    public final ConfigPropertyString SURFACE_CLIFF_STONE_BLOCK;
    public final ConfigPropertyInt SURFACE_CLIFF_STONE_BLOCK_META;
    public final ConfigPropertyString SURFACE_CLIFF_COBBLE_BLOCK;
    public final ConfigPropertyInt SURFACE_CLIFF_COBBLE_BLOCK_META;
    //public final ConfigPropertyInt CAVE_DENSITY;
    //public final ConfigPropertyInt CAVE_FREQUENCY;
    //public final ConfigPropertyInt RAVINE_FREQUENCY;
    public final ConfigPropertyInt BEACH_BIOME;
    public final ConfigPropertyFloat TREE_DENSITY_MULTIPLIER;
    public final ConfigPropertyString TEMPERATURE;

    public final ConfigPropertyBoolean SURFACE_BLEED_IN;
    public final ConfigPropertyBoolean SURFACE_BLEED_OUT;

    /*
     * OPTIONAL CONFIGS
     */

    public final ConfigPropertyBoolean ALLOW_LOGS;
    public final ConfigPropertyString SURFACE_MIX_BLOCK;
    public final ConfigPropertyInt SURFACE_MIX_BLOCK_META;
    public final ConfigPropertyString SURFACE_MIX_FILLER_BLOCK;
    public final ConfigPropertyInt SURFACE_MIX_FILLER_BLOCK_META;
    public final ConfigPropertyString SURFACE_MIX_2_BLOCK;
    public final ConfigPropertyInt SURFACE_MIX_2_BLOCK_META;
    public final ConfigPropertyString SURFACE_MIX_3_BLOCK;
    public final ConfigPropertyInt SURFACE_MIX_3_BLOCK_META;
    public final ConfigPropertyString SURFACE_MIX_4_BLOCK;
    public final ConfigPropertyInt SURFACE_MIX_4_BLOCK_META;
    public final ConfigPropertyBoolean ALLOW_PLATEAU_MODIFICATIONS;
    public final ConfigPropertyString PLATEAU_GRADIENT_BLOCK_ID;
    public final ConfigPropertyString PLATEAU_GRADIENT_METAS;
    public final ConfigPropertyString PLATEAU_BLOCK_ID;
    public final ConfigPropertyInt PLATEAU_BLOCK_META;
    public final ConfigPropertyBoolean ALLOW_PALM_TREES;
    public final ConfigPropertyBoolean ALLOW_CACTUS;
    public final ConfigPropertyBoolean ALLOW_COBWEBS;
    public final ConfigPropertyBoolean ALLOW_WHEAT;
    public final ConfigPropertyBoolean ALLOW_PONDS_WATER;
    public final ConfigPropertyInt WHEAT_CHANCE;
    public final ConfigPropertyInt WHEAT_MIN_Y;
    public final ConfigPropertyInt WHEAT_MAX_Y;
    public final ConfigPropertyBoolean USE_ARCTIC_SURFACE;
    public final ConfigPropertyBoolean ALLOW_ICE_TREES;
    public final ConfigPropertyFloat FALLEN_LOG_DENSITY_MULTIPLIER;
    public final ConfigPropertyBoolean ALLOW_SPONGE;
    public final ConfigPropertyBoolean ALLOW_OCEAN_WAVES;

    public BiomeConfig() {

        /*
         * GLOBAL CONFIGS
         */

        ALLOW_RIVERS = new ConfigPropertyBoolean(
            ConfigProperty.Type.BOOLEAN,
            "Allow Rivers",
            "Terrain Features",
            "Set this to FALSE to prevent rivers from generating in this biome.",
            true
        );
        this.addProperty(ALLOW_RIVERS);

        ALLOW_SCENIC_LAKES = new ConfigPropertyBoolean(
            ConfigProperty.Type.BOOLEAN,
            "Allow Scenic Lakes",
            "Terrain Features",
            "Set this to FALSE to prevent scenic lakes from generating in this biome.",
            true
        );
        this.addProperty(ALLOW_SCENIC_LAKES);

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

        USE_RTG_TERRAIN = new ConfigPropertyBoolean(
            ConfigProperty.Type.BOOLEAN,
            "Use RTG Terrain",
            "Terrain",
            "If FALSE, no realistic terrain will be generated in this biome. Instead, vanilla terrain will be generated.",
            true
        );
        this.addProperty(USE_RTG_TERRAIN);

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
                "and you would enter 6 here, because red wool has a data value of 6. (For most blocks, this value will be 0.)"
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
                "and you would enter 6 here, because red wool has a data value of 6. (For most blocks, this value will be 0.)"
                + Configuration.NEW_LINE +
                "For more info, visit http://minecraft.gamepedia.com/Data_values",
            0, 0, 15
        );
        this.addProperty(SURFACE_FILLER_BLOCK_META);

        SURFACE_CLIFF_STONE_BLOCK = new ConfigPropertyString(
            Type.STRING,
            "Cliff Stone Block ID",
            "Surfaces.Cliff Stone Block",
            "Cliff blocks are the blocks that are used on the cliffs of mountains (usually a blend of stone & cobblestone)."
                + Configuration.NEW_LINE +
                "If you want to change this biome's cliff stone block, enter a valid block ID here (e.g. minecraft:stone)."
                + Configuration.NEW_LINE +
                "For more info, visit http://minecraft.gamepedia.com/Data_values#Block_IDs",
            ""
        );
        this.addProperty(SURFACE_CLIFF_STONE_BLOCK);

        SURFACE_CLIFF_STONE_BLOCK_META = new ConfigPropertyInt(
            Type.INTEGER,
            "Cliff Stone Block Meta (Data Value)",
            "Surfaces.Cliff Stone Block",
            "If you're using a custom cliff stone block, enter its numeric data value here."
                + Configuration.NEW_LINE +
                "For example, if you want to use red wool for this biome's cliff stone block, you would enter minecraft:wool for the Cliff Stone Block ID,"
                + Configuration.NEW_LINE +
                "and you would enter 6 here, because red wool has a data value of 6. (For most blocks, this value will be 0.)"
                + Configuration.NEW_LINE +
                "For more info, visit http://minecraft.gamepedia.com/Data_values",
            0, 0, 15
        );
        this.addProperty(SURFACE_CLIFF_STONE_BLOCK_META);

        SURFACE_CLIFF_COBBLE_BLOCK = new ConfigPropertyString(
            Type.STRING,
            "Cliff Cobble Block ID",
            "Surfaces.Cliff Cobble Block",
            "Cliff blocks are the blocks that are used on the cliffs of mountains (usually a blend of stone & cobblestone)."
                + Configuration.NEW_LINE +
                "If you want to change this biome's cliff cobble block, enter a valid block ID here (e.g. minecraft:cobblestone)."
                + Configuration.NEW_LINE +
                "For more info, visit http://minecraft.gamepedia.com/Data_values#Block_IDs",
            ""
        );
        this.addProperty(SURFACE_CLIFF_COBBLE_BLOCK);

        SURFACE_CLIFF_COBBLE_BLOCK_META = new ConfigPropertyInt(
            Type.INTEGER,
            "Cliff Cobble Block Meta (Data Value)",
            "Surfaces.Cliff Cobble Block",
            "If you're using a custom cliff cobble block, enter its numeric data value here."
                + Configuration.NEW_LINE +
                "For example, if you want to use red wool for this biome's cliff cobble block, you would enter minecraft:wool for the Cliff Cobble Block ID,"
                + Configuration.NEW_LINE +
                "and you would enter 6 here, because red wool has a data value of 6. (For most blocks, this value will be 0.)"
                + Configuration.NEW_LINE +
                "For more info, visit http://minecraft.gamepedia.com/Data_values",
            0, 0, 15
        );
        this.addProperty(SURFACE_CLIFF_COBBLE_BLOCK_META);

//        CAVE_DENSITY = new ConfigPropertyInt(
//            Type.INTEGER,
//            "Cave Density",
//            "Caves",
//            "This setting controls the size of caves."
//                + Configuration.NEW_LINE + "HIGHER values = BIGGER caves & MORE lag. (14 = vanilla cave density)"
//                + Configuration.NEW_LINE + "Set to -1 to use global setting. Set to 0 to disable caves for this biome.",
//            -1, -1, 40
//        );
//        this.addProperty(CAVE_DENSITY);
//
//        CAVE_FREQUENCY = new ConfigPropertyInt(
//            Type.INTEGER,
//            "Cave Frequency",
//            "Caves",
//            "This setting controls the number of caves that generate."
//                + Configuration.NEW_LINE + "LOWER values = MORE caves & MORE lag. (6 = vanilla cave frequency)"
//                + Configuration.NEW_LINE + "Set to -1 to use global setting. Set to 0 to disable caves for this biome.",
//            -1, -1, 40
//        );
//        this.addProperty(CAVE_FREQUENCY);
//
//        RAVINE_FREQUENCY = new ConfigPropertyInt(
//            Type.INTEGER,
//            "Ravine Frequency",
//            "Ravines",
//            "This setting controls the number of ravines that generate."
//                + Configuration.NEW_LINE + "LOWER values = MORE ravines & MORE lag. (50 = vanilla ravine frequency)"
//                + Configuration.NEW_LINE + "Set to -1 to use global setting. Set to 0 to disable ravines for this biome.",
//            -1, -1, 100
//        );
//        this.addProperty(RAVINE_FREQUENCY);

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

        TEMPERATURE = new ConfigPropertyString(
            Type.STRING,
            "Temperature",
            "Biome Properties",
            "If you want to change this biome's temperature, enter a valid value here. [range: -2.0 ~ 2.0]"
                + Configuration.NEW_LINE +
                "In keeping with vanilla's temperature validation rules, values in the range of 0.1 to 0.2 (non-inclusive) are not valid and will result in a crash on startup."
                + Configuration.NEW_LINE +
                "If this value is empty, the biome's default temperature will be used."
                + Configuration.NEW_LINE +
                "Please note that changing a biome's temperature does NOT affect its climate type (DESERT, WARM, COOL, ICY)."
                + Configuration.NEW_LINE +
                "For more info, visit http://minecraft.gamepedia.com/Biome#Temperature",
            ""
        );
        this.addProperty(TEMPERATURE);

        SURFACE_BLEED_IN = this.addProperty(new ConfigPropertyBoolean(
                Type.BOOLEAN,
                "Surface Bleed In",
                "Surface Bleed",
                "Set to false if other biomes shouldn't bleed into this one",
                false
        ));

        SURFACE_BLEED_OUT = this.addProperty(new ConfigPropertyBoolean(
                Type.BOOLEAN,
                "Surface Bleed Out",
                "Surface Bleed",
                "Set to false if this biome shouldn't bleed into other biomes",
                false
        ));

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

        SURFACE_MIX_2_BLOCK = new ConfigPropertyString(
            Type.STRING,
            "Mix 2 Block ID",
            "Surfaces.Mix 2 Top Block",
            "If you want to change this biome's 2nd mix block, enter a valid block ID here (e.g. minecraft:grass)."
                + Configuration.NEW_LINE +
                "For more info, visit http://minecraft.gamepedia.com/Data_values#Block_IDs",
            ""
        );

        SURFACE_MIX_2_BLOCK_META = new ConfigPropertyInt(
            Type.INTEGER,
            "Mix 2 Block Meta (Data Value)",
            "Surfaces.Mix 2 Top Block",
            "If you're using a custom 2nd mix block, enter its numeric data value here."
                + Configuration.NEW_LINE +
                "For example, if you want to use podzol for this biome's 2nd mix block, you would enter minecraft:dirt for the Mix 2 Block ID,"
                + Configuration.NEW_LINE +
                "and you would enter 2 here, because podzol has a data value of 2. (For most blocks, this value will be 0.)"
                + Configuration.NEW_LINE +
                "For more info, visit http://minecraft.gamepedia.com/Data_values",
            0, 0, 15
        );

        SURFACE_MIX_3_BLOCK = new ConfigPropertyString(
            Type.STRING,
            "Mix 3 Block ID",
            "Surfaces.Mix 3 Top Block",
            "If you want to change this biome's 3rd mix block, enter a valid block ID here (e.g. minecraft:grass)."
                + Configuration.NEW_LINE +
                "For more info, visit http://minecraft.gamepedia.com/Data_values#Block_IDs",
            ""
        );

        SURFACE_MIX_3_BLOCK_META = new ConfigPropertyInt(
            Type.INTEGER,
            "Mix 3 Block Meta (Data Value)",
            "Surfaces.Mix 3 Top Block",
            "If you're using a custom 3rd mix block, enter its numeric data value here."
                + Configuration.NEW_LINE +
                "For example, if you want to use podzol for this biome's 3rd mix block, you would enter minecraft:dirt for the Mix 3 Block ID,"
                + Configuration.NEW_LINE +
                "and you would enter 2 here, because podzol has a data value of 2. (For most blocks, this value will be 0.)"
                + Configuration.NEW_LINE +
                "For more info, visit http://minecraft.gamepedia.com/Data_values",
            0, 0, 15
        );

        SURFACE_MIX_4_BLOCK = new ConfigPropertyString(
            Type.STRING,
            "Mix 4 Block ID",
            "Surfaces.Mix 4 Top Block",
            "If you want to change this biome's 4th mix block, enter a valid block ID here (e.g. minecraft:grass)."
                + Configuration.NEW_LINE +
                "For more info, visit http://minecraft.gamepedia.com/Data_values#Block_IDs",
            ""
        );

        SURFACE_MIX_4_BLOCK_META = new ConfigPropertyInt(
            Type.INTEGER,
            "Mix 4 Block Meta (Data Value)",
            "Surfaces.Mix 4 Top Block",
            "If you're using a custom 4th mix block, enter its numeric data value here."
                + Configuration.NEW_LINE +
                "For example, if you want to use podzol for this biome's 4th mix block, you would enter minecraft:dirt for the Mix 4 Block ID,"
                + Configuration.NEW_LINE +
                "and you would enter 2 here, because podzol has a data value of 2. (For most blocks, this value will be 0.)"
                + Configuration.NEW_LINE +
                "For more info, visit http://minecraft.gamepedia.com/Data_values",
            0, 0, 15
        );

        ALLOW_PLATEAU_MODIFICATIONS = new ConfigPropertyBoolean(
            ConfigProperty.Type.BOOLEAN,
            "Allow Plateau Modifications",
            "Plateaus",
            "",
            false
        );

        PLATEAU_GRADIENT_BLOCK_ID = new ConfigPropertyString(
            ConfigProperty.Type.STRING,
            "Plateau Gradient Block ID",
            "Plateaus.Gradient Blocks",
            "The block to use for this biome's plateau gradients. Defaults to stained hardened clay." +
                Configuration.NEW_LINE +
                "This can be any block, but it works best with blocks that have multiple colours, such as stained hardened clay." +
                Configuration.NEW_LINE +
                "The various 'meta' options in this section will use this block to configure the plateau gradients.",
            "minecraft:stained_hardened_clay"
        );

        PLATEAU_GRADIENT_METAS = new ConfigPropertyString(
            ConfigProperty.Type.STRING,
            "Plateau Gradient Block Meta Values",
            "Plateaus.Gradient Blocks",
            getPlateauGradientBlockMetasComment(),
            MESA_PLATEAU_GRADIENT_METAS
        );

        PLATEAU_BLOCK_ID = new ConfigPropertyString(
            ConfigProperty.Type.STRING,
            "Plateau Block ID",
            "Plateaus.Plateau Blocks",
            "An extra block to use for Mesa & Savanna plateau gradients. Defaults to hardened clay." +
                Configuration.NEW_LINE +
                "When configuring the various 'meta' options in this section, use a value of '-1' to reference this block.",
            "minecraft:hardened_clay"
        );

        PLATEAU_BLOCK_META = new ConfigPropertyInt(
            ConfigProperty.Type.INTEGER,
            "Plateau Block Meta Value",
            "Plateaus.Plateau Blocks",
            "The meta value of the plateau block.",
            0, 0, 15
        );

        ALLOW_LOGS = new ConfigPropertyBoolean(ConfigProperty.Type.BOOLEAN, "Allow Logs", "Decorations.Logs", "", true);
        ALLOW_PALM_TREES = new ConfigPropertyBoolean(Type.BOOLEAN, "Allow Palm Trees", "Decorations.Palm Trees", "", true);
        ALLOW_CACTUS = new ConfigPropertyBoolean(Type.BOOLEAN, "Allow Cactus", "Decorations.Cactus", "", true);
        ALLOW_COBWEBS = new ConfigPropertyBoolean(Type.BOOLEAN, "Allow Cobwebs", "Decorations.Cobwebs", "", true);
        ALLOW_WHEAT = new ConfigPropertyBoolean(Type.BOOLEAN, "Allow Wheat", "Decorations.Wheat", "", true);
        ALLOW_PONDS_WATER = new ConfigPropertyBoolean(Type.BOOLEAN, "Allow Ponds (Water)", "Decorations.Ponds", "", true);
        WHEAT_CHANCE = new ConfigPropertyInt(Type.INTEGER, "Wheat (Chance)", "Decorations.Wheat", "", 0, 0, Integer.MAX_VALUE);
        WHEAT_MIN_Y = new ConfigPropertyInt(Type.INTEGER, "Wheat (Min Y)", "Decorations.Wheat", "", 0, 0, Integer.MAX_VALUE);
        WHEAT_MAX_Y = new ConfigPropertyInt(Type.INTEGER, "Wheat (Max Y)", "Decorations.Wheat", "", 0, 0, Integer.MAX_VALUE);
        USE_ARCTIC_SURFACE = new ConfigPropertyBoolean(ConfigProperty.Type.BOOLEAN, "Use Arctic Surface", "Surfaces.Arctic Surface", "", true);
        ALLOW_ICE_TREES = new ConfigPropertyBoolean(ConfigProperty.Type.BOOLEAN, "Allow Ice Trees", "Trees.Ice Trees", "", true);

        FALLEN_LOG_DENSITY_MULTIPLIER = new ConfigPropertyFloat(
            Type.FLOAT,
            "Fallen Log Density Multiplier",
            "Decorations.Logs",
            "This setting allows you to increase/decrease the number of fallen logs that generate in this biome."
                + Configuration.NEW_LINE +
                "1.0 = Default density; 2.0 = Twice as many fallen logs; 0.5 = half as many fallen logs; 0 = No fallen logs",
            1f, 0f, 5.0f
        );

        ALLOW_SPONGE = new ConfigPropertyBoolean(Type.BOOLEAN, "Allow Sponge", "Decorations.Sponge", "", true);
        ALLOW_OCEAN_WAVES = new ConfigPropertyBoolean(Type.BOOLEAN, "Allow Ocean Waves", "Decorations.Waves", "", true);
    }

    public static String formatSlug(String s) {

        s = s.toLowerCase();
        s = s.replaceAll("\\+", "plus");
        s = s.replaceAll("\\W", "");

        return s;
    }

    private static String getPlateauGradientBlockMetasComment()
    {
        String comment =
            "Comma-separated list of meta values for the gradient plateau blocks used in this biome."
                + Configuration.NEW_LINE +
                "-1 = Plateau block; 0-15 = Plateau gradient block"
                + Configuration.NEW_LINE +
                "0 = White; 1 = Orange; 2 = Magenta; 3 = Light Blue; 4 = Yellow; 5 = Lime; 6 = Pink; 7 = Gray"
                + Configuration.NEW_LINE +
                "8 = Light Gray; 9 = Cyan; 10 = Purple; 11 = Blue; 12 = Brown; 13 = Green; 14 = Red; 15 = Black";

        return comment;
    }
}
