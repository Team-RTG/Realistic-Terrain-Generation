package teamrtg.rtg.mods.rtg;

import net.minecraft.block.BlockColored;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumDyeColor;
import teamrtg.rtg.api.config.ModConfig;

import static teamrtg.rtg.api.config.ConfigProperty.*;

public class ConfigRTG extends ModConfig {
    public final PropertyInt FLAT_BEDROCK_LAYERS = addInt("Number of flat bedrock layers", "bedrock");
    public final PropertyBlock BEDROCK_BLOCK = addBlock("BEDROCK_BLOCK", "bedrock");
    public final PropertyBool ENABLE_RTG_BIOME_DECORATIONS = addBool("Enable RTG Biome Decorations", "biomes");
    public final PropertyBool ENABLE_RTG_SURFACES = addBool("Enable RTG Biome Surfaces", "biomes");
    public final PropertyBool USE_BOP_LAYOUT = addBool("Use BOP biome layout if avaliable", "biomes");
    public final PropertyBool ENABLE_COUBLESTONE_BOULDERS = addBool("Enable Cobblestone Boulders", "boulders");
    public final PropertyInt COBBLESTONE_BOULDER_CHANCE = addInt("1/x chance that Cobblestone Boulders will generate if given the opportunity to do so during world gen", "boulders");
    public final PropertyBool ENABLE_CAVE_MODIFICATIONS = addBool("Enable Cave Modifications", "caves");
    public final PropertyBool ENABLE_CAVES = addBool("Enable Caves", "caves");
    public final PropertyInt CAVE_DENSITY = addInt("Cave Density", "caves");
    public final PropertyInt CAVE_FREQUENCY = addInt("Cave Frequency", "caves");
    public final PropertyInt DUNE_HEIGHT = addInt("Height of Dunes", "dunes");
    public final PropertyBool SHOW_DEBUG_INFO = addBool("Show Debug Info in F3 Screen", "debug");
    public final PropertyBool DEBUG_LOGGING = addBool("Enable Debug logging", "debug");
    public final PropertyBool GENERATE_DUNGEONS = addBool("Generate Dungeons", "dungeons");
    public final PropertyBool ENABLE_SURFACE_WATER_LAKES = addBool("Enable Water Surface Lakes", "surface lakes");
    public final PropertyInt SURFACE_WATER_LAKE_CHANCE = addInt("1/x chance that Water Surface Lakes will generate if given the opportunity to do so during world gen", "surface lakes");
    public final PropertyBool ENABLE_SURFACE_LAVA_LAKES = addBool("Enable Lava Surface Lakes", "surface lakes");
    public final PropertyInt SURFACE_LAVA_LAKE_CHANCE = addInt("1/x chance that Lava Surface Lakes will generate if given the opportunity to do so during world gen", "surface lakes");
    public final PropertyBool ENABLE_UNDERGROUND_WATER_LAKES = addBool("Enable Water Underground Lakes", "underground lakes");
    public final PropertyInt UNDERGROUND_WATER_LAKE_CHANCE = addInt("1/x chance that Water Underground Lakes will generate if given the opportunity to do so during world gen", "underground lakes");
    public final PropertyBool ENABLE_UNDERGROUND_LAVA_LAKES = addBool("Enable Lava Underground Lakes", "underground lakes");
    public final PropertyInt UNDERGROUND_LAVA_LAKE_CHANCE = addInt("1/x chance that Lava Underground Lakes will generate if given the opportunity to do so during world gen", "underground lakes");
    public final PropertyBool GENERATE_MINESHAFTS = addBool("Generate Mineshafts", "mineshafts");
    public final PropertyBool GENERATE_ORES = addBool("Generate ores", "ores");
    public final PropertyBool ENABLE_RAVINE_MODIFICATIONS = addBool("Enable Ravine Modifications", "ravines");
    public final PropertyBool ENABLE_RAVINES = addBool("Enable Ravines", "ravines");
    public final PropertyInt RAVINE_FREQUENCY = addInt("Ravine Frequency", "ravines");
    public final PropertyBool GENERATE_SCATTERED_FEATURES = addBool("Generate Scattered Features", "scattered features");
    public final PropertyInt MIN_DISTANCE_SCATTERED_FEATURES = addInt("Minimum distance between scattered features", "scattered features");
    public final PropertyInt MAX_DISTANCE_SCATTERED_FEATURES = addInt("Maximum distance between scattered features", "scattered features");
    public final PropertyBool GENERATE_OCEAN_MONUMENTS = addBool("Generate Ocean Monuments", "ocean monuments");
    public final PropertyBool GENERATE_STRONGHOLDS = addBool("Generate Strongholds", "strongholds");
    public final PropertyBool GENERATE_VILLAGES = addBool("Generate Villages", "villages");
    public final PropertyBool ENABLE_VILLAGE_MODIFICATIONS = addBool("Enable village modifications", "villages");
    public final PropertyInt VILLAGE_SIZE = addInt("Size of villages", "villages");
    public final PropertyInt MIN_DISTANCE_VILLAGES = addInt("Minimum distance between villages", "villages");
    public final PropertyInt MAX_DISTANCE_VILLAGES = addInt("Maximum distance between villages", "villages");
    public final PropertyBool VILLAGE_CRASH_FIX = addBool("Village Crash Fix", "villages");
    public final PropertyBool ENABLE_SNOW_LAYERS = addBool("Enable Snow Layers", "snow");
    public final PropertyBlock SHADOW_STONE_BLOCK = addBlock("Stone shadow block", "terrain shadowing");
    public final PropertyBlock SHADOW_DESERT_BLOCK = addBlock("Desert shadow block", "terrain shadowing");
    public final PropertyBool ALLOW_TREES_ON_SAND = addBool("Allow Trees to Generate on Sand", "trees");
    public final PropertyBool ALLOW_SHRUBS_UNDERGROUND = addBool("Allow Shrubs to Generate Below Surface", "trees");
    public final PropertyFloat RIVER_FREQUENCY_MULTIPLIER = addFloat("River frequency multiplier", "water systems");
    public final PropertyFloat RIVER_SIZE_MULTIPLIER = addFloat("River width multiplier", "water systems");
    public final PropertyFloat RIVER_BENDINESS_MULTIPLIER = addFloat("Multiplier to River Bending", "water systems");
    public final PropertyFloat LAKE_FREQUENCY_MULTIPLIER = addFloat("Lake Frequency Multipler", "water systems");
    public final PropertyFloat LAKE_SIZE_MULTIPLIER = addFloat("Lake Size Multipler", "water systems");
    public final PropertyFloat LAKE_BENDINESS_MULTIPLIER = addFloat("Lake Shore Irregularity", "water systems");
    public final PropertyInt SURFACE_BLEED_RADIUS = addInt("Surface bleeding radius", "surface bleeding");

