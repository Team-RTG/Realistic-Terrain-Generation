package rtg.api.config;

import java.io.File;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import rtg.api.config.property.ConfigPropertyBoolean;
import rtg.api.config.property.ConfigPropertyFloat;
import rtg.api.config.property.ConfigPropertyInteger;
import rtg.api.config.property.ConfigPropertyString;


// TODO: [Clean-up] Split this config implementation from the fields that hold the config values and move this implementation out of the API. The API class should have
//                  fields that hold the config values and not the Property objects. The fields should be synched whenever the Configuration changes.
// TODO: [Clean-up] Convert this config and the Config base class to use native Forge Property objects.
// TODO: [Clean-up] Add properties to a category heirarchy to better organise config settings.
@SuppressWarnings("WeakerAccess")
public class RTGConfig extends Config {

    // Maximum tree density.
    public static final float MAX_TREE_DENSITY = 5f;
    public final ConfigPropertyBoolean ENABLE_DEBUGGING;
    public final ConfigPropertyBoolean DISABLE_RTG_BIOME_DECORATIONS;
    public final ConfigPropertyBoolean DISABLE_RTG_BIOME_SURFACES;
    public final ConfigPropertyBoolean DISABLE_RTG_TERRAIN;
    public final ConfigPropertyBoolean USE_PATCH_BIOME;
    public final ConfigPropertyString PATCH_BIOME;
    public final ConfigPropertyInteger DUNE_HEIGHT; // TODO: [Generator settings] To be removed
    public final ConfigPropertyBoolean ENABLE_FLOWING_LIQUID_MODIFICATIONS; // TODO: [Generator settings] To be removed
    public final ConfigPropertyInteger FLOWING_LAVA_CHANCE; // TODO: [Generator settings] To be removed
    public final ConfigPropertyInteger FLOWING_WATER_CHANCE; // TODO: [Generator settings] To be removed
    public final ConfigPropertyFloat LAKE_SIZE_MULTIPLIER; // This is private because we want a transformed version. // TODO: [Generator settings] To be removed
    public final ConfigPropertyFloat LAKE_FREQUENCY_MULTIPLIER; // TODO: [Generator settings] To be removed
    public final ConfigPropertyFloat LAKE_SHORE_BENDINESS_MULTIPLIER; // TODO: [Generator settings] To be removed
    public final ConfigPropertyInteger SCENIC_LAKE_BIOME_ID; // TODO: [Generator settings] To be removed
    public final ConfigPropertyInteger SCENIC_FROZEN_LAKE_BIOME_ID; // TODO: [Generator settings] To be removed
    public final ConfigPropertyBoolean ENABLE_WATER_SURFACE_LAKES; // TODO: [Generator settings] To be removed
    public final ConfigPropertyInteger WATER_SURFACE_LAKE_CHANCE; // TODO: [Generator settings] To be removed
    public final ConfigPropertyBoolean ENABLE_LAVA_SURFACE_LAKES; // TODO: [Generator settings] To be removed
    public final ConfigPropertyInteger LAVA_SURFACE_LAKE_CHANCE; // TODO: [Generator settings] To be removed
    public final ConfigPropertyBoolean ENABLE_WATER_UNDERGROUND_LAKES; // TODO: [Generator settings] To be removed
    public final ConfigPropertyInteger WATER_UNDERGROUND_LAKE_CHANCE; // TODO: [Generator settings] To be removed
    public final ConfigPropertyBoolean ENABLE_LAVA_UNDERGROUND_LAKES; // TODO: [Generator settings] To be removed
    public final ConfigPropertyInteger LAVA_UNDERGROUND_LAKE_CHANCE; // TODO: [Generator settings] To be removed
    public final ConfigPropertyBoolean GENERATE_ORE_ANDESITE; // TODO: [Generator settings] To be removed
    public final ConfigPropertyBoolean GENERATE_ORE_COAL; // TODO: [Generator settings] To be removed
    public final ConfigPropertyBoolean GENERATE_ORE_DIAMOND; // TODO: [Generator settings] To be removed
    public final ConfigPropertyBoolean GENERATE_ORE_DIORITE; // TODO: [Generator settings] To be removed
    public final ConfigPropertyBoolean GENERATE_ORE_DIRT; // TODO: [Generator settings] To be removed
    public final ConfigPropertyBoolean GENERATE_ORE_EMERALD; // TODO: [Clean-up] Should we really allow configuration for this?
    public final ConfigPropertyBoolean GENERATE_ORE_GOLD; // TODO: [Generator settings] To be removed
    public final ConfigPropertyBoolean GENERATE_ORE_GRANITE; // TODO: [Generator settings] To be removed
    public final ConfigPropertyBoolean GENERATE_ORE_GRAVEL; // TODO: [Generator settings] To be removed
    public final ConfigPropertyBoolean GENERATE_ORE_IRON; // TODO: [Generator settings] To be removed
    public final ConfigPropertyBoolean GENERATE_ORE_LAPIS; // TODO: [Generator settings] To be removed
    public final ConfigPropertyBoolean GENERATE_ORE_REDSTONE; // TODO: [Generator settings] To be removed
    public final ConfigPropertyBoolean GENERATE_ORE_SILVERFISH; // TODO: [Clean-up] Should we really allow configuration for this?
    public final ConfigPropertyBoolean ALLOW_ORE_GEN_EVENT_CANCELLATION; // TODO: [Clean-up] Move to Debug category until ore gen is fixed.
    public final ConfigPropertyFloat RIVER_SIZE_MULTIPLIER; // This is private because we want a transformed version. // TODO: [Generator settings] To be removed
    public final ConfigPropertyFloat RIVER_FREQUENCY_MULTIPLIER; // TODO: [Generator settings] To be removed
    public final ConfigPropertyFloat RIVER_BENDINESS_MULTIPLIER; // TODO: [Generator settings] To be removed
    public final ConfigPropertyFloat RIVER_CUT_OFF_SCALE; // TODO: [Generator settings] To be removed
    public final ConfigPropertyFloat RIVER_CUT_OFF_AMPLITUDE; // TODO: [Generator settings] To be removed
    public final ConfigPropertyBoolean ENABLE_LUSH_RIVER_BANK_DECORATIONS_IN_HOT_BIOMES; // TODO: [Clean-up] Move to 'Mod Integration' category
    public final ConfigPropertyBoolean ENABLE_LUSH_RIVER_BANK_SURFACES_IN_HOT_BIOMES; // TODO: [Clean-up] Move to 'Mod Integration' category
    public final ConfigPropertyBoolean ENABLE_RTG_SAPLINGS; // TODO: [Clean-up] Move to 'Trees and Saplings' category
    public final ConfigPropertyInteger RTG_TREE_CHANCE; // TODO: [Clean-up] Move to 'Trees and Saplings' category
    public final ConfigPropertyBoolean ENABLE_SNOW_LAYERS; // TODO: [Generator settings] To be removed
    public final ConfigPropertyString SHADOW_STONE_BLOCK_ID; // TODO: [Clean-up] Move to 'Feature Block Configuration' -> 'Shadow Blocks' category
    public final ConfigPropertyString SHADOW_DESERT_BLOCK_ID; // TODO: [Clean-up] Move to 'Feature Block Configuration' -> 'Shadow Blocks' category
    public final ConfigPropertyBoolean ALLOW_TREES_TO_GENERATE_ON_SAND; // TODO: [Clean-up] Move to 'Trees and Saplings' category
    public final ConfigPropertyBoolean ALLOW_SHRUBS_TO_GENERATE_BELOW_SURFACE; // TODO: [Clean-up] Move to 'Trees and Saplings' category
    public final ConfigPropertyBoolean ALLOW_BARK_COVERED_LOGS; // TODO: [Clean-up] Move to 'Trees and Saplings' category
    public final ConfigPropertyFloat TREE_DENSITY_MULTIPLIER; // TODO: [Clean-up] Move to 'Trees and Saplings' category
    public final ConfigPropertyString MATERIALS_TREES_CAN_GROW_INTO; // TODO: [Clean-up] Move to 'Trees and Saplings' category
    public final ConfigPropertyString VOLCANO_MAIN_BLOCK; // TODO: [Clean-up] Move to 'Feature Block Configuration' -> 'Volcanos' category
    public final ConfigPropertyString VOLCANO_MIX1_BLOCK; // TODO: [Clean-up] Move to 'Feature Block Configuration' -> 'Volcanos' category
    public final ConfigPropertyString VOLCANO_MIX2_BLOCK; // TODO: [Clean-up] Move to 'Feature Block Configuration' -> 'Volcanos' category
    public final ConfigPropertyString VOLCANO_MIX3_BLOCK; // TODO: [Clean-up] Move to 'Feature Block Configuration' -> 'Volcanos' category
    public final ConfigPropertyBoolean ENABLE_VOLCANOES; // TODO: [Generator settings] To be removed
    public final ConfigPropertyBoolean ENABLE_VOLCANO_ERUPTIONS; // TODO: [Generator settings] To be removed
    public final ConfigPropertyInteger VOLCANO_CHANCE; // TODO: [Generator settings] To be removed
    public final ConfigPropertyBoolean ENABLE_VOLCANO_CONDUITS; // TODO: [Generator settings] To be removed
    public final ConfigPropertyInteger VOLCANO_CONDUIT_DEPTH; // TODO: [Generator settings] To be removed
    public final ConfigPropertyFloat VOLCANO_CALDERA_MULTIPLIER; // TODO: [Generator settings] To be removed
    public final ConfigPropertyInteger SURFACE_BLEED_RADIUS; // TODO: [Generator settings] Possibly make this a gen setting so it is World-specific
    public Property RTG_WORLDTYPE_NOTIFICATION;

