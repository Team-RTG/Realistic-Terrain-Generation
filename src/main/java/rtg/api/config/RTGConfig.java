package rtg.api.config;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

import rtg.api.config.property.ConfigPropertyBoolean;
import rtg.api.config.property.ConfigPropertyFloat;
import rtg.api.config.property.ConfigPropertyInteger;
import rtg.api.config.property.ConfigPropertyString;

// TODO: [Clean-up] Split this config implementation from the fields that hold the config values and move this implementation out of the API. The API class should have
//                  fields that hold the config values and not the Property objects. The fields should be synched whenever the Configuration changes.
// TODO: [Clean-up] Convert this config and the Config base class to use native Forge Property objects.
// TODO: [Clean-up] Add properties to a category heirarchy to better organise config settings.
@SuppressWarnings("WeakerAccess")
public class RTGConfig extends Config
{
    // Maximum tree density.
    public static final float MAX_TREE_DENSITY = 5f;

    public final ConfigPropertyBoolean DISABLE_RTG_BIOME_DECORATIONS;
    public final ConfigPropertyBoolean DISABLE_RTG_BIOME_SURFACES;
    public final ConfigPropertyBoolean DISABLE_RTG_TERRAIN;
    public final ConfigPropertyBoolean USE_PATCH_BIOME;
    public final ConfigPropertyString  PATCH_BIOME;

    public final ConfigPropertyBoolean ENABLE_CAVE_MODIFICATIONS; // TODO: [Generator settings] To be removed
    public final ConfigPropertyBoolean ENABLE_CAVES; // TODO: [Generator settings] To be removed
    public final ConfigPropertyInteger CAVE_DENSITY; // TODO: [Generator settings] To be removed
    public final ConfigPropertyInteger CAVE_FREQUENCY; // TODO: [Generator settings] To be removed

    public final ConfigPropertyBoolean ENABLE_DEBUGGING;
    public final ConfigPropertyBoolean CRASH_ON_STRUCTURE_EXCEPTIONS; // TODO: [Clean-up] Deprecate. Not needed anymore for MC >1.7.10 as structure gen is now sychronized

    public final ConfigPropertyInteger DUNE_HEIGHT; // TODO: [Generator settings] To be removed

    public final ConfigPropertyBoolean GENERATE_DUNGEONS; // TODO: [Generator settings] To be removed
    public final ConfigPropertyInteger DUNGEON_FREQUENCY; // TODO: [Generator settings] To be removed

    public final ConfigPropertyBoolean ENABLE_FLOWING_LIQUID_MODIFICATIONS; // TODO: [Generator settings] To be removed
    public final ConfigPropertyInteger FLOWING_LAVA_CHANCE; // TODO: [Generator settings] To be removed
    public final ConfigPropertyInteger FLOWING_WATER_CHANCE; // TODO: [Generator settings] To be removed

    public final ConfigPropertyBoolean ENABLE_WORLD_TYPE_NOTIFICATION_SCREEN; // TODO: [Clean-up] Rename this to be less ambiguous for what it does after removing the hashfile functionality

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

    public final ConfigPropertyBoolean GENERATE_MINESHAFTS; // TODO: [Generator settings] To be removed

    public final ConfigPropertyBoolean ENABLE_OCEAN_MONUMENT_MODIFICATIONS; // TODO: [Generator settings] To be removed
    public final ConfigPropertyBoolean GENERATE_OCEAN_MONUMENTS; // TODO: [Generator settings] To be removed
    public final ConfigPropertyInteger OCEAN_MONUMENT_SPACING; // TODO: [Generator settings] To be removed
    public final ConfigPropertyInteger OCEAN_MONUMENT_SEPARATION; // TODO: [Generator settings] To be removed

    public final ConfigPropertyBoolean ENABLE_OCEAN_WAVES; // TODO: [Generator settings] To be removed
    public final ConfigPropertyInteger OCEAN_WAVE_DIRECTION; // TODO: [Generator settings] Add a generator setting for this?

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

    public final ConfigPropertyBoolean ENABLE_RAVINE_MODIFICATIONS; // TODO: [Generator settings] To be removed
    public final ConfigPropertyBoolean ENABLE_RAVINES; // TODO: [Generator settings] To be removed
    public final ConfigPropertyInteger RAVINE_FREQUENCY; // TODO: [Generator settings] To be removed

