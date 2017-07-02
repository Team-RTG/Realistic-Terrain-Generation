package rtg.api.config;

import java.util.ArrayList;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;

import net.minecraftforge.common.config.Configuration;

import org.apache.commons.lang3.ArrayUtils;

import rtg.api.config.property.*;
import rtg.api.util.MaterialUtil;

public class RTGConfig extends Config {

    // Maximum tree density.
    public static final float MAX_TREE_DENSITY = 5f;

    // These constants are used as fallbacks during terrain shadowing, in case the user enters an invalid block ID.
    public static final String DEFAULT_SHADOW_STONE_BLOCK_ID = "minecraft:stained_hardened_clay";
    public static final int DEFAULT_SHADOW_STONE_BLOCK_META = 9;
    public static final String DEFAULT_SHADOW_DESERT_BLOCK_ID = "minecraft:stained_hardened_clay";
    public static final int DEFAULT_SHADOW_DESERT_BLOCK_META = 0;

    // These constants are used as fallbacks when generating volcanoes, in case the user enters an invalid block ID.
    public static final IBlockState DEFAULT_VOLCANO_BLOCK = Blocks.OBSIDIAN.getDefaultState();
    public static final IBlockState DEFAULT_VOLCANO_MIX1_BLOCK = Blocks.COBBLESTONE.getDefaultState();
    public static final IBlockState DEFAULT_VOLCANO_MIX2_BLOCK = Blocks.GRAVEL.getDefaultState();
    public static final IBlockState DEFAULT_VOLCANO_MIX3_BLOCK = Blocks.COAL_BLOCK.getDefaultState();

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Bedrock
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public final ConfigPropertyInt FLAT_BEDROCK_LAYERS;
    public final ConfigPropertyString BEDROCK_BLOCK_ID;
    public final ConfigPropertyInt BEDROCK_BLOCK_BYTE;

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Biomes
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public final ConfigPropertyBoolean ENABLE_RTG_BIOME_DECORATIONS;
    public final ConfigPropertyBoolean ENABLE_RTG_BIOME_SURFACES;
    public final ConfigPropertyBoolean ENABLE_RTG_TERRAIN;
    public final ConfigPropertyInt PATCH_BIOME_ID;

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Boulders
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public final ConfigPropertyBoolean ENABLE_COBBLESTONE_BOULDERS;
    public final ConfigPropertyInt COBBLESTONE_BOULDER_CHANCE;
    public final ConfigPropertyBoolean ENABLE_UBC_BOULDERS;

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Caves
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public final ConfigPropertyBoolean ENABLE_CAVE_MODIFICATIONS;
    public final ConfigPropertyBoolean ENABLE_CAVES;
    public final ConfigPropertyInt CAVE_DENSITY;
    public final ConfigPropertyInt CAVE_FREQUENCY;

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Debugging
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public final ConfigPropertyBoolean ENABLE_DEBUGGING;
    public final ConfigPropertyBoolean CRASH_ON_STRUCTURE_EXCEPTIONS;

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Dunes
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public final ConfigPropertyInt DUNE_HEIGHT;

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Dungeons
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public final ConfigPropertyBoolean GENERATE_DUNGEONS;
    public final ConfigPropertyInt DUNGEON_FREQUENCY;

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Flowing liquids
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public final ConfigPropertyBoolean ENABLE_FLOWING_LIQUID_MODIFICATIONS;
    public final ConfigPropertyInt FLOWING_LAVA_CHANCE;
    public final ConfigPropertyInt FLOWING_WATER_CHANCE;

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // GUI
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public final ConfigPropertyBoolean ENABLE_WORLD_TYPE_NOTIFICATION_SCREEN;

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Lakes (Scenic)
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    private final ConfigPropertyFloat LAKE_SIZE_MULTIPLIER; // This is private because we want a transformed version.
    public final ConfigPropertyFloat LAKE_FREQUENCY_MULTIPLIER;
    public final ConfigPropertyFloat LAKE_SHORE_BENDINESS_MULTIPLIER;
    public final ConfigPropertyInt SCENIC_LAKE_BIOME_ID;
    public final ConfigPropertyInt SCENIC_FROZEN_LAKE_BIOME_ID;

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Lakes (Surface)
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public final ConfigPropertyBoolean ENABLE_WATER_SURFACE_LAKES;
    public final ConfigPropertyInt WATER_SURFACE_LAKE_CHANCE;
    public final ConfigPropertyBoolean ENABLE_LAVA_SURFACE_LAKES;
    public final ConfigPropertyInt LAVA_SURFACE_LAKE_CHANCE;

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Lakes (Underground)
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public final ConfigPropertyBoolean ENABLE_WATER_UNDERGROUND_LAKES;
    public final ConfigPropertyInt WATER_UNDERGROUND_LAKE_CHANCE;
    public final ConfigPropertyBoolean ENABLE_LAVA_UNDERGROUND_LAKES;
    public final ConfigPropertyInt LAVA_UNDERGROUND_LAKE_CHANCE;

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Mineshafts
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public final ConfigPropertyBoolean GENERATE_MINESHAFTS;

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Ocean monuments
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public final ConfigPropertyBoolean ENABLE_OCEAN_MONUMENT_MODIFICATIONS;
    public final ConfigPropertyBoolean GENERATE_OCEAN_MONUMENTS;
    public final ConfigPropertyInt OCEAN_MONUMENT_SPACING;
    public final ConfigPropertyInt OCEAN_MONUMENT_SEPARATION;

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Ore gen
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public final ConfigPropertyBoolean GENERATE_ORE_ANDESITE;
    public final ConfigPropertyBoolean GENERATE_ORE_COAL;
    public final ConfigPropertyBoolean GENERATE_ORE_DIAMOND;
    public final ConfigPropertyBoolean GENERATE_ORE_DIORITE;
    public final ConfigPropertyBoolean GENERATE_ORE_DIRT;
    public final ConfigPropertyBoolean GENERATE_ORE_EMERALD;
    public final ConfigPropertyBoolean GENERATE_ORE_GOLD;
    public final ConfigPropertyBoolean GENERATE_ORE_GRANITE;
    public final ConfigPropertyBoolean GENERATE_ORE_GRAVEL;
    public final ConfigPropertyBoolean GENERATE_ORE_IRON;
    public final ConfigPropertyBoolean GENERATE_ORE_LAPIS;
    public final ConfigPropertyBoolean GENERATE_ORE_REDSTONE;
    public final ConfigPropertyBoolean GENERATE_ORE_SILVERFISH;