    public RTGConfig(File configFile) {
        super(configFile);

        ENABLE_DEBUGGING = new ConfigPropertyBoolean(
            "Enable Debugging",
            "Debug",
            "WARNING: This should only be enabled if you know what you're doing.",
            false
        );
        this.addProperty(ENABLE_DEBUGGING);

        DISABLE_RTG_BIOME_DECORATIONS = new ConfigPropertyBoolean(
            "Disable RTG Biome Decorations",
            "Debug.RTG Features",
            "If TRUE, disables all RTG decorations and uses vanilla decorations instead.",
            false
        );
        this.addProperty(DISABLE_RTG_BIOME_DECORATIONS);

        DISABLE_RTG_BIOME_SURFACES = new ConfigPropertyBoolean(
            "Disable RTG Biome Surfaces",
            "Debug.RTG Features",
            "If TRUE, disables all RTG surfaces and uses vanilla surfaces instead.",
            false
        );
        this.addProperty(DISABLE_RTG_BIOME_SURFACES);

        DISABLE_RTG_TERRAIN = new ConfigPropertyBoolean(
            "Disable RTG Terrain",
            "Debug.RTG Features",
            "If TRUE, disables all realistic terrain generation and uses vanilla terrain instead.",
            false
        );
        this.addProperty(DISABLE_RTG_TERRAIN);

        USE_PATCH_BIOME = new ConfigPropertyBoolean(
            "Use Patch Biome",
            "Debug.Patch Biome",
            "If TRUE, when RTG encounters a biome that is unsupported it will use the patch biome instead, otherwise crash.",
            true
        );
        this.addProperty(USE_PATCH_BIOME);

        PATCH_BIOME = new ConfigPropertyString(
            "Patch Biome",
            "Debug.Patch Biome",
            "If RTG encounters an unsupported biome it will generate this biome instead." + Configuration.NEW_LINE +
                "This uses the standard ResourceLocation format: mod_id:biome_registry_name",
            "minecraft:plains"
        );
        this.addProperty(PATCH_BIOME);

        DUNE_HEIGHT = new ConfigPropertyInteger(
            "Height of Dunes",
            "Dunes",
            "This setting controls the height of both sand dunes and snow dunes."
                + Configuration.NEW_LINE + "Higher values = taller dunes.",
            4, 1, 12
        );
        this.addProperty(DUNE_HEIGHT);

        ENABLE_FLOWING_LIQUID_MODIFICATIONS = new ConfigPropertyBoolean(
            "Enable Flowing Liquid Modifications",
            "Flowing Liquids",
            "Must be set to TRUE for the other flowing liquid settings to have any effect."
                + Configuration.NEW_LINE +
                "If FALSE, RTG won't interfere with flowing liquid generation at all."
                + Configuration.NEW_LINE +
                "(Flowing liquids are the water/lava streams that generate on the sides of hills and mountains.)",
            true
        );
        this.addProperty(ENABLE_FLOWING_LIQUID_MODIFICATIONS);

        FLOWING_LAVA_CHANCE = new ConfigPropertyInteger(
            "Flowing Lava Chance",
            "Flowing Liquids",
            "1/x chance that a lava stream will generate on the side of a hill or mountain."
                + Configuration.NEW_LINE +
                "0 = Never generate; 1 = Always generate if possible; 2 = 50% chance; 4 = 25% chance",
            200, 0, Integer.MAX_VALUE
        );
        this.addProperty(FLOWING_LAVA_CHANCE);

        FLOWING_WATER_CHANCE = new ConfigPropertyInteger(
            "Flowing Water Chance",
            "Flowing Liquids",
            "1/x chance that a water stream will generate on the side of a hill or mountain."
                + Configuration.NEW_LINE +
                "0 = Never generate; 1 = Always generate if possible; 2 = 50% chance; 4 = 25% chance",
            200, 0, Integer.MAX_VALUE
        );
        this.addProperty(FLOWING_WATER_CHANCE);

        LAKE_SIZE_MULTIPLIER = new ConfigPropertyFloat(
            "Lake Size Multiplier",
            "Lakes (Scenic)",
            "Defaults to 1 (standard size)",
            1, 0, 10
        );
        this.addProperty(LAKE_SIZE_MULTIPLIER);

        LAKE_FREQUENCY_MULTIPLIER = new ConfigPropertyFloat(
            "Lake Frequency Multiplier",
            "Lakes (Scenic)",
            "Defaults to 1 (standard frequency)",
            1, 0, 10
        );
        this.addProperty(LAKE_FREQUENCY_MULTIPLIER);

        LAKE_SHORE_BENDINESS_MULTIPLIER = new ConfigPropertyFloat(
            "Lake Shore Irregularity",
            "Lakes (Scenic)",
            "Makes scenic lake shores bend and curve more. Defaults to 1",
            1, 0, 2
        );
        this.addProperty(LAKE_SHORE_BENDINESS_MULTIPLIER);

        SCENIC_LAKE_BIOME_ID = new ConfigPropertyInteger(
            "Biome for scenic lakes",
            "Lakes (Scenic)",
            "Biome ID for scenic lakes when not frozen (default 7 = River)",
            7, 0, 254
        );
        this.addProperty(SCENIC_LAKE_BIOME_ID);

        SCENIC_FROZEN_LAKE_BIOME_ID = new ConfigPropertyInteger(
            "Biome for frozen scenic lakes",
            "Lakes (Scenic)",
            "Biome ID for scenic lakes when frozen (default 11 = Frozen River)",
            11, 0, 254
        );
        this.addProperty(SCENIC_FROZEN_LAKE_BIOME_ID);

        ENABLE_WATER_SURFACE_LAKES = new ConfigPropertyBoolean(
            "Enable Water Surface Lakes",
            "Lakes (Surface)",
            "",
            true
        );
        this.addProperty(ENABLE_WATER_SURFACE_LAKES);

        WATER_SURFACE_LAKE_CHANCE = new ConfigPropertyInteger(
            "1/x chance that Water Surface Lakes will generate if given the opportunity to do so during world gen",
            "Lakes (Surface)",
            "1 = Always generate if possible; 2 = 50% chance; 4 = 25% chance",
            10, 1, 100
        );
        this.addProperty(WATER_SURFACE_LAKE_CHANCE);

        ENABLE_LAVA_SURFACE_LAKES = new ConfigPropertyBoolean(
            "Enable Lava Surface Lakes",
            "Lakes (Surface)",
            "",
            true
        );
        this.addProperty(ENABLE_LAVA_SURFACE_LAKES);

        LAVA_SURFACE_LAKE_CHANCE = new ConfigPropertyInteger(
            "1/x chance that Lava Surface Lakes will generate if given the opportunity to do so during world gen",
            "Lakes (Surface)",
            "1 = Always generate if possible; 2 = 50% chance; 4 = 25% chance",
            10, 1, 100
        );
        this.addProperty(LAVA_SURFACE_LAKE_CHANCE);

        ENABLE_WATER_UNDERGROUND_LAKES = new ConfigPropertyBoolean(
            "Enable Water Underground Lakes",
            "Lakes (Underground)",
            "",
            true
        );
        this.addProperty(ENABLE_WATER_UNDERGROUND_LAKES);

        WATER_UNDERGROUND_LAKE_CHANCE = new ConfigPropertyInteger(
            "1/x chance that Water Underground Lakes will generate if given the opportunity to do so during world gen",
            "Lakes (Underground)",
            "1 = Always generate if possible; 2 = 50% chance; 4 = 25% chance",
            10, 1, 100
        );
        this.addProperty(WATER_UNDERGROUND_LAKE_CHANCE);

        ENABLE_LAVA_UNDERGROUND_LAKES = new ConfigPropertyBoolean(
            "Enable Lava Underground Lakes",
            "Lakes (Underground)",
            "",
            true
        );
        this.addProperty(ENABLE_LAVA_UNDERGROUND_LAKES);

        LAVA_UNDERGROUND_LAKE_CHANCE = new ConfigPropertyInteger(
            "1/x chance that Lava Underground Lakes will generate if given the opportunity to do so during world gen",
            "Lakes (Underground)",
            "1 = Always generate if possible; 2 = 50% chance; 4 = 25% chance",
            10, 1, 100
        );
        this.addProperty(LAVA_UNDERGROUND_LAKE_CHANCE);

        GENERATE_ORE_ANDESITE = new ConfigPropertyBoolean("Generate Andesite Ore", "Ore Gen", "", true);
        this.addProperty(GENERATE_ORE_ANDESITE);

        GENERATE_ORE_COAL = new ConfigPropertyBoolean("Generate Coal Ore", "Ore Gen", "", true);
        this.addProperty(GENERATE_ORE_COAL);

        GENERATE_ORE_DIAMOND = new ConfigPropertyBoolean("Generate Diamond Ore", "Ore Gen", "", true);
        this.addProperty(GENERATE_ORE_DIAMOND);

        GENERATE_ORE_DIORITE = new ConfigPropertyBoolean("Generate Diorite Ore", "Ore Gen", "", true);
        this.addProperty(GENERATE_ORE_DIORITE);

        GENERATE_ORE_DIRT = new ConfigPropertyBoolean("Generate Dirt Ore", "Ore Gen", "", true);
        this.addProperty(GENERATE_ORE_DIRT);

        GENERATE_ORE_EMERALD = new ConfigPropertyBoolean("Generate Emerald Ore", "Ore Gen", "", true);
        this.addProperty(GENERATE_ORE_EMERALD);

        GENERATE_ORE_GOLD = new ConfigPropertyBoolean("Generate Gold Ore", "Ore Gen", "", true);
        this.addProperty(GENERATE_ORE_GOLD);

        GENERATE_ORE_GRANITE = new ConfigPropertyBoolean("Generate Granite Ore", "Ore Gen", "", true);
        this.addProperty(GENERATE_ORE_GRANITE);

        GENERATE_ORE_GRAVEL = new ConfigPropertyBoolean("Generate Gravel Ore", "Ore Gen", "", true);
        this.addProperty(GENERATE_ORE_GRAVEL);

        GENERATE_ORE_IRON = new ConfigPropertyBoolean("Generate Iron Ore", "Ore Gen", "", true);
        this.addProperty(GENERATE_ORE_IRON);

        GENERATE_ORE_LAPIS = new ConfigPropertyBoolean("Generate Lapis Ore", "Ore Gen", "", true);
        this.addProperty(GENERATE_ORE_LAPIS);

        GENERATE_ORE_REDSTONE = new ConfigPropertyBoolean("Generate Redstone Ore", "Ore Gen", "", true);
        this.addProperty(GENERATE_ORE_REDSTONE);

        GENERATE_ORE_SILVERFISH = new ConfigPropertyBoolean("Generate Silverfish Ore", "Ore Gen", "", true);
        this.addProperty(GENERATE_ORE_SILVERFISH);

        ALLOW_ORE_GEN_EVENT_CANCELLATION = new ConfigPropertyBoolean(
            "Allow ore gen event cancellation",
            "Ore Gen",
            "Some mods might not be compatible with the way RTG handles ore generation." +
                Configuration.NEW_LINE +
                "If you're using one of those mods, you might need to set this to false." +
                Configuration.NEW_LINE +
                "You should only change this if you're having problems with ore gen and you know what you're doing.",
            true
        );
        this.addProperty(ALLOW_ORE_GEN_EVENT_CANCELLATION);

        RIVER_SIZE_MULTIPLIER = new ConfigPropertyFloat(
            "River Width Multiplier",
            "Rivers",
            "Defaults to 1 (standard width)",
            1, 0, 10
        );
        this.addProperty(RIVER_SIZE_MULTIPLIER);

        RIVER_FREQUENCY_MULTIPLIER = new ConfigPropertyFloat(
            "River Frequency Multiplier",
            "Rivers",
            "Multiplier to river frequencies. Defaults to 1",
            1, 0, 10
        );
        this.addProperty(RIVER_FREQUENCY_MULTIPLIER);

        RIVER_BENDINESS_MULTIPLIER = new ConfigPropertyFloat(
            "Multiplier to River Bending",
            "Rivers",
            "Higher numbers make rivers bend more. Defaults to 1",
            1, 0, 2
        );
        this.addProperty(RIVER_BENDINESS_MULTIPLIER);

        RIVER_CUT_OFF_SCALE = new ConfigPropertyFloat(
            "Scale of Large-Scale River Cut Off",
            "Rivers",
            "Higher numbers make grassy areas near rivers bigger, but also more rare. Defaults to 350",
            350, 50, 5000
        );
        this.addProperty(RIVER_CUT_OFF_SCALE);

        RIVER_CUT_OFF_AMPLITUDE = new ConfigPropertyFloat(
            "Amplitude of Large-Scale River Cut Off",
            "Rivers",
            "Higher numbers make the large-scale cut-off noise have a greater effect. Defaults to 0.5",
            0.5f, 0, 2
        );
        this.addProperty(RIVER_CUT_OFF_AMPLITUDE);

        ENABLE_LUSH_RIVER_BANK_DECORATIONS_IN_HOT_BIOMES = new ConfigPropertyBoolean(
            "Enable Lush River Bank Decorations in Hot Biomes",
            "Rivers",
            "Set this to FALSE to prevent RTG from generating lush river bank decorations in hot biomes, like Desert and Mesa."
                + Configuration.NEW_LINE +
                "Lush decorations consist of tallgrass, trees, shrubs, and other flora.",
            true
        );
        this.addProperty(ENABLE_LUSH_RIVER_BANK_DECORATIONS_IN_HOT_BIOMES);

        ENABLE_LUSH_RIVER_BANK_SURFACES_IN_HOT_BIOMES = new ConfigPropertyBoolean(
            "Enable Lush River Bank Surfaces in Hot Biomes",
            "Rivers",
            "Set this to FALSE to prevent RTG from generating lush river bank surfaces in hot biomes, like Desert and Mesa."
                + Configuration.NEW_LINE +
                "Lush surfaces consist (almost exclusively) of grass blocks.",
            true
        );
        this.addProperty(ENABLE_LUSH_RIVER_BANK_SURFACES_IN_HOT_BIOMES);

        ENABLE_RTG_SAPLINGS = new ConfigPropertyBoolean(
            "Enable RTG Saplings",
            "Saplings",
            "Set this to TRUE to allow RTG's custom trees to grow from vanilla saplings."
                + Configuration.NEW_LINE +
                "RTG's custom trees can be grown only from the saplings that their leaves would drop naturally, and only in the biomes where they naturally generate."
                + Configuration.NEW_LINE +
                "For example, you can only grow a Swamp Willow in a Swamp biome, and only with an Oak sapling (because Swamp Willows have Oak leaves).",
            true
        );
        this.addProperty(ENABLE_RTG_SAPLINGS);

        RTG_TREE_CHANCE = new ConfigPropertyInteger(
            "RTG Tree from Vanilla Sapling Chance",
            "Saplings",
            "1/x chance that a vanilla sapling will grow one of RTG's custom trees."
                + Configuration.NEW_LINE +
                "1 = Always generate if possible; 2 = 50% chance; 4 = 25% chance",
            2, 1, Integer.MAX_VALUE
        );
        this.addProperty(RTG_TREE_CHANCE);

        ENABLE_SNOW_LAYERS = new ConfigPropertyBoolean(
            "Enable Snow Layers",
            "Snow",
            "This applies to newly-generated chunks only. Snow layers will still appear in cold/snowy biomes after it snows.",
            true
        );
        this.addProperty(ENABLE_SNOW_LAYERS);

        SHADOW_STONE_BLOCK_ID = new ConfigPropertyString(
            "Stone shadow block ID",
            "Terrain shadowing",
            "The block to use for stone terrain shadowing, typically seen on the cliffs of stone mountains. Defaults to stained hardened clay.",
            "minecraft:stained_hardened_clay[color=cyan]"
        );
        this.addProperty(SHADOW_STONE_BLOCK_ID);

        SHADOW_DESERT_BLOCK_ID = new ConfigPropertyString(
            "Desert shadow block ID",
            "Terrain shadowing",
            "The block to use for desert terrain shadowing, typically seen on the cliffs of desert mountains. Defaults to stained hardened clay.",
            "minecraft:stained_hardened_clay[color=white]"
        );
        this.addProperty(SHADOW_DESERT_BLOCK_ID);

        ALLOW_TREES_TO_GENERATE_ON_SAND = new ConfigPropertyBoolean(
            "Allow Trees to Generate on Sand",
            "Trees",
            "Set this to FALSE to prevent trees from generating on sand."
                + Configuration.NEW_LINE +
                "This setting only affects trees generated by RTG. Trees generated by a biome's decorator"
                + Configuration.NEW_LINE +
                "will adhere to their own generation rules. (RTG's Palm Trees ignore this setting.)",
            false
        );
        this.addProperty(ALLOW_TREES_TO_GENERATE_ON_SAND);

        ALLOW_SHRUBS_TO_GENERATE_BELOW_SURFACE = new ConfigPropertyBoolean(
            "Allow Shrubs to Generate Below Surface",
            "Trees",
            "Set this to FALSE to prevent shrub trunks from generating below the surface.",
            true
        );
        this.addProperty(ALLOW_SHRUBS_TO_GENERATE_BELOW_SURFACE);

        ALLOW_BARK_COVERED_LOGS = new ConfigPropertyBoolean(
            "Allow bark-covered logs",
            "Trees",
            "Set this to FALSE to prevent the trunks of RTG trees from using the 'all-bark' texture model."
                + Configuration.NEW_LINE +
                "For more information, visit http://minecraft.gamepedia.com/Wood#Block_data",
            true
        );
        this.addProperty(ALLOW_BARK_COVERED_LOGS);

        TREE_DENSITY_MULTIPLIER = new ConfigPropertyFloat(
            "RTG Tree Density Multiplier",
            "Trees",
            "This setting allows you to increase/decrease the number of RTG trees that generate."
                + Configuration.NEW_LINE +
                "This setting only affects trees generated by RTG. Trees generated by a biome's decorator"
                + Configuration.NEW_LINE +
                "will adhere to their own density rules."
                + Configuration.NEW_LINE +
                "You can override this setting on a per-biome basis by using the biome configs."
                + Configuration.NEW_LINE +
                "1.0 = Default tree generation; 2.0 = Twice as many trees; 0.5 = half as many trees",
            1, 0, MAX_TREE_DENSITY
        );
        this.addProperty(TREE_DENSITY_MULTIPLIER);

        MATERIALS_TREES_CAN_GROW_INTO = new ConfigPropertyString(
            "Materials That Trees Can Grow Into",
            "Trees",
            "Comma-separated list of materials that trees can grow into (replace) when generating."
                + Configuration.NEW_LINE +
                "Valid values include the following:"
                + Configuration.NEW_LINE +
                "AIR,ANVIL,BARRIER,CACTUS,CAKE,CARPET,CIRCUITS,CLAY,CLOTH,CORAL,CRAFTED_SNOW,DRAGON_EGG,FIRE,GLASS,GOURD,GRASS,GROUND,ICE," +
                "IRON,LAVA,LEAVES,PACKED_ICE,PISTON,PLANTS,PORTAL,REDSTONE_LIGHT,ROCK,SAND,SNOW,SPONGE,STRUCTURE_VOID,TNT,VINE,WATER,WEB,WOOD"
                + Configuration.NEW_LINE +
                "For more information, visit http://minecraft.gamepedia.com/Materials",
            "AIR,WOOD,LEAVES,GRASS,GROUND,PLANTS,VINE,WATER,SNOW"
        );
        this.addProperty(MATERIALS_TREES_CAN_GROW_INTO);

        VOLCANO_MAIN_BLOCK = new ConfigPropertyString(
            "Volcano Main block",
            "Volcanoes.Volcano Blocks",
            "The main block to use for the surface of the volcano." + BLOCKSTATE_HELP,
            "minecraft:obsidian"
        );
        this.addProperty(VOLCANO_MAIN_BLOCK);

        VOLCANO_MIX1_BLOCK = new ConfigPropertyString(
            "Volcano mix block 1",
            "Volcanoes.Volcano Blocks",
            "The 1st volcano mix block." + BLOCKSTATE_HELP,
            "minecraft:cobblestone"
        );
        this.addProperty(VOLCANO_MIX1_BLOCK);

        VOLCANO_MIX2_BLOCK = new ConfigPropertyString(
            "Volcano mix block 2",
            "Volcanoes.Volcano Blocks",
            "The 2nd volcano mix block." + BLOCKSTATE_HELP,
            "minecraft:gravel"
        );
        this.addProperty(VOLCANO_MIX2_BLOCK);

        VOLCANO_MIX3_BLOCK = new ConfigPropertyString(
            "Volcano mix block 3",
            "Volcanoes.Volcano Blocks",
            "The 3rd volcano mix block." + BLOCKSTATE_HELP,
            "minecraft:coal_block"
        );
        this.addProperty(VOLCANO_MIX3_BLOCK);

        ENABLE_VOLCANOES = new ConfigPropertyBoolean(
            "Enable volcanoes",
            "Volcanoes",
            "Set this to FALSE to prevent volcanoes from generating.",
            true
        );
        this.addProperty(ENABLE_VOLCANOES);

        ENABLE_VOLCANO_ERUPTIONS = new ConfigPropertyBoolean(
            "Enable volcano eruptions",
            "Volcanoes",
            "Set this to FALSE to prevent lava from flowing down the sides of volcanoes.",
            true
        );
        this.addProperty(ENABLE_VOLCANO_ERUPTIONS);

        VOLCANO_CHANCE = new ConfigPropertyInteger(
            "Volcano Chance",
            "Volcanoes",
            "1/x chance that a volcano will generate in a biome that has volcanoes enabled."
                + Configuration.NEW_LINE +
                "1 = Always generate if possible; 2 = 50% chance; 4 = 25% chance",
            48, 1, Integer.MAX_VALUE
        );
        this.addProperty(VOLCANO_CHANCE);

        ENABLE_VOLCANO_CONDUITS = new ConfigPropertyBoolean(
            "Enable volcano conduits",
            "Volcanoes.Volcano Conduits",
            "Set this to FALSE to prevent volcanoes from generating conduits (lava tubes) below their throats.",
            true
        );
        this.addProperty(ENABLE_VOLCANO_CONDUITS);

        VOLCANO_CONDUIT_DEPTH = new ConfigPropertyInteger(
            "Volcano Conduit Depth",
            "Volcanoes.Volcano Conduits",
            "The lowest Y value that conduits should reach."
                + Configuration.NEW_LINE +
                "Please note that even though conduits can reach to Y=1, they will not replace bedrock.",
            0, 0, 120
        );
        this.addProperty(VOLCANO_CONDUIT_DEPTH);

        VOLCANO_CALDERA_MULTIPLIER = new ConfigPropertyFloat(
            "Volcano Caldera Multiplier",
            "Volcanoes",
            "This setting allows you to modify the radius of volcano calderas.",
            1, 1, 3
        );
        this.addProperty(VOLCANO_CALDERA_MULTIPLIER);

        SURFACE_BLEED_RADIUS = this.addProperty(new ConfigPropertyInteger(
            "Surface Bleed Radius",
            "Surface Bleed",
            "The maximum distance surfaces will bleed. Set to 0 to disable surface bleeds." +
                Configuration.NEW_LINE +
                "Per default surface bleeding is only enabled for beaches. You can control that in biome settings",
            16, 0, 32
        ));
    }

    // TODO: [1.12] Stop-gap until the main config class is rewritten. It is currently impossible to save config setting changes after the config is loaded.
    @Override
    public void loadConfig() {

        super.loadConfig();

        RTG_WORLDTYPE_NOTIFICATION = this.getConfig().get(
            "Client",
            "RTG WorldType Notification",
            true,
            "When enabled, this will display an informational message about RTG when entering the Customize World screen." + Configuration.NEW_LINE +
                "This will display once and automatically disable itself."
        );

        if (this.getConfig().hasChanged()) {
            this.getConfig().save();
        }
    }
}
