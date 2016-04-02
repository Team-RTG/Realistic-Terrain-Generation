package rtg.config;

import rtg.api.config.ConfigProperty;
import rtg.api.config.ModConfig;
import rtg.util.SupportedMod;

public class ConfigRTG extends ModConfig {
    public static final ConfigProperty.PropertyInt FLAT_BEDROCK_LAYERS = new ConfigProperty.PropertyInt("Number of flat bedrock layers", "bedrock");
    public static final ConfigProperty.PropertyBlock BEDROCK_BLOCK = new ConfigProperty.PropertyBlock("BEDROCK_BLOCK", "bedrock");
    public static final ConfigProperty.PropertyBool ENABLE_RTG_BIOME_DECORATIONS = new ConfigProperty.PropertyBool("ENABLE_RTG_BIOME_DECORATIONS", "biomes");
    public static final ConfigProperty.PropertyBool ENABLE_RTG_SURFACES = new ConfigProperty.PropertyBool("ENABLE_RTG_SURFACES", "biomes");
    public static final ConfigProperty.PropertyInt PATCH_BIOME_ID = new ConfigProperty.PropertyInt("PATCH_BIOME_ID", "biomes");
    public static final ConfigProperty.PropertyBool USE_BOP_LAYOUT = new ConfigProperty.PropertyBool("USE_BOP_LAYOUT", "biomes");
    public static final ConfigProperty.PropertyBool ENABLE_COUBLESTONE_BOULDERS = new ConfigProperty.PropertyBool("ENABLE_COUBLESTONE_BOULDERS", "boulders");
    public static final ConfigProperty.PropertyInt COBBLESTONE_BOULDER_CHANCE = new ConfigProperty.PropertyInt("COBBLESTONE_BOULDER_CHANCE", "boulders");
    public static final ConfigProperty.PropertyBool ENABLE_CAVE_MODIFICATIONS = new ConfigProperty.PropertyBool("ENABLE_CAVE_MODIFICATIONS", "caves");
    public static final ConfigProperty.PropertyBool ENABLE_CAVES = new ConfigProperty.PropertyBool("ENABLE_CAVES", "caves");
    public static final ConfigProperty.PropertyInt CAVE_DENSITY = new ConfigProperty.PropertyInt("CAVE_DENSITY", "caves");
    public static final ConfigProperty.PropertyInt CAVE_FREQUENCY = new ConfigProperty.PropertyInt("CAVE_FREQUENCY", "caves");
    public static final ConfigProperty.PropertyInt DUNE_HEIGHT = new ConfigProperty.PropertyInt("DUNE_HEIGHT", "dunes");
    public static final ConfigProperty.PropertyBool SHOW_DEBUG_INFO = new ConfigProperty.PropertyBool("SHOW_DEBUG_INFO", "debug");
    public static final ConfigProperty.PropertyBool DEBUG_LOGGING = new ConfigProperty.PropertyBool("DEBUG_LOGGING", "debug");
    public static final ConfigProperty.PropertyBool GENERATE_DUNGEONS = new ConfigProperty.PropertyBool("GENERATE_DUNGEONS", "dungeons");
    public static final ConfigProperty.PropertyBool ENABLE_SURFACE_WATER_LAKES = new ConfigProperty.PropertyBool("ENABLE_SURFACE_WATER_LAKES", "surface lakes");
    public static final ConfigProperty.PropertyBool SURFACE_WATER_LAKE_CHANCE = new ConfigProperty.PropertyBool("SURFACE_WATER_LAKE_CHANCE", "surface lakes");
    public static final ConfigProperty.PropertyBool ENABLE_SURFACE_LAVA_LAKES = new ConfigProperty.PropertyBool("ENABLE_SURFACE_LAVA_LAKES", "surface lakes");
    public static final ConfigProperty.PropertyBool SURFACE_LAVA_LAKE_CHANCE = new ConfigProperty.PropertyBool("SURFACE_LAVA_LAKE_CHANCE", "surface lakes");
    public static final ConfigProperty.PropertyBool ENABLE_UNDERGROUND_WATER_LAKES = new ConfigProperty.PropertyBool("ENABLE_UNDERGROUND_WATER_LAKES", "underground lakes");
    public static final ConfigProperty.PropertyBool UNDERGROUND_WATER_LAKE_CHANCE = new ConfigProperty.PropertyBool("UNDERGROUND_WATER_LAKE_CHANCE", "underground lakes");
    public static final ConfigProperty.PropertyBool ENABLE_UNDERGROUND_LAVA_LAKES = new ConfigProperty.PropertyBool("ENABLE_UNDERGROUND_LAVA_LAKES", "underground lakes");
    public static final ConfigProperty.PropertyBool UNDERGROUND_LAVA_LAKE_CHANCE = new ConfigProperty.PropertyBool("UNDERGROUND_LAVA_LAKE_CHANCE", "underground lakes");
    public static final ConfigProperty.PropertyBool GENERATE_MINESHAFTS = new ConfigProperty.PropertyBool("GENERATE_MINESHAFTS", "mineshafts");
    public static final ConfigProperty.PropertyBool GENERATE_ORE_COAL = new ConfigProperty.PropertyBool("GENERATE_ORE_COAL", "ores");
    public static final ConfigProperty.PropertyBool GENERATE_ORE_IRON = new ConfigProperty.PropertyBool("GENERATE_ORE_IRON", "ores");
    public static final ConfigProperty.PropertyBool GENERATE_ORE_GOLD = new ConfigProperty.PropertyBool("GENERATE_ORE_GOLD", "ores");
    public static final ConfigProperty.PropertyBool GENERATE_ORE_REDSTONE = new ConfigProperty.PropertyBool("GENERATE_ORE_REDSTONE", "ores");
    public static final ConfigProperty.PropertyBool GENERATE_ORE_LAPIS = new ConfigProperty.PropertyBool("GENERATE_ORE_LAPIS", "ores");
    public static final ConfigProperty.PropertyBool GENERATE_ORE_DIAMOND = new ConfigProperty.PropertyBool("GENERATE_ORE_DIAMOND", "ores");
    public static final ConfigProperty.PropertyBool GENERATE_ORE_EMERALD = new ConfigProperty.PropertyBool("GENERATE_ORE_EMERALD", "ores");
    public static final ConfigProperty.PropertyBool ENABLE_RAVINE_MODIFICATIONS = new ConfigProperty.PropertyBool("ENABLE_RAVINE_MODIFICATIONS", "ravines");
    public static final ConfigProperty.PropertyBool ENABLE_RAVINES = new ConfigProperty.PropertyBool("ENABLE_RAVINES", "ravines");
    public static final ConfigProperty.PropertyInt RAVINE_FREQUENCY = new ConfigProperty.PropertyInt("RAVINE_FREQUENCY", "ravines");
    public static final ConfigProperty.PropertyBool GENERATE_SCATTERED_FEATURES = new ConfigProperty.PropertyBool("GENERATE_SCATTERED_FEATURES", "scattered features");
    public static final ConfigProperty.PropertyInt MIN_DISTANCE_SCATTERED_FEATURES = new ConfigProperty.PropertyInt("MIN_DISTANCE_SCATTERED_FEATURES", "scattered features");
    public static final ConfigProperty.PropertyInt MAX_DISTANCE_SCATTERED_FEATURES = new ConfigProperty.PropertyInt("MAX_DISTANCE_SCATTERED_FEATURES", "scattered features");
    public static final ConfigProperty.PropertyBool GENERATE_OCEAN_MONUMENTS = new ConfigProperty.PropertyBool("GENERATE_OCEAN_MONUMENTS", "ocean monuments");
    public static final ConfigProperty.PropertyBool GENERATE_STRONGHOLDS = new ConfigProperty.PropertyBool("GENERATE_STRONGHOLDS", "strongholds");
    public static final ConfigProperty.PropertyBool GENERATE_VILLAGES = new ConfigProperty.PropertyBool("GENERATE_VILLAGES", "villages");
    public static final ConfigProperty.PropertyBool ENABLE_VILLAGE_MODIFICATIONS = new ConfigProperty.PropertyBool("ENABLE_VILLAGE_MODIFICATIONS", "villages");
    public static final ConfigProperty.PropertyInt VILLAGE_SIZE = new ConfigProperty.PropertyInt("VILLAGE_SIZE", "villages");
    public static final ConfigProperty.PropertyInt MIN_DISTANCE_VILLAGES = new ConfigProperty.PropertyInt("MIN_DISTANCE_VILLAGES", "villages");
    public static final ConfigProperty.PropertyInt MAX_DISTANCE_VILLAGES = new ConfigProperty.PropertyInt("MAX_DISTANCE_VILLAGES", "villages");
    public static final ConfigProperty.PropertyBool VILLAGE_CRASH_FIX = new ConfigProperty.PropertyBool("VILLAGE_CRASH_FIX", "villages");
    public static final ConfigProperty.PropertyBool ENABLE_SNOW_LAYERS = new ConfigProperty.PropertyBool("ENABLE_SNOW_LAYERS", "snow");
    public static final ConfigProperty.PropertyBlock SHADOW_STONE_BLOCK = new ConfigProperty.PropertyBlock("SHADOW_STONE_BLOCK", "terrain shadowing");
    public static final ConfigProperty.PropertyBlock SHADOW_DESERT_BLOCK = new ConfigProperty.PropertyBlock("SHADOW_DESERT_BLOCK", "terrain shadowing");
    public static final ConfigProperty.PropertyBool ALLOW_TREES_ON_SAND = new ConfigProperty.PropertyBool("ALLOW_TREES_ON_SAND", "trees");
    public static final ConfigProperty.PropertyBool ALLOW_SHRUBS_UNDERGROUND = new ConfigProperty.PropertyBool("ALLOW_SHRUBS_UNDERGROUND", "trees");

    public ConfigRTG() {
        super(SupportedMod.RTG);
    }

    @Override
    public void initDefaults() {
        FLAT_BEDROCK_LAYERS.setDefault(0);
        FLAT_BEDROCK_LAYERS.setComment("0 = Normal bedrock (rough pattern); 1-5 = Number of flat bedrock layers to generate");
    }
}