    public ConfigRTG() {
        super("RTG");
    }

    @Override
    public void initDefaults() {
        FLAT_BEDROCK_LAYERS.setDefault(0).setComment("0 = Normal bedrock (rough pattern); 1-5 = Number of flat bedrock layers to generate");
        BEDROCK_BLOCK.setDefault(Blocks.BEDROCK.getDefaultState()).setComment("The block to use for the bottom of the Overworld");
        ENABLE_RTG_BIOME_DECORATIONS.setDefault(true).setComment("If TRUE, uses the individual biome settings in the biome forgeConfig files. If FALSE, disables all RTG decorations and uses biomes decorations instead.");
        ENABLE_RTG_SURFACES.setDefault(true).setComment("If TRUE, uses the individual biome settings in the biome forgeConfig files. If FALSE, disables all RTG surfaces and uses biomes surfaces instead.");
        USE_BOP_LAYOUT.setDefault(false).setComment("If FALSE, RTG will use biomes biome layout, even if BOP is installed. This means no BOP biomes.");
        ENABLE_COUBLESTONE_BOULDERS.setDefault(true);
        COBBLESTONE_BOULDER_CHANCE.setDefault(1).setComment("1 = Always generate if possible; 2 = 50% chance; 4 = 25% chance");
        ENABLE_CAVE_MODIFICATIONS.setDefault(true).setComment("Must be set to TRUE for the other cave settings to have any effect." +
                "If FALSE, RTG won't interfere with cave generation at all.");
        ENABLE_CAVES.setDefault(true);
        CAVE_DENSITY.setDefault(8).setRange(1, 40).setComment("This setting controls the size of caves." + NEW_LINE +
                "HIGHER values = BIGGER caves & MORE lag. (14 = biomes cave density)");
        CAVE_FREQUENCY.setDefault(16).setRange(1, 40).setComment("This setting controls the number of caves that generate." + NEW_LINE +
                "LOWER values = MORE caves & MORE lag. (6 = biomes cave frequency)");
        DUNE_HEIGHT.setDefault(4).setRange(1, 12).setComment("This setting controls the height of both sand dunes and snow dunes.");
        SHOW_DEBUG_INFO.setDefault(false);
        DEBUG_LOGGING.setDefault(false).setComment("WARNING: This should only be enabled if you know what you're doing.");
        GENERATE_DUNGEONS.setDefault(true);
        ENABLE_SURFACE_WATER_LAKES.setDefault(true);
        SURFACE_WATER_LAKE_CHANCE.setDefault(10).setRange(1, 100).setComment("1 = Always generate if possible; 2 = 50% chance; 4 = 25% chance");
        ENABLE_SURFACE_LAVA_LAKES.setDefault(true);
        SURFACE_LAVA_LAKE_CHANCE.setDefault(10).setRange(1, 100).setComment("1 = Always generate if possible; 2 = 50% chance; 4 = 25% chance");
        ENABLE_UNDERGROUND_WATER_LAKES.setDefault(true);
        UNDERGROUND_WATER_LAKE_CHANCE.setDefault(10).setRange(1, 100).setComment("1 = Always generate if possible; 2 = 50% chance; 4 = 25% chance");
        ENABLE_UNDERGROUND_LAVA_LAKES.setDefault(true);
        UNDERGROUND_LAVA_LAKE_CHANCE.setDefault(10);
        GENERATE_MINESHAFTS.setDefault(true);
        GENERATE_ORES.setDefault(true).setComment("If FALSE, RTG will not generate ores. This may be required for some mod compatibility, as you would otherwise get double the ores");
        ENABLE_RAVINE_MODIFICATIONS.setDefault(true);
        ENABLE_RAVINES.setDefault(false);
        RAVINE_FREQUENCY.setDefault(2);
        GENERATE_SCATTERED_FEATURES.setDefault(true);
        MIN_DISTANCE_SCATTERED_FEATURES.setDefault(12);
        MAX_DISTANCE_SCATTERED_FEATURES.setDefault(48);
        GENERATE_OCEAN_MONUMENTS.setDefault(true);
        GENERATE_STRONGHOLDS.setDefault(true);
        GENERATE_VILLAGES.setDefault(true);
        ENABLE_VILLAGE_MODIFICATIONS.setDefault(true);
        VILLAGE_SIZE.setDefault(3);
        MIN_DISTANCE_VILLAGES.setDefault(12);
        MAX_DISTANCE_VILLAGES.setDefault(48);
        VILLAGE_CRASH_FIX.setDefault(true);
        ENABLE_SNOW_LAYERS.setDefault(true);
        SHADOW_STONE_BLOCK.setDefault(Blocks.STAINED_HARDENED_CLAY.getDefaultState().withProperty(BlockColored.COLOR, EnumDyeColor.CYAN));
        SHADOW_DESERT_BLOCK.setDefault(Blocks.STAINED_HARDENED_CLAY.getDefaultState().withProperty(BlockColored.COLOR, EnumDyeColor.WHITE));
        ALLOW_TREES_ON_SAND.setDefault(true);
        ALLOW_SHRUBS_UNDERGROUND.setDefault(true);
        RIVER_SIZE_MULTIPLIER.setDefault(1).setRange(0, 10).setComment("Defaults to 1 (standard width)");
        RIVER_FREQUENCY_MULTIPLIER.setDefault(1).setRange(0, 10).setComment("Defaults to 1 (standard frequency)");
        RIVER_BENDINESS_MULTIPLIER.setDefault(1).setRange(0, 2).setComment("Higher numbers make rivers bend more. Defaults to 1");
        LAKE_SIZE_MULTIPLIER.setDefault(1).setRange(0, 10).setComment("Defaults to 1 (standard size)");
        LAKE_FREQUENCY_MULTIPLIER.setDefault(1).setRange(0, 10).setComment("Defaults to 1 (standard frequency)");
        LAKE_BENDINESS_MULTIPLIER.setDefault(1).setRange(0, 2).setComment("Makes scenic lake shores bend and curve more. Defaults to 1");
        SURFACE_BLEED_RADIUS.setDefault(16).setRange(0, 32).setComment("The maximum number of blocks a surface can bleed away from its biome." + NEW_LINE +
            "Set to 0 to disable");
    }
}