    public final ConfigPropertyBoolean ALLOW_ORE_GEN_EVENT_CANCELLATION;

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Ravines
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public final ConfigPropertyBoolean ENABLE_RAVINE_MODIFICATIONS;
    public final ConfigPropertyBoolean ENABLE_RAVINES;
    public final ConfigPropertyInt RAVINE_FREQUENCY;

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Rivers
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    private final ConfigPropertyFloat RIVER_SIZE_MULTIPLIER; // This is private because we want a transformed version.
    public final ConfigPropertyFloat RIVER_FREQUENCY_MULTIPLIER;
    public final ConfigPropertyFloat RIVER_BENDINESS_MULTIPLIER;
    public final ConfigPropertyFloat RIVER_CUT_OFF_SCALE;
    public final ConfigPropertyFloat RIVER_CUT_OFF_AMPLITUDE;
    public final ConfigPropertyBoolean ENABLE_LUSH_RIVER_BANK_DECORATIONS_IN_HOT_BIOMES;
    public final ConfigPropertyBoolean ENABLE_LUSH_RIVER_BANK_SURFACES_IN_HOT_BIOMES;

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Saplings
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public final ConfigPropertyBoolean ENABLE_RTG_SAPLINGS;
    public final ConfigPropertyInt RTG_TREE_CHANCE;

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Scattered features
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public final ConfigPropertyBoolean ENABLE_SCATTERED_FEATURE_MODIFICATIONS;
    public final ConfigPropertyBoolean GENERATE_SCATTERED_FEATURES;
    public final ConfigPropertyInt MIN_DISTANCE_SCATTERED_FEATURES;
    public final ConfigPropertyInt MAX_DISTANCE_SCATTERED_FEATURES;

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Snow
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public final ConfigPropertyBoolean ENABLE_SNOW_LAYERS;

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Strongholds
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public final ConfigPropertyBoolean ENABLE_STRONGHOLD_MODIFICATIONS;
    public final ConfigPropertyBoolean GENERATE_STRONGHOLDS;
    public final ConfigPropertyInt STRONGHOLD_COUNT;
    public final ConfigPropertyInt STRONGHOLD_DISTANCE;
    public final ConfigPropertyInt STRONGHOLD_SPREAD;

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Terrain shadowing
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public final ConfigPropertyString SHADOW_STONE_BLOCK_ID;
    public final ConfigPropertyInt SHADOW_STONE_BLOCK_META;
    public final ConfigPropertyString SHADOW_DESERT_BLOCK_ID;
    public final ConfigPropertyInt SHADOW_DESERT_BLOCK_META;
    public final ConfigPropertyBoolean ENABLE_UBC_STONE_SHADOWING;
    public final ConfigPropertyBoolean ENABLE_UBC_DESERT_SHADOWING;

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Trees
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public final ConfigPropertyBoolean ALLOW_TREES_TO_GENERATE_ON_SAND;
    public final ConfigPropertyBoolean ALLOW_SHRUBS_TO_GENERATE_BELOW_SURFACE;
    public final ConfigPropertyBoolean ALLOW_BARK_COVERED_LOGS;
    public final ConfigPropertyFloat TREE_DENSITY_MULTIPLIER;
    public final ConfigPropertyString MATERIALS_TREES_CAN_GROW_INTO;

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Villages
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public final ConfigPropertyBoolean ENABLE_VILLAGE_MODIFICATIONS;
    public final ConfigPropertyBoolean GENERATE_VILLAGES;
    public final ConfigPropertyInt VILLAGE_SIZE;
    public final ConfigPropertyInt MIN_DISTANCE_VILLAGES;
    public final ConfigPropertyInt MAX_DISTANCE_VILLAGES;

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Volcanoes
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public final ConfigPropertyString VOLCANO_BLOCK_ID;
    public final ConfigPropertyInt VOLCANO_BLOCK_META;
    public final ConfigPropertyString VOLCANO_MIX1_BLOCK_ID;
    public final ConfigPropertyInt VOLCANO_MIX1_BLOCK_META;
    public final ConfigPropertyString VOLCANO_MIX2_BLOCK_ID;
    public final ConfigPropertyInt VOLCANO_MIX2_BLOCK_META;
    public final ConfigPropertyString VOLCANO_MIX3_BLOCK_ID;
    public final ConfigPropertyInt VOLCANO_MIX3_BLOCK_META;
    public final ConfigPropertyBoolean ENABLE_VOLCANOES;
    public final ConfigPropertyBoolean ENABLE_VOLCANO_ERUPTIONS;
    public final ConfigPropertyInt VOLCANO_CHANCE;
    public final ConfigPropertyBoolean ENABLE_VOLCANO_CONDUITS;
    public final ConfigPropertyInt VOLCANO_CONDUIT_DEPTH;
    public final ConfigPropertyFloat VOLCANO_CALDERA_MULTIPLIER;

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Surface Bleeding
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public final ConfigPropertyInt SURFACE_BLEED_RADIUS;

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public RTGConfig() {

        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        // Bedrock
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        FLAT_BEDROCK_LAYERS = new ConfigPropertyInt(
            ConfigProperty.Type.INTEGER,
            "Number of flat bedrock layers",
            "Bedrock",
            "0 = Normal bedrock (rough pattern); 1-5 = Number of flat bedrock layers to generate",
            0, 0, 5
        );
        this.addProperty(FLAT_BEDROCK_LAYERS);

        BEDROCK_BLOCK_ID = new ConfigPropertyString(
            ConfigProperty.Type.STRING,
            "Bedrock block ID",
            "Bedrock",
            "The block to use for the bottom of the Overworld.",
            "minecraft:bedrock"
        );
        this.addProperty(BEDROCK_BLOCK_ID);

        BEDROCK_BLOCK_BYTE = new ConfigPropertyInt(
            ConfigProperty.Type.INTEGER,
            "Bedrock block meta value",
            "Bedrock",
            "The meta value of the bedrock block.",
            0, 0, 15
        );
        this.addProperty(BEDROCK_BLOCK_BYTE);

        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        // Biomes
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        ENABLE_RTG_BIOME_DECORATIONS = new ConfigPropertyBoolean(
            ConfigProperty.Type.BOOLEAN,
            "Enable RTG Biome Decorations",
            "Biomes",
            "If TRUE, uses the individual biome settings in the biome config files. If FALSE, disables all RTG decorations and uses vanilla decorations instead.",
            true
        );
        this.addProperty(ENABLE_RTG_BIOME_DECORATIONS);

        ENABLE_RTG_BIOME_SURFACES = new ConfigPropertyBoolean(
            ConfigProperty.Type.BOOLEAN,
            "Enable RTG Biome Surfaces",
            "Biomes",
            "If TRUE, uses the individual biome settings in the biome config files. If FALSE, disables all RTG surfaces and uses vanilla surfaces instead.",
            true
        );
        this.addProperty(ENABLE_RTG_BIOME_SURFACES);

        ENABLE_RTG_TERRAIN = new ConfigPropertyBoolean(
            ConfigProperty.Type.BOOLEAN,
            "Enable RTG Terrain",
            "Biomes",
            "If TRUE, uses the individual biome settings in the biome config files. If FALSE, disables all realistic terrain generation and uses vanilla terrain instead.",
            true
        );
        this.addProperty(ENABLE_RTG_TERRAIN);

        PATCH_BIOME_ID = new ConfigPropertyInt(
            ConfigProperty.Type.INTEGER,
            "Patch Biome ID",
            "Biomes",
            "If RTG tries to generate an unsupported biome or a biome that has an ID conflict, it will generate this biome instead."
                + Configuration.NEW_LINE +
                "If set to -1, RTG will crash instead of generating the patch biome. You might want to do this if you're making a mod pack"
                + Configuration.NEW_LINE +
                "and want to make sure all biomes are generating correctly."
                + Configuration.NEW_LINE +
                "Default = Vanilla Plains",
            1, -1, 255
        );
        this.addProperty(PATCH_BIOME_ID);

        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        // Boulders
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        ENABLE_COBBLESTONE_BOULDERS = new ConfigPropertyBoolean(
            ConfigProperty.Type.BOOLEAN, "Enable Cobblestone Boulders", "Boulders", "", true
        );
        this.addProperty(ENABLE_COBBLESTONE_BOULDERS);

        COBBLESTONE_BOULDER_CHANCE = new ConfigPropertyInt(
            ConfigProperty.Type.INTEGER,
            "1/x chance that Cobblestone Boulders will generate if given the opportunity to do so during world gen",
            "Boulders",
            "1 = Always generate if possible; 2 = 50% chance; 4 = 25% chance",
            1, 1, 100
        );
        this.addProperty(COBBLESTONE_BOULDER_CHANCE);

        ENABLE_UBC_BOULDERS = new ConfigPropertyBoolean(
            ConfigProperty.Type.BOOLEAN,
            "Enable UBC Boulders",
            "Boulders",
            "Set this to TRUE to allow UBC to override cobblestone boulders."
                + Configuration.NEW_LINE +
                "This setting doesn't have any effect if UBC is not installed.",
            true
        );
        this.addProperty(ENABLE_UBC_BOULDERS);

        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        // Caves
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        ENABLE_CAVE_MODIFICATIONS = new ConfigPropertyBoolean(
            ConfigProperty.Type.BOOLEAN,
            "Enable Cave Modifications",
            "Caves",
            "Must be set to TRUE for the other cave settings to have any effect."
                + Configuration.NEW_LINE +
                "If FALSE, RTG won't interfere with cave generation at all."
                + Configuration.NEW_LINE +
                "WARNING! Setting this to FALSE may result in unpredictable cave generation.",
            true
        );
        this.addProperty(ENABLE_CAVE_MODIFICATIONS);

        ENABLE_CAVES = new ConfigPropertyBoolean(ConfigProperty.Type.BOOLEAN, "Enable Caves", "Caves", "", true);
        this.addProperty(ENABLE_CAVES);

        CAVE_DENSITY = new ConfigPropertyInt(
            ConfigProperty.Type.INTEGER,
            "Cave Density",
            "Caves",
            "This setting controls the size of caves."
                + Configuration.NEW_LINE + "HIGHER values = BIGGER caves & MORE lag. (14 = vanilla cave density)",
            8, 1, 40
        );
        this.addProperty(CAVE_DENSITY);

        CAVE_FREQUENCY = new ConfigPropertyInt(
            ConfigProperty.Type.INTEGER,
            "Cave Frequency",
            "Caves",
            "This setting controls the number of caves that generate."
                + Configuration.NEW_LINE + "LOWER values = MORE caves & MORE lag. (6 = vanilla cave frequency)",
            16, 1, 40
        );
        this.addProperty(CAVE_FREQUENCY);

        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        // Debugging
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        ENABLE_DEBUGGING = new ConfigPropertyBoolean(
            ConfigProperty.Type.BOOLEAN,
            "Enable Debugging",
            "Debugging",
            "WARNING: This should only be enabled if you know what you're doing.",
            false
        );
        this.addProperty(ENABLE_DEBUGGING);

        CRASH_ON_STRUCTURE_EXCEPTIONS = new ConfigPropertyBoolean(
            ConfigProperty.Type.BOOLEAN,
            "Crash on Structure Exceptions",
            "Debugging",
            "Instead of crashing when it experiences 'java.util.ConcurrentModificationException' (or any other exception)"
                + Configuration.NEW_LINE +
                "during structure generation, RTG will stop trying to generate that structure and continue generating the world."
                + Configuration.NEW_LINE +
                "You should only set this to TRUE if you have been instructed to do so by an RTG developer, or if you know what you're doing.",
            false
        );
        this.addProperty(CRASH_ON_STRUCTURE_EXCEPTIONS);

        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        // Dunes
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        DUNE_HEIGHT = new ConfigPropertyInt(
            ConfigProperty.Type.INTEGER,
            "Height of Dunes",
            "Dunes",
            "This setting controls the height of both sand dunes and snow dunes."
                + Configuration.NEW_LINE + "Higher values = taller dunes.",
            4, 1, 12
        );
        this.addProperty(DUNE_HEIGHT);

        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        // Dungeons
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        GENERATE_DUNGEONS = new ConfigPropertyBoolean(ConfigProperty.Type.BOOLEAN, "Generate Dungeons", "Dungeons", "", true);
        this.addProperty(GENERATE_DUNGEONS);

        DUNGEON_FREQUENCY = new ConfigPropertyInt(
            ConfigProperty.Type.INTEGER,
            "Dungeon Frequency",
            "Dungeons",
            "This setting controls the number of dungeons that generate."
                + Configuration.NEW_LINE + "HIGHER values = MORE dungeons & MORE lag. (8 = vanilla dungeon frequency)",
            8, 1, 200
        );
        this.addProperty(DUNGEON_FREQUENCY);

        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        // Flowing liquids
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        ENABLE_FLOWING_LIQUID_MODIFICATIONS = new ConfigPropertyBoolean(
            ConfigProperty.Type.BOOLEAN,
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

        FLOWING_LAVA_CHANCE = new ConfigPropertyInt(
            ConfigProperty.Type.INTEGER,
            "Flowing Lava Chance",
            "Flowing Liquids",
            "1/x chance that a lava stream will generate on the side of a hill or mountain."
                + Configuration.NEW_LINE +
                "0 = Never generate; 1 = Always generate if possible; 2 = 50% chance; 4 = 25% chance",
            200, 0, Integer.MAX_VALUE
        );
        this.addProperty(FLOWING_LAVA_CHANCE);

        FLOWING_WATER_CHANCE = new ConfigPropertyInt(
            ConfigProperty.Type.INTEGER,
            "Flowing Water Chance",
            "Flowing Liquids",
            "1/x chance that a water stream will generate on the side of a hill or mountain."
                + Configuration.NEW_LINE +
                "0 = Never generate; 1 = Always generate if possible; 2 = 50% chance; 4 = 25% chance",
            200, 0, Integer.MAX_VALUE
        );
        this.addProperty(FLOWING_WATER_CHANCE);

        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        // GUI
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        ENABLE_WORLD_TYPE_NOTIFICATION_SCREEN = new ConfigPropertyBoolean(
            ConfigProperty.Type.BOOLEAN,
            "Enable World Type Notification Screen",
            "GUI",
            "",
            true
        );
        this.addProperty(ENABLE_WORLD_TYPE_NOTIFICATION_SCREEN);

        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        // Lakes (Scenic)
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        LAKE_SIZE_MULTIPLIER = new ConfigPropertyFloat(
            ConfigProperty.Type.FLOAT,
            "Lake Size Multiplier",
            "Lakes (Scenic)",
            "Defaults to 1 (standard size)",
            1, 0, 10
        );
        this.addProperty(LAKE_SIZE_MULTIPLIER);

        LAKE_FREQUENCY_MULTIPLIER = new ConfigPropertyFloat(
            ConfigProperty.Type.FLOAT,
            "Lake Frequency Multiplier",
            "Lakes (Scenic)",
            "Defaults to 1 (standard frequency)",
            1, 0, 10
        );
        this.addProperty(LAKE_FREQUENCY_MULTIPLIER);

        LAKE_SHORE_BENDINESS_MULTIPLIER = new ConfigPropertyFloat(
            ConfigProperty.Type.FLOAT,
            "Lake Shore Irregularity",
            "Lakes (Scenic)",
            "Makes scenic lake shores bend and curve more. Defaults to 1",
            1, 0, 2
        );
        this.addProperty(LAKE_SHORE_BENDINESS_MULTIPLIER);

        SCENIC_LAKE_BIOME_ID = new ConfigPropertyInt(
            ConfigProperty.Type.INTEGER,
            "Biome for scenic lakes",
            "Lakes (Scenic)",
            "Biome ID for scenic lakes when not frozen (default 7 = River)",
            7, 0, 254
        );
        this.addProperty(SCENIC_LAKE_BIOME_ID);

        SCENIC_FROZEN_LAKE_BIOME_ID = new ConfigPropertyInt(
            ConfigProperty.Type.INTEGER,
            "Biome for frozen scenic lakes",
            "Lakes (Scenic)",
            "Biome ID for scenic lakes when frozen (default 11 = Frozen River)",
            11, 0, 254
        );
        this.addProperty(SCENIC_FROZEN_LAKE_BIOME_ID);

        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        // Lakes (Surface)
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        ENABLE_WATER_SURFACE_LAKES = new ConfigPropertyBoolean(
            ConfigProperty.Type.BOOLEAN,
            "Enable Water Surface Lakes",
            "Lakes (Surface)",
            "",
            true
        );
        this.addProperty(ENABLE_WATER_SURFACE_LAKES);

        WATER_SURFACE_LAKE_CHANCE = new ConfigPropertyInt(
            ConfigProperty.Type.INTEGER,
            "1/x chance that Water Surface Lakes will generate if given the opportunity to do so during world gen",
            "Lakes (Surface)",
            "1 = Always generate if possible; 2 = 50% chance; 4 = 25% chance",
            10, 1, 100
        );
        this.addProperty(WATER_SURFACE_LAKE_CHANCE);

        ENABLE_LAVA_SURFACE_LAKES = new ConfigPropertyBoolean(
            ConfigProperty.Type.BOOLEAN,
            "Enable Lava Surface Lakes",
            "Lakes (Surface)",
            "",
            true
        );
        this.addProperty(ENABLE_LAVA_SURFACE_LAKES);

        LAVA_SURFACE_LAKE_CHANCE = new ConfigPropertyInt(
            ConfigProperty.Type.INTEGER,
            "1/x chance that Lava Surface Lakes will generate if given the opportunity to do so during world gen",
            "Lakes (Surface)",
            "1 = Always generate if possible; 2 = 50% chance; 4 = 25% chance",
            10, 1, 100
        );
        this.addProperty(LAVA_SURFACE_LAKE_CHANCE);

        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        // Lakes (Underground)
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        ENABLE_WATER_UNDERGROUND_LAKES = new ConfigPropertyBoolean(
            ConfigProperty.Type.BOOLEAN,
            "Enable Water Underground Lakes",
            "Lakes (Underground)",
            "",
            true
        );
        this.addProperty(ENABLE_WATER_UNDERGROUND_LAKES);

        WATER_UNDERGROUND_LAKE_CHANCE = new ConfigPropertyInt(
            ConfigProperty.Type.INTEGER,
            "1/x chance that Water Underground Lakes will generate if given the opportunity to do so during world gen",
            "Lakes (Underground)",
            "1 = Always generate if possible; 2 = 50% chance; 4 = 25% chance",
            10, 1, 100
        );
        this.addProperty(WATER_UNDERGROUND_LAKE_CHANCE);

        ENABLE_LAVA_UNDERGROUND_LAKES = new ConfigPropertyBoolean(
            ConfigProperty.Type.BOOLEAN,
            "Enable Lava Underground Lakes",
            "Lakes (Underground)",
            "",
            true
        );
        this.addProperty(ENABLE_LAVA_UNDERGROUND_LAKES);

        LAVA_UNDERGROUND_LAKE_CHANCE = new ConfigPropertyInt(
            ConfigProperty.Type.INTEGER,
            "1/x chance that Lava Underground Lakes will generate if given the opportunity to do so during world gen",
            "Lakes (Underground)",
            "1 = Always generate if possible; 2 = 50% chance; 4 = 25% chance",
            10, 1, 100
        );
        this.addProperty(LAVA_UNDERGROUND_LAKE_CHANCE);

        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        // Mineshafts
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        GENERATE_MINESHAFTS = new ConfigPropertyBoolean(
            ConfigProperty.Type.BOOLEAN, "Generate Mineshafts", "Mineshafts", "", true
        );
        this.addProperty(GENERATE_MINESHAFTS);

        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        // Ocean monuments
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        ENABLE_OCEAN_MONUMENT_MODIFICATIONS = new ConfigPropertyBoolean(
            ConfigProperty.Type.BOOLEAN,
            "Enable Ocean Monument Modifications",
            "Ocean Monuments",
            "Must be set to TRUE for the other ocean monument settings to have any effect."
                + Configuration.NEW_LINE +
                "If FALSE, RTG won't interfere with ocean monument generation at all."
                + Configuration.NEW_LINE +
                "WARNING! Setting this to FALSE may result in ocean monuments generating in unpredictable locations, including those outside of oceanic biomes.",
            true
        );
        this.addProperty(ENABLE_OCEAN_MONUMENT_MODIFICATIONS);

        GENERATE_OCEAN_MONUMENTS = new ConfigPropertyBoolean(
            ConfigProperty.Type.BOOLEAN, "Generate Ocean Monuments", "Ocean Monuments", "", true
        );
        this.addProperty(GENERATE_OCEAN_MONUMENTS);

        OCEAN_MONUMENT_SPACING = new ConfigPropertyInt(
            ConfigProperty.Type.INTEGER,
            "Ocean Monument Spacing",
            "Ocean Monuments",
            "This setting determines the size of the grid, in chunks, on which ocean monuments are generated."
                + Configuration.NEW_LINE +
                "LOWER values = MORE monuments & MORE lag. (32 = Vanilla spacing)"
                + Configuration.NEW_LINE +
                "This value MUST be greater than the 'separation' value.",
            32, 1, 1024
        );
        this.addProperty(OCEAN_MONUMENT_SPACING);

        OCEAN_MONUMENT_SEPARATION = new ConfigPropertyInt(
            ConfigProperty.Type.INTEGER,
            "Ocean Monument Separation",
            "Ocean Monuments",
            "This setting determines the minimum distance, in chunks, between ocean monuments."
                + Configuration.NEW_LINE +
                "LOWER values = MORE monuments & MORE lag. (5 = Vanilla separation)"
                + Configuration.NEW_LINE +
                "This value MUST be less than the 'spacing' value.",
            5, 1, Integer.MAX_VALUE
        );
        this.addProperty(OCEAN_MONUMENT_SEPARATION);

        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        // Ore gen
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        GENERATE_ORE_ANDESITE = new ConfigPropertyBoolean(ConfigProperty.Type.BOOLEAN, "Generate Andesite Ore", "Ore Gen", "", true);
        this.addProperty(GENERATE_ORE_ANDESITE);

        GENERATE_ORE_COAL = new ConfigPropertyBoolean(ConfigProperty.Type.BOOLEAN, "Generate Coal Ore", "Ore Gen", "", true);
        this.addProperty(GENERATE_ORE_COAL);

        GENERATE_ORE_DIAMOND = new ConfigPropertyBoolean(ConfigProperty.Type.BOOLEAN, "Generate Diamond Ore", "Ore Gen", "", true);
        this.addProperty(GENERATE_ORE_DIAMOND);

        GENERATE_ORE_DIORITE = new ConfigPropertyBoolean(ConfigProperty.Type.BOOLEAN, "Generate Diorite Ore", "Ore Gen", "", true);
        this.addProperty(GENERATE_ORE_DIORITE);

        GENERATE_ORE_DIRT = new ConfigPropertyBoolean(ConfigProperty.Type.BOOLEAN, "Generate Dirt Ore", "Ore Gen", "", true);
        this.addProperty(GENERATE_ORE_DIRT);

        GENERATE_ORE_EMERALD = new ConfigPropertyBoolean(ConfigProperty.Type.BOOLEAN, "Generate Emerald Ore", "Ore Gen", "", true);
        this.addProperty(GENERATE_ORE_EMERALD);

        GENERATE_ORE_GOLD = new ConfigPropertyBoolean(ConfigProperty.Type.BOOLEAN, "Generate Gold Ore", "Ore Gen", "", true);
        this.addProperty(GENERATE_ORE_GOLD);

        GENERATE_ORE_GRANITE = new ConfigPropertyBoolean(ConfigProperty.Type.BOOLEAN, "Generate Granite Ore", "Ore Gen", "", true);
        this.addProperty(GENERATE_ORE_GRANITE);

        GENERATE_ORE_GRAVEL = new ConfigPropertyBoolean(ConfigProperty.Type.BOOLEAN, "Generate Gravel Ore", "Ore Gen", "", true);
        this.addProperty(GENERATE_ORE_GRAVEL);

        GENERATE_ORE_IRON = new ConfigPropertyBoolean(ConfigProperty.Type.BOOLEAN, "Generate Iron Ore", "Ore Gen", "", true);
        this.addProperty(GENERATE_ORE_IRON);

        GENERATE_ORE_LAPIS = new ConfigPropertyBoolean(ConfigProperty.Type.BOOLEAN, "Generate Lapis Ore", "Ore Gen", "", true);
        this.addProperty(GENERATE_ORE_LAPIS);

        GENERATE_ORE_REDSTONE = new ConfigPropertyBoolean(ConfigProperty.Type.BOOLEAN, "Generate Redstone Ore", "Ore Gen", "", true);
        this.addProperty(GENERATE_ORE_REDSTONE);

        GENERATE_ORE_SILVERFISH = new ConfigPropertyBoolean(ConfigProperty.Type.BOOLEAN, "Generate Silverfish Ore", "Ore Gen", "", true);
        this.addProperty(GENERATE_ORE_SILVERFISH);

        ALLOW_ORE_GEN_EVENT_CANCELLATION = new ConfigPropertyBoolean(
            ConfigProperty.Type.BOOLEAN,
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

        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        // Ravines
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        ENABLE_RAVINE_MODIFICATIONS = new ConfigPropertyBoolean(
            ConfigProperty.Type.BOOLEAN,
            "Enable Ravine Modifications",
            "Ravines",
            "Must be set to TRUE for the other ravine settings to have any effect."
                + Configuration.NEW_LINE +
                "If FALSE, RTG won't interfere with ravine generation at all."
                + Configuration.NEW_LINE +
                "WARNING! Setting this to FALSE may result in unpredictable ravine generation.",
            true
        );
        this.addProperty(ENABLE_RAVINE_MODIFICATIONS);

        ENABLE_RAVINES = new ConfigPropertyBoolean(
            ConfigProperty.Type.BOOLEAN,
            "Enable Ravines",
            "Ravines",
            "",
            false
        );
        this.addProperty(ENABLE_RAVINES);

        RAVINE_FREQUENCY = new ConfigPropertyInt(
            ConfigProperty.Type.INTEGER,
            "Ravine Frequency",
            "Ravines",
            "This setting controls the number of ravines that generate."
                + Configuration.NEW_LINE + "LOWER values = MORE ravines & MORE lag. (50 = vanilla ravine frequency)",
            50, 1, 100
        );
        this.addProperty(RAVINE_FREQUENCY);

        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        // Rivers
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        RIVER_SIZE_MULTIPLIER = new ConfigPropertyFloat(
            ConfigProperty.Type.FLOAT,
            "River Width Multiplier",
            "Rivers",
            "Defaults to 1 (standard width)",
            1, 0, 10
        );
        this.addProperty(RIVER_SIZE_MULTIPLIER);

        RIVER_FREQUENCY_MULTIPLIER = new ConfigPropertyFloat(
            ConfigProperty.Type.FLOAT,
            "River Frequency Multiplier",
            "Rivers",
            "Multiplier to river frequencies. Defaults to 1",
            1, 0, 10
        );
        this.addProperty(RIVER_FREQUENCY_MULTIPLIER);

        RIVER_BENDINESS_MULTIPLIER = new ConfigPropertyFloat(
            ConfigProperty.Type.FLOAT,
            "Multiplier to River Bending",
            "Rivers",
            "Higher numbers make rivers bend more. Defaults to 1",
            1, 0, 2
        );
        this.addProperty(RIVER_BENDINESS_MULTIPLIER);

        RIVER_CUT_OFF_SCALE = new ConfigPropertyFloat(
            ConfigProperty.Type.FLOAT,
            "Scale of Large-Scale River Cut Off",
            "Rivers",
            "Higher numbers make grassy areas near rivers bigger, but also more rare. Defaults to 350",
            350, 50, 5000
        );
        this.addProperty(RIVER_CUT_OFF_SCALE);

        RIVER_CUT_OFF_AMPLITUDE = new ConfigPropertyFloat(
            ConfigProperty.Type.FLOAT,
            "Amplitude of Large-Scale River Cut Off",
            "Rivers",
            "Higher numbers make the large-scale cut-off noise have a greater effect. Defaults to 0.5",
            0.5f, 0, 2
        );
        this.addProperty(RIVER_CUT_OFF_AMPLITUDE);

        ENABLE_LUSH_RIVER_BANK_DECORATIONS_IN_HOT_BIOMES = new ConfigPropertyBoolean(
            ConfigProperty.Type.BOOLEAN,
            "Enable Lush River Bank Decorations in Hot Biomes",
            "Rivers",
            "Set this to FALSE to prevent RTG from generating lush river bank decorations in hot biomes, like Desert and Mesa."
                + Configuration.NEW_LINE +
                "Lush decorations consist of tallgrass, trees, shrubs, and other flora.",
            true
        );
        this.addProperty(ENABLE_LUSH_RIVER_BANK_DECORATIONS_IN_HOT_BIOMES);

        ENABLE_LUSH_RIVER_BANK_SURFACES_IN_HOT_BIOMES = new ConfigPropertyBoolean(
            ConfigProperty.Type.BOOLEAN,
            "Enable Lush River Bank Surfaces in Hot Biomes",
            "Rivers",
            "Set this to FALSE to prevent RTG from generating lush river bank surfaces in hot biomes, like Desert and Mesa."
                + Configuration.NEW_LINE +
                "Lush surfaces consist (almost exclusively) of grass blocks.",
            true
        );
        this.addProperty(ENABLE_LUSH_RIVER_BANK_SURFACES_IN_HOT_BIOMES);

        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        // Saplings
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        ENABLE_RTG_SAPLINGS = new ConfigPropertyBoolean(
            ConfigProperty.Type.BOOLEAN,
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

        RTG_TREE_CHANCE = new ConfigPropertyInt(
            ConfigProperty.Type.INTEGER,
            "RTG Tree from Vanilla Sapling Chance",
            "Saplings",
            "1/x chance that a vanilla sapling will grow one of RTG's custom trees."
                + Configuration.NEW_LINE +
                "1 = Always generate if possible; 2 = 50% chance; 4 = 25% chance",
            2, 1, Integer.MAX_VALUE
        );
        this.addProperty(RTG_TREE_CHANCE);

        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        // Scattered features
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        ENABLE_SCATTERED_FEATURE_MODIFICATIONS = new ConfigPropertyBoolean(
            ConfigProperty.Type.BOOLEAN,
            "Enable Scattered Feature Modifications",
            "Scattered Features",
            "Must be set to TRUE for the other scattered feature settings to have any effect."
                + Configuration.NEW_LINE +
                "If FALSE, RTG won't interfere with scattered feature generation at all."
                + Configuration.NEW_LINE +
                "WARNING! Setting this to FALSE may result in unpredictable scattered feature generation.",
            true
        );
        this.addProperty(ENABLE_SCATTERED_FEATURE_MODIFICATIONS);

        GENERATE_SCATTERED_FEATURES = new ConfigPropertyBoolean(
            ConfigProperty.Type.BOOLEAN,
            "Generate Scattered Features",
            "Scattered Features",
            "",
            true
        );
        this.addProperty(GENERATE_SCATTERED_FEATURES);

        MIN_DISTANCE_SCATTERED_FEATURES = new ConfigPropertyInt(
            ConfigProperty.Type.INTEGER,
            "Minimum distance between scattered features",
            "Scattered Features",
            "Scattered features = desert temples, jungle temples, and witch huts; 8 = Vanilla",
            12, 2, Integer.MAX_VALUE
        );
        this.addProperty(MIN_DISTANCE_SCATTERED_FEATURES);

        MAX_DISTANCE_SCATTERED_FEATURES = new ConfigPropertyInt(
            ConfigProperty.Type.INTEGER,
            "Maximum distance between scattered features",
            "Scattered Features",
            "Scattered features = desert temples, jungle temples, and witch huts; 32 = Vanilla",
            48, 4, Integer.MAX_VALUE
        );
        this.addProperty(MAX_DISTANCE_SCATTERED_FEATURES);

        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        // Snow
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        ENABLE_SNOW_LAYERS = new ConfigPropertyBoolean(
            ConfigProperty.Type.BOOLEAN,
            "Enable Snow Layers",
            "Snow",
            "This applies to newly-generated chunks only. Snow layers will still appear in cold/snowy biomes after it snows.",
            true
        );
        this.addProperty(ENABLE_SNOW_LAYERS);

        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        // Strongholds
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        ENABLE_STRONGHOLD_MODIFICATIONS = new ConfigPropertyBoolean(
            ConfigProperty.Type.BOOLEAN,
            "Enable Stronghold Modifications",
            "Strongholds",
            "Must be set to TRUE for the other stronghold settings to have any effect."
                + Configuration.NEW_LINE +
                "If FALSE, RTG won't interfere with stronghold generation at all."
                + Configuration.NEW_LINE +
                "WARNING! Setting this to FALSE may result in unpredictable stronghold generation.",
            true
        );
        this.addProperty(ENABLE_STRONGHOLD_MODIFICATIONS);

        GENERATE_STRONGHOLDS = new ConfigPropertyBoolean(
            ConfigProperty.Type.BOOLEAN,
            "Generate Strongholds",
            "Strongholds",
            "",
            true
        );
        this.addProperty(GENERATE_STRONGHOLDS);

        STRONGHOLD_COUNT = new ConfigPropertyInt(
            ConfigProperty.Type.INTEGER,
            "Stronghold Count",
            "Strongholds",
            "This setting is the number of strongholds that exist per world."
                + Configuration.NEW_LINE +
                "HIGHER values = MORE strongholds & MORE lag. (128 = Vanilla)",
            128, 1, Integer.MAX_VALUE
        );
        this.addProperty(STRONGHOLD_COUNT);

        STRONGHOLD_DISTANCE = new ConfigPropertyInt(
            ConfigProperty.Type.INTEGER,
            "Stronghold Distance",
            "Strongholds",
            "This setting determines how far strongholds are from the spawn and other strongholds."
                + Configuration.NEW_LINE +
                "LOWER values = MORE strongholds & MORE lag. (32 = Vanilla)",
            32, 1, Integer.MAX_VALUE
        );
        this.addProperty(STRONGHOLD_DISTANCE);

        STRONGHOLD_SPREAD = new ConfigPropertyInt(
            ConfigProperty.Type.INTEGER,
            "Stronghold Spread",
            "Strongholds",
            "This setting determines how concentrated strongholds are around the spawn."
                + Configuration.NEW_LINE +
                "LOWER values = LOWER concentration around spawn. (3 = Vanilla)",
            3, 1, Integer.MAX_VALUE
        );
        this.addProperty(STRONGHOLD_SPREAD);

        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        // Terrain shadowing
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        SHADOW_STONE_BLOCK_ID = new ConfigPropertyString(
            ConfigProperty.Type.STRING,
            "Stone shadow block ID",
            "Terrain shadowing",
            "The block to use for stone terrain shadowing, typically seen on the cliffs of stone mountains. Defaults to stained hardened clay.",
            DEFAULT_SHADOW_STONE_BLOCK_ID
        );
        this.addProperty(SHADOW_STONE_BLOCK_ID);

        SHADOW_STONE_BLOCK_META = new ConfigPropertyInt(
            ConfigProperty.Type.INTEGER,
            "Stone shadow block meta value",
            "Terrain shadowing",
            "The meta value of the shadow block for stone cliffs. Defaults to " + DEFAULT_SHADOW_STONE_BLOCK_META + " (cyan).",
            DEFAULT_SHADOW_STONE_BLOCK_META, 0, 15
        );
        this.addProperty(SHADOW_STONE_BLOCK_META);

        SHADOW_DESERT_BLOCK_ID = new ConfigPropertyString(
            ConfigProperty.Type.STRING,
            "Desert shadow block ID",
            "Terrain shadowing",
            "The block to use for desert terrain shadowing, typically seen on the cliffs of desert mountains. Defaults to stained hardened clay.",
            DEFAULT_SHADOW_DESERT_BLOCK_ID
        );
        this.addProperty(SHADOW_DESERT_BLOCK_ID);

        SHADOW_DESERT_BLOCK_META = new ConfigPropertyInt(
            ConfigProperty.Type.INTEGER,
            "Desert shadow block meta value",
            "Terrain shadowing",
            "The meta value of the shadow block for desert cliffs. Defaults to " + DEFAULT_SHADOW_DESERT_BLOCK_META + " (white).",
            DEFAULT_SHADOW_DESERT_BLOCK_META, 0, 15
        );
        this.addProperty(SHADOW_DESERT_BLOCK_META);

        ENABLE_UBC_STONE_SHADOWING = new ConfigPropertyBoolean(
            ConfigProperty.Type.BOOLEAN,
            "UBC Mode (Stone)",
            "Terrain shadowing",
            "Set this to TRUE to allow UBC to override stone shadowing."
                + Configuration.NEW_LINE +
                "This setting doesn't have any effect if UBC is not installed.",
            true
        );
        this.addProperty(ENABLE_UBC_STONE_SHADOWING);

        ENABLE_UBC_DESERT_SHADOWING = new ConfigPropertyBoolean(
            ConfigProperty.Type.BOOLEAN,
            "UBC Mode (Desert)",
            "Terrain shadowing",
            "Set this to TRUE to allow UBC to override desert shadowing."
                + Configuration.NEW_LINE +
                "This setting doesn't have any effect if UBC is not installed.",
            true
        );
        this.addProperty(ENABLE_UBC_DESERT_SHADOWING);

        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        // Trees
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        ALLOW_TREES_TO_GENERATE_ON_SAND = new ConfigPropertyBoolean(
            ConfigProperty.Type.BOOLEAN,
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
            ConfigProperty.Type.BOOLEAN,
            "Allow Shrubs to Generate Below Surface",
            "Trees",
            "Set this to FALSE to prevent shrub trunks from generating below the surface.",
            true
        );
        this.addProperty(ALLOW_SHRUBS_TO_GENERATE_BELOW_SURFACE);

        ALLOW_BARK_COVERED_LOGS = new ConfigPropertyBoolean(
            ConfigProperty.Type.BOOLEAN,
            "Allow bark-covered logs",
            "Trees",
            "Set this to FALSE to prevent the trunks of RTG trees from using the 'all-bark' texture model."
                + Configuration.NEW_LINE +
                "For more information, visit http://minecraft.gamepedia.com/Wood#Block_data",
            true
        );
        this.addProperty(ALLOW_BARK_COVERED_LOGS);

        TREE_DENSITY_MULTIPLIER = new ConfigPropertyFloat(
            ConfigProperty.Type.FLOAT,
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
            ConfigProperty.Type.STRING,
            "Materials That Trees Can Grow Into",
            "Trees",
            "Comma-separated list of materials that trees can grow into (replace) when generating."
                + Configuration.NEW_LINE +
                "Valid values include the following:"
                + Configuration.NEW_LINE +
                "AIR,ANVIL,BARRIER,CACTUS,CAKE,CARPET,CIRCUITS,CLAY,CLOTH,CORAL,CRAFTED_SNOW,DRAGON_EGG,FIRE,GLASS,GOURD,GRASS,GROUND,ICE,IRON,LAVA,LEAVES,PACKED_ICE,PISTON,PLANTS,PORTAL,REDSTONE_LIGHT,ROCK,SAND,SNOW,SPONGE,STRUCTURE_VOID,TNT,VINE,WATER,WEB,WOOD"
                + Configuration.NEW_LINE +
                "For more information, visit http://minecraft.gamepedia.com/Materials",
                "AIR,WOOD,LEAVES,GRASS,GROUND,PLANTS,VINE,WATER,SNOW"
        );
        this.addProperty(MATERIALS_TREES_CAN_GROW_INTO);

        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        // Villages
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        ENABLE_VILLAGE_MODIFICATIONS = new ConfigPropertyBoolean(
            ConfigProperty.Type.BOOLEAN,
            "Enable village modifications",
            "Villages",
            "Set this to FALSE to resolve issues with mods that also modify villages."
                + Configuration.NEW_LINE +
                "If set to FALSE, the 'Minimum distance between villages', 'Maximum distance between villages' & 'Size of villages' settings will have no effect."
                + Configuration.NEW_LINE +
                "WARNING! Setting this to FALSE may result in unpredictable village generation.",
            true
        );
        this.addProperty(ENABLE_VILLAGE_MODIFICATIONS);

        GENERATE_VILLAGES = new ConfigPropertyBoolean(
            ConfigProperty.Type.BOOLEAN,
            "Generate Villages",
            "Villages",
            "",
            true
        );
        this.addProperty(GENERATE_VILLAGES);

        VILLAGE_SIZE = new ConfigPropertyInt(
            ConfigProperty.Type.INTEGER,
            "Size of villages",
            "Villages",
            "Higher values = bigger villages; 0 = Vanilla",
            0, 0, 10
        );
        this.addProperty(VILLAGE_SIZE);

        MIN_DISTANCE_VILLAGES = new ConfigPropertyInt(
            ConfigProperty.Type.INTEGER,
            "Minimum distance between villages",
            "Villages",
            "Higher values = villages further apart; 8 = Vanilla",
            12, 1, Integer.MAX_VALUE
        );
        this.addProperty(MIN_DISTANCE_VILLAGES);

        MAX_DISTANCE_VILLAGES = new ConfigPropertyInt(
            ConfigProperty.Type.INTEGER,
            "Maximum distance between villages",
            "Villages",
            "Lower values = villages closer together; 32 = Vanilla",
            48, 1, Integer.MAX_VALUE
        );
        this.addProperty(MAX_DISTANCE_VILLAGES);

        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        // Volcanoes
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        VOLCANO_BLOCK_ID = new ConfigPropertyString(
            ConfigProperty.Type.STRING,
            "Volcano block ID",
            "Volcanoes.Volcano Blocks.Volcano Block",
            "The main block to use for the surface of the volcano.",
            "minecraft:obsidian"
        );
        this.addProperty(VOLCANO_BLOCK_ID);

        VOLCANO_BLOCK_META = new ConfigPropertyInt(
            ConfigProperty.Type.INTEGER,
            "Volcano block meta value",
            "Volcanoes.Volcano Blocks.Volcano Block",
            "The meta value of the volcano block.",
            0, 0, 15
        );
        this.addProperty(VOLCANO_BLOCK_META);

        VOLCANO_MIX1_BLOCK_ID = new ConfigPropertyString(
            ConfigProperty.Type.STRING,
            "Volcano mix block 1 ID",
            "Volcanoes.Volcano Blocks.Mix Block 1",
            "The block ID of the 1st volcano mix block.",
            "minecraft:cobblestone"
        );
        this.addProperty(VOLCANO_MIX1_BLOCK_ID);

        VOLCANO_MIX1_BLOCK_META = new ConfigPropertyInt(
            ConfigProperty.Type.INTEGER,
            "Volcano mix block 1 meta value",
            "Volcanoes.Volcano Blocks.Mix Block 1",
            "The meta value of the 1st volcano mix block.",
            0, 0, 15
        );
        this.addProperty(VOLCANO_MIX1_BLOCK_META);

        VOLCANO_MIX2_BLOCK_ID = new ConfigPropertyString(
            ConfigProperty.Type.STRING,
            "Volcano mix block 2 ID",
            "Volcanoes.Volcano Blocks.Mix Block 2",
            "The block ID of the 2nd volcano mix block.",
            "minecraft:gravel"
        );
        this.addProperty(VOLCANO_MIX2_BLOCK_ID);

        VOLCANO_MIX2_BLOCK_META = new ConfigPropertyInt(
            ConfigProperty.Type.INTEGER,
            "Volcano mix block 2 meta value",
            "Volcanoes.Volcano Blocks.Mix Block 2",
            "The meta value of the 2nd volcano mix block.",
            0, 0, 15
        );
        this.addProperty(VOLCANO_MIX2_BLOCK_META);

        VOLCANO_MIX3_BLOCK_ID = new ConfigPropertyString(
            ConfigProperty.Type.STRING,
            "Volcano mix block 3 ID",
            "Volcanoes.Volcano Blocks.Mix Block 3",
            "The block ID of the 3rd volcano mix block.",
            "minecraft:coal_block"
        );
        this.addProperty(VOLCANO_MIX3_BLOCK_ID);

        VOLCANO_MIX3_BLOCK_META = new ConfigPropertyInt(
            ConfigProperty.Type.INTEGER,
            "Volcano mix block 3 meta value",
            "Volcanoes.Volcano Blocks.Mix Block 3",
            "The meta value of the 3rd volcano mix block.",
            0, 0, 15
        );
        this.addProperty(VOLCANO_MIX3_BLOCK_META);

        ENABLE_VOLCANOES = new ConfigPropertyBoolean(
            ConfigProperty.Type.BOOLEAN,
            "Enable volcanoes",
            "Volcanoes",
            "Set this to FALSE to prevent volcanoes from generating.",
            true
        );
        this.addProperty(ENABLE_VOLCANOES);

        ENABLE_VOLCANO_ERUPTIONS = new ConfigPropertyBoolean(
            ConfigProperty.Type.BOOLEAN,
            "Enable volcano eruptions",
            "Volcanoes",
            "Set this to FALSE to prevent lava from flowing down the sides of volcanoes.",
            true
        );
        this.addProperty(ENABLE_VOLCANO_ERUPTIONS);

        VOLCANO_CHANCE = new ConfigPropertyInt(
            ConfigProperty.Type.INTEGER,
            "Volcano Chance",
            "Volcanoes",
            "1/x chance that a volcano will generate in a biome that has volcanoes enabled."
                + Configuration.NEW_LINE +
                "1 = Always generate if possible; 2 = 50% chance; 4 = 25% chance",
            48, 1, Integer.MAX_VALUE
        );
        this.addProperty(VOLCANO_CHANCE);

        ENABLE_VOLCANO_CONDUITS = new ConfigPropertyBoolean(
            ConfigProperty.Type.BOOLEAN,
            "Enable volcano conduits",
            "Volcanoes.Volcano Conduits",
            "Set this to FALSE to prevent volcanoes from generating conduits (lava tubes) below their throats.",
            true
        );
        this.addProperty(ENABLE_VOLCANO_CONDUITS);

        VOLCANO_CONDUIT_DEPTH = new ConfigPropertyInt(
            ConfigProperty.Type.INTEGER,
            "Volcano Conduit Depth",
            "Volcanoes.Volcano Conduits",
            "The lowest Y value that conduits should reach."
                + Configuration.NEW_LINE +
                "Please note that even though conduits can reach to Y=1, they will not replace bedrock.",
            0, 0, 120
        );
        this.addProperty(VOLCANO_CONDUIT_DEPTH);

        VOLCANO_CALDERA_MULTIPLIER = new ConfigPropertyFloat(
            ConfigProperty.Type.FLOAT,
            "Volcano Caldera Multiplier",
            "Volcanoes",
            "This setting allows you to modify the radius of volcano calderas.",
            1, 1, 3
        );
        this.addProperty(VOLCANO_CALDERA_MULTIPLIER);

        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        // Surface Bleeding
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        SURFACE_BLEED_RADIUS = this.addProperty(new ConfigPropertyInt(
                ConfigProperty.Type.INTEGER,
                "Surface Bleed Radius",
                "Surface Bleed",
                "The maximum distance surfaces will bleed. Set to 0 to disable surface bleeds." +
                Configuration.NEW_LINE +
                "Per default surface bleeding is only enabled for beaches. You can control that in biome settings",
                16, 0, 32
        ));

        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    }

    public static byte[] getPlateauGradientBlockMetasFromConfigString(String configString)
    {
        String[] strings = configString.split(",");
        ArrayList<Byte> byteList = new ArrayList<Byte>(){};

        for (int i = 0; i < strings.length; i++) {
            strings[i] = strings[i].trim();
            if (strings[i].matches("-1|0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15")) {
                byteList.add(Byte.valueOf(strings[i]));
            }
        }

        Byte[] bytes = byteList.toArray(new Byte[byteList.size()]);
        return ArrayUtils.toPrimitive(bytes);
    }

    private static String getPlateauGradientBlockMetasComment(String biomeName)
    {
        String comment =
            "Comma-separated list of meta values for the gradient plateau blocks used in the " + biomeName + "."
                + Configuration.NEW_LINE +
                "-1 = Plateau block; 0-15 = Plateau gradient block"
                + Configuration.NEW_LINE +
                "0 = White; 1 = Orange; 2 = Magenta; 3 = Light Blue; 4 = Yellow; 5 = Lime; 6 = Pink; 7 = Gray"
                + Configuration.NEW_LINE +
                "8 = Light Gray; 9 = Cyan; 10 = Purple; 11 = Blue; 12 = Brown; 13 = Green; 14 = Red; 15 = Black";

        return comment;
    }

    public float lakeSizeMultiplier() {
        // With the river system changing frequency also shinks size and that will
        // confuse the heck out of users.
        return LAKE_SIZE_MULTIPLIER.get() * LAKE_FREQUENCY_MULTIPLIER.get();
    }

    public float riverSizeMultiplier() {
        // With the river system changing frequency also shinks size and that will
        // confuse the heck out of users.
        return RIVER_SIZE_MULTIPLIER.get() * RIVER_FREQUENCY_MULTIPLIER.get();
    }

    public static ArrayList<Material> getTreeMaterialsFromConfigString(String configString)
    {
        String[] strings = configString.split(",");
        ArrayList<Material> materials = new ArrayList<Material>(){};

        for (int i = 0; i < strings.length; i++) {

            String string = strings[i].trim().toUpperCase();

            try {

                MaterialUtil util = new MaterialUtil(string);
                materials.add(util.getMaterial());
            }
            catch (Exception e) {
                ;
            }
        }

        return materials;
    }
}