    public final ConfigPropertyFloat RIVER_SIZE_MULTIPLIER; // This is private because we want a transformed version. // TODO: [Generator settings] To be removed
    public final ConfigPropertyFloat RIVER_FREQUENCY_MULTIPLIER; // TODO: [Generator settings] To be removed
    public final ConfigPropertyFloat RIVER_BENDINESS_MULTIPLIER; // TODO: [Generator settings] To be removed
    public final ConfigPropertyFloat RIVER_CUT_OFF_SCALE; // TODO: [Generator settings] To be removed
    public final ConfigPropertyFloat RIVER_CUT_OFF_AMPLITUDE; // TODO: [Generator settings] To be removed
    public final ConfigPropertyBoolean ENABLE_LUSH_RIVER_BANK_DECORATIONS_IN_HOT_BIOMES; // TODO: [Clean-up] Move to 'Mod Integration' category
    public final ConfigPropertyBoolean ENABLE_LUSH_RIVER_BANK_SURFACES_IN_HOT_BIOMES; // TODO: [Clean-up] Move to 'Mod Integration' category

    public final ConfigPropertyBoolean ENABLE_RTG_SAPLINGS; // TODO: [Clean-up] Move to 'Trees and Saplings' category
    public final ConfigPropertyInteger RTG_TREE_CHANCE; // TODO: [Clean-up] Move to 'Trees and Saplings' category

    public final ConfigPropertyBoolean ENABLE_SCATTERED_FEATURE_MODIFICATIONS; // TODO: [Generator settings] To be removed
    public final ConfigPropertyBoolean GENERATE_SCATTERED_FEATURES; // TODO: [Generator settings] To be removed
    public final ConfigPropertyInteger MIN_DISTANCE_SCATTERED_FEATURES; // TODO: [Generator settings] To be removed
    public final ConfigPropertyInteger MAX_DISTANCE_SCATTERED_FEATURES; // TODO: [Generator settings] To be removed

    public final ConfigPropertyBoolean ENABLE_SNOW_LAYERS; // TODO: [Generator settings] To be removed

    public final ConfigPropertyBoolean ENABLE_STRONGHOLD_MODIFICATIONS; // TODO: [Generator settings] To be removed
    public final ConfigPropertyBoolean GENERATE_STRONGHOLDS; // TODO: [Generator settings] To be removed
    public final ConfigPropertyInteger STRONGHOLD_COUNT; // TODO: [Generator settings] To be removed
    public final ConfigPropertyInteger STRONGHOLD_DISTANCE; // TODO: [Generator settings] To be removed
    public final ConfigPropertyInteger STRONGHOLD_SPREAD; // TODO: [Generator settings] To be removed

    public final ConfigPropertyString SHADOW_STONE_BLOCK_ID; // TODO: [Clean-up] Move to 'Feature Block Configuration' -> 'Shadow Blocks' category
    public final ConfigPropertyString SHADOW_DESERT_BLOCK_ID; // TODO: [Clean-up] Move to 'Feature Block Configuration' -> 'Shadow Blocks' category
    public final ConfigPropertyBoolean ENABLE_UBC_STONE_SHADOWING; // TODO: [Clean-up] Move to 'Feature Block Configuration' -> 'Shadow Blocks' category
    public final ConfigPropertyBoolean ENABLE_UBC_DESERT_SHADOWING; // TODO: [Clean-up] Move to 'Feature Block Configuration' -> 'Shadow Blocks' category

    public final ConfigPropertyBoolean ALLOW_TREES_TO_GENERATE_ON_SAND; // TODO: [Clean-up] Move to 'Trees and Saplings' category
    public final ConfigPropertyBoolean ALLOW_SHRUBS_TO_GENERATE_BELOW_SURFACE; // TODO: [Clean-up] Move to 'Trees and Saplings' category
    public final ConfigPropertyBoolean ALLOW_BARK_COVERED_LOGS; // TODO: [Clean-up] Move to 'Trees and Saplings' category
    public final ConfigPropertyFloat TREE_DENSITY_MULTIPLIER; // TODO: [Clean-up] Move to 'Trees and Saplings' category
    public final ConfigPropertyString MATERIALS_TREES_CAN_GROW_INTO; // TODO: [Clean-up] Move to 'Trees and Saplings' category

