package rtg.config;

import rtg.api.config.ConfigProperty;
import rtg.api.config.ModConfig;
import rtg.util.mods.Mods;

public class ConfigRTG extends ModConfig {
    public final ConfigProperty.PropertyInt FLAT_BEDROCK_LAYERS = addInt("Number of flat bedrock layers", "bedrock");
    public final ConfigProperty.PropertyBlock BEDROCK_BLOCK = addBlock("BEDROCK_BLOCK", "bedrock");
    public final ConfigProperty.PropertyBool ENABLE_RTG_BIOME_DECORATIONS = addBool("ENABLE_RTG_BIOME_DECORATIONS", "biomes");
    public final ConfigProperty.PropertyBool ENABLE_RTG_SURFACES = addBool("ENABLE_RTG_SURFACES", "biomes");
    public final ConfigProperty.PropertyInt PATCH_BIOME_ID = addInt("PATCH_BIOME_ID", "biomes");
    public final ConfigProperty.PropertyBool USE_BOP_LAYOUT = addBool("USE_BOP_LAYOUT", "biomes");
    public final ConfigProperty.PropertyBool ENABLE_COUBLESTONE_BOULDERS = addBool("ENABLE_COUBLESTONE_BOULDERS", "boulders");
    public final ConfigProperty.PropertyInt COBBLESTONE_BOULDER_CHANCE = addInt("COBBLESTONE_BOULDER_CHANCE", "boulders");
    public final ConfigProperty.PropertyBool ENABLE_CAVE_MODIFICATIONS = addBool("ENABLE_CAVE_MODIFICATIONS", "caves");
    public final ConfigProperty.PropertyBool ENABLE_CAVES = addBool("ENABLE_CAVES", "caves");
    public final ConfigProperty.PropertyInt CAVE_DENSITY = addInt("CAVE_DENSITY", "caves");
    public final ConfigProperty.PropertyInt CAVE_FREQUENCY = addInt("CAVE_FREQUENCY", "caves");
    public final ConfigProperty.PropertyInt DUNE_HEIGHT = addInt("DUNE_HEIGHT", "dunes");
    public final ConfigProperty.PropertyBool SHOW_DEBUG_INFO = addBool("SHOW_DEBUG_INFO", "debug");
    public final ConfigProperty.PropertyBool DEBUG_LOGGING = addBool("DEBUG_LOGGING", "debug");
    public final ConfigProperty.PropertyBool GENERATE_DUNGEONS = addBool("GENERATE_DUNGEONS", "dungeons");
    public final ConfigProperty.PropertyBool ENABLE_SURFACE_WATER_LAKES = addBool("ENABLE_SURFACE_WATER_LAKES", "surface lakes");
    public final ConfigProperty.PropertyInt SURFACE_WATER_LAKE_CHANCE = addInt("SURFACE_WATER_LAKE_CHANCE", "surface lakes");
    public final ConfigProperty.PropertyBool ENABLE_SURFACE_LAVA_LAKES = addBool("ENABLE_SURFACE_LAVA_LAKES", "surface lakes");
    public final ConfigProperty.PropertyInt SURFACE_LAVA_LAKE_CHANCE = addInt("SURFACE_LAVA_LAKE_CHANCE", "surface lakes");
    public final ConfigProperty.PropertyBool ENABLE_UNDERGROUND_WATER_LAKES = addBool("ENABLE_UNDERGROUND_WATER_LAKES", "underground lakes");
    public final ConfigProperty.PropertyInt UNDERGROUND_WATER_LAKE_CHANCE = addInt("UNDERGROUND_WATER_LAKE_CHANCE", "underground lakes");
    public final ConfigProperty.PropertyBool ENABLE_UNDERGROUND_LAVA_LAKES = addBool("ENABLE_UNDERGROUND_LAVA_LAKES", "underground lakes");
    public final ConfigProperty.PropertyInt UNDERGROUND_LAVA_LAKE_CHANCE = addInt("UNDERGROUND_LAVA_LAKE_CHANCE", "underground lakes");
    public final ConfigProperty.PropertyBool GENERATE_MINESHAFTS = addBool("GENERATE_MINESHAFTS", "mineshafts");
    public final ConfigProperty.PropertyBool GENERATE_ORE_COAL = addBool("GENERATE_ORE_COAL", "ores");
    public final ConfigProperty.PropertyBool GENERATE_ORE_IRON = addBool("GENERATE_ORE_IRON", "ores");
    public final ConfigProperty.PropertyBool GENERATE_ORE_GOLD = addBool("GENERATE_ORE_GOLD", "ores");
    public final ConfigProperty.PropertyBool GENERATE_ORE_REDSTONE = addBool("GENERATE_ORE_REDSTONE", "ores");
    public final ConfigProperty.PropertyBool GENERATE_ORE_LAPIS = addBool("GENERATE_ORE_LAPIS", "ores");
    public final ConfigProperty.PropertyBool GENERATE_ORE_DIAMOND = addBool("GENERATE_ORE_DIAMOND", "ores");
    public final ConfigProperty.PropertyBool GENERATE_ORE_EMERALD = addBool("GENERATE_ORE_EMERALD", "ores");
    public final ConfigProperty.PropertyBool ENABLE_RAVINE_MODIFICATIONS = addBool("ENABLE_RAVINE_MODIFICATIONS", "ravines");
    public final ConfigProperty.PropertyBool ENABLE_RAVINES = addBool("ENABLE_RAVINES", "ravines");
    public final ConfigProperty.PropertyInt RAVINE_FREQUENCY = addInt("RAVINE_FREQUENCY", "ravines");
    public final ConfigProperty.PropertyBool GENERATE_SCATTERED_FEATURES = addBool("GENERATE_SCATTERED_FEATURES", "scattered features");
    public final ConfigProperty.PropertyInt MIN_DISTANCE_SCATTERED_FEATURES = addInt("MIN_DISTANCE_SCATTERED_FEATURES", "scattered features");
    public final ConfigProperty.PropertyInt MAX_DISTANCE_SCATTERED_FEATURES = addInt("MAX_DISTANCE_SCATTERED_FEATURES", "scattered features");
    public final ConfigProperty.PropertyBool GENERATE_OCEAN_MONUMENTS = addBool("GENERATE_OCEAN_MONUMENTS", "ocean monuments");
    public final ConfigProperty.PropertyBool GENERATE_STRONGHOLDS = addBool("GENERATE_STRONGHOLDS", "strongholds");
    public final ConfigProperty.PropertyBool GENERATE_VILLAGES = addBool("GENERATE_VILLAGES", "villages");
    public final ConfigProperty.PropertyBool ENABLE_VILLAGE_MODIFICATIONS = addBool("ENABLE_VILLAGE_MODIFICATIONS", "villages");
    public final ConfigProperty.PropertyInt VILLAGE_SIZE = addInt("VILLAGE_SIZE", "villages");
    public final ConfigProperty.PropertyInt MIN_DISTANCE_VILLAGES = addInt("MIN_DISTANCE_VILLAGES", "villages");
    public final ConfigProperty.PropertyInt MAX_DISTANCE_VILLAGES = addInt("MAX_DISTANCE_VILLAGES", "villages");
    public final ConfigProperty.PropertyBool VILLAGE_CRASH_FIX = addBool("VILLAGE_CRASH_FIX", "villages");
    public final ConfigProperty.PropertyBool ENABLE_SNOW_LAYERS = addBool("ENABLE_SNOW_LAYERS", "snow");
    public final ConfigProperty.PropertyBlock SHADOW_STONE_BLOCK = addBlock("SHADOW_STONE_BLOCK", "terrain shadowing");
    public final ConfigProperty.PropertyBlock SHADOW_DESERT_BLOCK = addBlock("SHADOW_DESERT_BLOCK", "terrain shadowing");
    public final ConfigProperty.PropertyBool ALLOW_TREES_ON_SAND = addBool("ALLOW_TREES_ON_SAND", "trees");
    public final ConfigProperty.PropertyBool ALLOW_SHRUBS_UNDERGROUND = addBool("ALLOW_SHRUBS_UNDERGROUND", "trees");

    public ConfigRTG() {
        super(Mods.RTG);
    }

    @Override
    public void initDefaults() {
        FLAT_BEDROCK_LAYERS.setDefault(0);
        FLAT_BEDROCK_LAYERS.setComment("0 = Normal bedrock (rough pattern); 1-5 = Number of flat bedrock layers to generate");
    }
}