    public final ConfigPropertyBoolean ENABLE_VILLAGE_MODIFICATIONS; // TODO: [Generator settings] To be removed
    public final ConfigPropertyBoolean GENERATE_VILLAGES; // TODO: [Generator settings] To be removed
    public final ConfigPropertyInteger VILLAGE_SIZE; // TODO: [Generator settings] To be removed
    public final ConfigPropertyInteger MIN_DISTANCE_VILLAGES; // TODO: [Generator settings] To be removed
    public final ConfigPropertyInteger MAX_DISTANCE_VILLAGES; // TODO: [Generator settings] To be removed

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

    public RTGConfig(File configFile) {
        super(configFile);

        DISABLE_RTG_BIOME_DECORATIONS = new ConfigPropertyBoolean(
            "Disable RTG Biome Decorations",
            "Debug",
            "If TRUE, disables all RTG decorations and uses vanilla decorations instead.",
            false
        );
        this.addProperty(DISABLE_RTG_BIOME_DECORATIONS);

        DISABLE_RTG_BIOME_SURFACES = new ConfigPropertyBoolean(
            "Disable RTG Biome Surfaces",
            "Debug",
            "If TRUE, disables all RTG surfaces and uses vanilla surfaces instead.",
            false
        );
        this.addProperty(DISABLE_RTG_BIOME_SURFACES);

        DISABLE_RTG_TERRAIN = new ConfigPropertyBoolean(
            "Disable RTG Terrain",
            "Debug",
            "If TRUE, disables all realistic terrain generation and uses vanilla terrain instead.",
            false
        );
        this.addProperty(DISABLE_RTG_TERRAIN);

        USE_PATCH_BIOME = new ConfigPropertyBoolean(
            "Use Patch Biome",
            "Debug",
            "If TRUE, when RTG encounters a biome that is unsupported it will use the patch biome instead, otherwise crash.",
            true
        );

        PATCH_BIOME = new ConfigPropertyString(
            "Patch Biome",
            "Debug",
            "If RTG encounters an unsupported biome it will generate this biome instead." + Configuration.NEW_LINE +
            "This uses the standard ResourceLocation format: \"mod_id:biome_registry_name\"" + Configuration.NEW_LINE +
            "Default = Vanilla Plains",
            "minecraft:plains"
        );
        this.addProperty(PATCH_BIOME);

        ENABLE_CAVE_MODIFICATIONS = new ConfigPropertyBoolean(
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

        ENABLE_CAVES = new ConfigPropertyBoolean("Enable Caves", "Caves", "", true);
        this.addProperty(ENABLE_CAVES);

        CAVE_DENSITY = new ConfigPropertyInteger(
            "Cave Density",
            "Caves",
            "This setting controls the size of caves."
                + Configuration.NEW_LINE + "HIGHER values = BIGGER caves & MORE lag. (14 = vanilla cave density)",
            8, 1, 40
        );
        this.addProperty(CAVE_DENSITY);

        CAVE_FREQUENCY = new ConfigPropertyInteger(
            "Cave Frequency",
            "Caves",
            "This setting controls the number of caves that generate."
                + Configuration.NEW_LINE + "LOWER values = MORE caves & MORE lag. (6 = vanilla cave frequency)",
            16, 1, 40
        );
        this.addProperty(CAVE_FREQUENCY);

        ENABLE_DEBUGGING = new ConfigPropertyBoolean(
            "Enable Debugging",
            "Debugging",
            "WARNING: This should only be enabled if you know what you're doing.",
            false
        );
        this.addProperty(ENABLE_DEBUGGING);

        CRASH_ON_STRUCTURE_EXCEPTIONS = new ConfigPropertyBoolean(
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

        DUNE_HEIGHT = new ConfigPropertyInteger(
            "Height of Dunes",
            "Dunes",
            "This setting controls the height of both sand dunes and snow dunes."
                + Configuration.NEW_LINE + "Higher values = taller dunes.",
            4, 1, 12
        );
        this.addProperty(DUNE_HEIGHT);

        GENERATE_DUNGEONS = new ConfigPropertyBoolean("Generate Dungeons", "Dungeons", "", true);
        this.addProperty(GENERATE_DUNGEONS);

        DUNGEON_FREQUENCY = new ConfigPropertyInteger(
            "Dungeon Frequency",
            "Dungeons",
            "This setting controls the number of dungeons that generate."
                + Configuration.NEW_LINE + "HIGHER values = MORE dungeons & MORE lag. (8 = vanilla dungeon frequency)",
            8, 1, 200
        );
        this.addProperty(DUNGEON_FREQUENCY);

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

        ENABLE_WORLD_TYPE_NOTIFICATION_SCREEN = new ConfigPropertyBoolean(
            "Enable World Type Notification Screen",
            "GUI",
            "",
            true
        );
        this.addProperty(ENABLE_WORLD_TYPE_NOTIFICATION_SCREEN);

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

        GENERATE_MINESHAFTS = new ConfigPropertyBoolean(
            "Generate Mineshafts", "Mineshafts", "", true
        );
        this.addProperty(GENERATE_MINESHAFTS);

        ENABLE_OCEAN_MONUMENT_MODIFICATIONS = new ConfigPropertyBoolean(
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

        GENERATE_OCEAN_MONUMENTS = new ConfigPropertyBoolean("Generate Ocean Monuments", "Ocean Monuments", "", true);
        this.addProperty(GENERATE_OCEAN_MONUMENTS);

        OCEAN_MONUMENT_SPACING = new ConfigPropertyInteger(
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

        OCEAN_MONUMENT_SEPARATION = new ConfigPropertyInteger(
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

        ENABLE_OCEAN_WAVES = new ConfigPropertyBoolean(
            "Enable Ocean Waves",
            "Ocean Waves",
            "If TRUE, uses the individual biome settings in relevant biome config files (e.g. Ocean and Deep Ocean)."
                + Configuration.NEW_LINE +
                "If FALSE, disables all ocean waves.",
            true
        );
        this.addProperty(ENABLE_OCEAN_WAVES);

        OCEAN_WAVE_DIRECTION = new ConfigPropertyInteger(
            "Ocean Wave Direction",
            "Ocean Waves",
            "This setting determines the directin that ocean waves are placed."
                + Configuration.NEW_LINE +
                "0 = East->West; 1 = North->South",
            0, 0, 1
        );
        this.addProperty(OCEAN_WAVE_DIRECTION);

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

        ENABLE_RAVINE_MODIFICATIONS = new ConfigPropertyBoolean(
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
            "Enable Ravines",
            "Ravines",
            "",
            false
        );
        this.addProperty(ENABLE_RAVINES);

        RAVINE_FREQUENCY = new ConfigPropertyInteger(
            "Ravine Frequency",
            "Ravines",
            "This setting controls the number of ravines that generate."
                + Configuration.NEW_LINE + "LOWER values = MORE ravines & MORE lag. (50 = vanilla ravine frequency)",
            50, 1, 100
        );
        this.addProperty(RAVINE_FREQUENCY);

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

        ENABLE_SCATTERED_FEATURE_MODIFICATIONS = new ConfigPropertyBoolean(
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
            "Generate Scattered Features",
            "Scattered Features",
            "",
            true
        );
        this.addProperty(GENERATE_SCATTERED_FEATURES);

        MIN_DISTANCE_SCATTERED_FEATURES = new ConfigPropertyInteger(
            "Minimum distance between scattered features",
            "Scattered Features",
            "Scattered features = desert temples, jungle temples, and witch huts; 8 = Vanilla",
            12, 2, Integer.MAX_VALUE
        );
        this.addProperty(MIN_DISTANCE_SCATTERED_FEATURES);

        MAX_DISTANCE_SCATTERED_FEATURES = new ConfigPropertyInteger(
            "Maximum distance between scattered features",
            "Scattered Features",
            "Scattered features = desert temples, jungle temples, and witch huts; 32 = Vanilla",
            48, 4, Integer.MAX_VALUE
        );
        this.addProperty(MAX_DISTANCE_SCATTERED_FEATURES);

        ENABLE_SNOW_LAYERS = new ConfigPropertyBoolean(
            "Enable Snow Layers",
            "Snow",
            "This applies to newly-generated chunks only. Snow layers will still appear in cold/snowy biomes after it snows.",
            true
        );
        this.addProperty(ENABLE_SNOW_LAYERS);

        ENABLE_STRONGHOLD_MODIFICATIONS = new ConfigPropertyBoolean(
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
            "Generate Strongholds",
            "Strongholds",
            "",
            true
        );
        this.addProperty(GENERATE_STRONGHOLDS);

        STRONGHOLD_COUNT = new ConfigPropertyInteger(
            "Stronghold Count",
            "Strongholds",
            "This setting is the number of strongholds that exist per world."
                + Configuration.NEW_LINE +
                "HIGHER values = MORE strongholds & MORE lag. (128 = Vanilla)",
            128, 1, Integer.MAX_VALUE
        );
        this.addProperty(STRONGHOLD_COUNT);

        STRONGHOLD_DISTANCE = new ConfigPropertyInteger(
            "Stronghold Distance",
            "Strongholds",
            "This setting determines how far strongholds are from the spawn and other strongholds."
                + Configuration.NEW_LINE +
                "LOWER values = MORE strongholds & MORE lag. (32 = Vanilla)",
            32, 1, Integer.MAX_VALUE
        );
        this.addProperty(STRONGHOLD_DISTANCE);

        STRONGHOLD_SPREAD = new ConfigPropertyInteger(
            "Stronghold Spread",
            "Strongholds",
            "This setting determines how concentrated strongholds are around the spawn."
                + Configuration.NEW_LINE +
                "LOWER values = LOWER concentration around spawn. (3 = Vanilla)",
            3, 1, Integer.MAX_VALUE
        );
        this.addProperty(STRONGHOLD_SPREAD);

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

        ENABLE_UBC_STONE_SHADOWING = new ConfigPropertyBoolean(
            "UBC Mode (Stone)",
            "Terrain shadowing",
            "Set this to TRUE to allow UBC to override stone shadowing."
                + Configuration.NEW_LINE +
                "This setting doesn't have any effect if UBC is not installed.",
            true
        );
        this.addProperty(ENABLE_UBC_STONE_SHADOWING);

        ENABLE_UBC_DESERT_SHADOWING = new ConfigPropertyBoolean(
            "UBC Mode (Desert)",
            "Terrain shadowing",
            "Set this to TRUE to allow UBC to override desert shadowing."
                + Configuration.NEW_LINE +
                "This setting doesn't have any effect if UBC is not installed.",
            true
        );
        this.addProperty(ENABLE_UBC_DESERT_SHADOWING);

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

        ENABLE_VILLAGE_MODIFICATIONS = new ConfigPropertyBoolean(
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
            "Generate Villages",
            "Villages",
            "",
            true
        );
        this.addProperty(GENERATE_VILLAGES);

        VILLAGE_SIZE = new ConfigPropertyInteger(
            "Size of villages",
            "Villages",
            "Higher values = bigger villages; 0 = Vanilla",
            0, 0, 10
        );
        this.addProperty(VILLAGE_SIZE);

        MIN_DISTANCE_VILLAGES = new ConfigPropertyInteger(
            "Minimum distance between villages",
            "Villages",
            "Higher values = villages further apart; 8 = Vanilla",
            12, 1, Integer.MAX_VALUE
        );
        this.addProperty(MIN_DISTANCE_VILLAGES);

        MAX_DISTANCE_VILLAGES = new ConfigPropertyInteger(
            "Maximum distance between villages",
            "Villages",
            "Lower values = villages closer together; 32 = Vanilla",
            48, 1, Integer.MAX_VALUE
        );
        this.addProperty(MAX_DISTANCE_VILLAGES);

        VOLCANO_MAIN_BLOCK = new ConfigPropertyString(
            "Volcano Main block",
            "Volcanoes.Volcano Blocks.Volcano Block",
            "The main block to use for the surface of the volcano.",
            "minecraft:obsidian"
        );
        this.addProperty(VOLCANO_MAIN_BLOCK);

        VOLCANO_MIX1_BLOCK = new ConfigPropertyString(
            "Volcano mix block 1",
            "Volcanoes.Volcano Blocks.Mix Block 1",
            "The block ID of the 1st volcano mix block.",
            "minecraft:cobblestone"
        );
        this.addProperty(VOLCANO_MIX1_BLOCK);

        VOLCANO_MIX2_BLOCK = new ConfigPropertyString(
            "Volcano mix block 2",
            "Volcanoes.Volcano Blocks.Mix Block 2",
            "The block ID of the 2nd volcano mix block.",
            "minecraft:gravel"
        );
        this.addProperty(VOLCANO_MIX2_BLOCK);

        VOLCANO_MIX3_BLOCK = new ConfigPropertyString(
            "Volcano mix block 3",
            "Volcanoes.Volcano Blocks.Mix Block 3",
            "The block ID of the 3rd volcano mix block.",
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
}
